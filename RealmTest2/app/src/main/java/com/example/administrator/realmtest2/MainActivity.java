package com.example.administrator.realmtest2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.realmtest2.Model.Cat;
import com.example.administrator.realmtest2.Model.Dog;
import com.example.administrator.realmtest2.Model.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootlayout = null;

    private Realm realm;
    public RealmConfiguration realmConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootlayout = ((LinearLayout) findViewById(R.id.container));
        rootlayout.removeAllViews();


        //Realm configuration 생성.
        realmConfiguration = new RealmConfiguration.Builder(this).build();
        // UI Thread를 위한 Realm 생성.
        realm = Realm.getInstance(realmConfiguration);

        basicCRUD(realm);
        basicQuery(realm);
        basicLinkQuery(realm);

        //다른 스레드에서 기능을 사용.
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String info;
                info = complexReadWrite();
                info += complexQuery();
                return info;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        }.execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        //realm을 닫아줘야한다.
    }

    private void showStatus(String txt) {
        Log.d(TAG, txt);
        TextView textView = new TextView(this);
        textView.setText(txt);
        rootlayout.addView(textView);
    }

    private void basicCRUD(Realm realm) {
        showStatus("Perfom basic Create/Read/Update/Delete operation");


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setId(1);
                user.setName("Jeon");
                user.setAge(26);
            }
        });


        final User user = realm.where(User.class).findFirst();
        showStatus(user.getName() + ":" + user.getAge());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                user.setName("DongHwa");
                user.setAge(27);
                showStatus(user.getName() + " got older: " + user.getAge());

            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
            }
        });
    }


    private void basicQuery(Realm realm){
        showStatus("\nPerforming basic Query operation");
        showStatus("Number of persons : " + realm.where(User.class).count());

        RealmResults<User> results = realm.where(User.class).equalTo("age",27).findAll();

        showStatus("Size of result set : " + results.size());

    }

    private void basicLinkQuery(Realm realm) {
        showStatus("\nPerforming basic Link Query operation");
        showStatus("Number of persons : " + realm.where(User.class).count());
        RealmResults<User> results = realm.where(User.class).equalTo("cats.name", "tiger").findAll();
        showStatus("Size of result set : " + results.size());
    }

    private String complexReadWrite() {
        String status = "\nPerforming complex Read/Write operation...";

        // Open the default realm. All threads must use it's own reference to the realm.
        // Those can not be transferred across threads.
        Realm realm = Realm.getInstance(realmConfiguration);

        // Add ten persons in one transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog fido = realm.createObject(Dog.class);
                fido.name = "fido";
                for (int i = 0; i < 10; i++) {
                    User user = realm.createObject(User.class);
                    user.setId(i);
                    user.setName("Person no. " + i);
                    user.setAge(i);
                    user.setDog(fido);

                    // The field tempReference is annotated with @Ignore.
                    // This means setTempReference sets the Person tempReference
                    // field directly. The tempReference is NOT saved as part of
                    // the RealmObject:
                    user.setTempref(42);

                    for (int j = 0; j < i; j++) {
                        Cat cat = realm.createObject(Cat.class);
                        cat.name = "Cat_" + j;
                        user.getCats().add(cat);
                    }
                }
            }
        });

        // Implicit read transactions allow you to access your objects
        status += "\nNumber of persons: " + realm.where(User.class).count();

        // Iterate over all objects
        for (User pers : realm.where(User.class).findAll()) {
            String dogName;
            if (pers.getDog() == null) {
                dogName = "None";
            } else {
                dogName = pers.getDog().name;
            }
            status += "\n" + pers.getName() + ":" + pers.getAge() + " : " + dogName + " : " + pers.getCats().size();
        }

        // Sorting
        RealmResults<User> sortedPersons = realm.where(User.class).findAllSorted("age", Sort.DESCENDING);
        status += "\nSorting " + sortedPersons.last().getName() + " == " + realm.where(User.class).findFirst()
                .getName();

        realm.close();
        return status;
    }
    private String complexQuery() {
        String status = "\n\nPerforming complex Query operation...";

        Realm realm = Realm.getInstance(realmConfiguration);
        status += "\nNumber of persons: " + realm.where(User.class).count();

        // Find all persons where age between 7 and 9 and name begins with "Person".
        RealmResults<User> results = realm.where(User.class)
                .between("age", 7, 9)       // Notice implicit "and" operation
                .beginsWith("name", "Person").findAll();
        status += "\nSize of result set: " + results.size();

        realm.close();
        return status;
    }
}
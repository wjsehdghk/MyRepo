package myggum.registerui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Administrator on 2017-01-09.
 */

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.register);


        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.bank,android.R.layout.simple_spinner_item);


        spinner.setAdapter(adapter);



    }
}

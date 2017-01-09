package myggum.registerui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017-01-09.
 */





public class AgreementActivity extends AppCompatActivity {



    Button button ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.agreement);

        button = (Button)findViewById(R.id.okcheck);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));


            }
        });




    }
}

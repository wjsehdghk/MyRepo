package com.example.dongjun.ottoee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    Button button1;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BusStation.getInstance().register(this);


        button1=(Button)findViewById(R.id.btn1);
        textView1=(EditText)findViewById(R.id.editText);
        textView2=(EditText)findViewById(R.id.editText2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusStation.getInstance().post(new Message(textView1.getText().toString()));
            }
        });
    }




    @Override
    protected void onDestroy() {
        BusStation.getInstance().unregister(this);
        super.onDestroy();
    }
    @Subscribe
    public void changemessage(Message message){
        textView2.setText(message.getMsg());
    }

}

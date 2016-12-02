package myggum.customlayout;


import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    CustomLayout customLayout;
    RelativeLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout)findViewById(R.id.container);
        Button button = (Button)findViewById(R.id.button);
        Button button1 = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customLayout = new CustomLayout(getBaseContext());
                customLayout.setOnTouchListener(customLayout);
                container.addView(customLayout);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=getCurrentFocus();
                if ( view1 instanceof AppCompatEditText){
                    customLayout.editText.setTextColor(Color.BLUE);
                }
            }
        });
    }
}

package jeon.popup;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ActionMode currentActionMode;
    private TextView tvname;

    private Button button;
    private Button button2;

    private android.view.ActionMode.Callback modeCallback = new android.view.ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {

        mode.setTitle("test");
            mode.getMenuInflater().inflate(R.menu.actions_textview,menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_edit:
                    Toast.makeText(MainActivity.this, "Editing!", Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the contextual menu
                    return true;
                case R.id.menu_delete:
                    // Trigger the deletion here
                    mode.finish(); // Action picked, so close the contextual menu
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

            currentActionMode = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        tvname = (TextView)findViewById(R.id.tvname);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterPopup(v);
            }
        });
        tvname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

               if(currentActionMode !=null) {return false;}
                startActionMode(modeCallback);

                v.setSelected(true);
                return true;
            }
        });

    }
    private void showFilterPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.getMenuInflater().inflate(R.menu.popup_filters,popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_keyword:
                        Toast.makeText(getBaseContext(),"keyword",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_popularity:
                        Toast.makeText(getBaseContext(),"Popularity",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }
    private void displayPopupWindow(View anchorView){
        PopupWindow popup = new PopupWindow(this);
        View layout = getLayoutInflater().inflate(R.layout.popup_content, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);


    }
}

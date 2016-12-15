package myggum.customlayout.DialogFrag;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import myggum.customlayout.R;

/**
 * Created by Administrator on 2016-12-13.
 */

public class DialogTextChange extends DialogFragment implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        EditNameDialogListener listener = (EditNameDialogListener) getActivity();
        listener.onFinishEditDialog(mEditText.getText().toString());
        dismiss();
    }

    /*@Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
       if(EditorInfo.IME_ACTION_DONE ==actionId){
           EditNameDialogListener listener = (EditNameDialogListener)getActivity();
           listener.onFinishEditDialog(mEditText.getText().toString());
           dismiss();
           return true;
       }
        return false;
    }*/
    public EditText mEditText;
    public Button mButton;

    public DialogTextChange() {
    }

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }
    public static DialogTextChange newInstance(String text) {
        DialogTextChange frag = new DialogTextChange();
        Bundle args = new Bundle();
        args.putString("text", text);
        frag.setArguments(args);
        return frag;
    }
   @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialogtext, container);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        mButton = (Button) view.findViewById(R.id.okbutton);
        //mEditText.setOnEditorActionListener(this);
        mButton.setOnClickListener(this);
        String title = getArguments().getString("text");
        mEditText.setText(title);
        getDialog().setTitle("자막 입력");
        mEditText.requestFocus();
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}

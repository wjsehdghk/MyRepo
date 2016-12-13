package myggum.customlayout.DialogFrag;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import myggum.customlayout.R;

/**
 * Created by Administrator on 2016-12-13.
 */

public class DialogTextChange extends DialogFragment {
    private EditText mEditText;
    public DialogTextChange() {
    }


    public static DialogTextChange newInstance(String title){
        DialogTextChange frag = new DialogTextChange();
        Bundle args = new Bundle();
        args.putString("title",title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.dialogtext,container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mEditText = (EditText)view.findViewById(R.id.txt_your_name);

        String title = getArguments().getString("title", "hello");

        getDialog().setTitle(title);

        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);






    }
}

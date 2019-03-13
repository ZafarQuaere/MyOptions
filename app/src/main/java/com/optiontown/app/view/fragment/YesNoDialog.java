package com.optiontown.app.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.optiontown.R;

/**
 * Created by amit on 27-06-2016.
 */

public abstract class YesNoDialog extends DialogFragment {

    public abstract void onClickYes();
    public abstract void onClickNo();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.warning_remove_user)
                .setPositiveButton(R.string.string_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onClickYes();
                    }
                })
                .setNegativeButton(R.string.string_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onClickNo();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

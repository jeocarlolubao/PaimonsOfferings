package com.example.paimonsofferings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertOrderDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Invalid Order")
                .setMessage("Check/Enter the respective fields \nand try again").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuFragment menuFragment = new MenuFragment();
                getFragmentManager().beginTransaction().add(R.id.fragmentBox, menuFragment).commit();
            }
        });
        setCancelable(false);
        return builder.create();
    }
}

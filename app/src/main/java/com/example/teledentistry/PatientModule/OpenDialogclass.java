package com.example.teledentistry.PatientModule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class OpenDialogclass extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
builder.setTitle("Reciept").setMessage("Your Receipt").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent=new Intent(getActivity(), RequestedConsultationActivity.class);
        startActivity(intent);
    }
});
return  builder.create();
    }
}

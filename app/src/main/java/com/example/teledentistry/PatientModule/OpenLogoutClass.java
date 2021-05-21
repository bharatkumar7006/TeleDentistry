package com.example.teledentistry.PatientModule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OpenLogoutClass extends AppCompatDialogFragment {
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Logout").setMessage("Are you sure You want to Logout").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(), LoginActivity_Pat_Module.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return  builder.create();
    }
    private void status(String status){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("status",status);
        reference.updateChildren(hashMap);
    }

    @Override
    public void onPause() {
        super.onPause();
        status("offline");
    }

}

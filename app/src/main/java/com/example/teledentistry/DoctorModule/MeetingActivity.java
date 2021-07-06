package com.example.teledentistry.DoctorModule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.facebook.react.modules.core.PermissionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MeetingActivity extends FragmentActivity implements JitsiMeetActivityInterface {
    private JitsiMeetView view;
    URL serverUrl;
    DatabaseReference reference;
    String pat_id, date, time, imageUrl, pat_name;

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        JitsiMeetActivityDelegate.onActivityResult(
                this, requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            view = new JitsiMeetView(this);
        Intent i = getIntent();
        String code = i.getStringExtra("code");
        pat_id = i.getStringExtra("pat_id");
        date = i.getStringExtra("date");
        time = i.getStringExtra("time");
        imageUrl = i.getStringExtra("imageUrl");
        pat_name = i.getStringExtra("pat_name");

        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom(code)
                .setWelcomePageEnabled(false)
                .build();
        view.join(options);

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Patients").child(pat_id);
       HashMap<String, Object> hashMap = new HashMap<>();
       hashMap.put("meeting_code", code);
       reference1.updateChildren(hashMap);

        setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.dispose();
        view = null;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userId = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Doctors").child(userId).child("Consulted_Patient").push();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pat_id",pat_id);
        hashMap.put("date", date);
        hashMap.put("time", time);
        hashMap.put("pat_name", pat_name);
        hashMap.put("imageUrl", imageUrl);

        reference.setValue(hashMap);
        Toast.makeText(this,"Meeting Destroyed",Toast.LENGTH_SHORT).show();
        //JitsiMeetActivityDelegate.onHostDestroy(this);
    }

//    @Override
//    public void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        JitsiMeetActivityDelegate.onNewIntent(intent);
//    }

//    @Override
//    public void onRequestPermissionsResult(
//            final int requestCode,
//            final String[] permissions,
//            final int[] grantResults) {
//        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"Meeting Resumed",Toast.LENGTH_SHORT).show();
     //   JitsiMeetActivityDelegate.onHostResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"Meeting Stopped",Toast.LENGTH_SHORT).show();

        // JitsiMeetActivityDelegate.onHostPause(this);
    }

    @Override
    public void requestPermissions(String[] strings, int i, PermissionListener permissionListener) {

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Toast.makeText(this,"User leaved",Toast.LENGTH_SHORT).show();

    }
}

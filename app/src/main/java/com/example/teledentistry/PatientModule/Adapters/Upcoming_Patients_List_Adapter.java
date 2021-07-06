package com.example.teledentistry.PatientModule.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.BookedSlots_Model;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Upcoming_Patients_List_Adapter extends FirebaseRecyclerAdapter<BookedSlots_Model, Upcoming_Patients_List_Adapter.ViewHolder> {
    FirebaseRecyclerOptions<BookedSlots_Model> options;
    static Context context;
    String doc_name;
    String imageUrl;
    Dialog dialog;
    EditText codeBox;
    Button jointBtn;
    URL serverUrl;


    public Upcoming_Patients_List_Adapter(@NonNull FirebaseRecyclerOptions<BookedSlots_Model> options, Context context) {
        super(options);
        this.context = context;
        this.options = options;

    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, int position, @NonNull BookedSlots_Model model) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors").child(model.getDoc_id());
        Log.d("iddd", model.getDoc_id());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doc_name = snapshot.child("full_name").getValue(String.class);
                imageUrl = snapshot.child("imageUrl").getValue(String.class);
                holder.name_tv.setText(doc_name);
                Glide.with(Upcoming_Patients_List_Adapter.context).load(imageUrl).into(holder.doc_img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String time = model.getTime();
        String time_split[] = time.split("-");
        String time1 = time_split[0];
        String time2 = time_split[1];

        holder.date_tv.setText(model.getDate());
        holder.time_tv.setText(time1 + " to");
        holder.time_tv2.setText(time2);

        try {
            serverUrl = new URL("https://meet.jit.si");

            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions
                    .Builder()
                    .setServerURL(serverUrl)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        holder.btn_consultNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.meeting_dialog_box_pat);
                codeBox = dialog.findViewById(R.id.codeBox);
                jointBtn = dialog.findViewById(R.id.joinBtn);

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                final String userId = user.getUid();

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Patients").child(userId);
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String code = snapshot.child("meeting_code").getValue(String.class);
                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                .setRoom(code)
                                .setWelcomePageEnabled(false)
                                .build();

                        JitsiMeetActivity.launch(v.getContext(), options);
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId).child("Consultation_Done").push();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("doc_id",model.getDoc_id());
                hashMap.put("date", model.getDate());
                hashMap.put("time", time);
                hashMap.put("doc_name", doc_name);
                hashMap.put("imageUrl", imageUrl);

                reference.setValue(hashMap);


//                jointBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//
//                    }
//                });

                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                dialog.show();

            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View upcomingPatientList = layoutInflater.inflate(R.layout.upcoming_patients_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(upcomingPatientList);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date_tv, name_tv, time_tv, time_tv2;
        CardView cardView;
        CircleImageView doc_img;
        Button btn_consultNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            date_tv = itemView.findViewById(R.id.date_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            time_tv2 = itemView.findViewById(R.id.time_tv2);
            doc_img = (CircleImageView) itemView.findViewById(R.id.doc_img);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            btn_consultNow = itemView.findViewById(R.id.btn_consultNow);
        }
    }
}

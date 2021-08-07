package com.example.teledentistry.PatientModule.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.DoctorModel;
import com.example.teledentistry.PatientModule.AppointmentActivity;
import com.example.teledentistry.PatientModule.ConsultMeActivity;
import com.example.teledentistry.PatientModule.ModelSpecialist;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Specialist_Adapter extends FirebaseRecyclerAdapter<DoctorModel, Specialist_Adapter.ViewHolder> {
    static Context context1;
    FirebaseRecyclerOptions<DoctorModel> options;
    String dayName;
    List<String> list;

    DatabaseReference databaseReference;
    Date date;

    public Specialist_Adapter(@NonNull FirebaseRecyclerOptions<DoctorModel> options, Context context) {
        super(options);
        context1 = context;
        this.options = options;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialist_list_pat_module, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, final int position, @NonNull final DoctorModel model) {

        list = new ArrayList<>();

        holder.doc_name_tv.setText(model.getFull_name());
        holder.experience_tv.setText(model.getExperience_year() + " years" + " - " + model.getExperience_month() + " months");
        holder.speciality_tv.setText(model.getSpeciality());
        holder.fee_tv.setText(model.getConsultation_fee());
        Glide.with(Specialist_Adapter.context1).load(model.getImageUrl()).into(holder.profile_iv);

        if (model.getStatus().equals("online")) {
            holder.online_iv.setVisibility(View.VISIBLE);
            holder.offline_iv.setVisibility(View.GONE);
            holder.status_tv.setText(model.getStatus());
        } else {
            holder.online_iv.setVisibility(View.GONE);
            holder.offline_iv.setVisibility(View.VISIBLE);
            holder.status_tv.setText("offline");
        }

        holder.btn_consultMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context1, ConsultMeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", holder.doc_name_tv.getText().toString());
                intent.putExtra("status", holder.status_tv.getText().toString());
                intent.putExtra("fee", holder.fee_tv.getText().toString());
                intent.putExtra("uri", model.getImageUrl());

                context1.startActivity(intent);
            }
        });

        holder.btn_bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context1, AppointmentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (model.getSlots() == null) {
                    intent.putExtra("slots", (HashMap<String, List<String>>) new HashMap<String, List<String>>());
                } else {
                    intent.putExtra("slots", (HashMap<String, List<String>>) model.getSlots());
                    intent.putExtra("numb", model.getPhone_no());
                    intent.putExtra("fee", model.getConsultation_fee());
                }
                context1.startActivity(intent);
            }
        });

        holder.favb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Favourite Doctors").push();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String, Object> hashMap = new HashMap<>();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        if (model.getSlots() != null) {
            HashMap<String, List<String>> hashMap = model.getSlots();

            for (String key : hashMap.keySet()) {
                Log.d("keyyy", key);

                DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
                try {
                    java.util.Date date = format.parse(key);
                    Log.d("date", String.valueOf(date));

                    DateFormat format2 = new SimpleDateFormat("EEEE");
                    dayName = format2.format(date);
                    Log.d("dayName", dayName);

                    if (!list.contains(dayName)){
                        list.add(dayName);
                        Log.d("listtt", String.valueOf(list));
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
//            if(dayName.equalsIgnoreCase("Monday") && dayName.equalsIgnoreCase("Tuesday") ) {
//                holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//            }
//            else if(dayName.equalsIgnoreCase("Tuesday")){
//                    holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//
//            }
//            else if(dayName.equalsIgnoreCase("Wednesday")){
//                holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_tuesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//
//            }else if(dayName.equalsIgnoreCase("Thursday")){
//                holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_tuesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//
//            }else if(dayName.equalsIgnoreCase("Friday")){
//                holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_tuesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//
//            }else if(dayName.equalsIgnoreCase("Saturday")){
//                holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_tuesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//
//            }else {
//                holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_tuesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                holder.btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//            }
//            if(dayName.equalsIgnoreCase("Monday") && dayName.equalsIgnoreCase("Tuesday") &&
//                    dayName.equalsIgnoreCase("Wednesday") && dayName.equalsIgnoreCase("Thursday"))
//                {
//                    holder.btn_monday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_tuesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_wednesday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                    holder.btn_thursday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//
//                }

            }
        }


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doc_name_tv, experience_tv, status_tv, specialities_tv, speciality_tv, fee_tv, slots_available_tv;
        Button btn_monday, btn_tuesday, btn_wednesday, btn_thursday, btn_friday, btn_saturday, btn_sunday,
                btn_consultMe, btn_bookAppointment;
        ImageButton favb;
        CircleImageView profile_iv, online_iv, offline_iv;
        List<String> list = new ArrayList<>();


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doc_name_tv = (TextView) itemView.findViewById(R.id.DocName_tv);
            experience_tv = (TextView) itemView.findViewById(R.id.experience_tv);
            status_tv = (TextView) itemView.findViewById(R.id.status_tv);
            specialities_tv = (TextView) itemView.findViewById(R.id.textView18);
            speciality_tv = (TextView) itemView.findViewById(R.id.textView19);
            fee_tv = (TextView) itemView.findViewById(R.id.textView67);
            slots_available_tv = (TextView) itemView.findViewById(R.id.textView17);
            profile_iv = (CircleImageView) itemView.findViewById(R.id.imageView11);
            online_iv = (CircleImageView) itemView.findViewById(R.id.online_iv);
            offline_iv = (CircleImageView) itemView.findViewById(R.id.offline_iv);

            btn_monday = (Button) itemView.findViewById(R.id.button2);
            btn_tuesday = (Button) itemView.findViewById(R.id.button3);
            btn_wednesday = (Button) itemView.findViewById(R.id.button4);
            btn_thursday = (Button) itemView.findViewById(R.id.button5);
            btn_friday = (Button) itemView.findViewById(R.id.button6);
            btn_saturday = (Button) itemView.findViewById(R.id.button7);
            btn_sunday = (Button) itemView.findViewById(R.id.button8);
            btn_consultMe = (Button) itemView.findViewById(R.id.consultMe_btn);
            btn_bookAppointment = (Button) itemView.findViewById(R.id.bookAppointment_btn);
            favb = (ImageButton) itemView.findViewById(R.id.fav_btn);


//            list.add(dayName);
//            Log.d("listtt", String.valueOf(list));
//
//            if (list.contains("Monday") && list.contains("Tuesday") && list.contains("Wednesday") && list.contains("Thursday")) {
//                btn_friday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                btn_saturday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//                btn_sunday.getBackground().setColorFilter(ContextCompat.getColor(context1, R.color.reddark), PorterDuff.Mode.LIGHTEN);
//            }

        }

    }

}

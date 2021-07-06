package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.DoctorModule.DoctorModel;
import com.example.teledentistry.PatientModule.AppointmentActivity;
import com.example.teledentistry.PatientModule.ConsultMeActivity;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearcResultAdapter extends FirebaseRecyclerAdapter<DoctorModel, SearcResultAdapter.ViewHolder> {
    static Context context1;
    FirebaseRecyclerOptions<DoctorModel> options;
    String dayName;
    List<String> list;


    public SearcResultAdapter(@NonNull FirebaseRecyclerOptions<DoctorModel> options, Context context) {
        super(options);
        context1 = context;
        this.options = options;

    }

    @Override
    protected void onBindViewHolder(@NonNull SearcResultAdapter.ViewHolder holder, int position, @NonNull DoctorModel model) {

        list = new ArrayList<>();

        holder.doc_name_tv.setText(model.getFull_name());
        holder.experience_tv.setText(model.getExperience_year() + " years" + " - " + model.getExperience_month() + " months");
        holder.speciality_tv.setText(model.getSpeciality());
        holder.fee_tv.setText(model.getConsultation_fee());
        Glide.with(SearcResultAdapter.context1).load(model.getImageUrl()).into(holder.profile_iv);

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

                    if (!list.contains(dayName)) {
                        list.add(dayName);
                        Log.d("listtt", String.valueOf(list));
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    @NonNull
    @Override
    public SearcResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_specialist_list_pat_module, parent, false);

        return new ViewHolder(v);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView doc_name_tv, experience_tv, status_tv, specialities_tv, speciality_tv, fee_tv, slots_available_tv;
        CircleImageView profile_iv, online_iv, offline_iv;
        ImageView favb;
        Button btn_consultMe, btn_bookAppointment;

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
            favb = (ImageView) itemView.findViewById(R.id.imageView8);
            online_iv = (CircleImageView) itemView.findViewById(R.id.online_iv);
            offline_iv = (CircleImageView) itemView.findViewById(R.id.offline_iv);

            btn_consultMe = (Button) itemView.findViewById(R.id.consultMe_btn);
            btn_bookAppointment = (Button) itemView.findViewById(R.id.bookAppointment_btn);

        }


    }


}

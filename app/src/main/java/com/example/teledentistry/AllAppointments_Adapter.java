package com.example.teledentistry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllAppointments_Adapter extends RecyclerView.Adapter<AllAppointments_Adapter.ViewHolder> {
    Context context;
    String date[], name[] ,time[];

    public AllAppointments_Adapter(Context context, String[] date, String[] name, String[] time) {
        this.context = context;
        this.date = date;
        this.name = name;
        this.time = time;
    }

    @NonNull
    @Override
    public AllAppointments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View allAppointments_list = layoutInflater.inflate(R.layout.all_appointments_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(allAppointments_list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllAppointments_Adapter.ViewHolder holder, int position) {
        holder.allAppointments_date_tv.setText(date[position]);
        holder.allAppointments_patient_name_tv.setText(name[position]);
        holder.allAppointments_patient_time_tv.setText(time[position]);
    }


    @Override
    public int getItemCount() {
        return date.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView allAppointments_date_tv, allAppointments_patient_name_tv, allAppointments_patient_time_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.allAppointments_date_tv = itemView.findViewById(R.id.allAppointmentsPatient_date_tv);
            this.allAppointments_patient_name_tv = itemView.findViewById(R.id.allAppointmentsPatient_Name_tv);
            this.allAppointments_patient_time_tv = itemView.findViewById(R.id.allAppointmentsPatient_time_tv);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), AllAppointments_PatientsInfo_Activity.class);
            v.getContext().startActivity(i);
        }
    }
}

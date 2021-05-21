package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.PatientConsultedActivity;
import com.example.teledentistry.R;

public class ConsultedPatientListAdapter extends RecyclerView.Adapter<ConsultedPatientListAdapter.ViewHolder>{
    Context context;
    String consultedPatientName_list[];

    public ConsultedPatientListAdapter(Context context, String[] consultedPatient_list) {
        this.context = context;
        this.consultedPatientName_list = consultedPatient_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View consultedPatient_list = layoutInflater.inflate(R.layout.consulted_patient_list_doc_module, parent, false);
        ConsultedPatientListAdapter.ViewHolder viewHolder = new ConsultedPatientListAdapter.ViewHolder(consultedPatient_list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.consulted_patient_name_tv.setText(consultedPatientName_list[position]);


    }

    @Override
    public int getItemCount() {
        return consultedPatientName_list.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView consulted_patient_name_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            consulted_patient_name_tv = itemView.findViewById(R.id.consultedPatient_name_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, PatientConsultedActivity.class);
            context.startActivity(i);

        }
    }
}

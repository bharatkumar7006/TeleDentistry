package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.PatientConsultationActivity;
import com.example.teledentistry.R;

public class CurrentPatientDataList_Adapter extends RecyclerView.Adapter<CurrentPatientDataList_Adapter.ViewHolder>{
    Context context;

    public CurrentPatientDataList_Adapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View currentPatient_list = layoutInflater.inflate(R.layout.current_patients_list_doc_module, parent, false);
        ViewHolder viewHolder = new ViewHolder(currentPatient_list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentPatientDataList_Adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView current_patient_date_tv,current_patient_name_tv,current_patient_time_tv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.current_patient_date_tv = itemView.findViewById(R.id.currentPatient_date_tv);
            this.current_patient_name_tv = itemView.findViewById(R.id.current_patientName_tv);
            this.current_patient_time_tv = itemView.findViewById(R.id.currentPatient_time_tv);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), PatientConsultationActivity.class);
            context.startActivity(i);

        }
    }







}

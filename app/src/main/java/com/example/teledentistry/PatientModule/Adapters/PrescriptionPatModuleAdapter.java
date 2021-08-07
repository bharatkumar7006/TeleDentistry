package com.example.teledentistry.PatientModule.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.Adapters.CurrentPatientDataList_Adapter;
import com.example.teledentistry.PatientModule.PrescriptionPatModule_Model;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrescriptionPatModuleAdapter extends FirebaseRecyclerAdapter<PrescriptionPatModule_Model,PrescriptionPatModuleAdapter.ViewHolder> {

    FirebaseRecyclerOptions<PrescriptionPatModule_Model> options;


    public PrescriptionPatModuleAdapter(FirebaseRecyclerOptions<PrescriptionPatModule_Model> options) {
        super(options);

        this.options = options;


    }

    @NonNull
    @Override
    public PrescriptionPatModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View received_prescription = layoutInflater.inflate(R.layout.prescription_adapter_pat_module, parent, false);
       ViewHolder viewHolder = new ViewHolder(received_prescription);

        return viewHolder;
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PrescriptionPatModule_Model model) {

        holder.diagnoses_tv.setText(model.getDiagnoses());

        String data = model.getTabs();
        String dataArray[] = data.split(";");
        String tab_name = dataArray[0];
        String dosage = dataArray[1];
        String comment = dataArray[2];

        holder.tab_tv.setText(tab_name);
        holder.diagnoses_tv.setText(model.getDiagnoses());
        holder.dosage_tv.setText(dosage);
        holder.comment_tv.setText(comment);

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView diagnoses_tv, tab_tv, dosage_tv, comment_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            diagnoses_tv = itemView.findViewById(R.id.diagnoses_val_tv);
            tab_tv = itemView.findViewById(R.id.tabName_tv);
            dosage_tv = itemView.findViewById(R.id.dosage_val_tv);
            comment_tv = itemView.findViewById(R.id.comments_val_tv);


        }

    }
}

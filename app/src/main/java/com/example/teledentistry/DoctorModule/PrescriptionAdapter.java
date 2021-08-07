package com.example.teledentistry.DoctorModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.Adapters.Slot_RecyclerView_Adapter;
import com.example.teledentistry.R;

import java.util.List;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.ViewHolder> {
    List<String> list;
    Context context;

    public PrescriptionAdapter(@NonNull Context context, List<String> list) {
    this.context = context;
    this.list = list;
    }

    public PrescriptionAdapter() {
    }

    @NonNull
    @Override
    public PrescriptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View prs = layoutInflater.inflate(R.layout.prescription_adapter, parent, false);
        ViewHolder viewHold = new ViewHolder(prs);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionAdapter.ViewHolder holder, int position) {

        String data = list.get(position);
        String[] data1 = data.split(";");

        holder.tabsName_tv.setText(data1[0]);
        holder.dosage_val_tv.setText(data1[1]);
        holder.comments_val_tv.setText(data1[2]);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tabsName_tv, dosage_val_tv, comments_val_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tabsName_tv = itemView.findViewById(R.id.tabName_tv);
            dosage_val_tv = itemView.findViewById(R.id.dosage_val_tv);
            comments_val_tv = itemView.findViewById(R.id.comments_val_tv);


        }
    }
}

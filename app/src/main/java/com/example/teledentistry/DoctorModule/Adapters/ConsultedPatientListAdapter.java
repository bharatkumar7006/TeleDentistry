package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teledentistry.Consulted_Patient_Model;
import com.example.teledentistry.DoctorModule.BookedSlots_Model;
import com.example.teledentistry.DoctorModule.PatientConsultedActivity;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsultedPatientListAdapter extends FirebaseRecyclerAdapter<Consulted_Patient_Model, ConsultedPatientListAdapter.ViewHolder> {
    FirebaseRecyclerOptions<Consulted_Patient_Model> options;
    static Context context;

    public ConsultedPatientListAdapter(Context context, FirebaseRecyclerOptions<Consulted_Patient_Model> options) {
        super(options);
        this.context = context;
        this.options = options;

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
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Consulted_Patient_Model model) {

        String time = model.getTime();
        String time_split[] = time.split("-");
        String time1 = time_split[0];
        String time2 = time_split[1];

        holder.date_tv.setText(model.getDate());
        holder.name_tv.setText(model.getPat_name());
        holder.time_tv.setText(time1+" to");
        holder.time_tv2.setText(time2);
        Glide.with(ConsultedPatientListAdapter.context).load(model.getImageUrl()).into(holder.pat_img);


    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView date_tv, name_tv, time_tv, time_tv2;
        CardView cardView;
        CircleImageView pat_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date_tv = itemView.findViewById(R.id.date_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            time_tv2 = itemView.findViewById(R.id.time_tv2);
            pat_img = (CircleImageView) itemView.findViewById(R.id.pat_img);
            cardView = (CardView) itemView.findViewById(R.id.cardView);


        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, PatientConsultedActivity.class);
            context.startActivity(i);

        }
    }
}

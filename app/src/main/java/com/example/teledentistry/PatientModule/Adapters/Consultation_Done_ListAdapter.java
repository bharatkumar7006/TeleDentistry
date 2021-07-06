package com.example.teledentistry.PatientModule.Adapters;

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
import com.example.teledentistry.DoctorModule.PatientConsultedActivity;
import com.example.teledentistry.PatientModule.Consultation_Done_Model;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Consultation_Done_ListAdapter extends FirebaseRecyclerAdapter<Consultation_Done_Model, Consultation_Done_ListAdapter.ViewHolder> {
    FirebaseRecyclerOptions<Consultation_Done_Model> options;
    static Context context;

    public Consultation_Done_ListAdapter(Context context, FirebaseRecyclerOptions<Consultation_Done_Model> options) {
        super(options);
        this.context = context;
        this.options = options;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View consultedPatient_list = layoutInflater.inflate(R.layout.consultation_done_list_pat_module, parent, false);
        Consultation_Done_ListAdapter.ViewHolder viewHolder = new Consultation_Done_ListAdapter.ViewHolder(consultedPatient_list);
        return viewHolder;
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Consultation_Done_Model model) {

        String time = model.getTime();
        String time_split[] = time.split("-");
        String time1 = time_split[0];
        String time2 = time_split[1];

        holder.date_tv.setText(model.getDate());
        holder.name_tv.setText(model.getDoc_name());
        holder.time_tv.setText(time1+" to");
        holder.time_tv2.setText(time2);
        Glide.with(Consultation_Done_ListAdapter.context).load(model.getImageUrl()).into(holder.doc_img);


    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView date_tv, name_tv, time_tv, time_tv2;
        CardView cardView;
        CircleImageView doc_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date_tv = itemView.findViewById(R.id.date_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            time_tv2 = itemView.findViewById(R.id.time_tv2);
            doc_img = (CircleImageView) itemView.findViewById(R.id.doc_img);
            cardView = (CardView) itemView.findViewById(R.id.cardView);


        }

        @Override
        public void onClick(View v) {
//            Intent i = new Intent(context, PatientConsultedActivity.class);
//            context.startActivity(i);

        }
    }
}

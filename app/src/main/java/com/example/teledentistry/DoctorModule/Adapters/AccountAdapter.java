package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.TransectionModel;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AccountAdapter extends FirebaseRecyclerAdapter<TransectionModel, AccountAdapter.ViewHolder> {
    Context context;
    FirebaseRecyclerOptions<TransectionModel> options;

    public AccountAdapter(@NonNull FirebaseRecyclerOptions<TransectionModel> options, Context context) {
        super(options);
        this.options = options;
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position, @NonNull TransectionModel model) {
        String pat_id = model.getPat_id();
        Log.d("idd", pat_id);


        Query reference = FirebaseDatabase.getInstance().getReference("Patients").child(pat_id);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pat_name = snapshot.child("full_name").getValue(String.class);
                holder.pat_name_tv.setText(pat_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.disease_tv.setText(model.getDisease());
        holder.amount_tv.setText(model.getFee());
        holder.date_tv.setText(model.getDate());


    }

    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View table_content = layoutInflater.inflate(R.layout.account_table_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(table_content);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_tv, pat_name_tv, disease_tv, amount_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_tv = itemView.findViewById(R.id.account_date_tv);
            pat_name_tv = itemView.findViewById(R.id.account_pat_name_tv);
            disease_tv = itemView.findViewById(R.id.account_disease_tv);
            amount_tv = itemView.findViewById(R.id.account_amount_tv);


        }



    }
}

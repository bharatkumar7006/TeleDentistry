package com.example.teledentistry.DoctorModule.Adapters;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.Pat_History_Model;
import com.example.teledentistry.PatientModule.Adapters.PrescriptionPatModuleAdapter;
import com.example.teledentistry.PatientModule.PrescriptionPatModule_Model;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Pat_History_Adapter extends FirebaseRecyclerAdapter<Pat_History_Model, Pat_History_Adapter.ViewHolder> {

    FirebaseRecyclerOptions<Pat_History_Model> options;
    String pat_id;
    PrescriptionPatModuleAdapter adapter;
    Dialog dialog;
    RecyclerView historyData_rv;

    public Pat_History_Adapter(@NonNull FirebaseRecyclerOptions<Pat_History_Model> options, String pat_id) {
        super(options);
        this.options = options;
        this.pat_id = pat_id;
    }

    @Override
    protected void onBindViewHolder(@NonNull Pat_History_Adapter.ViewHolder holder, int position, @NonNull Pat_History_Model model) {

        holder.docName.setText(model.getDoc_name());
        holder.date.setText(model.getDate());


        final FirebaseRecyclerOptions<PrescriptionPatModule_Model> options =
                new FirebaseRecyclerOptions.Builder<PrescriptionPatModule_Model>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference("Patients").child(pat_id).child("Prescriptions")
                                , PrescriptionPatModule_Model.class)
                        .build();

        holder.prs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.prescription_history_dialog_box_doc_module);
                historyData_rv = dialog.findViewById(R.id.history_data_rv);

               historyData_rv.setLayoutManager(new LinearLayoutManager(v.getContext()));

                adapter = new PrescriptionPatModuleAdapter(options);
                historyData_rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.startListening();

                dialog.getWindow().setLayout(70, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                dialog.show();



            }
        });




    }

    @NonNull
    @Override
    public Pat_History_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View history_content = layoutInflater.inflate(R.layout.pat_history_doc_module, parent, false);
        ViewHolder viewHolder = new ViewHolder(history_content);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView docName, date;
        Button prs_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            docName = itemView.findViewById(R.id.doc_name_tv);
            date = itemView.findViewById(R.id.date_tv);
            prs_btn = itemView.findViewById(R.id.prs_btn);


        }

        protected void onStart() {
            adapter.startListening();
        }

        protected void onStop() {

            adapter.stopListening();
        }


    }



}

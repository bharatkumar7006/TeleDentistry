package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.Calender_and_Schedule_Activity;
import com.example.teledentistry.DoctorModule.Slot_Model;
import com.example.teledentistry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Slot_RecyclerView_Adapter extends RecyclerView.Adapter<Slot_RecyclerView_Adapter.ViewHold> {
    Context context;
    View view;
    public static List<String> list;
    MaterialRadioButton lastChecked;
    int lastSelectedPosition;
    public Slot_RecyclerView_Adapter(Context context, View view, List<String> list) {

        this.context = context;
        this.view = view;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View slots = layoutInflater.inflate(R.layout.time_slots_doc_module, parent, false);
        ViewHold viewHold = new ViewHold(slots);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHold holder, final int position) {

        holder.materialRadioButton1.setText(list.get(position));

      holder.materialRadioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(lastChecked!=null) {
                  if(lastSelectedPosition!= position) {
                      lastChecked.setChecked(false);
                      lastChecked = null;
                  }
              }
              lastChecked = holder.materialRadioButton1;
              lastSelectedPosition = position;
          }

      });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        MaterialRadioButton materialRadioButton1;

        public ViewHold(@NonNull View itemView) {
            super(itemView);
            materialRadioButton1 = itemView.findViewById(R.id.radio_btn);
        }
    }

}



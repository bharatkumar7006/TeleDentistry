package com.example.teledentistry.PatientModule;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OpenDialogAppintmentSpecialistclass extends AppCompatDialogFragment {
    String time;
    Context context;

    public OpenDialogAppintmentSpecialistclass(String time, Context context) {
        this.time = time;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
builder.setTitle("Reciept").setMessage("Your Receipt").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        String[] array = time.split("-");
        String ac_time = array[0];
        try {
            startAlarm(ac_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Intent intent=new Intent(getActivity(), MyConsultationActivity.class);
        startActivity(intent);
    }
});
return  builder.create();
    }

    private void startAlarm(String time) throws ParseException {
//        DateFormat formatter=new SimpleDateFormat("HH:mm:ss");
//        Date date=formatter.parse(time);
//        String dateStr = formatter.format(date);
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//
//        date = c.getTime();
        String[] array = time.split(":");
        for(String s : array){
            s.replace("PM","");
            s.replace("AM","");
        }
        int hour = Integer.parseInt ( array[0].trim() );
        int min;
        String s = array[1];
        if(s.contains("PM")){
            min = Integer.parseInt(s.replace("PM", "").trim());
        }else {
            min = Integer.parseInt(s.replace("AM", "").trim());
        }

      //  int min = Integer.parseInt ( array[1].trim() );

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, 0);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

}

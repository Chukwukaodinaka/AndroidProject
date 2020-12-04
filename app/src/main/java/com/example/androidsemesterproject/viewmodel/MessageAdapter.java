package com.example.androidsemesterproject.viewmodel;


import android.app.Application;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsemesterproject.R;
import com.example.androidsemesterproject.model.message.Message;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> messages;
    private MessageViewModel messageViewModel;


    public MessageAdapter(List<Message> messages,MessageViewModel messageViewModel)
    {
        this.messageViewModel = messageViewModel;
        this.messages = messages;
    }
    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.messagesinglerow,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(messages.get(position).getTime());
        holder.email.setText(messages.get(position).getSendTo().fullAddress());
        holder.title.setText(messages.get(position).getTitle());
        holder.message.setText(messages.get(position).getMessage());
        holder.id.setText(String.valueOf(messages.get(position).getId()));

        if(holder.id != null)
        {
            startTiming(holder.time.getText().toString(),holder);
        }
    }

    void startTiming(String time,ViewHolder holder)
    {
        Timer timer = new Timer();
        timer.schedule(new MyTimeTask(messageViewModel,holder),getTime(time,holder) *1000 * 60 );
    }


    int getTime(String time,ViewHolder holder)
    {
        int time1 = 0;
        Calendar calendar = Calendar.getInstance();
        switch (time)
        {
            case "5 minutes":
                time1 = 5;

//                return  addHoursToJavaUtilDate(calendar.getTime(),time1);
                return time1;
            case "10 minutes":
                time1 = 10;
//                return  addHoursToJavaUtilDate(calendar.getTime(),time1);
                return time1;
            case "20 minutes":
                time1= 20;
//                return  addHoursToJavaUtilDate(calendar.getTime(),time1);
                return time1;
            case "1 hour":
                time1 = 60;
//                return  addHoursToJavaUtilDate(calendar.getTime(),time1);
                return time1;
        }
//        return  addHoursToJavaUtilDate(calendar.getTime(),0);
        return time1;
    }



    @Override
    public int getItemCount() {
        if(messages !=null) {
            return messages.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView email;
        TextView message;
        TextView time;
        TextView title;
        TextView id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
            id = itemView.findViewById(R.id.id);

        }


    }
}
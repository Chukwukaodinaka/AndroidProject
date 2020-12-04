package com.example.androidsemesterproject.viewmodel;

import com.example.androidsemesterproject.model.message.Message;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class MyTimeTask extends TimerTask {

    MessageViewModel viewModel;
    MessageAdapter.ViewHolder viewHolder;

    public MyTimeTask( MessageViewModel viewModel, MessageAdapter.ViewHolder viewHolder) {

        this.viewModel = viewModel;
        this.viewHolder = viewHolder;
    }



    @Override
    public void run() {
        viewHolder.time.setText("Sending ..");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = viewModel.getMessage(Integer.parseInt(viewHolder.id.getText().toString()));
        viewModel.send(message.getFrom().fullAddress(),message.getSendTo().fullAddress(),message.getTitle(),message.getMessage());
        viewModel.deleteMessage(Integer.parseInt(viewHolder.id.getText().toString()));
    }
}

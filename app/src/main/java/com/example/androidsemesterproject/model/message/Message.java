package com.example.androidsemesterproject.model.message;

import com.example.androidsemesterproject.model.email.EMail;

import org.threeten.bp.LocalTime;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(EMail.class)
    private EMail from;
    @TypeConverters(EMail.class)
    private EMail sendTo;
    private String title;
    private String message;
    private String time;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EMail getFrom() {
        return from;
    }

    public void setFrom(EMail from) {
        this.from = from;
    }

    public EMail getSendTo() {
        return sendTo;
    }

    public void setSendTo(EMail sendTo) {
        this.sendTo = sendTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    private boolean isSent;

    public Message(int id, EMail from, EMail sendTo, String title, String message, String time, boolean isSent) {
        this.id = id;
        this.from = from;
        this.sendTo = sendTo;
        this.title = title;
        this.message = message;
        this.time = time;
        this.isSent = isSent;
    }


    public Message(String from, String sendTo, String title, String message, String time, boolean isSent) {
        this.from = new EMail();
        this.from.setFrom(from);
        this.sendTo = new EMail();
        this.sendTo.setFrom(sendTo);
        this.title = title;
        this.message = message;
        this.time = time;
        this.isSent = isSent;
    }

}
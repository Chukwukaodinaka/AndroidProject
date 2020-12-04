package com.example.androidsemesterproject.model.email;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity
public class EMail {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String emailname;
    private String domain;
    private String extemsion;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailname() {
        return emailname;
    }

    public void setEmailname(String emailname) {
        this.emailname = emailname;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getExtemsion() {
        return extemsion;
    }

    public void setExtemsion(String extemsion) {
        this.extemsion = extemsion;
    }

    public String fullAddress() {
        return emailname + "@" + domain + extemsion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setFrom(String from)
    {

        String[] email= from.split("@");
        emailname= email[0];
        String rest = email[1];
        String[] str = rest.split("\\.");
        domain = str[0];
        if(str.length > 2) {
            extemsion = "." + str[1] + "." + str[2];
        }
        else
        {
            extemsion = "." + str[1];
        }
    }

    @TypeConverter
    public EMail toEmailString(String someEmail)
    {
        EMail eMail = new EMail();
        String[] email= someEmail.split("@");
        eMail.emailname= email[0];
        String rest = email[1];
        String[] str = rest.split("\\.");
        eMail.domain = str[0];
        if(str.length > 2) {
            eMail.extemsion = "." + str[1] + "." + str[2];
        }
        else eMail.extemsion = "." + str[1];

        return  eMail;
    }

    @TypeConverter
    public String toStringEmail(EMail eMail)
    {
        return eMail.fullAddress();
    }

}


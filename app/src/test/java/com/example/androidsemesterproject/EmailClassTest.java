package com.example.androidsemesterproject;

import com.example.androidsemesterproject.model.email.EMail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailClassTest {

    private EMail email;

    @BeforeEach
    public void init(){
        email = new EMail();
    }
    @Test
    public void setFromTest(){
        email.setFrom("obama@martin.dk");

        assertEquals("obama",email.getEmailname());
        assertEquals("martin",email.getDomain());
        assertEquals("dk",email.getExtemsion());
    }

    @Test
    public void setFromTestNoNull(){
        EMail emailemail = email.toEmailString("obama@martin.dk");

        assertEquals("obama",email.getEmailname());
        assertEquals("martin",email.getDomain());
        assertEquals("dk",email.getExtemsion());

        assertNotNull(emailemail);
    }

    @Test
    public void fullAddressThrows(){
       email.setEmailname("obama");
       email.setDomain("martin");
       email.setExtemsion("dk");

       assertNotEquals(email.fullAddress(),"Sommer@Tmmyturner.com");
    }









}

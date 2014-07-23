package com.tools.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator{
	private final String username = "jukuadsupport";
    private final String password = "cnmmdcdts";
    
	@Override
	protected PasswordAuthentication getPasswordAuthentication(){ 
       return new PasswordAuthentication(username,password); 
    } 
}

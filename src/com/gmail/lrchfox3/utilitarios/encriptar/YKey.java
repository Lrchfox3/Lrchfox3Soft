package com.gmail.lrchfox3.utilitarios.encriptar;

import javax.swing.*;
import java.util.*;


public class YKey {
	private String password;
	
	private char theKey[] = new char[10];
	
	public YKey( String currentPassword )
	{
		password = currentPassword;
		
	}
	
	public char[] generateKey()
	{
		
		char array[] = password.toCharArray();
		
		int value;
		
		for ( int i = 0; i < 10; i++ ) {
			
			
			value = (int) array[i];
			
			String s = Integer.toBinaryString(value);
			
			theKey[i] = s.charAt(5);
			
			
		}
		String keyString;
		
		String firstChar = String.valueOf(theKey[0]);
		String secondChar = String.valueOf(theKey[1]);
		String thirdChar = String.valueOf(theKey[2]);
		String fourthChar = String.valueOf(theKey[3]);
		String fifthChar = String.valueOf(theKey[4]);
		String sixthChar = String.valueOf(theKey[5]);
		String seventhChar = String.valueOf(theKey[6]);
		String eightChar = String.valueOf(theKey[7]);
		String nineChar = String.valueOf(theKey[8]);
		String tenthChar = String.valueOf(theKey[9]);
		
		keyString = firstChar + secondChar + thirdChar + fourthChar + fifthChar +
		            sixthChar + seventhChar + eightChar + nineChar + tenthChar;
		
		
	    return theKey;
            
		    
	}
	
	
}
		
		
		
	

			 
		
	

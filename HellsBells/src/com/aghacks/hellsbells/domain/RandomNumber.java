package com.aghacks.hellsbells.domain;

import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class RandomNumber {

	public static String[] execute(Context context){
	Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
	String[] name = new String[phones.getColumnCount()];
	String[] phoneNumber = new String[phones.getColumnCount()];
	
	int i=0;
	while (phones.moveToNext())
	{
	  name[i]=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
	  phoneNumber[i] = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	  i++;
	}
	phones.close();
	
	Random r = new Random();
	final int random= r.nextInt(i);
	
	String[] data = new String[2];
	data[0] = name[random];
	data[1] = phoneNumber[random];
	
	return data;
	}
}

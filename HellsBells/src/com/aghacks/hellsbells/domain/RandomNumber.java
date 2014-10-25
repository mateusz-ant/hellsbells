package com.aghacks.hellsbells.domain;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.Random;

public class RandomNumber {

    public static String[] execute(Context context) {
        String[] data = new String[2];
        Cursor phones = null;
        try {
            phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            int count = phones.getCount();

            if (count == 0) {
                return data;
            }

            int rand = new Random().nextInt(count);

            phones.moveToPosition(rand);

            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            data[0] = name;
            data[1] = phoneNumber;
        } finally {
            if (phones != null) {
                phones.close();
            }
        }

        return data;
    }
}

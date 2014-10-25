package com.aghacks.hellsbells.punishments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.aghacks.hellsbells.domain.RandomNumber;

public class CrazyCaller implements Punishment {

    @Override
    public void punish(Context context) {
        PhoneCallListener phoneListener = new PhoneCallListener(context);
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        String number = RandomNumber.execute(context)[1];

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callIntent);
    }

    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private Context context;

        PhoneCallListener(Context context) {
            this.context = context;
        }

        private boolean isPhoneCalling = false;

        String LOG_TAG = PhoneCallListener.class.getSimpleName();

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = context.getPackageManager()
                            .getLaunchIntentForPackage(
                                    context.getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);

                    isPhoneCalling = false;
                }
            }
        }
    }

}
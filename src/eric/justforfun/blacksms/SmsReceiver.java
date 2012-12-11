package eric.justforfun.blacksms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class SmsReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		Log.e("~~~~~receiver~~~~~", intent.getAction());
		if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < pdus.length; i++) {
			    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			    Log.e("messages." + i, pdus[i].toString());
			    String number = messages[i].getOriginatingAddress();
			    if (Utils.isEvil(number)) {
			    	abortBroadcast();
			    	// TODO save and delete message; 
			    }
			}
		}
	}
	
}
package uk.co.richyhbm.locker.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import uk.co.richyhbm.locker.Utilities.Settings;


public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Settings settings = new Settings(context);

            if(!settings.shouldPersistFailsAfterReboot()) {
                settings.resetCurrentFailedAttempts();
            }
        }
    }
}

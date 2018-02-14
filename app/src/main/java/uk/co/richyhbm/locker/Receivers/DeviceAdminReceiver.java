package uk.co.richyhbm.locker.Receivers;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

import uk.co.richyhbm.locker.R;
import uk.co.richyhbm.locker.Utilities.DeviceAdminManager;
import uk.co.richyhbm.locker.Utilities.NotificationHelper;
import uk.co.richyhbm.locker.Utilities.Settings;

public class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        Settings settings = new Settings(context);
        settings.resetCurrentFailedAttempts();
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return context.getString(R.string.remove_admin_warning);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Settings settings = new Settings(context);
        settings.resetCurrentFailedAttempts();
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent, UserHandle user) {
        Settings settings = new Settings(context);
        settings.addToCurrentFailedAttempts();
        DeviceAdminManager deviceAdminManager = new DeviceAdminManager(context);

        if(settings.currentFailedAttempts() > 0 && settings.currentFailedAttempts() >= settings.getMaxFailedAttemptsForWipe()) {
            if (settings.isInSafeMode()) {
                NotificationHelper.notify(context, R.string.app_name, R.string.safe_mode_triggered);
            } else {
                deviceAdminManager.wipe(settings.wipeExternalStorage());
            }
        }
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent, UserHandle user) {
        Settings settings = new Settings(context);
        settings.resetCurrentFailedAttempts();
    }
}

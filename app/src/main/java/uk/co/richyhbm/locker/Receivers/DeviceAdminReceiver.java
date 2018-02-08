package uk.co.richyhbm.locker.Receivers;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

import uk.co.richyhbm.locker.Utilities.Settings;

public class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        Settings settings = new Settings(context);

    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "remove_admin_warning";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Settings settings = new Settings(context);

        DevicePolicyManager dpm = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminName = new ComponentName(context, DeviceAdminReceiver.class);
        if (dpm != null) {
            dpm.setMaximumFailedPasswordsForWipe(adminName, 0);
        }
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent, UserHandle user) {

    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent, UserHandle user) {

    }
}

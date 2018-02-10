package uk.co.richyhbm.locker.Utilities;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import uk.co.richyhbm.locker.R;
import uk.co.richyhbm.locker.Receivers.DeviceAdminReceiver;

public class DeviceAdminManager {
    private Context context;
    private DevicePolicyManager devicePolicyManager;
    private ComponentName adminName;

    public DeviceAdminManager(Context context) {
        this.context = context;
        this.devicePolicyManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        this.adminName = new ComponentName(context, DeviceAdminReceiver.class);
    }

    public boolean isAdmin() {
        return devicePolicyManager != null && devicePolicyManager.isAdminActive(adminName);
    }

    public void openAddDeviceAdminActivity() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        ComponentName deviceAdminComponentName = new ComponentName(context, DeviceAdminReceiver.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdminComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, context.getString(R.string.admin_description));
        context.startActivity(intent);
    }

    private void openSecuritySettingsActivity() {
        Intent intent = new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void removeAdmin() {
        if(isAdmin())
            devicePolicyManager.removeActiveAdmin(adminName);
    }
}

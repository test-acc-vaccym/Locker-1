package uk.co.richyhbm.locker.Utilities;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

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

    public void openDeviceAdminActivity(boolean enableAdmin) {
        if(enableAdmin) openAddDeviceAdminActivity();
        else openSettingsDeviceAdminActivity();
    }

    private void openAddDeviceAdminActivity() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        ComponentName deviceAdminComponentName = new ComponentName(context, DeviceAdminReceiver.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdminComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, context.getString(R.string.admin_description));
        context.startActivity(intent);
    }

    private void openSettingsDeviceAdminActivity() {
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        context.startActivity(intent);
    }

    public void removeAdmin() {
        if(isAdmin())
            devicePolicyManager.removeActiveAdmin(adminName);
    }
}

package uk.co.richyhbm.locker.Fragments;


import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import uk.co.richyhbm.locker.R;
import uk.co.richyhbm.locker.Utilities.DeviceAdminManager;
import uk.co.richyhbm.locker.Utilities.DialogHelper;
import uk.co.richyhbm.locker.Utilities.Settings;

public class PreferencesFragment extends PreferenceFragment {
    Settings settings;
    DeviceAdminManager deviceAdminManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = new Settings(getActivity());
        deviceAdminManager = new DeviceAdminManager(getActivity());
        addPreferencesFromResource(R.xml.preferences);
        setUpDeviceAdminEnabler();
    }

    private void setUpDeviceAdminEnabler() {
        final CheckBoxPreference preference = (CheckBoxPreference)findPreference(getString(R.string.settings_key_device_admin));
        if(deviceAdminManager.isAdmin()) {
            preference.setTitle(R.string.remove_device_admin);
            preference.setChecked(true);
        } else {
            preference.setTitle(R.string.enable_device_admin);
            preference.setChecked(false);
        }

        preference.setOnPreferenceClickListener(onClick -> {
            if(deviceAdminManager.isAdmin()) {
                DialogHelper.BasicDialog(getActivity(), R.string.remove_admin_warning, () -> {
                            deviceAdminManager.removeAdmin();
                            getActivity().recreate();
                });
            } else {
                deviceAdminManager.openAddDeviceAdminActivity(getActivity());
            }
            return true;
        });
    }
}

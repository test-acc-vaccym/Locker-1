package uk.co.richyhbm.locker.Fragments;


import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import uk.co.richyhbm.locker.R;
import uk.co.richyhbm.locker.Receivers.DeviceAdminReceiver;
import uk.co.richyhbm.locker.Utilities.DeviceAdminManager;
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

        final Preference deviceAdminEnabler = findPreference(getString(R.string.settings_key_device_admin));

        deviceAdminEnabler.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                deviceAdminManager.openDeviceAdminActivity(!deviceAdminManager.isAdmin());
                return true;
            }
        });
    }
}

package uk.co.richyhbm.locker.Fragments;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import uk.co.richyhbm.locker.R;
import uk.co.richyhbm.locker.Utilities.Settings;

public class PreferencesFragment extends PreferenceFragment {
    Settings settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = new Settings(getActivity());

        addPreferencesFromResource(R.xml.preferences);
    }
}

package uk.co.richyhbm.locker.Utilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import uk.co.richyhbm.locker.R;

public class Settings {
    private Context context;
    private SharedPreferences sharedPreferences;

    public Settings(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private String getResString(int resId) { return context.getString(resId); }
    public String getString(int keyId, String defaultValue) { return sharedPreferences.getString(getResString(keyId), defaultValue); }
    private boolean getBoolean(int keyId, boolean defaultValue) { return sharedPreferences.getBoolean(getResString(keyId), defaultValue); }
    private int getInt(int keyId, int defaultValue) { return sharedPreferences.getInt(getResString(keyId), defaultValue); }

    private void setBoolean(int keyId, boolean value) {
        sharedPreferences.edit().putBoolean(getResString(keyId), value).apply();
    }

    private void setInt(int keyId, int value) {
        sharedPreferences.edit().putInt(getResString(keyId), value).apply();
    }

    private void remove(int keyId) {
        sharedPreferences.edit().remove(getResString(keyId)).apply();
    }

    public boolean isInSafeMode() { return getBoolean(R.string.settings_key_safe_mode, true); }
    public boolean shouldPersistFailsAfterReboot() { return getBoolean(R.string.settings_key_safe_mode, true); }

    public int getMaxFailedAttemptsForWipe() { return Integer.parseInt(getString(R.string.settings_key_max_failed_logins_for_wipe, "0")); }
    public int currentFailedAttempts() { return getInt(R.string.settings_key_failed_logins_count, 0); }
    public void addToCurrentFailedAttempts() {
        setInt(R.string.settings_key_failed_logins_count,
                getInt(R.string.settings_key_failed_logins_count, 0) + 1
        );
    }

    public void resetCurrentFailedAttempts() {
        setInt(R.string.settings_key_failed_logins_count, 0);
    }

    public boolean wipeExternalStorage() {
        return getBoolean(R.string.settings_key_wipe_external_storage, false);
    }
}

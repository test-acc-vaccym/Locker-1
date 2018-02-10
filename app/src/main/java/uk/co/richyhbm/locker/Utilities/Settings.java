package uk.co.richyhbm.locker.Utilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    private Context context;
    private SharedPreferences settings;

    public Settings(Context context) {
        this.context = context;
        this.settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private String getResString(int resId) { return context.getString(resId); }
    private int getResInt(int resId) { return context.getResources().getInteger(resId); }
    private boolean getBoolean(int keyId, boolean defaultValue) { return settings.getBoolean(getResString(keyId), defaultValue); }
    private int getInt(int keyId, int defaultId) { return settings.getInt(getResString(keyId), getResInt(defaultId)); }
    private int getIntValue(int keyId, int defaultValue) { return settings.getInt(getResString(keyId), defaultValue); }
    private long getLong(int keyId, long defaultValue) { return settings.getLong(getResString(keyId), defaultValue); }

    private void setBoolean(int keyId, boolean value) {
        settings.edit().putBoolean(getResString(keyId), value).apply();
    }

    private void setInt(int keyId, int value) {
        settings.edit().putInt(getResString(keyId), value).apply();
    }

    private void remove(int keyId) {
        settings.edit().remove(getResString(keyId)).apply();
    }
}

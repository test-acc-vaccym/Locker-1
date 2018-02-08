package uk.co.richyhbm.locker.Activities;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import uk.co.richyhbm.locker.Fragments.PreferencesFragment;
import uk.co.richyhbm.locker.R;
import uk.co.richyhbm.locker.Receivers.DeviceAdminReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new PreferencesFragment())
                .commit();

        /*

        checkBoxAdmin.clickListener(
            if checkBoxAdmin.isGettingDisabled()
            {
                DevicePolicyManager dpm = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
                ComponentName adminName = new ComponentName(this, DeviceAdminReceiver.class);

                if(dpm != null && dpm.isAdminActive(adminName))
                    dpm.removeActiveAdmin(adminName);
            }
        )

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_dropdown, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

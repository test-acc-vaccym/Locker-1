package uk.co.richyhbm.locker.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.richyhbm.locker.R;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(R.string.about);
    }
}

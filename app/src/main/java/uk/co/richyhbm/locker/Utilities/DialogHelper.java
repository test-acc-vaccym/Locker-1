package uk.co.richyhbm.locker.Utilities;


import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StringRes;


public class DialogHelper {
    public static void BasicDialog(Context context, @StringRes int message, final Runnable positive) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (onClick, i) -> {
                    if(positive != null)
                        positive.run();
                })
                .setNegativeButton(android.R.string.cancel, (onClick, i) -> {})
                .create()
                .show();
    }
}

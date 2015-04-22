package com.em.utils;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.em.AppController;

public class EmUtils {
    public static void showProgressDialog(ProgressDialog pDialog) {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    public static void hideProgressDialog(ProgressDialog pDialog) {
        if (pDialog != null && pDialog.isShowing())
            pDialog.hide();
    }

    public static void clearPreferences() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
    }
}

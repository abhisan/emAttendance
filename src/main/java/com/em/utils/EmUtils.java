package com.em.utils;

import android.app.ProgressDialog;

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

}

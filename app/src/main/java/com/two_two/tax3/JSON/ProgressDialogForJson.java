package com.two_two.tax3.JSON;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This will show LOADING bar while getting JSON.
 */
public class ProgressDialogForJson {
    private static ProgressDialog pDialog;

    public static void pDialogProgress(Context context){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading");
        pDialog.show();
    }
    public static void pDialogHide() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }

    }
}
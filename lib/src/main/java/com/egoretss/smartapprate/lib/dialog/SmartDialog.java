package com.egoretss.smartapprate.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by eGoretss on 18.06.2014.
 */
public class SmartDialog extends Dialog implements DialogInterface.OnClickListener {

    public SmartDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}

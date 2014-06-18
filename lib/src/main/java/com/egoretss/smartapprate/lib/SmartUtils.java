package com.egoretss.smartapprate.lib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by eGoretss on 18.06.2014.
 */
public class SmartUtils {

    public static void openPlayStore(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}

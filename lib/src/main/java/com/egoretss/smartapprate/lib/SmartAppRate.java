package com.egoretss.smartapprate.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import static com.egoretss.smartapprate.lib.rate.provider.AppConstants.USER_VIEW_COUNT;
import static com.egoretss.smartapprate.lib.rate.provider.AppConstants.VIEW_COUNT;

/**
 * Created by eGoretss on 18.06.2014.
 */
public class SmartAppRate {

    private static final String TAG = SmartAppRate.class.getCanonicalName();

    private SharedPreferences sharedPreferences;
    private SmartSetting smartSetting;
    private Context context;

    private SmartAppRate(Context context, SmartSetting smartSetting) {
        this.smartSetting = smartSetting;
        this.context = context;

        sharedPreferences = context.getSharedPreferences("SmartAppRate", Context.MODE_PRIVATE);

        init(smartSetting);

        if (decreaseOrUpdateViewCounterToShow()) {
            if (!smartSetting.getSmart()) {
                SmartUtils.openPlayStore(context);
            }
        }

    }

    private static SmartAppRate smartAppRate;

    public static SmartAppRate getInstance(Context context, SmartSetting smartSetting) {
        if (smartAppRate == null) {
            smartAppRate = new SmartAppRate(context, smartSetting);
        }
        return smartAppRate;
    }

    private void init(SmartSetting smartSetting) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (getOpenAppCount() == -1) { // first init
            editor.putInt(VIEW_COUNT, smartSetting.getViewCount());
            editor.putInt(USER_VIEW_COUNT, smartSetting.getViewCount());
        }

        int userViewCount = sharedPreferences.getInt(USER_VIEW_COUNT, -1);
        if (userViewCount != smartSetting.getViewCount()) { // update counter
            editor.putInt(VIEW_COUNT, smartSetting.getViewCount());
            editor.putInt(USER_VIEW_COUNT, smartSetting.getViewCount());
        }

        editOrApply(editor);
    }

    /**
     * if you want to reset countdown just call this method
    **/
    public void reset() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(VIEW_COUNT, smartSetting.getViewCount());
        editOrApply(editor);
    }

    private boolean decreaseOrUpdateViewCounterToShow() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int current = sharedPreferences.getInt(VIEW_COUNT, smartSetting.getViewCount());
        if (current <= 0) {
            editor.putInt(VIEW_COUNT, smartSetting.getViewCount());
            editOrApply(editor);
            return true;
        }
        editor.putInt(VIEW_COUNT, --current);
        editOrApply(editor);
        return false;
    }


    private Integer getOpenAppCount() {
        return sharedPreferences.getInt(VIEW_COUNT, -1);
    }

    private void editOrApply(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

}

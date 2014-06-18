package com.egoretss.smartapprate.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import static com.egoretss.smartapprate.lib.rate.provider.AppConstants.RATING_LEVEL;
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
    private AlertDialog.Builder dialog;

    private SmartAppRate(Context context, SmartSetting smartSetting) {
        this.smartSetting = smartSetting;
        this.context = context;

        sharedPreferences = context.getSharedPreferences("SmartAppRate", Context.MODE_PRIVATE);

        init(smartSetting);

        dialog = initSmartDialog();

        if (decreaseOrUpdateViewCounterToShow()) {

            dialog.show();

            if (!smartSetting.getSmart()) {
                SmartUtils.openPlayStore(context);
            }
        }

    }

    private AlertDialog.Builder initSmartDialog() {

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogLayout = inflater.inflate(R.layout.sample_rate_dialog, null);

        final RatingBar ratingBar = (RatingBar) dialogLayout.findViewById(R.id.ratingBar);
        final EditText commentView = (EditText) dialogLayout.findViewById(R.id.commentTextView);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogLayout)
            .setTitle(smartSetting.getDialogHeader())
            .setMessage(smartSetting.getDialogMessage());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float stars = ratingBar.getRating();
                if (stars >= RATING_LEVEL) {

                    commentView.setVisibility(View.INVISIBLE);
                } else {
                    commentView.setVisibility(View.VISIBLE);
                }
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                float stars = ratingBar.getRating();
                if (stars >= RATING_LEVEL) {
                    openStore();
                    // TODO sent message to google, mail or server
                }
            }
        });
        builder.setNegativeButton("Next time", null);

        return builder;
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

    public void show() {
        dialog.show();
    }
    public void openStore() {
        SmartUtils.openPlayStore(context);
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

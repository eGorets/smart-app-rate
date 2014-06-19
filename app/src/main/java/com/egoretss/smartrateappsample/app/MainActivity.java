package com.egoretss.smartrateappsample.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.egoretss.smartapprate.lib.SmartAppRate;
import com.egoretss.smartapprate.lib.SmartSetting;
import com.egoretss.smartapprate.lib.rate.provider.GoogleSheetNotifier;


public class MainActivity extends ActionBarActivity {

    private SmartAppRate smartAppRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartSetting smartSettingBuilder = new SmartSetting();
            smartSettingBuilder.setViewCount(1);
            smartSettingBuilder.setDialogHeader("Rate Us");
            smartSettingBuilder.setDialogMessage("Please rate!");
            smartSettingBuilder.setRateNotifier(new GoogleSheetNotifier(""));
        smartAppRate = SmartAppRate.getInstance(this, smartSettingBuilder);
    }


    public void showButton(View view) {
        smartAppRate.show();
    }

    public void resetButton(View view) {
        smartAppRate.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

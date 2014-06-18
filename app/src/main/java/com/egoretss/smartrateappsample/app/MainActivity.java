package com.egoretss.smartrateappsample.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.egoretss.smartapprate.lib.SmartAppRate;
import com.egoretss.smartapprate.lib.SmartSetting;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartSetting smartSettingBuilder = new SmartSetting();
            smartSettingBuilder.setViewCount(1);
            smartSettingBuilder.setDialogHeader("Rate Us");
            smartSettingBuilder.setDialogMessage("Please rate!");
        SmartAppRate.getInstance(this, smartSettingBuilder);
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

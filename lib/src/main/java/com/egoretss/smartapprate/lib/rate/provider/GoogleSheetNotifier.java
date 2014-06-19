package com.egoretss.smartapprate.lib.rate.provider;

/**
 * Created by eGoretss on 19.06.2014.
 */
public class GoogleSheetNotifier implements IRateNotifier {

    private String googleSheetKey;
    private String googleSheetUrl = "https://spreadsheets.google.com/feeds/list/%s/od6/public/values?alt=json-in-script&callback=";


    public GoogleSheetNotifier(String googleSheetKey) {
        this.googleSheetKey = googleSheetKey;
        googleSheetKey = String.format(googleSheetUrl, googleSheetKey);
    }

    @Override
    public boolean send(String message) {
        return false;
    }
}

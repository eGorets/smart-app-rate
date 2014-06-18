package com.egoretss.smartapprate.lib;

import com.egoretss.smartapprate.lib.rate.provider.AppConstants;

/**
 * Created by eGoretss on 18.06.2014.
 */
public class SmartSetting {

    private Integer viewCount = AppConstants.DEFAULT_COUNT;

    private Integer ratingLevel = AppConstants.RATING_LEVEL;

    private String dialogHeader;
    private Integer dialogHeaderId;

    private String dialogMessage;

    private String googleSheetUrl;

    private Boolean smart = true;

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setDialogHeader(String dialogHeader) {
        this.dialogHeader = dialogHeader;
    }

    public Integer getRatingLevel() {
        return ratingLevel;
    }

    public void setRatingLevel(Integer ratingLevel) {
        this.ratingLevel = ratingLevel;
    }

    public String getDialogHeader() {
        return dialogHeader;
    }

    public Integer getDialogHeaderId() {
        return dialogHeaderId;
    }

    public void setDialogHeaderId(Integer dialogHeaderId) {
        this.dialogHeaderId = dialogHeaderId;
    }

    public void setDialogHeader(Integer stringId) {
        this.dialogHeaderId = stringId;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    public Boolean getSmart() {
        return smart;
    }

    public void setSmart(Boolean smart) {
        this.smart = smart;
    }

    public String getGoogleSheetUrl() {
        return googleSheetUrl;
    }

    public void setGoogleSheetUrl(String googleSheetUrl) {
        this.googleSheetUrl = googleSheetUrl;
    }
}

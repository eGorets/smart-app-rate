package com.egoretss.smartapprate.lib;

import com.egoretss.smartapprate.lib.rate.provider.AppConstants;

/**
 * Created by eGoretss on 18.06.2014.
 */
public class SmartSetting {

    private Integer viewCount = AppConstants.DEFAULT_COUNT;

    private String dialogHeader;
    private Integer dialogHeaderId;

    private String dialogMessage;

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
}

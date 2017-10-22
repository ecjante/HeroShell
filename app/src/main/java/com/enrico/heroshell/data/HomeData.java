package com.enrico.heroshell.data;

/**
 * Created by enrico on 10/22/17.
 */

public class HomeData {
    String primaryString;
    String secondaryString;

    public HomeData(String primary, String secondary) {
        this.primaryString = primary;
        this.secondaryString = secondary;
    }

    public String getPrimaryString() {
        return primaryString;
    }

    public void setPrimaryString(String primaryString) {
        this.primaryString = primaryString;
    }

    public String getSecondaryString() {
        return secondaryString;
    }

    public void setSecondaryString(String secondaryString) {
        this.secondaryString = secondaryString;
    }
}

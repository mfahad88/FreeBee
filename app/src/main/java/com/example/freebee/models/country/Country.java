
package com.example.freebee.models.country;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    int countryCode;
    String countryName;
    String countryFlag;

    public Country(int countryCode, String countryName, String countryFlag) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.countryFlag = countryFlag;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode=" + countryCode +
                ", countryName='" + countryName + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                '}';
    }
}

package com.example.hjb.nickdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hjb on 2017/4/23.
 */

public class NKDeviceInfoModel implements Parcelable {
    String id;
    String deviceName;

    public NKDeviceInfoModel(){}

    protected NKDeviceInfoModel(Parcel in) {
        id = in.readString();
        deviceName = in.readString();
    }

    public static final Creator<NKDeviceInfoModel> CREATOR = new Creator<NKDeviceInfoModel>() {
        @Override
        public NKDeviceInfoModel createFromParcel(Parcel in) {
            return new NKDeviceInfoModel(in);
        }

        @Override
        public NKDeviceInfoModel[] newArray(int size) {
            return new NKDeviceInfoModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(deviceName);
    }
}

package com.example.hjb.nickdemo.nickfragment.picture;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yaoch on 2016/7/3.
 */
public class NKPhotoCategoryBean implements Parcelable {

    /**
     * location :
     * person : 2012-03-04
     * date :
     * scene :
     * photoes : []
     */

    private String location;
    private String person;
    private String date;
    private String uploader;
    private List<NKPhotoBean> photoes;

    protected NKPhotoCategoryBean(Parcel in) {
        location = in.readString();
        person = in.readString();
        date = in.readString();
        uploader = in.readString();
        photoes = in.createTypedArrayList(NKPhotoBean.CREATOR);
    }
    //额外添加
    protected NKPhotoCategoryBean() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(person);
        dest.writeString(date);
        dest.writeString(uploader);
        dest.writeTypedList(photoes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NKPhotoCategoryBean> CREATOR = new Creator<NKPhotoCategoryBean>() {
        @Override
        public NKPhotoCategoryBean createFromParcel(Parcel in) {
            return new NKPhotoCategoryBean(in);
        }

        @Override
        public NKPhotoCategoryBean[] newArray(int size) {
            return new NKPhotoCategoryBean[size];
        }
    };

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String scene) {
        this.uploader = scene;
    }

    public List<NKPhotoBean> getPhotoes() {
        return photoes;
    }

    public void setPhotoes(List<NKPhotoBean> photoes) {
        this.photoes = photoes;
    }
}

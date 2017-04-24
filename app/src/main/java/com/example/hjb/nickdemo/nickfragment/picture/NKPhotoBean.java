package com.example.hjb.nickdemo.nickfragment.picture;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:yaocl
 * Created on 2016/6/15.
 */
public class NKPhotoBean implements Parcelable{

    /**
     * mediaId : xxxxxxx
     * width : 33
     * hight : 44
     * photoURL : xxxxxxxx
     * fileSize : 13
     * thumbURL : xxxxxxx
     * uploader : xxxx
     * uploaderNickName : xxxxx
     * uploadTime : 443646456
     * description : xxxxxxx
     * attriPerson : xxxxx
     * attriLocation : xxxx
     * attriScene : xxxxx
     */

    private String mediaId;
    private String width;
    private String hight;
    private String photoURL;
    private String fileSize;
    private String thumbURL;
    private String uploader;
    private String uploaderNickName;
    private String uploadTime;
    private String description;
    private String attriPerson;
    private String attriLocation;
    private String attriScene;


    protected NKPhotoBean(Parcel in) {
        mediaId = in.readString();
        width = in.readString();
        hight = in.readString();
        photoURL = in.readString();
        fileSize = in.readString();
        thumbURL = in.readString();
        uploader = in.readString();
        uploaderNickName = in.readString();
        uploadTime = in.readString();
        description = in.readString();
        attriPerson = in.readString();
        attriLocation = in.readString();
        attriScene = in.readString();
    }
    //额外添加的尝试
    protected NKPhotoBean() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mediaId);
        dest.writeString(width);
        dest.writeString(hight);
        dest.writeString(photoURL);
        dest.writeString(fileSize);
        dest.writeString(thumbURL);
        dest.writeString(uploader);
        dest.writeString(uploaderNickName);
        dest.writeString(uploadTime);
        dest.writeString(description);
        dest.writeString(attriPerson);
        dest.writeString(attriLocation);
        dest.writeString(attriScene);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NKPhotoBean> CREATOR = new Creator<NKPhotoBean>() {
        @Override
        public NKPhotoBean createFromParcel(Parcel in) {
            return new NKPhotoBean(in);
        }

        @Override
        public NKPhotoBean[] newArray(int size) {
            return new NKPhotoBean[size];
        }
    };

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getUploaderNickName() {
        return uploaderNickName;
    }

    public void setUploaderNickName(String uploaderNickName) {
        this.uploaderNickName = uploaderNickName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttriPerson() {
        return attriPerson;
    }

    public void setAttriPerson(String attriPerson) {
        this.attriPerson = attriPerson;
    }

    public String getAttriLocation() {
        return attriLocation;
    }

    public void setAttriLocation(String attriLocation) {
        this.attriLocation = attriLocation;
    }

    public String getAttriScene() {
        return attriScene;
    }

    public void setAttriScene(String attriScene) {
        this.attriScene = attriScene;
    }
}

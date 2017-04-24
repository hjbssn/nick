package com.example.hjb.nickdemo.nickfragment.picture;

/**
 * Author:yaocl
 * Created on 2016/6/16.
 */
public class NKPhotoHeader {

    /**
     * id : xxxx
     * token : xxxx
     * deviceId : xxxxx
     * mediaId : xxxxxxxxxxxxx
     */

    private String id;
    private String token;
    private String deviceId;
    private String mediaId;

    public NKPhotoHeader() {
    }

    public NKPhotoHeader(String id, String token, String deviceId, String mediaId) {
        this.id = id;
        this.token = token;
        this.deviceId = deviceId;
        this.mediaId = mediaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}

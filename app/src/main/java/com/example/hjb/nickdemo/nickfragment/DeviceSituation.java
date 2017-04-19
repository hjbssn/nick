package com.example.hjb.nickdemo.nickfragment;

/**
 * Created by hjb on 2017/4/19.
 */

public class DeviceSituation {
    private int imageId;
    private boolean atLine;
    public DeviceSituation(int imageId,boolean atLine){
        this.atLine=atLine;
        this.imageId=imageId;
    }
    public int getImageId(){return imageId;}
    public boolean getAtLine(){return atLine;}
}

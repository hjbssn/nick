package com.example.hjb.nickdemo.consts;

/**
 * @author Muhx 2016/4/19
 */
public interface IIntentExtraConsts {
    /**
     * 用户
     */
    String USER_NAME = "userName";
    String USER_TOKEN = "userToken";
    String USER_EMAIL = "userEmail";

    String USER_GET_BACK_PASSWORD_MODEL = "userGetBackPasswordModel";
    String DEVICE_OBJECT = "device";

    String DEVICE_VIDEO_URL = "videoURL";
    String DEVICE_VIDEO_START_TIME = "startTime";
    String VIDEO_TYPE = "videoType";
    String DATE_FORMAT = "yyyy.M.dd";

    String DATE_YEAR = "yyyy";
    String DATE_MONTH = "M";
    String DATE_DAY = "dd";

    String DEVICE_ID = "deviceId";
    String DEVICE_NAME = "deviceName";
    String USER_ID = "userId";
    String ADMIN_ID = "adminId";




    /**
     * device manager
     */
    String DEVICE_NAME_TEXT = "deviceNameText";
    String DEVICE_NUMBER_TEXT = "deviceNumberText";
    String FIRST_ADD_ALARM_ID = "firstAddAlarm";
    String FINISH_ALARM_ID = "finish";
    String SELECTE_ALARM_SCENE = "selectedAlarmScene";
    String ALTER_ALARM = "alterAlarm";
    String RECORDED_VIDEO_MODEL = "recordedVideoModel";
    String ALARM_INFO_MODEL = "alarmInfoModel";
    String ZY_CID = "zyCID";
    String ZY_ACC = "zyAcc";
    String ZY_PW = "zyPw";
    String mediaURL = "mediaURL";


    String ACTION_ID = "actionId";
    String MQTT_MESSAGE = "mqttMessage";

    /**
     * 自定义推送消息的广播的Action和Category
     */
    String ACTION_NAME = "evounic.nick.action.PUSHNOTIFICATION";
    String CATEGORY_ALARM = "evounic.nick.category.ALARM";
    String CATEGORY_VALIDATION = "evounic.nick.category.VALIDATION";
    String CATEGORY_COLUMN = "evounic.nick.category.COLUMN";
    String CATEGORY_DEVICE = "evounic.nick.category.DEVICE";

    /**
     * 设备管理页面 获得的数据
     */
    String DEVICE_SETTINGS_MODEL = "deviceSettingsModel";
    String ITEM_NUM = "itemNum";
    String DEVICE_IS_ADMIN_FLAG = "isAdminFlag";
    String ITEMS = "items";
    String DEVICE_SETTINGS_IS_UPDATE = "isUpdate";
    String DEVICE_ALARM_SETTING_MODEL ="deviceAlarmSettingModel" ;
    String SELECT_POSITION = "selectPosition";
    String DEVICE_SETTINGS_ID = "deviceSettingsId";
    String CUSTOM_TIME_FlAG="customTimeFlag";
    String CUSTOM_TIME_STARTTIME = "customTimeStartTime";
    String CUSTOM_TIME_ENDTIME = "customTimeEndTime";
    String CUSTOM_TIME_REPEAT_LIST ="customTimeRepeatList" ;
    String CUSTOM_TIME_MODEL ="customTimeModel" ;

    /*照片*/
    String PHOTO = "photo";
    String CURRENTPHOTO_POSITION = "position";

    String DEVICE_NICKNAME ="deviceNickname" ;
    String DEVICE_FAVICON ="deviceFavicon" ;
    String FAVICON = "favicon";

    /*相册*/
    String ALBUM_ID  = "albumId";
    String IS_ALBUM  = "isAlbum";
    String MEDIA_ID  = "mediaId";

    String DEVICE_VIDEO_DURATION ="deviceVideoDuration" ;

    /*普通相册*/
    String NORMAL_ALBUM_DATA = "normalAlbumData";
    String NORMAL_ALBUM_DATA_MODIFY = "normalAlbumModify";
    String NORMAL_ALBUM_DATA_ADD = "normalAlbumAdd";
    String NORMAL_ALBUM_ALWAYS = "always";
    String NORMAL_ALBUM_CUSTOM = "custom";
    String DEVICE_INFO = "deviceInfo";
    String DEVICE_SECURITY_SETTINGS_BEAN ="deviceSecuritySettingBean" ;
    String SELECT_COVER ="selectCover" ;

    /*设备状态更改action*/
    String DEVICE_STATE_ACTION = "deviceStateChange";
    String DEVICE_HEAD_ICON ="deviceHeadIcon" ;
    String USER_HEAD_ICON ="userHeadIcon" ;

    String ZY_FILE_NAME = "zyFileName";
    String ZY_VIDEO_DURATION = "zyDuration";
    String ZY_FILE_CREATE_TIME = "zyCreateTime";
    String LOCAL_VIDEO_FILE_NAME = "localVideoFileName";
    String LOCAL_VIDEO_ICON_NAME = "localVideoIconName";
    String LOCAL_VIDEO_CREATE_TIME = "localVideoCreatedTime";
    String LOCAL_VIDEO_DURATION = "localVideoDuration";
    String LOCAL_VIDEO_FILTER_BEGIN_TIME = "localVideoFilterBeginTime";
    String LOCAL_VIDEO_FILTER_END_TIME = "localVideoFilterEndTime";
    String LOCAL_VIDEO_USER = "localVideoUser";
    String LOCAL_VIDEO_DEVICE_ID = "localVideoDeviceId";
}

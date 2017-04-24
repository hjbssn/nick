package com.example.hjb.nickdemo.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hjb.nickdemo.consts.ISharedPreferenceConsts;


/**
 * 用户相关的信息
 * @author Muhx 2016/4/19
 */
public class NKUserModel {

    private SharedPreferences sharedPreferences;

    private String userName;
    private String email;
    private String password;
    private String token;
    private String favicon;
    private String phoneNumber;
    private String nickName;
    private boolean login;

    public NKUserModel(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(ISharedPreferenceConsts.CATEGORY_USER, Context.MODE_PRIVATE);
        userName  = sharedPreferences.getString(ISharedPreferenceConsts.USER_NAME,null);
        password = sharedPreferences.getString(ISharedPreferenceConsts.USER_PASSWORD,null);
        token = sharedPreferences.getString(ISharedPreferenceConsts.USER_TOKEN,null);
        email = sharedPreferences.getString(ISharedPreferenceConsts.USER_EMAIL,null);
        favicon = sharedPreferences.getString(ISharedPreferenceConsts.USER_FAVICON,null);
        phoneNumber = sharedPreferences.getString(ISharedPreferenceConsts.USER_PHONENUMBER,null);
        nickName = sharedPreferences.getString(ISharedPreferenceConsts.USER_NICKNAME,null);
        login = sharedPreferences.getBoolean(ISharedPreferenceConsts.USER_LOGIN,false);
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getUserName(){
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_NAME,null);
    }

    public String getPassword(){
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_PASSWORD,null);
    }

    public String getToken(){
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_TOKEN,null);
    }

    public String getEmail(){
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_EMAIL,null);
    }

    public String getFavicon() {
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_FAVICON,null);
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_PHONENUMBER,null);
    }

    public String getNickName() {
        return sharedPreferences.getString(ISharedPreferenceConsts.USER_NICKNAME,null);
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(ISharedPreferenceConsts.USER_LOGIN,false);
    }

    public void save(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ISharedPreferenceConsts.USER_NAME,userName);
        editor.putString(ISharedPreferenceConsts.USER_PASSWORD,password);
        editor.putString(ISharedPreferenceConsts.USER_TOKEN,token);
        editor.putString(ISharedPreferenceConsts.USER_EMAIL,email);
        editor.putString(ISharedPreferenceConsts.USER_FAVICON,favicon);
        editor.putString(ISharedPreferenceConsts.USER_PHONENUMBER,phoneNumber);
        editor.putString(ISharedPreferenceConsts.USER_NICKNAME,nickName);
        editor.putBoolean(ISharedPreferenceConsts.USER_LOGIN,login);
        editor.apply();
    }

    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        userName = null;
        password = null;
        email = null;
        token = null;
        favicon = null;
        phoneNumber = null;
        nickName = null;
        login = false;
        editor.apply();
    }



}

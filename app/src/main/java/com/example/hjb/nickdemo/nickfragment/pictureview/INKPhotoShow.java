package com.example.hjb.nickdemo.nickfragment.pictureview;

import android.content.Context;


import com.example.hjb.nickdemo.nickfragment.picture.NKPhotoBean;

import java.util.List;

/**
 * Author:yaocl
 * Created on 2016/7/19.
 */
public interface INKPhotoShow {

    interface Presenter {
        void deletePhotos(String... mediaIds);

        void deleteNormalAlbumPhoto(List<String> mediaIds, String albumId);

        void downloadPhotos(String dowloadSuccess, String dowloadDefeat, NKPhotoBean... list);
    }

    interface View {
        Context getContext();

        void showPhotoPage();

        void showNoPhotoPage();

        void showDeleteResult();

        void showMessage(String message);
    }
}

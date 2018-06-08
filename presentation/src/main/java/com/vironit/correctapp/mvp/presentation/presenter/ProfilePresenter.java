package com.vironit.correctapp.mvp.presentation.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.ImageInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.image.ImageData;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.CustomView;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IProfileView;
import com.vironit.correctapp.utils.AppLog;

import java.io.File;

import javax.inject.Inject;

@InjectViewState
public class ProfilePresenter extends BaseAppPresenter<IProfileView> {

    @Inject
    ImageInteractor mImageInteractor;


    public ProfilePresenter() {
        App.getsAppComponent().inject(this);
    }

    public static final int GALLERY_REQUEST_CODE = 123;
    public static final int CAMERA_REQUEST_CODE = 321;

    public void startGallery(@NonNull Fragment fragment) {
        RxPermissions rxPermissions = new RxPermissions(fragment.getActivity());

        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        // Always true pre-M
                        // I can control the camera now - есть разрешения на камеру
                        chooseGallery(fragment);
                    } else {
                        // ups permission denied - нет разрешения на камеру
                    }
                });
    }

    public void chooseGallery(@NonNull Fragment fragment) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            fragment.startActivityForResult(intent, GALLERY_REQUEST_CODE);
        }
    }

    public void startCamera(@NonNull Fragment fragment) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //проверяет наличие такого Intent в телефоне
        // в данном случае ,если камера у пользователя

        if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            File photo = getCameraFile(fragment.getActivity());

            if (photo.exists()) {
                photo.delete();
            }
            Log.e("OOO", "File path = " + photo.getAbsolutePath());

            //для 8 андроида требуется такая запись
            //если как раньоше , то intent.putExtra(MediaStore.EXTRA_OUTPUT,photo);

            Uri uri = FileProvider.getUriForFile(fragment.getActivity(),
                    "com.vironit.correctapp.utils.FileProviderGallery", photo);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            fragment.startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }

    public File getCameraFile(@NonNull Activity activity) {
        File root = activity.getExternalFilesDir(null);
        if (root == null) {

            //приватная аудитория - то где в телеяоне хранится
            root = activity.getFilesDir();
        }
        File myDir = new File(root.getAbsoluteFile() + "/myDir");//     /Android/package - место сохранения - так принято
        if (!myDir.exists()) {
            myDir.mkdir();
        }

        return new File(myDir.getAbsoluteFile() + "/" + "image.jpeg"); //System.currentTimeMillis() - для уникального имени можно взяьб
    }

    public File getImageFromResult(Activity activity, int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case CAMERA_REQUEST_CODE: {

                File file = getCameraFile(activity);

                if (file.exists()) {
                    return file;
                } else {
                    return null;
                }
            }
            //+ case для галереи
            case GALLERY_REQUEST_CODE: {
                Uri uri = data.getData();

                //Cursor - это что-то вроде arraylist с Hashmap
                Cursor cursor = activity.getApplicationContext().getContentResolver()
                        .query(uri, new String[]{MediaStore.Images.Media.DATA},
                                null, null, null);
                if (cursor == null) {

                    return null;
                }

                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String file = cursor.getString(index);

                //проверить String file на ноль
                cursor.close();

                return new File(file);
            }
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
        if (resultCode == Activity.RESULT_OK) {
            File file = getImageFromResult(activity, requestCode, resultCode, data);
            if (file != null) {
                CustomView.isPictire = true;
                CustomView.d = Drawable.createFromPath(file.getPath());
                getViewState().setPhoto(file);
                sendRequest(file);
            }
        }
    }

    private void sendRequest(File file) {
        Log.e("PATH_Photo", file.getAbsolutePath());
        addLiteDisposable(mImageInteractor.uploadImage(file.getAbsolutePath())
                .observeOn(mUIScheduler)
                .doOnSuccess(this::parseCloudinaryResponse)
                .subscribe(list -> AppLog.logPresenter(this),
                        this));
    }

    private void parseCloudinaryResponse(ImageData response) {
        getViewState().setPhoto(new File(response.getUrl()));
    }
}

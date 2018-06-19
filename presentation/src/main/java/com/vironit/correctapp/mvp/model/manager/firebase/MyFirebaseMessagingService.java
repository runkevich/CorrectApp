package com.vironit.correctapp.mvp.model.manager.firebase;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vironit.correctapp.R;
import com.vironit.correctapp.constans.AppConstants;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private NotificationTarget mNotificationTarget;

    private NotificationCompat.Builder notificationBuilder;

    @Inject
    @Named(AppConstants.IO_SCHEDULER)
    Scheduler mIOScheduler;

    Bitmap resourcee;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String type = remoteMessage.getData().get("type");
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");
        String urlImage = remoteMessage.getData().get("img_url");

        switch (type) {
            case "photo": {

//                public Observable<Void> get(final String url) {
//                    return Observable.create(new Observable.OnSubscribe<Void>() {
//                        @Override
//                        public void call(final Subscriber<? super Void> subscriber) {
//                            Glide
//                                    .with(context)
//                                    .load(url)
//                                    .downloadOnly(2000, 2000);
//
//                            subscriber.onNext(null);
//                            subscriber.onCompleted();
//                        }
//                    });
//                }

//                Single.just(1)
//                        .delay(1000, TimeUnit.MILLISECONDS)
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribe((Integer as) ->{
//                            convertPhotoByGlide(urlImage,title,message);
//
//                        },throwable -> throwable.printStackTrace());


                Handler mainHandler = new Handler(Looper.getMainLooper());
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getApplicationContext())
                                .asBitmap()
                                .load(urlImage)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        buildNotification(resource, title, message);
                                    }
                                });
                    }
                };
                mainHandler.post(myRunnable);
              //  buildNotification(resourcee, title, message);
                break;
            }
            case "text": {
                notificationBuilder = new NotificationCompat.Builder(this, "1")
                        .setSmallIcon(R.drawable.ic_local_taxi_black_24dp)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setContentText(message)
                        .setContentTitle(title);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notificationBuilder.build());

                break;
            }
        }


    }

    private Bitmap convertPhotoByGlide(String urlImage, String title, String message) {

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(urlImage)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //resourcee = resource;
                        buildNotification(resource, title, message);
                    }
                });
        return resourcee;
    }

    public void buildNotification(Bitmap resource, CharSequence title, CharSequence message) {
        notificationBuilder = new NotificationCompat.Builder(this, "1")
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(resource))
                //.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), resource)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_local_taxi_black_24dp)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}

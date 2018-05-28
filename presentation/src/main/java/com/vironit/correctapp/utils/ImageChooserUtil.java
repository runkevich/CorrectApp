package com.vironit.correctapp.utils;

public abstract class ImageChooserUtil {

//   public static final int GALLERY_REQUEST_CODE = 123;
//   public static final int CAMERA_REQUEST_CODE = 321;
//
//
//    public static void startGallery(@Nullable Activity activity){
//        RxPermissions rxPermissions = new RxPermissions(activity);
//
//        rxPermissions
//                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe(granted -> {
//                    if (granted) { // Always true pre-M
//                        // I can control the camera now - есть разрешения на камеру
//                        chooseGallery(activity);
//                    } else {
//                        // Oups permission denied - нет разрешения на камеру
//                    }
//                });
//    }
//
//    public static void chooseGallery(@NonNull Activity activity){
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        if (intent.resolveActivity(activity.getPackageManager())!=null){
//            activity.startActivityForResult(intent,GALLERY_REQUEST_CODE);
//        }
//    }
//
//    public static void startCamera(Activity activity){
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        //проверяет наличие такого Intent у вас в телефоне
//        // в данном случае ,если камера у пользователя
//        if (intent.resolveActivity(activity.getPackageManager())!=null){
//
//            File photo = getCameraFile(activity);
//
//            if (photo.exists()){
//                photo.delete();
//            }
//
//            Log.e("OOO","File path = " + photo.getAbsolutePath());
//
//            //для 8 андроида требуется такая запись
//            // если как раньоше , то intent.putExtra(MediaStore.EXTRA_OUTPUT,photo);
//            Uri uri =  FileProvider.getUriForFile(activity,
//                    "com.vironit.correctapp.utils.FileProviderGallery",photo);
//
//            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//            activity.startActivityForResult(intent,CAMERA_REQUEST_CODE);
//
//        }
//
//    }
//
//    public static  File getCameraFile(@NonNull Activity activity){
//        File root = activity.getExternalFilesDir(null);
//        if (root == null){
//            //приватная аудитория - то где в телеяоне хранитс
//            root = activity.getFilesDir();
//        }
//
//        File myDir = new File(root.getAbsoluteFile() + "/myDir");//     /Android/package - место сохранения - так принято
//        if (!myDir.exists()){
//            myDir.mkdir();
//        }
//
//        return new File(myDir.getAbsoluteFile() +"/"+  "image.jpeg"); //System.currentTimeMillis() - для уникального имени можно взяьб
//    }
//
//    public static File getImageFromResult(Activity activity, int requestCode, int resultCode, Intent data){
//
//        switch (requestCode){
//
//            case CAMERA_REQUEST_CODE:{
//
//                File file = getCameraFile(activity);
//
//                if (file.exists()){
//                    return  file;
//                } else {
//                    return null;
//                }
//            }
//            //+ case для галереи
//            case GALLERY_REQUEST_CODE:{
//                Uri uri =  data.getData();
//                //Cursor - это что-то вроде arraylist с Hashmap
//                Cursor cursor = activity.getApplicationContext().getContentResolver()
//                        .query(uri,new String[]{MediaStore.Images.Media.DATA},
//                                null,null,null);
//                if (cursor == null){
//                    //здесь в крэшлитикс отправлять логи с ошибками
//                    return null;
//                }
//                //работает как курсор в вордовском документе
//                cursor.moveToFirst();
//                int index =  cursor.getColumnIndex(MediaStore.Images.Media.DATA);
//                String file = cursor.getString(index);
//                //проверить String file на ноль
//                cursor.close();
//                return new File(file);
//            }
//        }
//
//        return null;
//    }
}

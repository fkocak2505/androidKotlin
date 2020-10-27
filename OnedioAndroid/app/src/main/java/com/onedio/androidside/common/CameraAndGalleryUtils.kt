package com.onedio.androidside.common

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.onedio.androidside.BuildConfig
import java.text.SimpleDateFormat
import java.util.*


class CameraAndGalleryUtils {

    companion object {
        private lateinit var path: String
        val GALLERY_DIRECTORY_NAME = "Hello Camera"
        val MEDIA_TYPE_IMAGE = 1
        val MEDIA_TYPE_IMAGE_GALLERY = 2
        val IMAGE_EXTENSION = "jpg"
        val CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100
        val BITMAP_SAMPLE_SIZE = 8

        //image pick code
        val IMAGE_PICK_CODE = 1000;

        /**
         * Gallery
         */
        /////======/////======/////======/////======/////======/////======/////======/////======/////======/////========
        fun getNewBitMap(context: Context, uri: Uri?, imageView: ImageView): Bitmap {
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.getContentResolver().query(uri!!, filePathColumn, null, null, null)
            cursor?.moveToFirst()

            val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
            val picturePath = cursor?.getString(columnIndex!!)
            cursor?.close()

            val file = File(picturePath)


            imageView.invalidate()
            val drawable = imageView.drawable as BitmapDrawable
            val bitmap = drawable.bitmap

            path = file.absolutePath

            return modifyOrientation(
                bitmap,
                file.absolutePath
            )
        }

        @Throws(IOException::class)
        fun modifyOrientation(bitmap: Bitmap, image_absolute_path: String): Bitmap {
            val ei = ExifInterface(image_absolute_path)
            //val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> return rotate(
                    bitmap,
                    90f
                )

                ExifInterface.ORIENTATION_ROTATE_180 -> return rotate(
                    bitmap,
                    180f
                )

                ExifInterface.ORIENTATION_ROTATE_270 -> return rotate(
                    bitmap,
                    270f
                )

                ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> return flip(
                    bitmap,
                    true,
                    false
                )

                ExifInterface.ORIENTATION_FLIP_VERTICAL -> return flip(
                    bitmap,
                    false,
                    true
                )

                else -> return bitmap
            }
        }

        fun rotate(bitmap: Bitmap, degrees: Float): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(degrees)
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }

        fun flip(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap {
            val matrix = Matrix()
            matrix.preScale((if (horizontal) -1 else 1).toFloat(), (if (vertical) -1 else 1).toFloat())
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }

        fun getFileToByte(bitMap: Bitmap): String? {
            lateinit var newBitMap: Bitmap

            if (bitMap.byteCount > 1000000)
                newBitMap = Bitmap.createScaledBitmap(
                    bitMap,
                    (bitMap.getWidth() * 0.1).toInt(),
                    (bitMap.getHeight() * 0.1).toInt(),
                    true
                )
            else
                newBitMap = bitMap

            val byteArrayOutputStream = ByteArrayOutputStream()
            newBitMap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()

            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }

        fun checkPermissions4Gallery(context: Context): Boolean {
            return (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) === PackageManager.PERMISSION_GRANTED)
        }

        /**
         * Take Picture With Camera
         */
        /////======/////======/////======/////======/////======/////======/////======/////======/////======/////========
        fun checkPermissions4Camera(context: Context): Boolean {
            return (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) === PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) === PackageManager.PERMISSION_GRANTED)
        }

        /**
         * Creates and returns the image or video file before opening the camera
         */
        fun getOutputMediaFile(type: Int): File? {

            // External sdcard location
            val mediaStorageDir = File(
                Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                GALLERY_DIRECTORY_NAME
            )

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.e(
                        GALLERY_DIRECTORY_NAME, "Oops! Failed create "
                                + GALLERY_DIRECTORY_NAME + " directory"
                    )
                    return null
                }
            }

            // Preparing media file naming convention
            // adds timestamp
            val timeStamp = SimpleDateFormat(
                "yyyyMMdd_HHmmss",
                Locale.getDefault()
            ).format(Date())
            val mediaFile: File
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = File(
                    mediaStorageDir.path + File.separator
                            + "IMG_" + timeStamp + "." + IMAGE_EXTENSION
                )
            } else {
                return null
            }

            return mediaFile
        }


        fun getOutputMediaFileUri(context: Context, file: File): Uri {
            return FileProvider.getUriForFile(context, context.packageName + ".provider", file)
        }

        /**
         * Downsizing the bitmap to avoid OutOfMemory exceptions
         */
        fun optimizeBitmap(sampleSize: Int, filePath: String): Bitmap {
            // bitmap factory
            val options = BitmapFactory.Options()

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = sampleSize

            return BitmapFactory.decodeFile(filePath, options)
        }

        /**
         * Open device app settings to allow user to enable permissions
         */
        fun openSettings(context: Context) {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        /**
         * Refreshes gallery on adding new image/video. Gallery won't be refreshed
         * on older devices until device is rebooted
         */
        fun refreshGallery(context: Context, filePath: String) {
            // ScanFile so it will be appeared on Gallery
            MediaScannerConnection.scanFile(
                context,
                arrayOf(filePath), null
            ) { path, uri -> }
        }

    }
}
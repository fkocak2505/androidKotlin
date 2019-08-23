package tr.com.android.selectimagefromgallery

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var imageStoragePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //BUTTON CLICK
        img_pick_btn.setOnClickListener {
            if (CameraUtils.checkPermissions4Gallery(applicationContext))
                pickImageFromGallery()
            else
                requestGalleryPermission(CameraUtils.MEDIA_TYPE_IMAGE_GALLERY)
        }

        img_capture_btn.setOnClickListener() {
            if (CameraUtils.checkPermissions(applicationContext))
                captureImage()
            else
                requestCameraPermission(CameraUtils.MEDIA_TYPE_IMAGE)
        }
    }

    private fun requestGalleryPermission(type: Int) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                        if (type == CameraUtils.MEDIA_TYPE_IMAGE_GALLERY) {
                            // capture picture
                            pickImageFromGallery()
                        }

                    } else if (report.isAnyPermissionPermanentlyDenied) {
                        showPermissionsAlert()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    /**
     * Requesting permissions using Dexter library
     */
    private fun requestCameraPermission(type: Int) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                        if (type == CameraUtils.MEDIA_TYPE_IMAGE) {
                            // capture picture
                            captureImage()
                        }

                    } else if (report.isAnyPermissionPermanentlyDenied) {
                        showPermissionsAlert()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }


    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Common.IMAGE_PICK_CODE)
    }

    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private fun captureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file = CameraUtils.getOutputMediaFile(CameraUtils.MEDIA_TYPE_IMAGE)
        if (file != null) {
            imageStoragePath = file!!.getAbsolutePath()
        }
        val fileUri = CameraUtils.getOutputMediaFileUri(applicationContext, file!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        // start the image capture Intent
        startActivityForResult(intent, CameraUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
    }

    /**
     * Display image from gallery
     */
    private fun previewCapturedImage() {
        try {
            val bitmap = CameraUtils.optimizeBitmap(CameraUtils.BITMAP_SAMPLE_SIZE, imageStoragePath)
            val newBitmap = CameraUtils.modifyOrientation(bitmap, imageStoragePath)
            image_view.setImageBitmap(newBitmap)

        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    private fun previewSelectedImageFromGallery(data: Intent?) {
        try {
            val uri: Uri? = data?.data
            image_view.setImageURI(data?.data)
            val bitmap = CameraUtils.getNewBitMap(applicationContext, uri, image_view)
            image_view.setImageBitmap(bitmap)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }


    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Common.IMAGE_PICK_CODE) {
                previewSelectedImageFromGallery(data)
            } else if (requestCode == CameraUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(applicationContext, imageStoragePath)

                // successfully captured the image
                // display it in image view
                previewCapturedImage()
            }
        } else if (resultCode == RESULT_CANCELED) {
            if(requestCode == Common.IMAGE_PICK_CODE){
                // user cancelled Image capture
                Toast.makeText(
                    applicationContext,
                    "User cancelled selected image", Toast.LENGTH_SHORT
                )
                    .show()
            } else if(requestCode == CameraUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE){
                Toast.makeText(
                    applicationContext,
                    "User cancelled image capture", Toast.LENGTH_SHORT
                )
                    .show()
            }

        } else {
            // failed to capture image
            Toast.makeText(
                applicationContext,
                "Sorry! Failed to capture image", Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    /**
     * Alert dialog to navigate to app settings
     * to enable necessary permissions
     */
    private fun showPermissionsAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permissions required!")
            .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
            .setPositiveButton("GOTO SETTINGS",
                DialogInterface.OnClickListener { dialog, which -> CameraUtils.openSettings(this@MainActivity) })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, which -> }).show()
    }
}

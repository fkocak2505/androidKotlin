package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.profileSetting

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.github.florent37.viewtooltip.ViewTooltip
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.onedio.androidside.R
import com.onedio.androidside.common.CameraAndGalleryUtils
import com.onedio.androidside.common.OnedioCommon
import com.onedio.androidside.common.OnedioSharedPrefs
import com.onedio.androidside.common.model.OnedioEzDialogMessageModel4Activity
import com.onedio.androidside.constant.OnedioConstant
import com.onedio.androidside.enumaration.OnedioEnum
import com.onedio.androidside.mvp.model.Response4ErrorObj
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.UserProfileInfoDetailActivityModelImpl
import com.onedio.androidside.mvp.model.dashboard.profileDetail.requestModel.UpdateUserProfileInfoParams
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfilePhoto
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.CountryData
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.Response4CountryList
import com.onedio.androidside.mvp.presenter.dashboard.profileDetail.IUserProfileInfoDetailActivityPresenter
import com.onedio.androidside.mvp.presenter.dashboard.profileDetail.UserProfileInfoDetailActivityPresenterImpl
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_setting.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class UserProfileInfoDetailActivityViewImpl : AppCompatActivity(),
    IUserProfileInfoDetailActivityView {

    private lateinit var toolBar: Toolbar

    private var startDate: Calendar? = null
    private var dateFormatLong: DateFormat? = null
    private var formattedBirthDay: String? = null

    private lateinit var imageStoragePath: String

    private lateinit var userProfileInfoDetailActivityPresenter: IUserProfileInfoDetailActivityPresenter

    var mListOfCountr: MutableList<CountryData> = mutableListOf()

    private var theme: String = ""

    //==================================================================================================================
    /**
     * Activity onCreate Func.
     */
    //==================================================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile_setting)

        val sharedPref =
            getSharedPreferences(OnedioConstant.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        theme = sharedPref.getString("mode", "default")!!

        prepareToolbar()

        initProfileSettingActivityComponents()

        changeIconIfDarkModeOn()

        changeComponentTypeface()

        initDatePicker()

        clickUpdateUserProfileInfo()

        clickProfilePic()

        //goBack()

        clickEmptyArea()

        scrollEditText()

    }

    private fun prepareToolbar() {
        toolBar = toolBar4ProfileUpdate as Toolbar

        toolBar.title = "Profil"
        toolBar.setTitleTextAppearance(this, R.style.MuliSemiboldTextAppearance)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        toolBar.setOnClickListener {

        }

        if (theme == "dark") {
            toolBar.setTitleTextColor(Color.parseColor("#FFFFFF"))
            toolBar.setNavigationIcon(R.drawable.ic_back_dark_mode)
            profilePic.foreground = ContextCompat.getDrawable(applicationContext, R.drawable.profile_image_view_shape_dark_mode)
        } else {
            toolBar.setTitleTextColor(Color.parseColor("#191919"))
            toolBar.setNavigationIcon(R.drawable.ic_back)
            profilePic.foreground = ContextCompat.getDrawable(applicationContext, R.drawable.profile_image_view_shape)
        }
    }

    private fun getUserProfileData() {
        userProfileInfoDetailActivityPresenter.getUserProfileData("me")
    }

    override fun handleUserProfileData(response4UsersProfile: Response4UsersProfile) {
        fillUserInfoProfileData(response4UsersProfile)
    }

    //==================================================================================================================
    /**
     * Init User Profile Detail Activity Component
     */
    //==================================================================================================================
    override fun initProfileSettingActivityComponents() {
        //supportActionBar?.hide()

        window?.decorView?.clearFocus()

        edtOfTouchListener()

        userProfileInfoDetailActivityPresenter =
            UserProfileInfoDetailActivityPresenterImpl(
                UserProfileInfoDetailActivityModelImpl(),
                this
            )

        //navMenuProfileSetting.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val listOfGender =
            arrayOf(
                OnedioEnum.MALE.valOfGender,
                OnedioEnum.FEMALE.valOfGender,
                OnedioEnum.UNKNOWN.valOfGender
            )
        val spinnerAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, listOfGender)
        spinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        genderSelection.adapter = spinnerAdapter

        changeNavMenuIcon()

        getCountryList()

        //fillUserInfoProfileData()


    }

    //==================================================================================================================
    /**
     * Change icon if dark mode on...
     */
    //==================================================================================================================
    override fun changeIconIfDarkModeOn() {
        /*if (OnedioSharedPrefs(applicationContext).isNightModeEnabled()) {
            fabProfileSetting.setImageResource(R.drawable.ic_onedio_icon_dark_mode)
            updateProfile.setImageResource(R.drawable.ic_click)
        } else {
            fabProfileSetting.setImageResource(R.drawable.ic_onedio_icon)
            updateProfile.setImageResource(R.drawable.ic_click)
        }*/
    }

    //==================================================================================================================
    /**
     * Change Component Typeface...
     */
    //==================================================================================================================
    override fun changeComponentTypeface() {
        //toolbarTitleProfileSetting.typeface =
        OnedioCommon.changeTypeFace4Activity(applicationContext)
        //updateProfile2.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUsername.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        extUserName.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUserEmail.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        extUserEmail.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUserAbout.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        extUserAbout.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUserNameAndSurname.typeface =
            OnedioCommon.changeTypeFace4Activity(applicationContext)
        extUserNameAndSurname.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUserGender.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtUserBirthday.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        extUserBirthday.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUserCity.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        extCity.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)
        txtInfoUserCountry.typeface = OnedioCommon.changeTypeFace4Activity(applicationContext)


    }

    //==================================================================================================================
    /**
     * Get Country List...
     */
    //==================================================================================================================
    override fun getCountryList() {
        userProfileInfoDetailActivityPresenter.getCountries()
    }

    //==================================================================================================================
    /**
     * Init DatePicker for Birthday Component
     */
    //==================================================================================================================
    override fun initDatePicker() {

        startDate = Calendar.getInstance()
        val locale = resources.configuration.locale
        dateFormatLong = SimpleDateFormat("yyyy-MM-dd", locale)  // Sun Dec 31, 2017


        txtUserBirthday.setOnClickListener {
            val dialog = DatePickerDialog(
                this@UserProfileInfoDetailActivityViewImpl,
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    startDate?.set(year, month, day)
                    formattedBirthDay = dateFormatLong?.format(startDate?.timeInMillis)!!
                    extUserBirthday.setText(
                        changeUserBirthday2Local(
                            dateFormatLong?.format(
                                startDate?.timeInMillis
                            )!!
                        )
                    )
                },
                startDate!!.get(Calendar.YEAR),
                startDate!!.get(Calendar.MONTH),
                startDate!!.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

        extUserBirthday.setOnClickListener {
            val dialog = DatePickerDialog(
                this@UserProfileInfoDetailActivityViewImpl,
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    startDate?.set(year, month, day)
                    formattedBirthDay = dateFormatLong?.format(startDate?.timeInMillis)!!
                    extUserBirthday.setText(
                        changeUserBirthday2Local(
                            dateFormatLong?.format(
                                startDate?.timeInMillis
                            )!!
                        )
                    )
                },
                startDate!!.get(Calendar.YEAR),
                startDate!!.get(Calendar.MONTH),
                startDate!!.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.maxDate = System.currentTimeMillis()
            dialog.show()
        }
    }

    //==================================================================================================================
    /**
     * Fill UserProfile Info Data..
     */
    //==================================================================================================================
    private fun fillUserInfoProfileData(response4ProfileInfoData: Response4UsersProfile) {
        //val response4ProfileInfoData = Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UserProfileInfo::class.java)
        //val response4ProfileInfoData = Gson().fromJson(OnedioSingletonPattern.instance.getUserProfileInfoData(), Response4UsersProfile::class.java)

        /*if (!response4ProfileInfoData.valOfUserProfileInfoData?.avatar!!.take(
                1
            ).equals(
                "/"
            )
        )
            Picasso.get().load(response4ProfileInfoData.valOfUserProfileInfoData?.avatar)
                .into(profilePic)*/

        response4ProfileInfoData.data?.image?.let {
            Picasso.get()
                .load(response4ProfileInfoData.data.image.alternates?.standardResolution?.url)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(profilePic, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception?) {

                    }
                })
        } ?: run {

        }

        val userName = response4ProfileInfoData
            .data?.username
        val email =
            response4ProfileInfoData.data?.email
        val title =
            response4ProfileInfoData.data?.title
        val name =
            response4ProfileInfoData.data?.name
        val gender =
            response4ProfileInfoData.data?.gender
        val city =
            response4ProfileInfoData.data?.city
        /*val country = response4ProfileInfoData
            .data?.country*/
        val country = "Türkiye"
        val birthdate = response4ProfileInfoData
            .data?.birthdate

        if (OnedioCommon.nullChecker(userName))
            extUserName.setText(
                userName!!
            )

        if (OnedioCommon.nullChecker(email))
            extUserEmail.setText(email)

        if (OnedioCommon.nullChecker(title))
            extUserAbout.setText(title)

        if (OnedioCommon.nullChecker(name))
            extUserNameAndSurname.setText(name)

        when (gender) {
            "unknown" -> genderSelection.post { genderSelection.setSelection(2) }
            "male" -> genderSelection.post { genderSelection.setSelection(0) }
            else -> genderSelection.post { genderSelection.setSelection(1) }
        }


        if (OnedioCommon.nullChecker(city))
            extCity.setText(city)

        if (OnedioCommon.nullChecker(country)) {
            extCountry.post {
                extCountry.setSelection(mListOfCountr.indexOfFirst {
                    it.name.equals(
                        country
                    )
                })
            }
        }



        if (birthdate != null && !birthdate.contains("1970-01-01")) {
            if (OnedioCommon.nullChecker(
                    changeUserBirthday2Local(
                        response4ProfileInfoData.data.birthdate.substring(
                            0,
                            10
                        )
                    )
                )
            )
                extUserBirthday.setText(
                    changeUserBirthday2Local(
                        response4ProfileInfoData.data.birthdate.substring(
                            0,
                            10
                        )
                    )
                )
        } else
        //extUserBirthday.setText(changeUserBirthday2Local("1970-01-01"))
            extUserBirthday.setText("")
    }

    //==================================================================================================================
    /**
     * Change User Birthday 2 Location...
     */
    //==================================================================================================================
    override fun changeUserBirthday2Local(birthDay: String): String {

        formattedBirthDay = birthDay

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var date: Date? = null
        try {
            date = sdf.parse(birthDay)
        } catch (ex: ParseException) {
            Toast.makeText(applicationContext, "Date Parse Error..", Toast.LENGTH_SHORT).show()
        }

        val dateFormat: DateFormat =
            android.text.format.DateFormat.getDateFormat(applicationContext)

        return dateFormat.format(date!!)
    }

    //==================================================================================================================
    /**
     * UpdateUserInfo Data and Trigger Presenter..
     */
    //==================================================================================================================
    override fun updateUserProfileInfoData() {
        val userName = extUserName.text.toString()
        val userEmail = extUserEmail.text.toString()
        val userAbout = extUserAbout.text.toString()
        val userNameAndSurname = extUserNameAndSurname.text.toString()
        val genderText = when (genderSelection.selectedItemPosition) {
            0 -> "male"
            1 -> "female"
            else -> "unknown"
        }

        //val birthDay = extUserBirthday.text.toString()
        val birthDay = if (formattedBirthDay != null) formattedBirthDay!! else "1970-01-01"
        val city = extCity.text.toString()
        //val country = extCountry.text.toString()
        //val country = mListOfCountr[extCountry.selectedItemPosition].name!!
        val country = "Türkiye"


        val updateUserProfileInfoParams =
            UpdateUserProfileInfoParams()
        if (OnedioCommon.stringCheck(userName)) updateUserProfileInfoParams.username =
            userName.trim()
        if (OnedioCommon.stringCheck(userEmail)) updateUserProfileInfoParams.email =
            userEmail.trim()
        if (OnedioCommon.stringCheck(userNameAndSurname)) updateUserProfileInfoParams.name =
            userNameAndSurname.trim()
        if (OnedioCommon.stringCheck(genderText)) updateUserProfileInfoParams.gender =
            genderText.trim()
        if (OnedioCommon.stringCheck(birthDay)) updateUserProfileInfoParams.birthdate =
            birthDay.trim()
        if (OnedioCommon.stringCheck(city)) updateUserProfileInfoParams.city = city.trim()
        if (OnedioCommon.stringCheck(country)) updateUserProfileInfoParams.country = country.trim()
        if (OnedioCommon.stringCheck(userAbout)) updateUserProfileInfoParams.title =
            userAbout.trim()

        if (userName.trim() != "") {
            if (userEmail.trim() != "")
                if (OnedioCommon.isEmailValid(userEmail))
                    userProfileInfoDetailActivityPresenter.updateUserProfileInfoDataNew(
                        updateUserProfileInfoParams
                    )
                else {
                    ViewTooltip
                        .on(this, extUserEmail)
                        .autoHide(true, 3000)
                        .corner(100)
                        .color(Color.parseColor("#FFFFFF"))
                        .textColor(Color.parseColor("#fc3937"))
                        .position(ViewTooltip.Position.TOP)
                        .text("Geçerli bir mail adresi giriniz..")
                        .show()
                }
            else {
                ViewTooltip
                    .on(this, extUserEmail)
                    .autoHide(true, 3000)
                    .corner(100)
                    .color(Color.parseColor("#FFFFFF"))
                    .textColor(Color.parseColor("#fc3937"))
                    .position(ViewTooltip.Position.TOP)
                    .text("Mail Adresi Boş olamaz")
                    .show()
            }
        } else {
            ViewTooltip
                .on(this, extUserName)
                .autoHide(true, 3000)
                .corner(100)
                .color(Color.parseColor("#FFFFFF"))
                .textColor(Color.parseColor("#fc3937"))
                .position(ViewTooltip.Position.TOP)
                .text("Kullanıcı Adı boş olamaz")
                .show()
        }
    }

    //==================================================================================================================
    /**
     * Click Update User Profile Info...
     */
    //==================================================================================================================
    override fun clickUpdateUserProfileInfo() {
        updateProfile.setOnClickListener {
            updateUserProfileInfoData()
        }

        /*updateProfile2.setOnClickListener() {
            updateUserProfileInfoData()
        }*/
    }

    //==================================================================================================================
    /**
     * Click Profile Pic...
     */
    //==================================================================================================================
    override fun clickProfilePic() {
        profilePic.setOnClickListener {
            /*EZDialog.Builder(this)
                .setTitle("Profil Fotoğrafını Değiştir")
                .setMessage("Profil fotoğrafını değiştirmek için lütfen aşağıdaki seçeneklerden birini seçiniz")
                .setPositiveBtnText("Galeri")
                .setNegativeBtnText("Kamera")
                .setHeaderColor(resources.getColor(R.color.tabIndicatorColor4Proile))
                .setTitleTextColor(resources.getColor(R.color.constWhite))
                .setMessageTextColor(resources.getColor(R.color.constTextColor))
                .setButtonTextColor(resources.getColor(R.color.constTextColor))
                .setCancelableOnTouchOutside(true)
                .OnPositiveClicked {
                    if (CameraAndGalleryUtils.checkPermissions4Gallery(applicationContext))
                        pickImageFromGallery()
                    else
                        requestGalleryPermission(CameraAndGalleryUtils.MEDIA_TYPE_IMAGE_GALLERY)
                }
                .OnNegativeClicked {
                    if (CameraAndGalleryUtils.checkPermissions4Camera(applicationContext))
                        captureImage()
                    else
                        requestCameraPermission(CameraAndGalleryUtils.MEDIA_TYPE_IMAGE)
                }
                .build()*/
        }
    }

    //==================================================================================================================
    /**
     * Request Gallery Permission...
     */
    //==================================================================================================================
    private fun requestGalleryPermission(type: Int) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                        if (type == CameraAndGalleryUtils.MEDIA_TYPE_IMAGE_GALLERY) {
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

    //==================================================================================================================
    /**
     * Request Camera Permission...
     */
    //==================================================================================================================
    private fun requestCameraPermission(type: Int) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                        if (type == CameraAndGalleryUtils.MEDIA_TYPE_IMAGE) {
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

    //==================================================================================================================
    /**
     * Pick Image From Gallery...
     */
    //==================================================================================================================
    override fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, CameraAndGalleryUtils.IMAGE_PICK_CODE)
    }

    //==================================================================================================================
    /**
     * Capture Image...
     */
    //==================================================================================================================
    private fun captureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file = CameraAndGalleryUtils.getOutputMediaFile(CameraAndGalleryUtils.MEDIA_TYPE_IMAGE)
        if (file != null) {
            imageStoragePath = file.absolutePath
        }
        val fileUri = CameraAndGalleryUtils.getOutputMediaFileUri(applicationContext, file!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        // start the image capture Intent
        startActivityForResult(intent, CameraAndGalleryUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
    }

    //==================================================================================================================
    /**
     * Preview Captured Image...
     */
    //==================================================================================================================
    private fun previewCapturedImage() {
        try {
            val bitmap =
                CameraAndGalleryUtils.optimizeBitmap(
                    CameraAndGalleryUtils.BITMAP_SAMPLE_SIZE,
                    imageStoragePath
                )
            val newBitmap = CameraAndGalleryUtils.modifyOrientation(bitmap, imageStoragePath)
            profilePic.setImageBitmap(newBitmap)

            var imageBase64String = CameraAndGalleryUtils.getFileToByte(newBitmap)
            imageBase64String = imageBase64String?.replace("\n", "")

            userProfileInfoDetailActivityPresenter.updateUserProfilePhoto(imageBase64String!!)

        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    //==================================================================================================================
    /**
     * Preview Selected Image From Gallery...
     */
    //==================================================================================================================
    private fun previewSelectedImageFromGallery(data: Intent?) {
        try {
            val uri: Uri? = data?.data
            profilePic.setImageURI(data?.data)
            val bitmap = CameraAndGalleryUtils.getNewBitMap(applicationContext, uri, profilePic)
            profilePic.setImageBitmap(bitmap)

            var imageBase64String = CameraAndGalleryUtils.getFileToByte(bitmap)
            imageBase64String = imageBase64String?.replace("\n", "")

            userProfileInfoDetailActivityPresenter.updateUserProfilePhoto(imageBase64String!!)


        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    //==================================================================================================================
    /**
     * onActivity Result...
     */
    //==================================================================================================================
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CameraAndGalleryUtils.IMAGE_PICK_CODE) {

                previewSelectedImageFromGallery(data)

            } else if (requestCode == CameraAndGalleryUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

                CameraAndGalleryUtils.refreshGallery(applicationContext, imageStoragePath)
                previewCapturedImage()

            }
        } else if (resultCode == RESULT_CANCELED) {
            if (requestCode == CameraAndGalleryUtils.IMAGE_PICK_CODE) {
                // user cancelled Image capture
                Toast.makeText(
                    applicationContext,
                    "User cancelled selected image", Toast.LENGTH_SHORT
                )
                    .show()
            } else if (requestCode == CameraAndGalleryUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
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

    //==================================================================================================================
    /**
     * Handle Update User Profile Info...
     */
    //==================================================================================================================
    override fun handleUpdateUserProfileInfo(response4UpdateUserProfileInfo: Response4UpdateUserProfileInfo) {

        response4UpdateUserProfileInfo.message?.let {
            showEzDialogMessage(
                true,
                "Başarılı",
                "Kullanıcı profili başarıyla güncellendi",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        } ?: run {
            showEzDialogMessage(
                true,
                "Hata..!",
                response4UpdateUserProfileInfo.status?.message!!,
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        }
    }

    //==================================================================================================================
    /**
     * Handle Update User Profile Photo Info...
     */
    //==================================================================================================================
    override fun handleUpdateUserProfilePhotoInfo(response4UpdateUserProfilePhoto: Response4UpdateUserProfilePhoto) {
        if (response4UpdateUserProfilePhoto.status?.code == 200) {
            showEzDialogMessage(
                false,
                "Başarılı",
                "Profil fotoğrafınız başarıyla değiştirilmiştir.",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
        } else
            showEzDialogMessage(
                true,
                "Başarısız..!",
                "Profil fotoğrafınız güncellenirken bir hata oluştu",
                "Tamam",
                ContextCompat.getColor(
                    applicationContext,
                    R.color.tabIndicatorColor4Proile
                ),
                ContextCompat.getColor(applicationContext, R.color.constWhite),
                ContextCompat.getColor(applicationContext, R.color.constTextColor),
                ContextCompat.getColor(applicationContext, R.color.constTextColor)
            )
    }

    //==================================================================================================================
    /**
     * Handle Country List Data...
     */
    //==================================================================================================================
    override fun handleCountryListData(response4CountryList: Response4CountryList) {
        mListOfCountr = response4CountryList.data!!

        val listOfCountryData: MutableList<String> = mutableListOf()
        for (itemOfCountryData in mListOfCountr)
            listOfCountryData.add(itemOfCountryData.name!!)


        val spinnerAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, listOfCountryData)
        spinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        extCountry.adapter = spinnerAdapter

        //fillUserInfoProfileData()

        getUserProfileData()
    }

    //==================================================================================================================
    /**
     * Show Permission Alert...
     */
    //==================================================================================================================
    private fun showPermissionsAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permissions required!")
            .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
            .setPositiveButton(
                "GOTO SETTINGS"
            ) { _, _ ->
                CameraAndGalleryUtils.openSettings(
                    this@UserProfileInfoDetailActivityViewImpl
                )
            }
            .setNegativeButton("CANCEL") { _, _ -> }
            .show()
    }

    //==================================================================================================================
    /**
     * Bottom Navigation Menu Item listener..
     */
    //==================================================================================================================
    /*private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {

                    OnedioSingletonPattern.instance.setTabIndex(0)
                    startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))

                }
                R.id.navigation_notification -> {

                    OnedioSingletonPattern.instance.setTabIndex(1)
                    startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))

                }
                R.id.i_empty -> {

                    OnedioSingletonPattern.instance.setTabIndex(2)
                    startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))

                }
                R.id.navigation_message -> {

                    OnedioSingletonPattern.instance.setTabIndex(3)
                    startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))

                }
                R.id.navigation_profile -> {

                    OnedioSingletonPattern.instance.setTabIndex(4)
                    startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))
                }


            }
            false
        }*/

    //==================================================================================================================
    /**
     * Change Nav Menu Icon...
     */
    //==================================================================================================================
    override fun changeNavMenuIcon() {
        /*if (checkIconIfDarkModeOn()) {
            navMenuProfileSetting.menu.getItem(0).setIcon(R.drawable.ic_nav_home_dark_mode)
            navMenuProfileSetting.menu.getItem(1).setIcon(R.drawable.ic_nav_trending_dark_mode)
            navMenuProfileSetting.menu.getItem(3).setIcon(R.drawable.ic_nav_category_dark_mode)
            navMenuProfileSetting.menu.getItem(4).setIcon(R.drawable.ic_nav_search_dark_mode)
        } else {
            navMenuProfileSetting.menu.getItem(0).setIcon(R.drawable.ic_nav_home)
            navMenuProfileSetting.menu.getItem(1).setIcon(R.drawable.ic_nav_trending)
            navMenuProfileSetting.menu.getItem(3).setIcon(R.drawable.ic_nav_category)
            navMenuProfileSetting.menu.getItem(4).setIcon(R.drawable.ic_nav_search)
        }*/
    }

    //==================================================================================================================
    /**
     * Check icon if dark mode on...
     */
    //==================================================================================================================
    override fun checkIconIfDarkModeOn(): Boolean = when {
        OnedioSharedPrefs(applicationContext).isNightModeEnabled() -> true
        else -> false
    }

    //==================================================================================================================
    /**
     * Hide Loading...
     */
    //==================================================================================================================
    override fun hideLoading() {
        avlIndicatorProgress.cancelAnimation()
        avlIndicatorProgress.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    //==================================================================================================================
    /**
     * Show Loading...
     */
    //==================================================================================================================
    override fun showLoading() {
        avlIndicatorProgress.playAnimation()
        avlIndicatorProgress.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    //==================================================================================================================
    /**
     * Show Error...
     */
    //==================================================================================================================
    override fun showError(response4ErrorObj: Response4ErrorObj) {
        showEzDialogMessage(
            false,
            "Uyarı..!",
            response4ErrorObj.status?.message!!,
            "Tamam",
            ContextCompat.getColor(
                applicationContext,
                R.color.tabIndicatorColor4Proile
            ),
            ContextCompat.getColor(applicationContext, R.color.constWhite),
            ContextCompat.getColor(applicationContext, R.color.constTextColor),
            ContextCompat.getColor(applicationContext, R.color.constTextColor)
        )
    }

    /*//==================================================================================================================
    */
    /**
     * Go Back...
     *//*
    //==================================================================================================================
    override fun goBack() {
        goBack.setOnClickListener() {
            startActivity(
                Intent(
                    this@UserProfileInfoDetailActivityViewImpl,
                    GeneralSettingActivityViewImpl::class.java
                )
            )
        }
    }

    //==================================================================================================================
    */
    /**
     * Handle Keyboard Back Button...
     *//*
    //==================================================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> startActivity(
                Intent(
                    this@UserProfileInfoDetailActivityViewImpl,
                    GeneralSettingActivityViewImpl::class.java
                )
            )
        }
        return super.onKeyDown(keyCode, event);
    }*/

    //==================================================================================================================
    /**
     * Click Empty Area...
     */
    //==================================================================================================================
    override fun clickEmptyArea() {
        profileActivityConstraint.setOnClickListener {
            hideSoftKeyboard(profileActivityConstraint)
        }
    }

    //==================================================================================================================
    /**
     * Hide Soft Keyboard..
     */
    //==================================================================================================================
    private fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //==================================================================================================================
    /**
     * Scroll Edittext...
     */
    //==================================================================================================================
    override fun scrollEditText() {
        extUserAbout.setOnTouchListener { v, event ->
            if (v?.id == R.id.extUserAbout)
                v.parent.requestDisallowInterceptTouchEvent(true)

            when (event?.action) {
                MotionEvent.ACTION_UP -> v?.parent?.requestDisallowInterceptTouchEvent(false)
            }

            false
        }
    }

    //==================================================================================================================
    /**
     * EditText Touch Listener...
     */
    //==================================================================================================================
    override fun edtOfTouchListener() {
        extUserName.setOnTouchListener { _, _ ->
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }

        extUserEmail.setOnTouchListener { _, _ ->
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }

        extUserAbout.setOnFocusChangeListener { _, b ->
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

        extUserNameAndSurname.setOnTouchListener { _, _ ->
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }
        extUserBirthday.setOnTouchListener { _, _ ->
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }
        extCity.setOnTouchListener { _, _ ->
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            false
        }
    }

    override fun showEzDialogMessage(
        isCallbackTrigger: Boolean,
        titleText: String,
        messageText: String,
        buttonText: String,
        headerColor: Int,
        titleTextColor: Int,
        messageTextColor: Int,
        buttonTextColor: Int
    ) {
        val ezDialogMessage =
            OnedioEzDialogMessageModel4Activity(
                this,
                titleText,
                messageText,
                buttonText,
                headerColor,
                titleTextColor,
                messageTextColor,
                buttonTextColor
            )

        OnedioCommon.showEzDialog4Activity(ezDialogMessage) {}

        /*{
            when (isCallbackTrigger) {
                true -> goDashboard()
            }
        }*/

    }

    /*override fun goDashboard() {
        OnedioSingletonPattern.instance.setTabIndex(4)
        startActivity(
            Intent(
                this@UserProfileInfoDetailActivityViewImpl,
                DashboardActivityViewImpl::class.java
            )
        )
    }*/

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
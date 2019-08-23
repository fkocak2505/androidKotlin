package tr.com.fkocak.bytranslator;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;
import tr.com.fkocak.bytranslator.adapter.CustomListAdapter4Language;
import tr.com.fkocak.bytranslator.api.ApiUtils4AWSEB;
import tr.com.fkocak.bytranslator.api.interfaced.APIService4AWSEB;
import tr.com.fkocak.bytranslator.common.CameraUtils;
import tr.com.fkocak.bytranslator.common.UUIDGenerator;
import tr.com.fkocak.bytranslator.enumaration.LanguageEnums;
import tr.com.fkocak.bytranslator.model.LanguageModel;
import tr.com.fkocak.bytranslator.model.SnapTransModel;
import tr.com.fkocak.bytranslator.model.rekognition.Response4Rekognition;
import tr.com.fkocak.bytranslator.model.rekognition.TextDetection;
import tr.com.fkocak.bytranslator.service.ApiService;

public class SnapTrans extends AppCompatActivity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int BITMAP_SAMPLE_SIZE = 8;
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";
    public static final String IMAGE_EXTENSION = "jpg";

    private static String imageStoragePath;
    private static String appKey;
    private static String s3Path;
    private static String detectedText = "";

    private ImageView snapTrans;
    private TextView toLanguage;
    private EditText translatedText;
    private ImageView copy;
    private ImageView downSes;
    private APIService4AWSEB apiService4AWSEB;
    ApiService apiService;
    Gson gson = new Gson();
    TextToSpeech textToSpeech;
    File file;
    List<String> arrOfTextLanguage;

    private GuideView mGuideView;
    private GuideView.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snaptrans);

        AWSMobileClient.getInstance().initialize(this).execute();
        apiService4AWSEB = ApiUtils4AWSEB.getAPIService();
        apiService = new ApiService();

        detectedText = "";


        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(),
                    "Cihazınız Kamera Özelliğini Desteklememektedir",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        appKey = new SharedPreferences4Setting(getApplicationContext()).getString1("APP_KEY");

        bindComponent4SnapTrans();
        setTourGuide();

        snapTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toLanguage.getText().toString().equals("")) {
                    if (CameraUtils.checkPermissions(getApplicationContext())) {
                        captureImage();
                    } else {
                        requestCameraPermission(MEDIA_TYPE_IMAGE);
                    }
                } else
                    Toast.makeText(SnapTrans.this, "Çevrilcek Dil Kısmı boş olamaz", Toast.LENGTH_SHORT).show();

            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                if (!translatedText.getText().toString().equals("")) {
                    clipboard.setText(translatedText.getText());
                    Toast.makeText(SnapTrans.this, "Metin Kopyalandı", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SnapTrans.this, "Lütfen Önce çeviri yapınız", Toast.LENGTH_SHORT).show();
            }
        });

        downSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.getDefault());
                        String textOfSpeeching = translatedText.getText().toString();
                        if (!textOfSpeeching.equals(""))
                            textToSpeech.speak(textOfSpeeching, TextToSpeech.QUEUE_FLUSH, null);
                        else
                            Toast.makeText(SnapTrans.this, "Çevrilen metin boş..!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        toLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog4SelectLanguage();
            }
        });

        restoreFromBundle(savedInstanceState);
    }

    private void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH)) {
                imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
                if (!TextUtils.isEmpty(imageStoragePath)) {
                    if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + IMAGE_EXTENSION)) {
                        previewCapturedImage(false);
                    }
                }
            }
        }
    }

    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                captureImage();
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
                previewCapturedImage(true);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "Fotoğraf çekmekten Vazgeçildi", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "İşlem başarısız olmuştur.", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void previewCapturedImage(boolean afterCapturePics) {
        try {

            ExifInterface ei = null;
            try {
                ei = new ExifInterface(imageStoragePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
            Bitmap rotatedBitmap = null;
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;
            }

            snapTrans.setImageBitmap(rotatedBitmap);

            if (afterCapturePics) {
                File file = new File(imageStoragePath);
                OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.close();
                uploadImage2AWSS3(file);
            }


        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public void uploadImage2AWSS3(File file) {
        BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAI4MBWXU2AQQNBEPQ", "kFLFuBEoMPZ0hZ09U0r+wJ3zZS1VoIBHM0WyCct5");
        AmazonS3Client s3Client = new AmazonS3Client(credentials);
        //AmazonS3Client s3Client = new AmazonS3Client(new BasicAWSCredentials("ff","aaa"));
        TransferUtility transferUtility =
                TransferUtility.builder()
                        .context(getApplicationContext())
                        .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                        .s3Client(s3Client)
                        .build();

        s3Path = appKey + "/" + new UUIDGenerator().generateUUID() + ".png";
        TransferObserver uploadObserver = transferUtility.upload("bytranslator", s3Path, file);

        uploadObserver.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                if (TransferState.COMPLETED == state) {
                    Toast.makeText(SnapTrans.this, "Tamamlandı", Toast.LENGTH_SHORT).show();
                    SnapTransModel snapTransModel = new SnapTransModel();
                    //String imageName = "aa2c652d-61a5-45c7-9416-72b18abda19c/2e6f591e-a757-4845-9660-2f4ba273e157.png";
                    String imageName = s3Path;
                    snapTransModel.setImageName(imageName);
                    sendRequestWithString(snapTransModel);
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                int percentDone = (int) percentDonef;
            }

            @Override
            public void onError(int id, Exception ex) {
                Toast.makeText(SnapTrans.this, "Error", Toast.LENGTH_SHORT).show();
            }

        });

        if (TransferState.COMPLETED == uploadObserver.getState()) {
            Toast.makeText(this, "Okkk..", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendRequestWithString(SnapTransModel snapTransModel) {
        apiService4AWSEB.rekognition(RequestBody.create(MediaType.parse("application/json"), gson.toJson(snapTransModel))).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    String strOfResponse = response.body();
                    Response4Rekognition response4Rekognition = new Gson().fromJson(strOfResponse, Response4Rekognition.class);
                    List<TextDetection> listOfTextDetections = response4Rekognition.getData().getTextDetections();
                    for (TextDetection textDetection : listOfTextDetections) {
                        if (textDetection.getType().equals("LINE"))
                            detectedText += textDetection.getDetectedText() + " ";
                    }
                    detectedText = detectedText.substring(0, detectedText.length() - 1);
                    apiService.translate(getApplicationContext(), detectedText, translatedText, snapTrans, "auto", toLanguage.getText().toString().toLowerCase(), false, false);

                } else {
                    /// TO DO AFTER
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                /// TO DO AFTER
            }
        });
    }

    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("İzniniz Gerekli")
                .setMessage("Kamera Özelliği bazı izinlere ihtiyaç duymaktadır")
                .setPositiveButton("Ayarlara git", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(SnapTrans.this);
                    }
                })
                .setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void showDialog4SelectLanguage() {
        final Dialog dialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            dialog = new Dialog(SnapTrans.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            //builder = new AlertDialog.Builder(context);
            dialog = new Dialog(SnapTrans.this);
        }

        dialog.setContentView(R.layout.cd_select_language_4_setting);
        String titleText = "Çevrilecek Dil                             ";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.yellow));
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        dialog.setTitle(ssBuilder);
        dialog.getWindow().setBackgroundDrawableResource(R.color.viewColor);

        ListView languageList = (ListView) dialog.findViewById(R.id.languageList);

        arrOfTextLanguage = new ArrayList<>();

        final List<LanguageModel> allLanguages = new ArrayList<>();
        allLanguages.add(LanguageEnums.en.getLanguage());
        allLanguages.add(LanguageEnums.tr.getLanguage());
        allLanguages.add(LanguageEnums.fr.getLanguage());
        allLanguages.add(LanguageEnums.ja.getLanguage());
        allLanguages.add(LanguageEnums.de.getLanguage());
        allLanguages.add(LanguageEnums.pl.getLanguage());


        final List<LanguageModel> languageModels = new ArrayList<>();
        languageModels.add(LanguageEnums.EN.getLanguage());
        languageModels.add(LanguageEnums.TR.getLanguage());
        languageModels.add(LanguageEnums.FR.getLanguage());
        languageModels.add(LanguageEnums.JA.getLanguage());
        languageModels.add(LanguageEnums.DE.getLanguage());
        languageModels.add(LanguageEnums.PL.getLanguage());

        for (LanguageModel languageItem : allLanguages) {
            arrOfTextLanguage.add(languageItem.getlVal());
        }

        CustomListAdapter4Language customListAdapter4Language = new CustomListAdapter4Language(getApplicationContext(), arrOfTextLanguage);

        languageList.setAdapter(customListAdapter4Language);

        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String txtOfSelectedLanguage = allLanguages.get(position).getlVal();

                for (LanguageModel languageModelItem : languageModels) {
                    if (languageModelItem.getlKey().equals(txtOfSelectedLanguage)) {
                        translatedText.setText("");
                        toLanguage.setText(languageModelItem.getlVal());
                        if (!detectedText.equals(""))
                            apiService.translate(getApplicationContext(), detectedText, translatedText, snapTrans, "auto", toLanguage.getText().toString().toLowerCase(), false, false);
                        //toLanguage.setText(txtOfSelectedLanguage);
                    }
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void bindComponent4SnapTrans() {
        snapTrans = (ImageView) findViewById(R.id.snapTrans);
        toLanguage = (TextView) findViewById(R.id.toLanguage);
        translatedText = (EditText) findViewById(R.id.translatedText);
        copy = (ImageView) findViewById(R.id.copy);
        downSes = (ImageView) findViewById(R.id.downSes);
    }

    public void setTourGuide() {
        builder = new GuideView.Builder(this)
                .setTitle("Kameranın açıldığı alan")
                .setContentSpan((Spannable) Html.fromHtml("<font color='red'>Kamera ile fotoğraf çekip bu fotoğraftaki yazıyı çevirmenizi sağlar..</p>"))
                .setGravity(Gravity.auto) //optional
                .setDismissType(DismissType.anywhere) //optional - default DismissType.targetView
                .setTargetView(snapTrans)
                .setContentTextSize(13)//optional
                .setTitleTextSize(15)//optional
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        switch (view.getId()) {
                            case R.id.snapTrans:
                                builder.setTitle("Dil Seçme Alanı");
                                builder.setContentSpan((Spannable) Html.fromHtml("<font color='red'>Hangi dile çevirmek istediğinizi seçebilirsiniz.</p>"));
                                builder.setTargetView(findViewById(R.id.toLanguage));
                                break;
                            case R.id.toLanguage:
                                builder.setTitle("Çevrilen yazıyı gösterir");
                                builder.setContentSpan((Spannable) Html.fromHtml("<font color='red'>Dil seçimi yaptıktan sonra seçtiğiniz dildeki yazıyı gösteren alandır</p>"));
                                builder.setTargetView(findViewById(R.id.translatedText));
                                break;
                            case R.id.translatedText:
                                builder.setTitle("Çevrilen yazıyı kopyalar");
                                builder.setContentSpan((Spannable) Html.fromHtml("<font color='red'>Çevrilen yazıyı kopyalamanıza olanak sağlar.</p>"));
                                builder.setTargetView(findViewById(R.id.copy));
                                break;
                            case R.id.copy:
                                builder.setTitle("Çevrilen yazıyı Sese Dönüştür");
                                builder.setContentSpan((Spannable) Html.fromHtml("<font color='red'>Çevrilen yazıyı sesli olarak dinlemenizi sağlar. Konuşma dili telefonunuzun dil seçimiyle aynıdır.</p>"));
                                builder.setTargetView(findViewById(R.id.downSes));
                                break;
                            case R.id.downSes:
                                return;
                        }

                        mGuideView = builder.build();
                        mGuideView.show();

                    }
                });

        mGuideView = builder.build();
        mGuideView.show();

        updatingForDynamicLocationViews();
    }

    private void updatingForDynamicLocationViews() {
        copy.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mGuideView.updateGuideViewLocation();
            }
        });
    }
}



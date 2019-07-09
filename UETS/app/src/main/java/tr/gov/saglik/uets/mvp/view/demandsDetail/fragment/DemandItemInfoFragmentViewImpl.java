package tr.gov.saglik.uets.mvp.view.demandsDetail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.demands.DemandsActivityModelImpl;
import tr.gov.saglik.uets.mvp.presenter.demands.DemandsActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demandsDetail.DemandsDetailActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DemandItemInfoFragmentViewImpl extends Fragment implements IDemandItemInfoFragmentView {

    int demandId = 0;

    /// Component
    Button btnOfApprove;
    Button btnOfCancel;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Presenter
    DemandsActivityPresenterImpl demandsActivityPresenter;

    /// Data
    int operationId;

    //==============================================================================================

    /**
     * Fragment Constructor
     */
    //==============================================================================================
    public DemandItemInfoFragmentViewImpl() {
        // Required empty public constructor
    }

    //==============================================================================================

    /**
     * Fragment onCreate View
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==============================================================================================

    /**
     * Fragment onCreateView function..
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_demands_detail_demands_info, container, false);


        initDemandItemInfoFragmentView(view);

        //getTaskOperationListByDemandId(demandId);

        clickApprove(view);
        clickGoBack(view);

        return view;
    }

    //==============================================================================================

    /**
     * Init Fragment Components
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void initDemandItemInfoFragmentView(View view) {
        demandId = UETSSingletonPattern.getInstance().getDemandId();
        demandsActivityPresenter = new DemandsActivityPresenterImpl(new DemandsActivityModelImpl(), this);

        btnOfApprove = (Button) view.findViewById(R.id.approve);
        btnOfCancel = (Button) view.findViewById(R.id.reject);
        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avloadingProgressBar);
    }

    //==============================================================================================

    /**
     * Send Req..
     *
     * @param demandId
     */
    //==============================================================================================
    @Override
    public void getTaskOperationListByDemandId(int demandId) {
        demandsActivityPresenter.taskOperationListByDemandId(demandId);
    }

    //==============================================================================================

    /**
     * Show Loading When request to API
     */
    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Hide Loading When come to data from API
     */
    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Show Error When getting Error from API
     */
    //==============================================================================================
    @Override
    public void showError(String errorMsg) {
        new EZDialog.Builder(getActivity())
                .setTitle("Hata..!")
                .setMessage(errorMsg)
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getActivity().getApplicationContext(), DemandsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle Coming Data from API...
     *
     * @param strOfTaskOperationListByDemandId
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView4DemandsComplated(String strOfTaskOperationListByDemandId) {
        try {
            JSONArray jsonArray = new JSONArray(strOfTaskOperationListByDemandId);
            JSONObject response4TaskOperationListByDemandId = (JSONObject) jsonArray.get(0);
            btnOfApprove.setText(response4TaskOperationListByDemandId.getString("Name"));
            btnOfCancel.setText("Vazgeç");
            operationId = response4TaskOperationListByDemandId.getInt("Id");
        } catch (JSONException e) {
            new EZDialog.Builder(getActivity())
                    .setTitle("Hata..!")
                    .setMessage("Error When parsing Data.. (Task Operation Demands Id)")
                    .setPositiveBtnText("Tamam")
                    .setCancelableOnTouchOutside(true)
                    .showTitleDivider(true)
                    .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                    .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                    .OnPositiveClicked(new EZDialogListener() {
                        @Override
                        public void OnClick() {
                            startActivity(new Intent(getActivity().getApplicationContext(), DemandsActivityViewImpl.class));
                        }
                    })
                    .build();
            e.printStackTrace();
        }
    }

    //==============================================================================================

    /**
     *
     * @param response
     */
    //==============================================================================================

    @Override
    public void sendData2ActivityView4Demand(String response) {
        new EZDialog.Builder(getActivity())
                .setTitle("Başarılı")
                .setMessage("Kayıt Başarıyla Gerçekleştirildi")
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getActivity().getApplicationContext(), DemandsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Click Cancel Button..
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void clickGoBack(View view) {
        btnOfCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UETSSingletonPattern.getInstance().setDemandId(0);
                startActivity(new Intent(getActivity().getApplicationContext(), DemandsActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Click Approve Button..
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void clickApprove(View view) {
        btnOfApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //demandsActivityPresenter.operationTask(demandId, 1, operationId);
                demandsActivityPresenter.operationTask(56, 1, 1);
            }
        });
    }
}

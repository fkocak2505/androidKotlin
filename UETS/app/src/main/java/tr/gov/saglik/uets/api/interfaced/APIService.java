package tr.gov.saglik.uets.api.interfaced;

/**
 * Created by fatihkocak on 25.04.2019.
 */

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4Announcements;
import tr.gov.saglik.uets.mvp.model.announcements.responseModel.Response4AnnouncementsDetail;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.Response4CurriculumList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.Response4CurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.Response4Demands;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4Login;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4DemandsAndRecommendation;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4NewDemandAndRecommendationDemandType;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4SaveNewDemandAndRecommendation;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel.Response4UserInfoData;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.Response4ProgramInfo;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.Response4RotationList;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4NewTransportTransferType;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4SaveTransport;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransferProgram;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransportInfoData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ResponseYetkinlikKayitlarim;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ResponseYetkinlikListFilterListData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ResponseYetkinlikList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4EducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4InstitutionData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4TeamMemberList;

public interface APIService {

    @FormUrlEncoded
    @POST("token")
    Call<Response4Login> token(@Field("grant_type") String grantType, @Field("username") String userName, @Field("password") String password);

    @POST("/api/Perfection/GetPerfectionMobileFilterList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseYetkinlikListFilterListData> getYetkinlikListFilterList(@Header("Authorization") String authKey, @Body RequestBody type);

    @POST("/api/Perfection/GetPerfectionListByMember")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseYetkinlikList> getYetkinlikList(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/Perfection/GetPerfectionDemandListByMember")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<ResponseYetkinlikKayitlarim> getYetkinlikKayitlarim(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/Institution/GetInstitutionList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4InstitutionData> getInstitutionList(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/Member/GetMemberEducatorList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4EducatorList> getEducatorList(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/Member/GetMemberList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4TeamMemberList> getTeamMember(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/Perfection/SaveModifyStudentPerfection")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<String> saveYetkinlik(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/Perfection/DeleteStudentPerfection")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<String> deleteYetkinlik(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/Announcement/GetAnnouncementList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4Announcements> announcementsList(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/Announcement/GetAnnouncementById")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4AnnouncementsDetail> announcementDetail(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Body RequestBody type);

    @POST("/api/StudentCard/GetStudentCardProgramByMemberId")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4ProgramInfo> programInfo(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/Curriculum/GetCurriculumList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4CurriculumList> curriculumList(@Header("Authorization") String authKey, @Body RequestBody type);

    @POST("/api/Curriculum/GetCurriculumMobileFilterList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4CurriculumFilterList> curriculumFilterList(@Header("Authorization") String authKey, @Body RequestBody type);

    @POST("/api/Demand/GetUserDemandList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4Demands> demandsList(@Header("Authorization") String authKey, @Body RequestBody type);

    @POST("/api/Demand/GetTaskOperationListByDemandId")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<String> taskOperationListByDemandId(@Header("Authorization") String authKey, @Body RequestBody type);

    @POST("/api/Demand/OperationTask")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<String> operationTask(@Header("Authorization") String authKey, @Body RequestBody type);

    @POST("/api/SuggestionDemand/GetSuggestionDemant")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4DemandsAndRecommendation> demandsAndRecommendation(@Header("Authorization") String authKey, @Header("IsMock") String isMock, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/SuggestionDemand/GetSuggestionDemantType")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4NewDemandAndRecommendationDemandType> newDemandsAndRecommendationDemandType(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/SuggestionDemand/GetMemberGroup")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4NewDemandAndRecommendationDemandType> newDemandsAndRecommendationMemberGroup(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/SuggestionDemand/InsertSuggestionDemant")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4SaveNewDemandAndRecommendation> saveDemandsAndRecommendation(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/StudentTransferInformation/GetStudentTransferInformationList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4TransportInfoData> transportInfoData(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/ConstantValue/GetGeneralTypeList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4NewTransportTransferType> newTransportTransferType(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/StudentTransferInformation/GetTransferProgram")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4TransferProgram> newTransportProgramData(@Header("ReqType") String reqType);

    @POST("/api/StudentTransferInformation/SaveStudentTransferInformationMobile")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4SaveTransport> saveTransport(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/StudentRotation/GetStudentRotationList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4RotationList> rotationList(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/StudentRotation/GetStudentRotationDetailList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4RotationList> rotationDetailList(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/Member/GetMemberList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4UserInfoData> userInfoData(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);

    @POST("/api/Announcement/GetDocumentList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4DocumentList> documentList(@Header("Authorization") String authKey, @Header("ReqType") String reqType);

    @POST("/api/Announcement/GetDecisiontList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<Response4DocumentList> decisionList(@Header("Authorization") String authKey, @Header("ReqType") String reqType);

    @POST("/api/Announcement/GetDecisiontList")
    @Headers({"ContentNeYapmaliyim-Type: application/json;charset=UTF-8"})
    Call<String> thesis(@Header("Authorization") String authKey, @Header("ReqType") String reqType, @Body RequestBody type);


}
package tr.gov.saglik.uets.mvp.model.curriculum;

import java.util.List;

import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public interface ICurriculumActivityModel {

    void initApiService();

    void setCurriculumListRequestParam(List<FilterArrParam> filterList);

    void setCurriculumFilterListRequestParam();

    void getCurriculumList(List<FilterArrParam> filterList, RequestResultListener requestResultListener);

    void getCurriculumFilterList(RequestResultListener requestResultListener);

}

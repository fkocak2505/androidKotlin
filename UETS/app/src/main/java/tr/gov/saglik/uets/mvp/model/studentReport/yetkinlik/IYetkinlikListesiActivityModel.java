package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik;

import java.util.List;

import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public interface IYetkinlikListesiActivityModel {

    void initApiService();

    void setYetkinlikListFiltersParam();

    void setYetkinlikListParam(int memberId, List<FilterArrParam> filterList);

    void getYetkinlikListFilterList(RequestResultListener requestResultListener);

    void getYetkinlikListByMember(int memberId, List<FilterArrParam> filterLists, RequestResultListener requestResultListener);

}


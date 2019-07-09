package tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public interface IYetkinlikListesiPresenter {

    void filterList();

    void yetkinlikListByMember(int memberId, List<FilterArrParam> filterLists);

}

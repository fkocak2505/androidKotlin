package tr.gov.saglik.uets.mvp.presenter.curriculum;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public interface ICurriculumActivityPresenter {

    void curriculumList(List<FilterArrParam> filterList);

    void curriculumFilterList();

}

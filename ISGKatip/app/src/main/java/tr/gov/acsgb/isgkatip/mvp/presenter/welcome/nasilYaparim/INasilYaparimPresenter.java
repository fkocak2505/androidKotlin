package tr.gov.acsgb.isgkatip.mvp.presenter.welcome.nasilYaparim;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel.FilterList4NasilYaparim;

public interface INasilYaparimPresenter {

    void nasilYaparim(Context context, int page, int size, List<FilterList4NasilYaparim> filterList4NasilYaparims);

}

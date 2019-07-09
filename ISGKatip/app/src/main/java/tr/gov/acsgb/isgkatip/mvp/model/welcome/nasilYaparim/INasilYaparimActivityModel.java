package tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim;

import android.content.Context;

import java.util.List;

import tr.gov.acsgb.isgkatip.RequestResultListener;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel.FilterList4NasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;

public interface INasilYaparimActivityModel {

    void initApiService(int page, int size, List<FilterList4NasilYaparim> filterList4NasilYaparims);

    void getNasilYaparimData(Context context, int page, int size, List<FilterList4NasilYaparim> filterList4NasilYaparims, RequestResultListener requestResultListener);

}

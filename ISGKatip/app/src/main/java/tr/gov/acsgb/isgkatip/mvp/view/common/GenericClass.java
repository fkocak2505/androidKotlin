package tr.gov.acsgb.isgkatip.mvp.view.common;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.common.CSharedPreferences;

public class GenericClass<T> {

    Context mContext;
    List<T> list = new ArrayList<>();

    public GenericClass(Context mContext) {
        this.mContext = mContext;
    }

    public GenericClass(Context mContext, List<T> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void saveList(String key) {
        new CSharedPreferences(mContext).putListObj(key, list);
    }

    public <T> List<T> returnList(String key, final Class<T[]> tClass) {
        return new CSharedPreferences(mContext).getListObj(key, tClass);
    }
}

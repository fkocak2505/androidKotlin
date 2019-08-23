package tr.com.fkocak.bytranslator.service.interfaced;

import android.content.Context;
import android.widget.LinearLayout;

import javax.security.auth.callback.Callback;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public interface IApiService4Dictionary {

    public void getDictionary(Context context, String word, String when, String encrypted);
}

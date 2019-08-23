package tr.com.fkocak.bytranslator.service.interfaced;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fatihkocak on 21.09.2018.
 */

public interface IApiService {

    public void translate(Context context, String enteredText, EditText textView, ImageView imageView, String fromLangue, String toLanguage, boolean isSavedWOS, boolean isLeaf);

}

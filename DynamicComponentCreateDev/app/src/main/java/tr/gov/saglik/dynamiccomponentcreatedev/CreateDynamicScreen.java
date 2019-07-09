package tr.gov.saglik.dynamiccomponentcreatedev;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CreateDynamicScreen implements ICreateDynamicScreen {

    Activity activity;
    List<ComponentArr> listOfComponentData;

    TextView textView;
    EditText editText;
    Button button;

    public CreateDynamicScreen(Activity activity, List<ComponentArr> listOfComponentData) {
        this.activity = activity;
        this.listOfComponentData = listOfComponentData;
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean createComponent() {

        ConstraintLayout constraintLayout = new ConstraintLayout(activity);
        constraintLayout.setId(1);
        ConstraintSet constraintSet = new ConstraintSet();

        for (ComponentArr componentItem : listOfComponentData) {
            String valType = componentItem.getValueType();
            String compType = componentItem.getComponentType();


            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(600,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);


            switch (compType) {
                case "text":
                    textView = new TextView(activity);
                    textView.setId(componentItem.getId());
                    textView.setText("Fatih Koçak");
                    textView.setTextColor(Color.parseColor(componentItem.getTextColor()));
                    textView.setTextSize(16);
                    constraintLayout.addView(textView, params);
                    constraintSet.clone(constraintLayout);
                    if (componentItem.getId() == 2)
                        constraintSet.connect(textView.getId(), ConstraintSet.TOP, componentItem.getId() - 1, ConstraintSet.TOP, 8);
                    else
                        constraintSet.connect(textView.getId(), ConstraintSet.TOP, componentItem.getId() - 1, ConstraintSet.BOTTOM, 64);
                    constraintSet.connect(textView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 120);
                    constraintSet.applyTo(constraintLayout);
                    break;
                case "input":
                    editText = new EditText(activity);
                    editText.setId(componentItem.getId());
                    if (componentItem.getHintText() != null)
                        editText.setHint(componentItem.getHintText());
                    editText.setBackgroundResource(R.drawable.edt_input);
                    constraintLayout.addView(editText, params);
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(editText.getId(), ConstraintSet.TOP, componentItem.getId() - 1, ConstraintSet.BOTTOM, 32);
                    constraintSet.connect(editText.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 60);
                    constraintSet.applyTo(constraintLayout);
                    break;
                case "button":
                    button = new Button(activity);
                    button.setId(componentItem.getId());
                    button.setText("Awfawf");
                    constraintLayout.addView(button, params);
                    constraintSet.clone(constraintLayout);
                    constraintSet.connect(button.getId(), ConstraintSet.TOP, componentItem.getId() - 1, ConstraintSet.BOTTOM, 32);
                    constraintSet.connect(button.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 60);
                    constraintSet.applyTo(constraintLayout);
                    break;
                default:
                    return false;
            }
        }
        activity.setContentView(constraintLayout);
        return true;
    }
}

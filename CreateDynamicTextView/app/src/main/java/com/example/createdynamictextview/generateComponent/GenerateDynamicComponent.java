package com.example.createdynamictextview.generateComponent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.createdynamictextview.dataPojoModel.AllComponent;
import com.example.createdynamictextview.dataPojoModel.button.ButtonProp;
import com.example.createdynamictextview.dataPojoModel.common.CMargin;
import com.example.createdynamictextview.dataPojoModel.text.TextProp;
import com.example.createdynamictextview.objectUtil.ConvertObject2HashMap;

import java.util.List;
import java.util.Map;

public class GenerateDynamicComponent<T> implements IGenerateDynamicTextView<T> {

    /// Activity
    Activity activity;

    /// Data
    List<AllComponent> listOfComponentData;

    // Layout Type
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet;
    ConstraintLayout.LayoutParams componentParams;

    /// Component Types
    TextView textView;
    Button button;

    //==============================================================================================
    /// Constructor of Generate Dynamic Component View...
    //==============================================================================================
    public GenerateDynamicComponent(Activity activity, List<AllComponent> listOfComponentData) {
        this.activity = activity;
        this.listOfComponentData = listOfComponentData;
    }

    //==============================================================================================
    /// First method calling in MainActivity...
    //==============================================================================================
    @SuppressLint("ResourceType")
    @Override
    public boolean createComponent() {

        constraintLayout = new ConstraintLayout(activity);
        constraintLayout.setId(0);
        constraintSet = new ConstraintSet();

        loopingComponentsArr();

        activity.setContentView(constraintLayout);
        return true;
    }

    //==============================================================================================
    /// Loop Service Data
    //==============================================================================================
    @Override
    public void loopingComponentsArr() {
        for (AllComponent itemOfAllComponent : listOfComponentData) {
            switch (itemOfAllComponent.getComponentName()) {
                case "text":
                    List<TextProp> listOfTextProp = itemOfAllComponent.getTextProp();
                    loopingTextViewComponentArr(listOfTextProp);
                    break;

                case "button":
                    List<ButtonProp> listOfButtonProp = itemOfAllComponent.getButtonProp();
                    loopingButtonComponentArr(listOfButtonProp);
                    break;

                default:
                    break;
            }
        }
    }

    //==============================================================================================
    /// Looping TextView Prop..
    //==============================================================================================
    @Override
    public void loopingTextViewComponentArr(List<TextProp> listOfTextProp) {
        /// Text Prop Data
        for (TextProp itemOfTextProp : listOfTextProp) {

            setComponentWidthAndHeightProperty(itemOfTextProp.getWidth(),
                    itemOfTextProp.getHeight());

            decisionComponent("text", itemOfTextProp);
        }
    }


    //==============================================================================================
    /// Looping Button Prop..
    //==============================================================================================
    @Override
    public void loopingButtonComponentArr(List<ButtonProp> listOfButtonProp) {
        /// Button Prop Data
        for (ButtonProp itemOfButtonProp : listOfButtonProp) {

            setComponentWidthAndHeightProperty(itemOfButtonProp.getWidth(),
                    itemOfButtonProp.getHeight());

            decisionComponent("button", itemOfButtonProp);
        }
    }


    //==============================================================================================
    /// Component Width & Height Config...
    //==============================================================================================
    @Override
    public void setComponentWidthAndHeightProperty(int width, int height) {
        componentParams = new ConstraintLayout.LayoutParams(width, height);
    }

    //==============================================================================================
    /// Component Selection...
    //==============================================================================================
    @Override
    public void decisionComponent(String componentName, Object objOfAllComponent) {
        switch (componentName) {
            case "text": /// for TextView
                createTextView((TextProp) objOfAllComponent);
                break;
            case "button": /// for Button
                createButton((ButtonProp) objOfAllComponent);
                break;
            default:
                Toast.makeText(activity, "Beklenmedik bir component", Toast.LENGTH_SHORT).show();
        }
    }

    //==============================================================================================
    /// Create new TextView with text Properties...
    //==============================================================================================
    @Override
    public void createTextView(TextProp componentProperty) {
        textView = new TextView(activity);

        /// Set TextView Component Property
        if (nullChecker(componentProperty.getId()))
            textView.setId(componentProperty.getId());
        if (nullChecker(componentProperty.getText()))
            textView.setText(componentProperty.getText());
        if (nullChecker(componentProperty.getCStyle()))
            textView.setTypeface(Typeface.createFromAsset(activity.getAssets(), componentProperty.getCStyle().getFontName()), Typeface.BOLD_ITALIC);
        if (nullChecker(componentProperty.getSize()))
            textView.setTextSize(componentProperty.getSize());
        if (nullChecker(componentProperty.getColor()))
            textView.setTextColor(Color.parseColor(componentProperty.getColor()));

        constraintLayout.addView(textView, componentParams);

        setMarginProp4Component(componentProperty.getId(), componentProperty.getConnectedID(), componentProperty.getCMargin());

    }

    //==============================================================================================
    /// Create new Button with text Properties...
    //==============================================================================================
    @Override
    public void createButton(ButtonProp buttonProperty) {
        button = new Button(activity);

        /// Set TextView Component Property
        if (nullChecker(buttonProperty.getId()))
            button.setId(buttonProperty.getId());
        if (nullChecker(buttonProperty.getText()))
            button.setText(buttonProperty.getText());
        if (nullChecker(buttonProperty.getCStyle()))
            button.setTypeface(Typeface.createFromAsset(activity.getAssets(), buttonProperty.getCStyle().getFontName()), Typeface.BOLD_ITALIC);
        if (nullChecker(buttonProperty.getSize()))
            button.setTextSize(buttonProperty.getSize());
        if (nullChecker(buttonProperty.getColor()))
            button.setTextColor(Color.parseColor(buttonProperty.getColor()));

        constraintLayout.addView(button, componentParams);

        setMarginProp4Component(buttonProperty.getId(), buttonProperty.getConnectedID(), buttonProperty.getCMargin());
    }

    //==============================================================================================
    /// Set TextView Component Margin Value...
    //==============================================================================================
    @Override
    public void setMarginProp4Component(int id, int connectedID, CMargin cMargin) {
        constraintSet.clone(constraintLayout);

        if (nullChecker(cMargin.getTop())) {
            if (connectedID == -1) /// Bind Root Layout
                constraintSet.connect(id, ConstraintSet.TOP, 0, ConstraintSet.TOP, cMargin.getTop());
            else  /// Bind connected ID's component
                constraintSet.connect(id, ConstraintSet.TOP, connectedID, ConstraintSet.BOTTOM, cMargin.getTop());

        }

        if (nullChecker(cMargin.getLeft()))
            constraintSet.connect(id, ConstraintSet.LEFT, 0, ConstraintSet.LEFT, cMargin.getLeft());
        if (nullChecker(cMargin.getRight()))
            constraintSet.connect(id, ConstraintSet.RIGHT, 0, ConstraintSet.RIGHT, cMargin.getRight());
        if (nullChecker(cMargin.getBottom()))
            constraintSet.connect(id, ConstraintSet.BOTTOM, 0, ConstraintSet.BOTTOM, cMargin.getBottom());

        constraintSet.applyTo(constraintLayout);
    }

    //==============================================================================================
    /// Check Properties is null...
    //==============================================================================================
    @Override
    public boolean nullChecker(Object data) {
        if (!objectChecker(data))
            return data != null;
        try {
            Map<String, Object> mapOfPojoData = ConvertObject2HashMap.getFieldNamesValueMap(data);
            return nullCheck4Map(mapOfPojoData);
        } catch (IllegalAccessException e) {
            /// TO DO Logging for error...
            return false;
        }
    }

    //==============================================================================================
    /// Check Properties is Object...
    //==============================================================================================
    @Override
    public boolean objectChecker(Object obj) {
        if (obj == null) return false;
        if (obj instanceof String) return false;
        if (obj instanceof Integer) return false;
        if (obj instanceof Boolean) return false;

        return true;
    }

    //==============================================================================================
    /// Check if Properties is Object and has null value...
    //==============================================================================================
    public boolean nullCheck4Map(Map<String, Object> map) {
        for (Map.Entry<String, Object> mapItem : map.entrySet()) {
            if (mapItem.getValue() == null)
                return false;
        }
        return true;
    }
}
package com.example.createdynamictextview.generateComponent;

import com.example.createdynamictextview.dataPojoModel.button.ButtonProp;
import com.example.createdynamictextview.dataPojoModel.common.CMargin;
import com.example.createdynamictextview.dataPojoModel.text.TextProp;

import java.util.List;

public interface IGenerateDynamicTextView <T> {

    boolean createComponent();

    void loopingComponentsArr();

    void loopingTextViewComponentArr(List<TextProp> listOfTextProp);

    void loopingButtonComponentArr(List<ButtonProp> listOfButtonProp);

    void setComponentWidthAndHeightProperty(int width, int height);

    <E> void decisionComponent(String componentName, E objOfAllComponent);

    void createTextView(TextProp componentProperty);

    void createButton(ButtonProp buttonProperty);

    boolean nullChecker(T data);

    boolean objectChecker(T obj);

    void setMarginProp4Component(int id, int connectedID, CMargin cMargin);

}

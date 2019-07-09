package tr.gov.saglik.dynamiccomponentcreatedev;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComponentModel {

    @SerializedName("componentArr")
    @Expose
    private List<ComponentArr> componentArr = null;

    public List<ComponentArr> getComponentArr() {
        return componentArr;
    }

    public void setComponentArr(List<ComponentArr> componentArr) {
        this.componentArr = componentArr;
    }
}

package tr.com.fkocak.bytranslator.singleton;

/**
 * Created by fatihkocak on 15.10.2018.
 */

public class CommonSingletonClass {
    private static CommonSingletonClass instance;

    boolean isShowDashboard = true;
    boolean decideBackPressBtn4Setting = false;

    public boolean isShowDashboard() {
        return isShowDashboard;
    }

    public void setShowDashboard(boolean showDashboard) {
        isShowDashboard = showDashboard;
    }

    public boolean isDecideBackPressBtn4Setting() {
        return decideBackPressBtn4Setting;
    }

    public void setDecideBackPressBtn4Setting(boolean decideBackPressBtn4Setting) {
        this.decideBackPressBtn4Setting = decideBackPressBtn4Setting;
    }

    public static CommonSingletonClass getInstance(){
        if(instance == null){
            instance = new CommonSingletonClass();
        }
        return instance;
    }
}

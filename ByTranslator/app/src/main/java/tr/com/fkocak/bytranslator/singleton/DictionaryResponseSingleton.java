package tr.com.fkocak.bytranslator.singleton;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class DictionaryResponseSingleton {
    private static DictionaryResponseSingleton instance;

    String response = "";

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static DictionaryResponseSingleton getInstance(){
        if(instance == null){
            instance = new DictionaryResponseSingleton();
        }
        return instance;
    }
}

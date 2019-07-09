package tr.gov.saglik.dynamiccomponentcreatedev;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String json = "{\n" +
            "  \"componentArr\":[\n" +
            "    {\n" +
            "      \"valueType\":\"string\",\n" +
            "      \"componentType\":\"text\",\n" +
            "      \"isCompulsory\":null,\n" +
            "      \"textColor\": \"#000000\",\n" +
            "      \"hintText\":null,\n" +
            "      \"id\":2\n" +
            "    },\n" +
            "    {\n" +
            "      \"valueType\":\"string\",\n" +
            "      \"componentType\":\"text\",\n" +
            "      \"isCompulsory\":null,\n" +
            "      \"textColor\": \"#000000\",\n" +
            "      \"hintText\":null,\n" +
            "      \"id\":3\n" +
            "    },\n" +
            "    {\n" +
            "      \"valueType\":\"string\",\n" +
            "      \"componentType\":\"input\",\n" +
            "      \"isCompulsory\":true,\n" +
            "      \"textColor\": \"#FF0000\",\n" +
            "      \"hintText\":\"Fatih\",\n" +
            "      \"id\":4\n" +
            "    },\n" +
            "    {\n" +
            "      \"valueType\":null,\n" +
            "      \"componentType\":\"button\",\n" +
            "      \"isCompulsory\":false,\n" +
            "      \"textColor\": \"#FFFF00\",\n" +
            "      \"hintText\":null,\n" +
            "      \"id\":5\n" +
            "    },\n" +
            "    {\n" +
            "      \"valueType\":\"number\",\n" +
            "      \"componentType\":\"input\",\n" +
            "      \"isCompulsory\":false,\n" +
            "      \"textColor\": \"#FFFF00\",\n" +
            "      \"hintText\":\"123\",\n" +
            "      \"id\":6\n" +
            "    }\n" +
            "    ]\n" +
            "}";

    Gson gson = new Gson();
    ConstraintLayout constraintLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        constraintLayout = new ConstraintLayout(this);
        constraintLayout.setId(1);
        ConstraintSet constraintSet = new ConstraintSet();


        ComponentModel componentModel = gson.fromJson(json, ComponentModel.class);
        List<ComponentArr> listOfComponentData = componentModel.getComponentArr();

        CreateDynamicScreen createDynamicScreen = new CreateDynamicScreen(this, listOfComponentData);
        if (createDynamicScreen.createComponent())
            Toast.makeText(this, "Ekran oluşturuldu", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Beklenmedik bir Component Tipi geldi.", Toast.LENGTH_SHORT).show();

    }
}

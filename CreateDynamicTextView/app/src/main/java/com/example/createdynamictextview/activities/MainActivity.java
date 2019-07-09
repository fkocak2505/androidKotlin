package com.example.createdynamictextview.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.createdynamictextview.dataPojoModel.AllComponent;
import com.example.createdynamictextview.dataPojoModel.AllData;
import com.example.createdynamictextview.dataPojoModel.Response4AllData;
import com.example.createdynamictextview.generateComponent.GenerateDynamicComponent;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    String strOfJSON = "{\n" +
            "  \"data\":[{\n" +
            "    \"screenName\":\"DynamicScreenActivity\",\n" +
            "    \"allComponent\":[{\n" +
            "      \"componentName\":\"text\",\n" +
            "      \"textProp\": \n" +
            "      [{\n" +
            "        \"id\":1,\n" +
            "        \"connectedID\":-1,\n" +
            "        \"text\":\" Fatih Koçak\",\n" +
            "        \"cStyle\":{\n" +
            "            \"isBold\":true,\n" +
            "          \"fontName\":\"fonts/OswaldBold.ttf\"\n" +
            "        },\n" +
            "        \"size\":20,\n" +
            "        \"color\":\"#666666\",\n" +
            "        \"width\":-2,\n" +
            "        \"height\":-2,\n" +
            "        \"cMargin\":{\n" +
            "            \"top\":60,\n" +
            "            \"left\":8,\n" +
            "            \"right\":8,\n" +
            "            \"bottom\":null\n" +
            "        }\n" +
            "      }]\n" +
            "    },\n" +
            "    {\n" +
            "      \"componentName\":\"text\",\n" +
            "      \"textProp\": \n" +
            "      [{\n" +
            "        \"id\":2,\n" +
            "        \"connectedID\":1,\n" +
            "        \"text\":\"Deneme Text..\",\n" +
            "        \"cStyle\":{\n" +
            "            \"isBold\":true,\n" +
            "          \"fontName\":\"fonts/OswaldBold.ttf\"\n" +
            "        },\n" +
            "        \"size\":20,\n" +
            "        \"color\":\"#e4e4e4\",\n" +
            "        \"width\":-2,\n" +
            "        \"height\":-2,\n" +
            "        \"cMargin\":{\n" +
            "            \"top\":8,\n" +
            "            \"left\":8,\n" +
            "            \"right\":8,\n" +
            "            \"bottom\":null\n" +
            "        }\n" +
            "      }]\n" +
            "    },\n" +
            "    {\n" +
            "      \"componentName\":\"button\",\n" +
            "      \"buttonProp\":\n" +
            "      [{\n" +
            "        \"id\":3,\n" +
            "        \"connectedID\":2,\n" +
            "        \"text\":\"Giriş Yap\",\n" +
            "        \"cStyle\":{\n" +
            "          \"isBold\":true,\n" +
            "          \"fontName\":null\n" +
            "        },\n" +
            "        \"size\":15,\n" +
            "        \"color\": \"#000000\",\n" +
            "        \"width\":-2,\n" +
            "        \"height\":-2,\n" +
            "        \"cMargin\":{\n" +
            "            \"top\":8,\n" +
            "            \"left\":8,\n" +
            "            \"right\":8,\n" +
            "            \"bottom\":null\n" +
            "        }\n" +
            "      }]\n" +
            "    }]\n" +
            "  }]\n" +
            "}";

    //==============================================================================================
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Response4AllData response4AllData = new Gson().fromJson(strOfJSON, Response4AllData.class);
        List<AllData> listOfAllData = response4AllData.getData();

        for (AllData itemOfAllData : listOfAllData) {
            if (itemOfAllData.getScreenName().equals("DynamicScreenActivity")) {
                List<AllComponent> listOfAllComponent = itemOfAllData.getAllComponent();
                isActivityComponentsCreated(new GenerateDynamicComponent(this, listOfAllComponent));
            }
        }
    }

    //==============================================================================================
    @SuppressLint("ResourceType")
    @Override
    public void isActivityComponentsCreated(GenerateDynamicComponent generateDynamicComponent) {
        if (generateDynamicComponent.createComponent())
            Toast.makeText(getApplicationContext(), "Screen created successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Unexpected Error..", Toast.LENGTH_SHORT).show();


    }
}

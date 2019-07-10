package tr.com.cs.pushnotification;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String listOfNotificationFromStorage;
    Gson gson;
    ListView listView;
    List<String> iller = new ArrayList<>();

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();


        //listView'i tasarımdakiyle bağlıyoruz.
        listView = (ListView) findViewById(R.id.listView1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(), iller.get(position), Toast.LENGTH_LONG).show();

            }
        });

        if (getIntent().hasExtra("fromNotification")) {
            String strNotificationBoddyMessage = getIntent().getExtras().getString("deneme");

            String listOfNotificationFromStorage = sharedPreferences.getString("notificationList", "['']");


            gson = new Gson();
            iller = gson.fromJson(listOfNotificationFromStorage, new TypeToken<List<String>>() {
            }.getType());

            if (iller.get(0).equals("")) {
                iller.remove(0);
            }

            iller.add(strNotificationBoddyMessage);
            setList("notificationList", iller);
        }

        //editor.remove("notificationList").commit();

        //ArrayAdapter'i hazırlıyoruz.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, iller);

        //listView için hazırladığımız adapter'i ayarlıyoruz.
        listView.setAdapter(adapter);

        listOfNotificationFromStorage = sharedPreferences.getString("notificationList", "['']");
        Toast.makeText(this, listOfNotificationFromStorage, Toast.LENGTH_SHORT).show();

    }


    public <T> void setList(String key, List<T> list) {
        gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    public static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }


}

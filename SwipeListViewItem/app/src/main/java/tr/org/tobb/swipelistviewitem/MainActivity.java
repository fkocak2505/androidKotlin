package tr.org.tobb.swipelistviewitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    BildirimAdapter bildirimAdapter;
    SwipeDetector swipeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.bildirimList);

        final List<String> deneme  = new ArrayList<>();
            deneme.add("Fatih");
            deneme.add("Fatih");
            deneme.add("Fatih");
            deneme.add("Fatih");
            deneme.add("Fatih");

        swipeDetector = new SwipeDetector();

        listView.setAdapter(new BildirimAdapter(getApplicationContext(),deneme));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (swipeDetector.swipeDetected()) {
                    if (swipeDetector.getAction() == Action.LR) {
                        Toast.makeText(getApplicationContext(), "Sola" + deneme.get(i), Toast.LENGTH_SHORT).show();
                    }
                    if (swipeDetector.getAction() == Action.RL) {
                        Toast.makeText(getApplicationContext(), "sağa" + deneme.get(i), Toast.LENGTH_SHORT).show();
                    }
                    if (swipeDetector.getAction() == Action.TB) {
                        Toast.makeText(getApplicationContext(), "Yukarı" + deneme.get(i), Toast.LENGTH_SHORT).show();
                    }
                    if (swipeDetector.getAction() == Action.BT) {
                        Toast.makeText(getApplicationContext(), "Aşağıo" + deneme.get(i), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}

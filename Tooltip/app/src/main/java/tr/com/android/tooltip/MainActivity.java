package tr.com.android.tooltip;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewtooltip.ViewTooltip;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = (TextView) findViewById(R.id.awfawf);
        ViewTooltip
                .on(this, textView)
                .autoHide(true, 3000)
                .corner(100)
                .color(Color.parseColor("#FFFFFF"))
                .textColor(Color.BLACK)
                .position(ViewTooltip.Position.TOP)
                .text("Bu eposta zatan daha önce alınmıştır")
                .show();

    }
}

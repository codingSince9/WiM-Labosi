package hr.fer.wim.flowerpower;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab5.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.logo);
        imageView.getLayoutParams().width=500;
        imageView.getLayoutParams().height=500;
        layout.addView(imageView);

        TextView text = new TextView(this);
        final TextView text2 = new TextView(this);
        final TextView text3 = new TextView(this);
        TextView text4 = new TextView(this);
        TextView text5 = new TextView(this);
        TextView text6 = new TextView(this);
        text.setText("Flower Power");
        text.setTextSize(25);
        text.setPadding(0,0,0,10);
        text2.setText("Unska 3, Zagreb");
        text2.setTextSize(20);
        text2.setPadding(0,0,0,20);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:0,0?q=Unska 3, Zagreb");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        text3.setText("+385 1 6129 999");
        text3.setPadding(0,0,0,20);
        text3.setOnClickListener((v) -> {
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setTitle(R.string.callormsg);
                adb.setCancelable(true);
                adb.setPositiveButton(R.string.callnum, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + text3.getText().toString()));
                        startActivity(intent);
                    }
                });
                adb.setNegativeButton(R.string.sendmsg, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Uri uri = Uri.parse("smsto:" + text3.getText());
                        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                        startActivityForResult(intent, 1);
                    }
                });
                adb.show();
        });

        text4.setText("email: info@ciklama.fer.hr");
        text4.setPadding(0,0,0,20);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:info@ciklama.fer.hr"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Dobro cviće");
                startActivity(intent);
            }
        });

        text5.setText("http://www.ciklama.fer.hr");
        text5.setPadding(0,0,0,20);
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.ciklama.fer.hr");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        text6.setText("App by: Fran Vlahović");
        text.setGravity(Gravity.CENTER);
        text2.setGravity(Gravity.CENTER);
        text3.setGravity(Gravity.CENTER);
        text4.setGravity(Gravity.CENTER);
        text5.setGravity(Gravity.CENTER);
        text6.setGravity(Gravity.CENTER);

        layout.addView(text);
        layout.addView(text2);
        layout.addView(text3);
        layout.addView(text4);
        layout.addView(text5);
        layout.addView(text6);

        this.setContentView(layout);
    }
}
package hr.fer.wim.flowerpower;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.lab5.R;
import com.squareup.picasso.Picasso;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);
        Map<Category, List<Product>> data = new TreeMap<>();
        data = Utils.loadData();
        for (Map.Entry<Category, List<Product>> entry : data.entrySet()){
            int counter = 0;
            Category category = null;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                category = (Category) extras.getSerializable("category");
            }
            if (entry.getKey().getName().equals(category.getName())){
                TextView textView1 = new TextView(this);
                textView1.setTextSize(30);
                textView1.setText(category.getName() + ":");
                layout.addView(textView1);

                for(Product a : entry.getValue()) {
                    LinearLayout row = new LinearLayout(this);
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0));
                    TextView textView = new TextView(this);
                    textView.setText(a.getName());
                    textView.setTextSize(20);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                    row.addView(textView);

                    Button plus = new Button(this);
                    plus.setText("+");
                    plus.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                    row.addView(plus);
                    plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ProductListActivity.this, R.string.itemAdded, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Button info = new Button(this);
                    info.setText("INFO");
                    info.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                    info.setOnClickListener((v) -> {
                        AlertDialog.Builder adb = new AlertDialog.Builder(this);
                        adb.setTitle(R.string.wantToAddItem);

                        ImageView imageView1 = new ImageView(this);
                        imageView1.setLayoutParams(params);
                        String url = a.getImageUrl();
                        Log.d("url", url);
                        Picasso.get().load(url).into(imageView1);

                        TextView textView2 = new TextView(this);
                        String price = a.getPrice().toString();
                        textView2.setText(R.string.price + price);

                        adb.setView(imageView1);

                        adb.setCancelable(false);
                        adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(ProductListActivity.this, R.string.itemAdded, Toast.LENGTH_SHORT).show();
                            }
                        });
                        adb.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        adb.show();
                    });
                    row.addView(info);

                    layout.addView(row);
                    counter++;
                }

            }
            if (counter == 0 && entry.getKey().getName().equals(category.getName())){
                TextView textView = new TextView(this);
                textView.setText(R.string.noItems);
                textView.setTextSize(30);
                textView.setWidth(550);
                textView.setGravity(Gravity.CENTER);
                layout.addView(textView);
            }
        }
        scrollView.addView(layout);
        this.setContentView(scrollView);
    }
}
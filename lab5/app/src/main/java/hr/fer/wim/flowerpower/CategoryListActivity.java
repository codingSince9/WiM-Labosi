package hr.fer.wim.flowerpower;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

import com.example.lab5.R;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.internal.Util;

import static hr.fer.wim.flowerpower.Utils.loadData;

public class CategoryListActivity<data> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        layout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        Map<Category, List<Product>> data = new TreeMap<>();
        data= Utils.loadData();
        int i=0;
        String language = Locale.getDefault().getLanguage();
        Context context = this.getApplicationContext();
        for (Map.Entry<Category, List<Product>> entry : data.entrySet()){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(context.getResources().getIdentifier(entry.getKey().getImgName(), "drawable", context.getPackageName()));
            ++i;
            imageView.getLayoutParams().width=300;
            imageView.getLayoutParams().height=300;
            layout.addView(imageView);
            final Button button = new Button(this);
            button.setText(entry.getKey().getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Category category = entry.getKey();
                    Intent intent = new Intent(CategoryListActivity.this, ProductListActivity.class);
                    intent.putExtra("category", category);
                    startActivity(intent);
                }
            });
            layout.addView(button);
        }
        scrollView.addView(layout);
        this.setContentView(scrollView);
    }
}
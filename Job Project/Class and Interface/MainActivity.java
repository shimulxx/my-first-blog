package com.example.jobproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Details details;
    private RecyclerView recyclerView;
    private AvatarAdapter avatarAdapter;
    private TextView companyName, url, textDetails, page, per_page, total, total_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyName = (TextView)findViewById(R.id.textCompanyName);
        url = (TextView) findViewById(R.id.textUrl);
        textDetails = (TextView) findViewById(R.id.textDetails);
        page = (TextView)findViewById(R.id.textPage);
        per_page = (TextView)findViewById(R.id.textPerPage);
        total = (TextView) findViewById(R.id.textTotal);
        total_pages = (TextView) findViewById(R.id.textTotalPages);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);
        avatarAdapter = new AvatarAdapter(this);
        retrofitWork();
        recyclerView.setAdapter(avatarAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void retrofitWork(){
        Log.d(TAG, "retrofitWork: started");
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<Details> call = retrofitClient.getDetails();
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                details = response.body();
                avatarAdapter.setDataValues(details.getData());
                String pageVal = "Page: " + String.valueOf(details.getPage());
                page.setText(pageVal);
                String perPageVal = "Per Page: " + String.valueOf(details.getPer_page());
                per_page.setText(perPageVal);
                String totalVal = "Total: " + String.valueOf(details.getTotal());
                total.setText(totalVal);
                String totalPagesVal = "Total pages: " + String.valueOf(details.getTotal_pages());
                total_pages.setText(totalPagesVal);
                String companyNameVal = "Company: " + details.getAd().getCompany();
                companyName.setText(companyNameVal);
                String urlVal = "Url: " + details.getAd().getUrl();
                url.setText(urlVal);
                String textDetailsVal = "Text: " + details.getAd().getText();
                textDetails.setText(textDetailsVal);
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.tanuwijaya_202102261.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class ForexActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private TextView usdTextView, nzdTextView, kwdTextView, jpyTextView, jmdTextView, jodTextView, hkdTextView , hnlTextView, copTextView, zwlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex);

        swipeRefreshLayout1 = findViewById(R.id.swipeRefreshLayout1);
        usdTextView = findViewById(R.id.usdTextView);
        nzdTextView = findViewById(R.id.nzdTextView);
        kwdTextView = findViewById(R.id.kwdTextView);
        jpyTextView = findViewById(R.id.jpyTextView);
        jmdTextView = findViewById(R.id.jmdTextView);
        jodTextView = findViewById(R.id.jodTextView);
        hkdTextView = findViewById(R.id.hkdTextView);
        hnlTextView = findViewById(R.id.hnlTextView);
        copTextView = findViewById(R.id.copTextView);
        zwlTextView = findViewById(R.id.zwlTextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        initSwipeRefreshLayout();
        initForex();
    }


    private void initSwipeRefreshLayout() {
        swipeRefreshLayout1.setOnRefreshListener(() -> {
            initForex();

            swipeRefreshLayout1.setRefreshing(false);
        });
    }

    public String formatNumber(double number, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }

    private void initForex() {

        loadingProgressBar.setVisibility(TextView.VISIBLE);

        String url = "https://openexchangerates.org/api/latest.json?app_id=c3ce328dc9494e458ae23d26009e7ba9";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //Log.d("*tw*", new String(responseBody));
                Gson gson = new Gson();
                RootModel rootModel = gson.fromJson(new String(responseBody), RootModel.class);
                RatesModel ratesModel = rootModel.getRatesModel();

                double nzd = ratesModel.getIDR() / ratesModel.getNZD();
                double kwd = ratesModel.getIDR() / ratesModel.getKWD();
                double jpy = ratesModel.getIDR() / ratesModel.getJPY();
                double jmd = ratesModel.getIDR() / ratesModel.getJMD();
                double jod = ratesModel.getIDR() / ratesModel.getJOD();
                double hkd = ratesModel.getIDR() / ratesModel.getHKD();
                double hnl = ratesModel.getIDR() / ratesModel.getHNL();
                double cop = ratesModel.getIDR() / ratesModel.getCOP();
                double zwl = ratesModel.getIDR() / ratesModel.getZWL();
                double idr = ratesModel.getIDR();

                nzdTextView.setText(formatNumber(nzd, "###,##0.##"));
                kwdTextView.setText(formatNumber(kwd, "###,##0.##"));
                jpyTextView.setText(formatNumber(jpy, "###,##0.##"));
                jmdTextView.setText(formatNumber(jmd, "###,##0.##"));
                jodTextView.setText(formatNumber(jod, "###,##0.##"));
                hkdTextView.setText(formatNumber(hkd, "###,##0.##"));
                hnlTextView.setText(formatNumber(hnl, "###,##0.##"));
                copTextView.setText(formatNumber(cop, "###,##0.##"));
                zwlTextView.setText(formatNumber(zwl, "###,##0.##"));
                usdTextView.setText(formatNumber(idr, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });
    }
}
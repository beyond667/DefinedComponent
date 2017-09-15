package paul.com.defined;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        setContentView(new DefinedView(this));
    }


    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            Log.e("aaa","========response==="+response.body());
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    @OnClick(R.id.bt_test)
    void clickTest(){
        Log.e("aaa","========clickTest===");
        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String... strings) {
                String response = "";
                try {
                    response =  run("http://172.30.60.222:8080/login.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("aaa","========response2==="+response);
                return response;
            }
        }.execute("");
    }

}

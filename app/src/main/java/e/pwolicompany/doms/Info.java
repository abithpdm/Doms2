package e.pwolicompany.doms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Size;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Info extends AppCompatActivity {

   private TextView Printresponse;
  public JSONObject symptom_object_id;
  public JSONArray symptomarray;
  String id[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Printresponse=findViewById(R.id.responsetext);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.infermedica.com/v2/symptoms")
                    .method("GET", null)
                    .addHeader("App-Id", "18bbee9d")
                    .addHeader("App-Key", "9ae9bbda28bc6dea2aefaed014632c26")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Interview-Id", "r8oK9tf83dEtwZm9bBJU")
                    .addHeader("Dev-Mode", "true")
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){

                        final String httpresponse = response.body().string();
                        try {
                             symptomarray = new JSONArray(httpresponse);
                             id=new String[symptomarray.length()];
                            for (int i = 0; i<symptomarray.length(); i++)
                            {
                                symptom_object_id = symptomarray.getJSONObject(i);
                                id[i]=symptom_object_id.getString("common_name");
                            }
                            //JSONObject symptomobj= new JSONObject(httpresponse);
                           // final JSONArray symptomsarray = symptomobj.getJSONArray("common_name");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Info.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               /* for (int i=0;i<symptom_object_id.length();i++)
                               {
                                    Printresponse.append(id[i]+"\n");
                                }*/
                               Printresponse.setText(symptomarray.toString());


                            }
                        });

                    }
                }
            });
           /* try {
                Response response = client.newCall(request).execute();
                final String httpresponse = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }*/


        }
    }


package hospital.code.com.onthesheets.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hospital.code.com.onthesheets.Adapters.Sheets_Adapter;
import hospital.code.com.onthesheets.Models.Sheet;
import hospital.code.com.onthesheets.Models.Subjects;
import hospital.code.com.onthesheets.MySingleTon.MySingleTon;
import hospital.code.com.onthesheets.R;

public class Sheets_Activity extends AppCompatActivity {
ArrayList<Sheet>arrayList;
ListView listView;
Sheets_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheets_);
        arrayList=new ArrayList<Sheet>();
        listView=(ListView)findViewById(R.id.lv_sheets) ;


        fill_list();

    }

    private void fill_list() {
        String url="https://onthesheets.000webhostapp.com/sheets/select.php";
        StringRequest request=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                read(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                final Toast toast;
                toast=Toast.makeText(Sheets_Activity.this,"connection error",Toast.LENGTH_LONG);
                toast.show();
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            toast.cancel();
                            fill_list();


                        }catch (Exception e){

                        }
                    }
                });
                t.start();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map=new HashMap();
                map.put("id_wanted",getIntent().getStringExtra("id_teacher").toString());
                return map;
            }
        };
        MySingleTon.getMinstance(Sheets_Activity.this).addRequestQueuex(request);
    }

    private void read(String response) {
        try {
            JSONObject object=new JSONObject(response);
            JSONArray result=object.getJSONArray("result");
            String id;String name;String created_at;String id_teacher;String lesson;String drive;
            for(int i=0;i<result.length();i++){
                JSONObject sheet=result.getJSONObject(i);
                id=sheet.getString("id");
                name=sheet.getString("name");
                created_at=sheet.getString("created_at");
                id_teacher=sheet.getString("id_teacher");
                lesson=sheet.getString("lesson");
                drive=sheet.getString("drive");
                Sheet sheet_element=new Sheet(id,name,created_at,id_teacher,lesson,drive);
                arrayList.add(sheet_element);

            }
            adapter=new Sheets_Adapter(Sheets_Activity.this,R.layout.custom_sheets_list,arrayList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

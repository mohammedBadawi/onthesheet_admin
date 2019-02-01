package hospital.code.com.onthesheets.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import hospital.code.com.onthesheets.Adapters.Teacher_Adapter;
import hospital.code.com.onthesheets.Models.Teacher;
import hospital.code.com.onthesheets.MySingleTon.MySingleTon;
import hospital.code.com.onthesheets.R;

public class TeachersActivity extends AppCompatActivity {

    ArrayList<Teacher> arrayList;
    ListView listView;
    Teacher_Adapter adapter;
    FloatingActionButton fb_add_teachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teachers);
        arrayList = new ArrayList<Teacher>();
        listView=(ListView)findViewById(R.id.lv_teachers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TeachersActivity.this,Sheets_Activity.class);
                intent.putExtra("id_teacher",arrayList.get(position).getId());
                startActivity(intent);
            }
        });
        fill_list();

    }

    private void fill_list() {


        String url = "https://onthesheets.000webhostapp.com/teachers/select.php";
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                read(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                final Toast toast;
                toast=Toast.makeText(TeachersActivity.this,"connection error",Toast.LENGTH_LONG);
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                    String id_wanted=getIntent().getStringExtra("id_subject").toString();
                    Map map = new HashMap();
                map.put("id_wanted",id_wanted);
                    return map;


            }
        };
        MySingleTon.getMinstance(TeachersActivity.this).addRequestQueuex(stringRequest);
    }




    private void read(String response) {
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
            String id;String name;String id_subject;

            for(int i=0;i<jsonArray.length();i++){
                JSONObject teacher=jsonArray.getJSONObject(i);
                id=teacher.getString("id");
                name=teacher.getString("name");
                id_subject=teacher.getString("id_subject");
                Teacher member=new Teacher(id,name,id_subject);
                arrayList.add(member);

            }
            adapter=new Teacher_Adapter(TeachersActivity.this,R.layout.custom_teacher_list,arrayList);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

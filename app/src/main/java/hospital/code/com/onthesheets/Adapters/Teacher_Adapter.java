package hospital.code.com.onthesheets.Adapters;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hospital.code.com.onthesheets.Models.Subjects;
import hospital.code.com.onthesheets.Models.Teacher;
import hospital.code.com.onthesheets.MySingleTon.MySingleTon;
import hospital.code.com.onthesheets.R;


public class Teacher_Adapter extends ArrayAdapter<Teacher> {
    ArrayList<Teacher> item;
    Context context;
    int resource;

    public Teacher_Adapter(Context context, int resource ,ArrayList<Teacher> item) {
        super(context, resource , item);
        this.context = context ;
        this.resource = resource ;
        this.item = item ;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_teacher_list, null, true);

        }

        TextView name=(TextView) view.findViewById(R.id.teacher_name);
        name.setText(item.get(position).getName().toString());




        return view;}


}
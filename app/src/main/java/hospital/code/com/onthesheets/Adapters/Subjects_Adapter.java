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
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import hospital.code.com.onthesheets.Models.Subjects;
import hospital.code.com.onthesheets.MySingleTon.MySingleTon;
import hospital.code.com.onthesheets.R;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop;


public class Subjects_Adapter extends ArrayAdapter<Subjects> {
    ArrayList<Subjects> item;
    Context context;
    int resource;

    public Subjects_Adapter(Context context, int resource ,ArrayList<Subjects> item) {
        super(context, resource , item);
        this.context = context ;
        this.resource = resource ;
        this.item = item ;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_subjects_list, null, true);

        }

        ImageView logo=(ImageView)view.findViewById(R.id.iv_subject_logo);
        TextView name=(TextView) view.findViewById(R.id.tv_subject_name);
        name.setText(item.get(position).getName());
        String image_url=item.get(position).getLogo().toString();
        Glide.with(context).load(image_url).placeholder(R.mipmap.placeholder).into(logo);



    return view;}



}
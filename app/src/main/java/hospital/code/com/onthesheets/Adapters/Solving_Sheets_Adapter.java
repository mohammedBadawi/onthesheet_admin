package hospital.code.com.onthesheets.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import hospital.code.com.onthesheets.Models.Solving_Sheet;
import hospital.code.com.onthesheets.MySingleTon.MySingleTon;
import hospital.code.com.onthesheets.R;

public class Solving_Sheets_Adapter extends ArrayAdapter<Solving_Sheet> {
    ArrayList<Solving_Sheet> item;
    Context context;
    int resource;

    public Solving_Sheets_Adapter(Context context, int resource ,ArrayList<Solving_Sheet> item) {
        super(context, resource , item);
        this.context = context ;
        this.resource = resource ;
        this.item = item ;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_solving_sheets_list, null, true);

        }

        TextView name=(TextView) view.findViewById(R.id.tv_solving_sheet_name);
        TextView lesson=(TextView) view.findViewById(R.id.tv_solving_sheet_lesson);
        TextView created_at=(TextView) view.findViewById(R.id.tv_solving_sheet_created_at);
        ImageView drive=(ImageView) view.findViewById(R.id.iv_drive_solving_sheets);
        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse(item.get(position).getDrive()); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getContext(),"Link is not available yet",Toast.LENGTH_LONG).show();
                }

            }
        });

        name.setText(item.get(position).getName());
        lesson.setText("("+item.get(position).getLesson()+")");
        created_at.setText(item.get(position).getCreated_at());


        return view;}



}

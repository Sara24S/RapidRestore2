package com.example.rapidrestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {

    JSONArray data;


    String[] names;// will be used to hold the names such as php, html,...
    //Names
    String[] provNames;
    //Country
    String[] country;
    Context context; // will be used to reference the activity that contains the listview
    int[] imageIDs; // will be used to hold the ids of the images in the drawable folder
    //Rate
    int[] rateIDs;
    LayoutInflater inflater = null;

    public CustomAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public CustomAdapter(Context context, String[] names, int[] imageIDs, String[] provNames, int[] rateIDs, String[] country) {
        this.names = names;
        this.context = context;
        this.imageIDs = imageIDs;
        //country
        this.country = country;
        //Names
        this.provNames = provNames;
        //Rate
        this.rateIDs = rateIDs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*@Override
    public int getCount() {
        return names.length;
    }
     */
    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class Holder {
        TextView tv;
        //Names
        TextView pName;
        //country
        TextView country;
        ImageView img;
        //rate
        ImageView rate;


        //TextView textViewName, textViewType, textViewPrice;
        TextView textViewName, textViewProfession, textViewRegion;
        ImageView imageViewDelete;

    }
/*
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.provider_profile, null);
        holder.textViewName = rowView.findViewById(R.id.dname);
        holder.textViewProfession = rowView.findViewById(R.id.dtype);
        holder.textViewRegion = rowView.findViewById(R.id.dprice);
        holder.img = rowView.findViewById(R.id.delete);
        // fill them with data:
        // Data is in the json object that was passed to the constructor
        // and we named it "data".
        // extract the object from the json array
        //optJSONObject() is the same as getJSONObject() but better in case the object is null
        // position is the current position, it increments itself
        JSONObject obj = data.optJSONObject(position);
        try {
            holder.textViewName.setText(obj.getString("name"));
            holder.textViewType.setText(obj.getString("type"));

            holder.textViewPrice.setText(obj.getString("price"));
            holder.imageViewDelete.setTag(obj.getInt("id"));

            holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "http://10.0.2.2/restaurant/deletedish.php?id=" +
                            holder.imageViewDelete.getTag();
                    RequestQueue queue = Volley.newRequestQueue(context);
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("success")){
                                // force the listview to refresh
                                // call onresume in the activity again
                                ((MainActivity)context).onResume();
                            }
                            else {
                                Toast.makeText(context, "Delete failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(request);
                }
            }); //setOnClickListener
        }  //try
        catch (JSONException e) { }
        return rowView;
    }

 */
    


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

// i is the position. starts at 0 and keeps on incrementing till the end of the data
        Holder holder = new Holder();

        final View rowView;

        rowView = inflater.inflate(R.layout.provider_profile, null);
        holder.tv = rowView.findViewById(R.id.textView);
        holder.img = rowView.findViewById(R.id.imageView);
        //Names
        holder.pName = rowView.findViewById(R.id.prov_name);
        //Rate
        holder.rate = rowView.findViewById(R.id.starRate);
        //country
        holder.country = rowView.findViewById(R.id.country);

        holder.tv.setText(names[i]);
        holder.img.setImageResource(imageIDs[i]);
        //Names
        holder.pName.setText(provNames[i]);
        //rate
        holder.rate.setImageResource(rateIDs[i]);
        //country
        holder.country.setText(country[i]);


        rowView.setTag(i); // here we are tagging (adding) the value i (position) to our view (row)

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = names[Integer.parseInt(rowView.getTag().toString())];
                Toast.makeText(context, name, Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    } // end of getView()
}



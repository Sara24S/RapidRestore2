package com.example.rapidrestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

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

    @Override
    public int getCount() {
        return names.length;
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

    }

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



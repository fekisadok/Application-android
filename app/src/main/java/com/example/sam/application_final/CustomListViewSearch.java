package com.example.sam.application_final;

/**
 * Created by sam on 18/03/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListViewSearch extends ArrayAdapter<Search> {

    ArrayList<Search> searchs;
    Context context;
    int resource;


    public CustomListViewSearch(Context context, int resource, ArrayList<Search> searchs) {
        super(context, resource, searchs);
        this.searchs = searchs;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.search, null, true);

        }
        Search search = getItem(position);


        ImageView imageView = (ImageView) convertView.findViewById(R.id.Avatar);
            Picasso.with(context).load("http://192.168.43.194"+search.getAvatar()).fit().centerCrop().into(imageView);




        TextView txtPseudo = (TextView) convertView.findViewById(R.id.Pseudo);
        txtPseudo.setText(search.getPseudo());



        return convertView;
    }


}
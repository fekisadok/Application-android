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

public class CustomListViewProfilPublic extends ArrayAdapter<Micropost_profil_public> {

    ArrayList<Micropost_profil_public> microposts;
    Context context;
    int resource;


    public CustomListViewProfilPublic(Context context, int resource, ArrayList<Micropost_profil_public> microposts) {
        super(context, resource, microposts);
        this.microposts = microposts;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.micropost, null, true);

        }
        Micropost_profil_public micropost_profil_public = getItem(position);


        ImageView imageView = (ImageView) convertView.findViewById(R.id.Avatar);
        Picasso.with(context).load("http://192.168.43.194"+micropost_profil_public.getAvatar()).fit().centerCrop().into(imageView);



        TextView txtPseudo = (TextView) convertView.findViewById(R.id.Pseudo);

            //final String pseudo = sessionManager.getPseudo();
            txtPseudo.setText(micropost_profil_public.getPseudo());


        TextView txtDateMicropost = (TextView) convertView.findViewById(R.id.DateMicropost);
        txtDateMicropost.setText(micropost_profil_public.getCreated_at());

        TextView txtMicropost = (TextView) convertView.findViewById(R.id.txtMicropost);
        txtMicropost.setText(micropost_profil_public.getContenu());

        return convertView;
    }

}
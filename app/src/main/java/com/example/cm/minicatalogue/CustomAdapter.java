package com.example.cm.minicatalogue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<User> {
    ArrayList<User> users;
    Context context;

    public CustomAdapter(Context c, ArrayList<User> users){
        super(c,0,users);
        this.context = c;
        this.users= users;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mStatus;
    }
    @NonNull
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        View v = convertView ;
        ViewHolder vHolder ;
        if(v==null){
            v = LayoutInflater.from(context).inflate(R.layout.line,parent,false);
            vHolder = new ViewHolder();
            vHolder.mName = (TextView) v.findViewById(R.id.nameView);
            vHolder.mStatus = (TextView) v.findViewById(R.id.statusView);
            vHolder.mImage = (ImageView) v.findViewById(R.id.pictureView);
            v.setTag(vHolder);
        }
        else { vHolder = (ViewHolder) v.getTag(); }
        User him = users.get(pos);
        ImageView img = vHolder.mImage;
        //img.setImageResource(him.getPicture());
        //img.setImageResource(him.getImage().);
        //img.setImageResource(R.drawable.cm_nasser);

        //img.setImageBitmap(( (BitmapDrawable) him.getImage().getDrawable()).getBitmap());
        //Bitmap bitmap= Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888);
  /*      try{
            //bitmap = ((BitmapDrawable) him.getImage().getDrawable()).getBitmap();
            bitmap = him.getBm();
            System.out.println("bitmap charged !");
            //re-use bitmap somehow?
        }
        catch(NullPointerException e){
//Bitmap dont exists
            System.out.println("bitmap not charged !");

        }*/
        System.out.println("befor img.set()");
        img.setImageBitmap(him.getBm());
        System.out.println("after img.set()");

        //img.setImageResource(him.getImage().);

        TextView name = vHolder.mName;
        //TextView name = (TextView) v.findViewById(R.id.name);
        name.setText(him.getName());
        TextView status = vHolder.mStatus;
        //TextView status = (TextView) v.findViewById(R.id.status);
        status.setText(him.getStatus());
        return v;
    }
}

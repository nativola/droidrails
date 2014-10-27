package com.androidrestclient.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidrestclient.R;
import com.androidrestclient.models.Provinces;

import java.util.List;

/**
 * Created by hanzel on 10/27/14.
 */
public class ProvinceAdapter  extends BaseAdapter {
    private Context context;
    private List<Provinces> provinceList;

    // Constructor
    public ProvinceAdapter(Context context, List<Provinces> provinceList){
        this.context = context;
        this.provinceList = provinceList;
    }

    @Override
    public int getCount() {
        return this.provinceList.size();
    }

    @Override
    public Provinces getItem(int position) {
        return this.provinceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        Provinces currentItem = getItem(position);
        // Inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_user, null);

        TextView name = (TextView) convertView.findViewById(R.id.item_name_text);
        /* Set the name */
        name.setText(currentItem.getName());

        return convertView;
    }
}

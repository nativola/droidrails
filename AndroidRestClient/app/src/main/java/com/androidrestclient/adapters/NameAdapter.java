package com.androidrestclient.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.androidrestclient.R;
import java.util.ArrayList;
import dto.User;

/**
 * Created by hanzel on 10/15/14.
 */
public class NameAdapter extends ArrayAdapter<User> {
    public NameAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.item_name_text);
        tvName.setText(user.first_name);
        // Return the completed view to render on screen
        return convertView;
    }
}

package com.androidrestclient.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androidrestclient.R;
import java.util.List;
import com.androidrestclient.models.User;

/**
 * Created by HanzelCruz on 10/15/14.
 */

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    // Constructor
    public UserAdapter(Context context, List<User> users){
        this.context = context;
        this.userList = users;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public User getItem(int position) {
        return this.userList.get(position);
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
        User currentItem = getItem(position);
        // Inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_user, null);

        TextView name = (TextView) convertView.findViewById(R.id.item_name_text);
        /* Set the name */
        name.setText(currentItem.first_name);

        return convertView;
    }
}

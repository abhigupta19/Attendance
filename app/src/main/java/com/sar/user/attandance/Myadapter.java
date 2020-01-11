package com.sar.user.attandance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends BaseAdapter {

    public Context ba_context;
    public ArrayList<String> listitem = new ArrayList<String>();
    public LayoutInflater inflater;

    public Myadapter(Context ma_context, ArrayList<String> ma_listitem) {
        super();
        this.ba_context = ma_context;
        this.listitem = ma_listitem;
        Log.e("MyBaseAdapter", "getCounlt() => " + this.listitem.size());
        inflater = (LayoutInflater) ba_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.e("MyBaseAdapter", "getCount() => " + this.listitem.size());
        return this.listitem.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        Log.i("MyBaseAdapter", "String=>" + this.listitem.get(position).toString());
        if (convertView == null)
            vi = inflater.inflate(R.layout.card, parent, false);

        TextView tvData = (TextView) vi.findViewById(R.id.textView36);

        String data = listitem.get(position).toString();

        tvData.setText(data);

        return vi;
    }
}


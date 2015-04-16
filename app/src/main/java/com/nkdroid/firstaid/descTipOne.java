package com.nkdroid.firstaid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class descTipOne extends ActionBarActivity {

    private ListView listDescTipsOne;
    private ArrayList<String> postArrayList;
    private PostAdapter postAdapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_tip_one);
        postArrayList = new ArrayList<String>();
        postArrayList.add("1");
        postArrayList.add("2");
        postArrayList.add("3");
        postArrayList.add("4");
        postArrayList.add("5");
        postArrayList.add("6");
        postArrayList.add("7");



        listDescTipsOne = (ListView) findViewById(R.id.listDescTipsOne);
        postAdapter = new PostAdapter(descTipOne.this, postArrayList);
        listDescTipsOne.setAdapter(postAdapter);

        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Heart Attack Tips");
            setSupportActionBar(toolbar);
        }
    }

    public class PostAdapter extends BaseAdapter {

        Context context;
        ArrayList<String> postArrayList;

        public PostAdapter(Context context, ArrayList<String> postArrayList) {

            this.context = context;
            this.postArrayList = postArrayList;
        }

        public int getCount() {
            return postArrayList.size();
        }

        public Object getItem(int position) {
            return postArrayList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView txtDescTitle,txtDesc,txtSeq;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_desctipsone, parent, false);
                holder = new ViewHolder();
                holder.txtSeq = (TextView) convertView.findViewById(R.id.txtSeq);
                holder.txtDescTitle = (TextView) convertView.findViewById(R.id.txtDescTitle);
                holder.txtDesc = (TextView) convertView.findViewById(R.id.txtDesc);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txtSeq.setText(postArrayList.get(position));
//            holder.txtPostDate.setText(postArrayList.get(position).getPost_date());
//            holder.txtPostDescription.setText(postArrayList.get(position).getDescription());

            return convertView;
        }}



}

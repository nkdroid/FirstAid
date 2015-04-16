package com.nkdroid.firstaid;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AnnouncementActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ListView tipsList;
    private ArrayList<String> postArrayList;
    private PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        postArrayList = new ArrayList<String>();
        postArrayList.add("Swine Flu");
        postArrayList.add("Heat wave");
        postArrayList.add("Tips for summer");
        postArrayList.add("Skin burn");
        postArrayList.add("Dehydration");
        postArrayList.add("title 1");
        postArrayList.add("title 2");
        postArrayList.add("title 3");
        postArrayList.add("title 4");
        postArrayList.add("title 5");

        tipsList = (ListView) findViewById(R.id.announcementList);
        postAdapter = new PostAdapter(AnnouncementActivity.this, postArrayList);
        tipsList.setAdapter(postAdapter);

        setToolbar();
    }
        private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Notification");
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
            TextView txtPostTitle, txtPostDate, txtPostDescription;
            ImageView imgPost;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_view, parent, false);
                holder = new ViewHolder();
                holder.txtPostTitle = (TextView) convertView.findViewById(R.id.txtPostTitle);
                holder.txtPostDate = (TextView) convertView.findViewById(R.id.txtPostDate);
                holder.txtPostDescription = (TextView) convertView.findViewById(R.id.txtPostDescription);
                holder.imgPost = (ImageView) convertView.findViewById(R.id.imgPost);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txtPostTitle.setText(postArrayList.get(position));
//            holder.txtPostDate.setText(postArrayList.get(position).getPost_date());
//            holder.txtPostDescription.setText(postArrayList.get(position).getDescription());

            Glide.with(AnnouncementActivity.this)
                    .load(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(holder.imgPost);
            return convertView;
        }
    }
}

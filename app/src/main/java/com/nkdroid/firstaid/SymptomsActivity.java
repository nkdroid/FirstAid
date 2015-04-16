package com.nkdroid.firstaid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;


public class SymptomsActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener {

    private SliderLayout mDemoSlider;
    private ListView tipsList;
    private ArrayList<String> postArrayList;
    private PostAdapter postAdapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        setImageSlider();
        postArrayList = new ArrayList<String>();
        postArrayList.add("Allergies");
        postArrayList.add("Anxiety");
        postArrayList.add("Blood clot");

        postArrayList.add("Cold");
        postArrayList.add("Dengue");
        postArrayList.add("Diabetes");
        postArrayList.add("Power outage");
        postArrayList.add("Tornado");
        postArrayList.add("Tsunami");
        postArrayList.add("Water safety");


        tipsList = (ListView) findViewById(R.id.tipsList);
        postAdapter = new PostAdapter(SymptomsActivity.this, postArrayList);
        tipsList.setAdapter(postAdapter);
        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Symptoms");
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
            TextView txtTips;
            ImageView imgTips;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_tips, parent, false);
                holder = new ViewHolder();
                holder.txtTips = (TextView) convertView.findViewById(R.id.txtTips);
                holder.imgTips = (ImageView) convertView.findViewById(R.id.imgTips);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txtTips.setText(postArrayList.get(position));
//            holder.txtPostDate.setText(postArrayList.get(position).getPost_date());
//            holder.txtPostDescription.setText(postArrayList.get(position).getDescription());

            Glide.with(SymptomsActivity.this)
                    .load(R.drawable.heart)
                    .centerCrop()
                    .into(holder.imgTips);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent1 = new Intent(SymptomsActivity.this, Tip1Activity.class);
//                    startActivity(intent1);
//                }
//            });
            return convertView;
        }
    }

    private void setImageSlider() {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);


//        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Fever",R.drawable.fever);
        file_maps.put("Swine Flu",R.drawable.swinwflu);
        file_maps.put("Heart Attack",R.drawable.heartattack);
        file_maps.put("Cold", R.drawable.cold);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);


            //add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);


            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        Toast.makeText(this, baseSliderView.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }
}

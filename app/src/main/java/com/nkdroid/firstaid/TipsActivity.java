package com.nkdroid.firstaid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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
import com.nkdroid.firstaid.model.Tips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class TipsActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener{
    private SliderLayout mDemoSlider;

    private ListView tipsList;
    private ArrayList<Tips> postArrayList;
    private PostAdapter postAdapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        setImageSlider();
        postArrayList = new ArrayList<Tips>();
        postArrayList.add(new Tips("Heart Attack","A heart attack happens when the flow of oxygen-rich blood to a section of heart muscle suddenly becomes blocked and the heart can't get oxygen. If blood flow isn't restored quickly, the section of heart muscle begins to die.",R.drawable.heartattack));
        postArrayList.add(new Tips("Allergies","An allergy is a hypersensitivity disorder of the immune system.Allergic reactions occur when a person's immune system reacts to normally harmless substances in the environment. A substance that causes a reaction is called an allergen.These reactions are acquired, predictable, and rapid.",R.drawable.allergies));
        postArrayList.add(new Tips("Asthma","Asthma (AZ-ma) is a chronic (long-term) lung disease that inflames and narrows the airways. Asthma causes recurring periods of wheezing (a whistling sound when you breathe), chest tightness, shortness of breath, and coughing. The coughing often occurs at night or early in the morning.Asthma affects people of all ages, but it most often starts during childhood.",R.drawable.asthma));
        postArrayList.add(new Tips("Bleeding","Bleeding, technically known as hemorrhaging or haemorrhaging, is blood escaping from the circulatory system.[1] Bleeding can occur internally, where blood leaks from blood vessels inside the body, or externally, either through a natural opening such as the mouth, nose, ear, urethra, vagina or anus, or through a break in the skin.",R.drawable.bleeding));
        postArrayList.add(new Tips("Broken Bone","A bone fracture is a medical condition in which there is a break in the continuity of the bone. A bone fracture can be the result of high force impact or stress, or a minimal trauma injury as a result of certain medical conditions that weaken the bones, such as osteoporosis, bone cancer, or osteogenesis imperfecta, where the fracture is then properly termed a pathologic fracture.",R.drawable.brokenbone));
        postArrayList.add(new Tips("Burns","A burn is a type of injury to flesh or skin caused by heat, electricity, chemicals, friction, or radiation. When damage penetrates into some of the underlying layers, it is a partial-thickness or second-degree burn. In a full-thickness or third-degree burn, the injury extends to all layers of the skin. A fourth-degree burn additionally involves injury to deeper tissues, such as muscle or bone.",R.drawable.burn));
        postArrayList.add(new Tips("Diabetic Emergency","Diabetes, often referred to by doctors as diabetes mellitus, describes a group of metabolic diseases in which the person has high blood glucose (blood sugar), either because insulin production is inadequate, or because the body's cells do not respond properly to insulin, or both. Patients with high blood sugar will typically experience polyuria (frequent urination), they will become increasingly thirsty (polydipsia) and hungry (polyphagia).",R.drawable.diabete));
        postArrayList.add(new Tips("Head Injury","Any injury that results in trauma to the skull, or brain can be classified as a head injury. The terms traumatic brain injury and head injury are often used interchangeably in medical literature.This broad classification includes neuronal injuries, hemorrhages, vascular injuries, cranial nerve injuries, and subdural hygromas, among many others.These classifications can be further categorized as open (penetrating) or closed head injuries. This depends on if the skull was broken or not",R.drawable.headinjury));
        postArrayList.add(new Tips("Heat Stroke","Heat stroke (also known as heatstroke, sun stroke or sunstroke) is a severe heat illness, defined as hyperthermia with a body temperature greater than 40.6 °C (105.1 °F) due to environmental heat exposure with lack of thermoregulation. This is distinct from a fever, where there is a physiological increase in the temperature set point of the body. The term 'stroke' in 'heat stroke' is a misnomer in that it does not involve a blockage or hemorrhage of blood flow to the brain. Treatment requires rapid physical cooling of the body.",R.drawable.heatstroke));
        postArrayList.add(new Tips("Poisoning","A poison is any substance that is harmful to your body. You might swallow it, inhale it, inject it, or absorb it through your skin. Any substance can be poisonous if too much is taken.",R.drawable.poisoning));
        postArrayList.add(new Tips("Shock","Shock is a life-threatening condition that occurs when the body is not getting enough blood flow. Lack of blood flow means that the cells and organs do not get enough oxygen and nutrients to function properly. Multiple organs can suffer damage as a result. Shock requires immediate medical treatment and can get worse very rapidly.",R.drawable.shock));



        tipsList = (ListView) findViewById(R.id.tipsList);
        postAdapter = new PostAdapter(TipsActivity.this, postArrayList);
        tipsList.setAdapter(postAdapter);

        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Tips");
            setSupportActionBar(toolbar);
        }
    }

    public class PostAdapter extends BaseAdapter {

        Context context;
        ArrayList<Tips> postArrayList;
        ArrayList<Tips> arraylist;

        public PostAdapter(Context context, ArrayList<Tips> postArrayList) {

            this.context = context;
            this.postArrayList = postArrayList;
            arraylist = new ArrayList<Tips>();
            arraylist.addAll(postArrayList);
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
            holder.txtTips.setText(postArrayList.get(position).title);
//            holder.txtPostDate.setText(postArrayList.get(position).getPost_date());
//            holder.txtPostDescription.setText(postArrayList.get(position).getDescription());

            Glide.with(TipsActivity.this)
                    .load(postArrayList.get(position).imagePath)
                    .centerCrop()
                    .into(holder.imgTips);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(TipsActivity.this, Tip1Activity.class);
                    intent1.putExtra("title",postArrayList.get(position).title);
                    intent1.putExtra("description",postArrayList.get(position).description);
                    intent1.putExtra("image",postArrayList.get(position).imagePath);
                    startActivity(intent1);
                }
            });
            return convertView;
        }
        // Filter Class
        public void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());

            postArrayList.clear();
            if (charText.length() == 0) {
                postArrayList.addAll(arraylist);

            } else {
                for (Tips  movieDetails : arraylist) {
                    if (charText.length() != 0 && movieDetails.title.toLowerCase(Locale.getDefault()).contains
                            (charText)) {
                        postArrayList.add(movieDetails);
                    }

                }
            }
            notifyDataSetChanged();
        }
    }

    private void setImageSlider() {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Burn",R.drawable.burn);
        file_maps.put("Head injury",R.drawable.headinjury);
        file_maps.put("Asthma",R.drawable.asthma);
        file_maps.put("poisoning", R.drawable.poisoning);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(MainActivity.this, "called", Toast.LENGTH_SHORT).show();



                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                postAdapter.filter(searchQuery.toString().trim());
                tipsList.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_bar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

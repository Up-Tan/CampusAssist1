package iet.jxufe.cn.campuslife;

import iet.jxufe.cn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class CampusSceneryActivity extends Activity {
	int[] images=new int[]{
			R.drawable.baosige,R.drawable.beihu,R.drawable.chayuan,
			R.drawable.fengyuan,R.drawable.guiyuan,R.drawable.huzhongting,R.drawable.jiaogonglou,
			R.drawable.jiaohu,R.drawable.liyuan,R.drawable.lumiyuan,R.drawable.mailu,
			R.drawable.qifeiting,R.drawable.sanbulang,R.drawable.taoyuan,R.drawable.tiyuguan,
			R.drawable.waijiaoshenghuoqu,R.drawable.xiaomen,R.drawable.yinyuanguangchang,R.drawable.youyongchi,
			R.drawable.zonghedalou	
	};
	private Button goBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campus_scenery);
		goBack=(Button)findViewById(R.id.goBack);
		goBack.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent intent=new Intent(CampusSceneryActivity.this,CampusLifeActivity.class);
				startActivity(intent);
				finish();				
			}
		});
		
		 final Gallery gallery = (Gallery) findViewById(R.id.gallery);	
			final ImageSwitcher switcher = (ImageSwitcher)findViewById(R.id.switcher2);		
			switcher.setFactory(new ViewFactory(){		
				public View makeView(){
					ImageView imageView = new ImageView(CampusSceneryActivity.this);
					imageView.setBackgroundColor(0xff0000);
					imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
					imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					return imageView;
				}
			});		
			switcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
			switcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));		
			BaseAdapter adapter = new BaseAdapter(){			
				public int getCount(){
					return Integer.MAX_VALUE;
					//return images.length;
				}			
				public Object getItem(int position){
					return position;
				}
				public long getItemId(int position){
					return position;
				}
				public View getView(int position, View convertView, ViewGroup parent){
					
					ImageView imageView = new ImageView(CampusSceneryActivity.this);
					imageView
						.setImageResource(images[position % images.length]);
					
					imageView.setScaleType(ImageView.ScaleType.FIT_XY);
					imageView.setLayoutParams(new Gallery.LayoutParams(75, 100));
					TypedArray typedArray = obtainStyledAttributes(
						R.styleable.Gallery);
					imageView.setBackgroundResource(typedArray.getResourceId(
						R.styleable.Gallery_android_galleryItemBackground, 0));
					return imageView;
				}
			};
			gallery.setAdapter(adapter);
			gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {
					switcher.setImageResource(images[position%images.length]);
					
				}
				public void onNothingSelected(AdapterView<?> arg0) {				
					
				}			
			});
	}
}

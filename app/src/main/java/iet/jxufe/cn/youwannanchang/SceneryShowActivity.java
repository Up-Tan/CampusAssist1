package iet.jxufe.cn.youwannanchang;

import iet.jxufe.cn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SceneryShowActivity extends Activity {
	private ImageView imageView;
	private TextView content;
	private Button goBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scenery_show);
		imageView=(ImageView)findViewById(R.id.image);
		content=(TextView)findViewById(R.id.content);
		goBack=(Button)findViewById(R.id.goBack);
		int image=getIntent().getIntExtra("image",R.drawable.guangzhouta);
		String show=getIntent().getStringExtra("content");
		System.out.println(show);
		imageView.setBackgroundResource(image);
		content.setText(show);
		goBack.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				
				Intent intent=new Intent(SceneryShowActivity.this,SceneryActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}

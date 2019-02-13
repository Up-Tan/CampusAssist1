package iet.jxufe.cn.chuxingxinxi;

import iet.jxufe.cn.android.MainActivity;
import iet.jxufe.cn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//出行信息
public class ChuxingxinxiActivity extends Activity {
	private Button xianluchaxun,wozaina,guanjiandian,goBack;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chuxingxinxi);


		xianluchaxun = (Button) findViewById(R.id.xianluchaxun);
		wozaina = (Button) findViewById(R.id.wozaina);
		guanjiandian=(Button)findViewById(R.id.guanjiandian);
		goBack = (Button) findViewById(R.id.back);
		MyOnClickListener myListener = new MyOnClickListener();
		goBack.setOnClickListener(myListener);
		xianluchaxun.setOnClickListener(myListener);
		wozaina.setOnClickListener(myListener);
		guanjiandian.setOnClickListener(myListener);
	}

	private class MyOnClickListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
				case R.id.xianluchaxun:  //线路查询
					intent=new Intent(ChuxingxinxiActivity.this,GongjiaoluxianActivity.class);
					break;
				case R.id.wozaina:   //我的位置
					intent=new Intent(ChuxingxinxiActivity.this,WozainaActivity.class);
					break;
				case R.id.guanjiandian:  //位置查询
					intent=new Intent(ChuxingxinxiActivity.this,GuanjiandianActivity.class);
					break;
				case R.id.back:   //返回
					intent = new Intent(ChuxingxinxiActivity.this,
							MainActivity.class);
					break;
				default:
					intent = new Intent(ChuxingxinxiActivity.this,
							MainActivity.class);
					break;
			}
			startActivity(intent);
		}
	}
}


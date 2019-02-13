package iet.jxufe.cn.android;

import iet.juxfe.cn.phone.PhoneListActivity;
import iet.jxufe.cn.campuslife.CampusLifeActivity;
import iet.jxufe.cn.chuxingxinxi.ChuxingxinxiActivity;
import iet.jxufe.cn.youwannanchang.SceneryActivity;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
//	private Button phoneAssist,trafficAssist,campusLife,scenery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] btnIDs=new int[]{
        		R.id.phoneAssist,R.id.campusLife,R.id.scenery,R.id.trafficAssist};
        Button[] buttons=new Button[btnIDs.length];
        MyOnClickListener myOnClickListener=new MyOnClickListener();
        for(int i=0;i<btnIDs.length;i++){
        	buttons[i]=(Button)findViewById(btnIDs[i]);
        	buttons[i].setOnClickListener(myOnClickListener);
        }
//        phoneAssist=(Button)findViewById(R.id.phoneAssist);
//        trafficAssist=(Button)findViewById(R.id.trafficAssist);
//        campusLife=(Button)findViewById(R.id.campusLife);
//        scenery=(Button)findViewById(R.id.scenery);
//        
//        phoneAssist.setOnClickListener(myOnClickListener);
//        trafficAssist.setOnClickListener(myOnClickListener);
//        campusLife.setOnClickListener(myOnClickListener);
//        scenery.setOnClickListener(myOnClickListener);
    }
    public class MyOnClickListener implements OnClickListener{
    	Intent intent=null;
    	public void onClick(View v) {
    		switch (v.getId()) {
			case R.id.phoneAssist:
				intent=new Intent(MainActivity.this,PhoneListActivity.class);
				//startActivity(intent);
				break;
			case R.id.trafficAssist:
				intent=new Intent(MainActivity.this,ChuxingxinxiActivity.class);
				//startActivity(intent);
				break;
			case R.id.campusLife:
				intent =new Intent(MainActivity.this,CampusLifeActivity.class);
				break;
			case R.id.scenery:
				intent=new Intent(MainActivity.this,SceneryActivity.class);
				//startActivity(intent);
				break;
			default:
				break;
			}	
    		st
			;
		}
    }
}

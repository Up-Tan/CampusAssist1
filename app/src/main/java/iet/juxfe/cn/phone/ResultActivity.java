package iet.juxfe.cn.phone;

import java.util.ArrayList;
import java.util.Map;

import iet.jxufe.cn.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ResultActivity extends Activity {
	private ListView resultList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		resultList = (ListView) findViewById(R.id.result_list);
		Bundle bundle = getIntent().getExtras();
		ArrayList<Map<String, String>> phoneList = (ArrayList<Map<String, String>>) bundle
				.getSerializable("result");
		System.out.println(phoneList);
		System.out.println(phoneList.size());
		SimpleAdapter adapter = new SimpleAdapter(this, phoneList,
				R.layout.child, new String[] { "name", "phone" }, new int[] {
						R.id.name, R.id.phone });
		resultList.setAdapter(adapter);
	}
}

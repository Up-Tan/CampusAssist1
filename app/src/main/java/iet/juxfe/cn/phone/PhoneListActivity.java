package iet.juxfe.cn.phone;


import iet.jxufe.cn.android.R;
import iet.jxufe.cn.db.DBHandler;
import iet.jxufe.cn.db.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleExpandableListAdapter;

public class PhoneListActivity extends ExpandableListActivity {
	public static MyDatabaseHelper myHelper;
	private EditText keyword;
	private Button query;
	DBHandler dbHandler = new DBHandler();
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_list);
		myHelper = new MyDatabaseHelper(this, "phone.db", null, 1);
		db = myHelper.getReadableDatabase();
		String sql = "select distinct type from phone_tb";
		ArrayList<String> type = dbHandler.getType(db, sql);
		ArrayList<Map<String, String>> groups = new ArrayList<Map<String, String>>();
		ArrayList<List<Map<String, String>>> children = new ArrayList<List<Map<String, String>>>();
		for (String str : type) {
			Map<String, String> item = new HashMap<String, String>();
			item.put("group", str);
			groups.add(item);
			ArrayList<Map<String, String>> child = dbHandler.getData(db,
					"select name,phone from phone_tb where type=?", new String[]{str});
			System.out.println(child);
			children.add(child);
		}

		/**
		 * 使用SimpleExpandableListAdapter显示ExpandableListView 参数1.上下文对象Context
		 * 参数2.一级条目目录集合 参数3.一级条目对应的布局文件 参数4.fromto，就是map中的key，指定要显示的对象
		 * 参数5.与参数4对应，指定要显示在groups中的id 参数6.二级条目目录集合 参数7.二级条目对应的布局文件
		 * 参数8.fromto，就是map中的key，指定要显示的对象 参数9.与参数8对应，指定要显示在childs中的id
		 */

		SimpleExpandableListAdapter simpleExpandListAdapter = new SimpleExpandableListAdapter(
				this, groups, R.layout.group, new String[] { "group" },
				new int[] { R.id.group }, children, R.layout.child,
				new String[] { "name", "phone" }, new int[] { R.id.name,
				R.id.phone });
		setListAdapter(simpleExpandListAdapter);
		keyword = (EditText) findViewById(R.id.keyword);
		query = (Button) findViewById(R.id.query);
		query.setOnClickListener(new OnClickListener() {
			String sql = "select name,phone from phone_tb where keyword like ?";

			public void onClick(View v) {
				ArrayList<Map<String, String>> phoneList= dbHandler.getData(db, sql,
						new String[] { "%" + keyword.getText().toString() + "%"});
				Intent intent=new Intent(PhoneListActivity.this,ResultActivity.class);
				Bundle bundle=new Bundle();
				bundle.putSerializable("result", phoneList);
				System.out.println("phoneList="+phoneList);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.phone_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.addphone:
				Intent intent = new Intent(PhoneListActivity.this,
						AddPhoneActivity.class);
				star
				tActivity(intent);
				break;
			case R.id.exit:
				this.finish();
				break;

			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}

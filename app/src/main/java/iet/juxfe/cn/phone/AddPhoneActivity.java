package iet.juxfe.cn.phone;

import iet.jxufe.cn.android.R;
import iet.jxufe.cn.db.DBHandler;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhoneActivity extends Activity {
	private Button submit, reset;
	private EditText name, phone, type, keyword;
	private SQLiteDatabase db = PhoneListActivity.myHelper
			.getReadableDatabase();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_phone);

		submit = (Button) findViewById(R.id.submit);
		reset = (Button) findViewById(R.id.reset);
		name = (EditText) findViewById(R.id.name);
		phone = (EditText) findViewById(R.id.phone);
		type = (EditText) findViewById(R.id.type);
		keyword = (EditText) findViewById(R.id.keyword);
		myOnclickListener myOnclickListener = new myOnclickListener();
		submit.setOnClickListener(myOnclickListener);
		reset.setOnClickListener(myOnclickListener);
	}

	private class myOnclickListener implements OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.submit:
					DBHandler dbHandler = new DBHandler();
					String sql = "insert into phone_tb values(null,?,?,?,?)";
					String keywordStr = keyword.getText().toString();
					if (keywordStr == null || "".equals(keywordStr)) {
						keywordStr = name.getText().toString()
								+ phone.getText().toString();
					}
					dbHandler.insert(db, sql, new String[] {
							name.getText().toString(), phone.getText().toString(),
							type.getText().toString(), keywordStr });
					Toast.makeText(AddPhoneActivity.this, "号码添加成功！", 1000).show();
					Intent intent=new Intent(AddPhoneActivity.this,PhoneListActivity.class);
					startActivity(intent);
					finish();
					break;
				case R.id.reset:
					name.setText("");
					phone.setText("");
					type.setText("");
					keyword.setText("");
					break;
				default:
					break;
			}

		}
	}
}

package iet.jxufe.cn.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	final String CREATE_TABLE_SQL=
			"create table phone_tb(_id integer primary " +
					"key autoincrement,name,phone,type,keyword)";
	public MyDatabaseHelper(Context context, String name,
							CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
		init(db);
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion,
						  int newVersion) {
		System.out.println("---------"+oldVersion+"------->"+newVersion);
	}
	public void init(SQLiteDatabase db){
		db.execSQL("insert into phone_tb values (null,'信息工程学院','39322253','学院号码','信息工程学院39322253')");
		db.execSQL("insert into phone_tb values (null,'自动化学院','39322552','学院号码','自动化学院39322552')");
		db.execSQL("insert into phone_tb values (null,'机电工程学院','39322212','学院号码','机电工程学院39322212')");
		db.execSQL("insert into phone_tb values (null,'轻工化工学院','39322231','学院号码','轻工化工学院39322231')");
		db.execSQL("insert into phone_tb values (null,'土木与交通工程学院','39322527','学院号码','土木与交通工程学院39322527')");
		db.execSQL("insert into phone_tb values (null,'计算机学院','39322279','学院号码','计算机学院39322279')");
		db.execSQL("insert into phone_tb values (null,'材料与能源学院','39322570','学院号码','材料与能源学院39322570')");
		db.execSQL("insert into phone_tb values (null,'环境科学与工程学院','39322291','学院号码','环境科学与工程学院39322291')");
		db.execSQL("insert into phone_tb values (null,'外国语学院','39322187','学院号码','外国语学院39322187')");
		db.execSQL("insert into phone_tb values (null,'物理与光电工程学院','39322265','学院号码','物理与光电工程学院39322265')");
		db.execSQL("insert into phone_tb values (null,'艺术与设计学院','37628060','学院号码','艺术与设计学院37628060')");
		db.execSQL("insert into phone_tb values (null,'建筑与城市规划学院','37627791','学院号码','建筑与城市规划学院37627791')");
		db.execSQL("insert into phone_tb values (null,'继续教育学院','37627789','学院号码','继续教育学院37627789')");
		db.execSQL("insert into phone_tb values (null,'管理学院','87080366','学院号码','管理学院87080366')");
		db.execSQL("insert into phone_tb values (null,'应用数学学院','87084696','学院号码','应用数学学院87084696')");
		db.execSQL("insert into phone_tb values (null,'政法学院','87580504','学院号码','政法学院87580504')");
		db.execSQL("insert into phone_tb values (null,'经济与贸易学院','87080256','学院号码','经济与贸易学院87080256')");
		db.execSQL("insert into phone_tb values (null,'马克思主义学院','87080070','学院号码','马克思主义学院87080070')");
		db.execSQL("insert into phone_tb values (null,'刘喜英老师','13640207088','老师号码','刘喜英老师13640207088')");
		db.execSQL("insert into phone_tb values (null,'陈俊耀老师','13560063586','老师号码','陈俊耀老师13560063586')");
		db.execSQL("insert into phone_tb values (null,'罗思杰老师','13926081313','老师号码','罗思杰老师13926081313')");
		db.execSQL("insert into phone_tb values (null,'翟因虎老师','13925070721','老师号码','翟因虎老师13925070721')");
		db.execSQL("insert into phone_tb values (null,'阿亮麻辣烫','18826455228','订餐热线','阿亮麻辣烫18826455228')");
		db.execSQL("insert into phone_tb values (null,'Q堡堡','18577448653','订餐热线','Q堡堡18577448653')");
		db.execSQL("insert into phone_tb values (null,'多滋味瓦罐饭','13662255711','订餐热线','多滋味瓦罐饭13662255711')");
		db.execSQL("insert into phone_tb values (null,'味驰','13798174641','订餐热线','味驰13798174641')");

	}
}

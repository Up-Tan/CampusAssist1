package iet.jxufe.cn.youwannanchang;

import iet.jxufe.cn.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SceneryActivity extends Activity {
	int[] images = new int[] { R.drawable.xiguan,R.drawable.guangzhouta,
			R.drawable.nanyuewang, R.drawable.baiyunshan,
			R.drawable.chenjiaci, R.drawable.shawanguzhen,
			R.drawable.zhujiangyeyou };
	String[] names = new String[] { "西关","广州塔", "西汉南越王博物馆", "白云山", "陈家祠", "沙湾古镇",
			"珠江新城" };
	String[] briefs = new String[] { "食在广州，味在西关","中国第一高塔", "汉代彩绘石室墓",
			"羊城第一秀", "百年陈氏书院", "水乡文化古村落",
			"广州新城，CBD" };
	String[] contents = new String[] {
			"\t\t西关，明清时地处南海县管辖的广州城西门外一带地方的统称，由南海县县府直辖，位于今日荔湾区，北接龙津路，南濒珠江，东至人民路，西至荔枝湾。西关分为上西关和下西关，其中上西关地势较高，下西关地势较低。明末兴建起十八甫，开设有十三行。清朝中后期起，西关先后兴建了宝华街、逢源街、多宝街等居民住宅区。西关是当时广州城西面的地区，因明清时地处南海县府管辖的广州城西门外而得名（但当时非属广州城），在明清时期这里就是南海县乃至整个广东省的商贸中心。",
			"\t\t广州塔又称广州新电视塔，昵称小蛮腰。位于广州市海珠区（艺洲岛）赤岗塔附近，距离珠江南岸125米，与珠江新城、花城广场、海心沙岛隔江相望。广州塔塔身主体高454米，天线桅杆高146米，总高度600米。是中国第一高塔，世界第二高塔，仅次于东京晴空塔，是国家AAAA级旅游景区。",
			"\t\t西汉南越王博物馆是1983年发现的南越国第二代国王赵眜之墓，是岭南地区所发现的规模最大的唯一汉代彩绘石室墓。墓中出土文物一万余件，其中“文帝行玺”金印、玉角杯、错 金铭文虎节、印花铜板模、平板玻璃铜牌饰等文物具有重大历史、科学、艺术价值，集中反映了两千年前岭南政治、经济和文化等多方面的内容。 ",
			"\t\t白云山，位于广东省广州市白云区，为南粤名山之一，自古就有“羊城第一秀”之称。山体相当宽阔，由30多座山峰组成，为广东最高峰九连山的支脉。面积20.98平方公里，主峰摩星岭高382米。\n\t\t白云山是广州市风景区行业的第一家国家AAAAA级旅游景区，也是广州市唯一同时拥有全国文明风景旅游区、国家AAAAA级旅游景区两项荣誉的景区。",
			"\t\t陈氏书院，俗称陈家祠，位于广州市中山七路。陈氏书院筹建于清光绪十四年（1888），二十年（1894）落成。陈氏书院是广东规模最大、装饰华丽、保存完好的传统岭南祠堂式建筑，被誉为“岭南建筑艺术明珠”，它集中了广东民间建筑装饰艺术之大成，巧妙运用木雕、砖雕、石雕、灰塑、陶塑、铜铁铸和彩绘等装饰艺术，是一座民间装饰艺术的璀璨殿堂。陈家祠的建筑雕塑饰件多达284件，包括石雕58件、木雕57件、灰塑57件、陶塑58件、砖雕41件、铜铁铸和壁画13件。陈家祠是全国重点文物保护单位。\n\t\t陈家祠也是广东民间工艺博物馆的所在地，是国家一级博物馆。",
			"\t\t沙湾古镇始建于南宋，是一个有着800多年历史的岭南文化古镇，历史文化资源丰富，民间艺术饮誉南国。先后获授中国民间艺术之乡、中国历史文化名镇、中国兰花名镇、全国文明镇、国家珠江新城是广州天河CBD的主要组成部分。天河CBD是国务院批准的三大国家级中央商务区之一（另外两个为北京CBD与上海陆家嘴CBD）  ，主要服务于珠三角经济圈，是华南地区最大的CBD、唯一的世界商务区联卫生镇等荣誉称号。2017年6月，获评国家AAAA级旅游景区。\n\t\t在800多年的发展历史中，沙湾古镇形成并保留了以传统历史文化和民间文化为主体的岭南文化，是以珠江三角洲为核心的广府文化的杰出代表，物质文化遗产和非物质文化遗产资源丰富，大量祠堂、庙宇等古建筑和商业遗址、民居遗址保存完好，广东音乐、飘色、龙狮、兰花、饮食等民间艺术和民俗文化长盛不衰。",
			"\t\t盟成员、粤港澳服务贸易自由化示范基地，已成为华南地区总部经济和金融、科技、商务等高端产业高度集聚区。"  };

	private ListView myList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scenery);
		myList = (ListView) findViewById(R.id.sceneryList);
		ArrayList<Map<String, String>> sceneryList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, String> sceneryItem = new HashMap<String, String>();
			sceneryItem.put("name", names[i]);
			sceneryItem.put("brief", briefs[i]);
			sceneryItem.put("image", images[i] + "");
			sceneryList.add(sceneryItem);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, sceneryList,
				R.layout.scenery_item,
				new String[] { "image", "name", "brief" }, new int[] {
				R.id.image, R.id.name, R.id.brief });
		myList.setAdapter(adapter);
		myList.setOnItemClickListener(new myOnItemClickListener());

	}
	private class myOnItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			Intent intent = new Intent();
			intent.setClass(SceneryActivity.this, SceneryShowActivity.class);
			intent.putExtra("image", images[position]);
			intent.putExtra("content", contents[position]);
			startActivity(intent);
		}
	}
}

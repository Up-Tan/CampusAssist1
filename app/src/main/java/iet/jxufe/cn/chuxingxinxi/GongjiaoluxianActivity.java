package iet.jxufe.cn.chuxingxinxi;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

import iet.jxufe.cn.android.R;

public class GongjiaoluxianActivity extends FragmentActivity implements
        OnGetPoiSearchResultListener, OnGetBusLineSearchResultListener,
        BaiduMap.OnMapClickListener {

    private Button mBtnPre = null; // 上一个节点
    private Button mBtnNext = null; // 下一个节点
    private int nodeIndex = -2; // 节点索引,供浏览节点时使用
    private BusLineResult route = null; // 保存驾车/步行路线数据的变量，供浏览节点时使用
    private List<String> busLineIDList = null;
    private int busLineIndex = 0;
    // 搜索相关
    private PoiSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    private BusLineSearch mBusLineSearch = null;
    private BaiduMap mBaiduMap = null;
    BusLineOverlay overlay; // 公交路线绘制对象

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_busline);
        CharSequence titleLable = "公交线路查询功能";
        setTitle(titleLable);
        mBtnPre = (Button) findViewById(R.id.pre);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        mBaiduMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.bmapView)).getBaiduMap();
        mBaiduMap.setOnMapClickListener(this);
        mSearch = PoiSearch.newInstance();
        mSearch.setOnGetPoiSearchResultListener(this);
        mBusLineSearch = BusLineSearch.newInstance();
        mBusLineSearch.setOnGetBusLineSearchResultListener(this);
        busLineIDList = new ArrayList<String>();
        overlay = new BusLineOverlay(mBaiduMap);
        mBaiduMap.setOnMarkerClickListener(overlay);
    }

    /**
     * 发起检索
     *
     * @param v
     */
    public void searchButtonProcess(View v) {
        busLineIDList.clear();
        busLineIndex = 0;
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        EditText editCity = (EditText) findViewById(R.id.city);
        EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
        // 发起poi检索，从得到所有poi中找到公交线路类型的poi，再使用该poi的uid进行公交详情搜索
        mSearch.searchInCity((new PoiCitySearchOption()).city(
                editCity.getText().toString())
                .keyword(editSearchKey.getText().toString()));
    }

    public void searchNextBusline(View v) {
        if (busLineIndex >= busLineIDList.size()) {
            busLineIndex = 0;
        }
        if (busLineIndex >= 0 && busLineIndex < busLineIDList.size()
                && busLineIDList.size() > 0) {
            mBusLineSearch.searchBusLine((new BusLineSearchOption()
                    .city(((EditText) findViewById(R.id.city)).getText()
                            .toString()).uid(busLineIDList.get(busLineIndex))));

            busLineIndex++;
        }

    }

    /**
     * 节点浏览示例
     *
     * @param v
     */
    public void nodeClick(View v) {

        if (nodeIndex < -1 || route == null
                || nodeIndex >= route.getStations().size()) {
            return;
        }
        TextView popupText = new TextView(this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xff000000);
        // 上一个节点
        if (mBtnPre.equals(v) && nodeIndex > 0) {
            // 索引减
            nodeIndex--;
        }
        // 下一个节点
        if (mBtnNext.equals(v) && nodeIndex < (route.getStations().size() - 1)) {
            // 索引加
            nodeIndex++;
        }
        if (nodeIndex >= 0) {
            // 移动到指定索引的坐标
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(route
                    .getStations().get(nodeIndex).getLocation()));
            // 弹出泡泡
            popupText.setText(route.getStations().get(nodeIndex).getTitle());
            mBaiduMap.showInfoWindow(new InfoWindow(popupText, route.getStations()
                    .get(nodeIndex).getLocation(), 10));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mSearch.destroy();
        mBusLineSearch.destroy();
        super.onDestroy();
    }

    @Override
    public void onGetBusLineResult(BusLineResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(GongjiaoluxianActivity.this, "抱歉，未找到结果",
                    Toast.LENGTH_LONG).show();
            return;
        }
        mBaiduMap.clear();
        route = result;
        nodeIndex = -1;
        overlay.removeFromMap();
        overlay.setData(result);
        overlay.addToMap();
        overlay.zoomToSpan();
        mBtnPre.setVisibility(View.VISIBLE);
        mBtnNext.setVisibility(View.VISIBLE);
        Toast.makeText(GongjiaoluxianActivity.this, result.getBusLineName(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPoiResult(PoiResult result) {

        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(GongjiaoluxianActivity.this, "抱歉，未找到结果",
                    Toast.LENGTH_LONG).show();
            return;
        }
        // 遍历所有poi，找到类型为公交线路的poi
        busLineIDList.clear();
        for (PoiInfo poi : result.getAllPoi()) {
            busLineIDList.add(poi.uid);
        }
        searchNextBusline(null);
        route = null;
    }

    /**
     * V5.2.0版本之后，还方法废弃，使用{@link #onGetPoiDetailResult(PoiDetailSearchResult)}
     * 代替
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult result) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onMapClick(LatLng point) {
        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi poi) {
        return false;
    }


}




/*
import iet.jxufe.cn.android.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKPoiInfo;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.RouteOverlay;
//出行信息
public class GongjiaoluxianActivity extends MapActivity {
	private MapView mapView;
	private BMapManager bMapManager;
	private MapController mc;
	private MKSearch mkSearch;// 用于位置检索、周边检索、范围检索、公交检索、驾乘检索、步行检索
//	private String keyString = "81856CEC017E23D5DA533A5BED3D0414BBB307C3";
	private String keyString = "H61xOHjsAUmYTan60ELDQCmEGmlPCv5n";
	private EditText bus,city;
	private Button search;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gongjiaoluxian);
		bus=(EditText)findViewById(R.id.bus);
		search=(Button)findViewById(R.id.search);
		mapView = (MapView) findViewById(R.id.bmapView);
		city=(EditText)findViewById(R.id.city);

		bMapManager = new BMapManager(this);
		bMapManager.init(keyString, new MKGeneralListener() {
			public void onGetPermissionState(int result) {
				if (result == 300) {
					Toast.makeText(GongjiaoluxianActivity.this, "验证不通过，请重新输入！",
							Toast.LENGTH_SHORT).show();
				}//返回授权验证错误,300表示验证失败
			}
			public void onGetNetworkState(int result) {

			}
		});
		initMapActivity(bMapManager);
		mapView.setBuiltInZoomControls(true);
		mc=mapView.getController();
		mc.setZoom(15);
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mkSearch = new MKSearch();
				mkSearch.init(bMapManager,new MyMKSearchListener());
				mkSearch.poiSearchInCity(city.getText().toString().trim(), bus.getText().toString().trim());
			}
		});
	}
	public class MyMKSearchListener implements MKSearchListener{
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
											int arg1) {
			// 返回步行路线搜索结果
		}
		public void onGetTransitRouteResult(MKTransitRouteResult result,
											int arg1) {
			// 返回公交搜索结果
		}
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// 返回联想词信息搜索结果
		}

		public void onGetRGCShareUrlResult(String s, int i) {

		}

		public void onGetPoiDetailSearchResult(int i, int i1) {

		}

		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			// 返回poi搜索结果 result - 搜索结果 iError - 错误号，0表示正确返回
			if (result == null || iError != 0) {
				Toast.makeText(GongjiaoluxianActivity.this, "对不起，没有相应结果",
						Toast.LENGTH_LONG).show();
				return;
			}
			MKPoiInfo mkPoiInfo=null;
			int mkPoiNum=result.getNumPois();
			for(int i=0;i<mkPoiNum;i++){
				mkPoiInfo=result.getPoi(i);
				if(mkPoiInfo.ePoiType==2){
					break;
				}
			}
			mkSearch.busLineSearch(city.getText().toString().trim(), mkPoiInfo.uid);
		}
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
											int arg1) {
			// 返回驾乘路线搜索结果
		}
		public void onGetBusDetailResult(MKBusLineResult result, int iError) {
			// 返回公交车详情信息搜索结果
			if (result == null || iError != 0) {
				Toast.makeText(GongjiaoluxianActivity.this, "对不起，没有相应结果",
						Toast.LENGTH_LONG).show();
				return;
			}
			RouteOverlay routeOverlay=new RouteOverlay(GongjiaoluxianActivity.this, mapView);
			routeOverlay.setData(result.getBusRoute());
			mapView.getOverlays().clear();
			mapView.getOverlays().add(routeOverlay);
			mapView.invalidate();
			mapView.getController().animateTo(result.getBusRoute().getStart());
		}
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			// 返回地址信息搜索结果
		}
	}
	protected void onResume() {
		super.onResume();
		if (bMapManager != null) {
			bMapManager.start();
		}
	}
	protected void onPause() {
		super.onPause();
		if (bMapManager != null) {
			bMapManager.stop();
		}
	}
	protected void onDestroy() {
		super.onDestroy();
		if (bMapManager != null) {
			bMapManager.destroy();
			bMapManager = null;
		}
	}
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
*/

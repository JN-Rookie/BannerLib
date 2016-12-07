package com.sjsm.banner_line;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sjsm.bannerlib.AutoPlayViewPager;
import com.sjsm.bannerlib.BannerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private AutoPlayViewPager autoPlayViewPage;
    private String[]          images = {"http://img2.3lian.com/2014/c7/12/d/76.jpg",
            "http://img2.3lian.com/2014/c7/12/d/77.jpg",
            "http://img2.3lian.com/2014/c7/12/d/78.jpg",
            "http://img2.3lian.com/2014/c7/12/d/79.jpg",
            "http://img2.3lian.com/2014/c7/12/d/80.jpg",
            "http://img2.3lian.com/2014/c7/12/d/81.jpg"
    };
    private ArrayList<String> views  = new ArrayList<>();
    LinearLayout mLayout;
    private ArrayList<View> mList = new ArrayList<>();
    private BannerAdapter mBannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBannerAdapter = new BannerAdapter(this);
        initView();
    }

    private void initView() {
        for (int i = 0; i < images.length; i++) {
            views.add(images[i]);
        }
        mBannerAdapter.update(views);
        initImageLoader();
        mLayout= (LinearLayout) findViewById(R.id.ll_hottest_indicator);
        for (int i = 0; i < views.size(); i++) {
            View view=new View(this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
            view.setLayoutParams(layoutParams);
            if(i==0){
                view.setBackgroundColor(Color.RED);
            }
            mList.add(view);
            mLayout.addView(view);
        }
        autoPlayViewPage = (AutoPlayViewPager) findViewById(R.id.view_pager);
        autoPlayViewPage.setAdapter(mBannerAdapter);
        autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        autoPlayViewPage.setCurrentItem(2520);
        autoPlayViewPage.start(); // 开始轮播
        autoPlayViewPage.setOnPageChangeListener(this);
    }

    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_launcher)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mList.size(); i++) {
            if (i == position% mList.size()) {
                mList.get(i).setBackgroundColor(Color.RED);
            } else {
                mList.get(i).setBackgroundColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

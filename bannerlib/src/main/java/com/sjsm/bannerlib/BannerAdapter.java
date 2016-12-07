package com.sjsm.bannerlib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class BannerAdapter extends PagerAdapter {
    private Context mContext;

    private ArrayList<String>  resIds;

    public BannerAdapter(Context context) {
        this.mContext = context;
    }

    public void update(ArrayList<String> resIds) {
        if (this.resIds != null)
            this.resIds.clear();
        if (resIds != null)
            this.resIds = resIds;
    }

    @Override
    public int getCount() {
        return resIds == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        // 如果是http://www.xx.com/xx.png这种连接，这里可以用ImageLoader之类的框架加载
//        imageView.setImageResource(resIds.get(position % resIds.size()));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(resIds.get(position% resIds.size()), imageView);
        container.addView(imageView);
//        View view= LayoutInflater.from(mContext).inflate(R.layout.banner_item, null, false);
//        View mView=view.findViewById(R.id.banner_view);
//        mView.setBackgroundColor(Color.RED);
//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner);
//        ImageLoader.getInstance().displayImage(resIds.get(position% resIds.size()), imageView);
//        container.addView(imageView);
//        container.addView(mView);
        return imageView;
    }


}

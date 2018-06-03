package com.raoqian.rollpagerviewsupport.rollviewpager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.raoqian.rollpagerviewsupport.rollviewpager.RollPagerView;

/**
 * Created by Administrator on 2018/6/3.
 */

public final class ImageAdapter extends LoopPagerAdapter {
    String[] imgs = new String[0];
    int[] imsRes = new int[0];


    public void setImgs(String[] imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    public void setImgs(int[] imgs) {
        this.imsRes = imgs;
        notifyDataSetChanged();
    }

//        public void setImgesAndName(String[] imgs, List<String> names, int selectedDotRes) {
//            if (this.imgs.length != imgs.length) {
//                mView.setHintView(new CustomHintView(mContext, names, selectedDotRes));
//            }
//            this.imgs = imgs;
//            notifyDataSetChanged();
//        }

    RollPagerView mView;
    Context mContext;

    public ImageAdapter(RollPagerView viewPager, Context context) {
        super(viewPager);
        mContext = context;
        mView = viewPager;
    }

    @Override
    public View getView(ViewGroup container, final int position) {

        ImageView view = new ImageView(container.getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RollViewPager", "onClick");
            }
        });
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (imsRes.length > 0) {
            view.setImageResource(imsRes[position]);
        } else {
            ImageLoader.getInstance().displayImage(imgs[position], view);
        }
        return view;
    }

    @Override
    public int getRealCount() {
        return imgs.length + imsRes.length;
    }

}
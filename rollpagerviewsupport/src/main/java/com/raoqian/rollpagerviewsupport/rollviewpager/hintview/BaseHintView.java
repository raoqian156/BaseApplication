package com.raoqian.rollpagerviewsupport.rollviewpager.hintview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raoqian.rollpagerviewsupport.R;
import com.raoqian.rollpagerviewsupport.rollviewpager.HintView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by raoqian on 2017/9/21.
 */

public class BaseHintView extends LinearLayout implements HintView {
    List<String> mName = new ArrayList<>();
    ImageView[] imgs;
    TextView nameShow;

    int selectedDotRes;

    public int getSelectedDotRes() {
        return selectedDotRes;
    }

    public BaseHintView(Context context, List<String> name, int selectedDot, int layout, int textID, int imgID) {
        super(context);
        imgs = new ImageView[name.size()];
        this.selectedDotRes = selectedDot;
        this.mName = name;
        init(context, name.size(), layout, textID, imgID);
    }

//
//
//    public void setData(List<String> name) {
//        mName.clear();
//        mName.addAll(name);
//        init(getContext(), mName.size());
//        invalidate();
//    }

    protected void init(Context context, int size, int layoutID, int textID, int imgID) {
        if (size <= 1) {
            return;
        }
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(layoutID, this, false);
//        View.inflate(context, R.layout.view_top_pic_text, this);
        if (textID > 0) {
            nameShow = view.findViewById(textID);
        }
        if (imgID > 0) {
            imgs[0] = view.findViewById(imgID);
            setImageShow(imgs[0]);
            LayoutParams lp = (LayoutParams) imgs[0].getLayoutParams();
            for (int i = 1; i < size; i++) {
                ImageView imageView = new ImageView(context);
                setImageShow(imageView);
                imageView.setLayoutParams(lp);
                imgs[i] = imageView;
                view.addView(imageView, view.getChildCount());
            }
        }
        addView(view);
        setCurrent(0);
    }

    private void setImageShow(ImageView imageView) {
        if (getSelectedDotRes() > 0) {
            imageView.setImageResource(getSelectedDotRes());
        } else {
            imageView.setImageResource(R.drawable.selector_main_image_dot);
        }
    }


    @Override
    public void initView(int length, int gravity) {
//        switch (gravity) {
//            case 0:
//                setGravity(Gravity.LEFT| Gravity.CENTER_VERTICAL);
//                break;
//            case 1:
//                setGravity(Gravity.CENTER);
//                break;
//            case 2:
//                setGravity(Gravity.RIGHT| Gravity.CENTER_VERTICAL);
//                break;
//        }
    }

    @Override
    public void setCurrent(int current) {
        if (imgs != null && current < imgs.length) {
            for (int i = 0; i < imgs.length; i++) {
                imgs[i].setSelected(current == i);
            }
        }
        if (mName != null && current < mName.size()) {
            nameShow.setText(mName.get(current));
        }
    }
}

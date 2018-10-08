package com.raoqian.mobprosaleapplication.aa_game.fragment;

import android.view.View;
import android.widget.TextView;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.aa_game.Person;
import com.raoqian.mobprosaleapplication.aa_game.WuQi;
import com.raoqian.mobprosaleapplication.aa_game.ZhuangBei;
import com.raoqian.mobprosaleapplication.base.BaseFragment;


/**
 * Created by Administrator on 2018/5/29.
 */

public class GameSettingFragment extends BaseFragment {
    WuQi wuQi;
    ZhuangBei zhuangBei;
    Person person1;
    Person person2;
    TextView info;

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_game_setting;
    }

    @Override
    protected void initView(View view) {
        setNeedMoveToBottomViewId(view, R.id.title);
        info = view.findViewById(R.id.info);
    }

    private void createPerson() {
        if (this.wuQi == null || this.zhuangBei == null) return;
        this.person1 = new Person(1);
        this.person2 = new Person(2);
//        this.person1.setWuQi(this.wuQi);
//        this.person1.setZhuangBei(this.zhuangBei);
//        this.person2.setWuQi(this.wuQi);
//        this.person2.setZhuangBei(this.zhuangBei);
        info.setText(person1.toString() + "\n\n" + person2.toString());
    }

    public Person getPerson1() {
        if (person1 == null) {
            createPerson();
        }
        return person1;
    }

    public Person getPerson2() {
        if (person2 == null) {
            createPerson();
        }
        return person2;
    }

    public void setWuQi(WuQi wuQi) {
        this.wuQi = wuQi;
        createPerson();
    }

    public void setZhuangBei(ZhuangBei zhuangBei) {
        this.zhuangBei = zhuangBei;
        createPerson();
    }
}

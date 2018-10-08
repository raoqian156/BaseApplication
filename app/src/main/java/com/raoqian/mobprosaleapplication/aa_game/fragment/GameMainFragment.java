package com.raoqian.mobprosaleapplication.aa_game.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.aa_game.AttackHelper;
import com.raoqian.mobprosaleapplication.aa_game.Person;
import com.raoqian.mobprosaleapplication.base.BaseFragment;
import com.raoqian.mobprosaleapplication.utils.TLog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/5/29.
 */

public class GameMainFragment extends BaseFragment implements View.OnClickListener {
    private Person person1, person2;
    TextView resultPan;

    @Override
    protected int onGetLayoutId() {
        return R.layout.fragment_game_main;
    }

    @Override
    protected void initView(View view) {
        setNeedMoveToBottomViewId(view, R.id.title);
        view.findViewById(R.id.btn_top).setOnClickListener(this);
        resultPan = view.findViewById(R.id.result);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_top) {
            if (person1 == null || person2 == null) {
                Toast.makeText(getActivity(), "请先生成角色", Toast.LENGTH_LONG).show();
                return;
            }
            action(person1, person2);
        }
    }

    private void action(@NonNull final Person person1, @NonNull final Person person2) {
        Person start;
        Person next;
        if (person1.getCYZ() >= person2.getCYZ()) {
            start = person1;
            next = person2;
        } else {
            start = person2;
            next = person1;
        }
        List<String> step = getSteps(start, next);
        String res = "";
        for (int i = 0; i < step.size(); i++) {
            res += step.get(i);
        }
        resultPan.setText(res);
        TLog.bean("GameMainFragment", res);

    }

    private List<String> getSteps(Person start, Person next) {//获取交战记录
        List<String> steps = new ArrayList<>();
        for (int i = 1; start.getSM() > 0 && next.getSM() > 0; i++) {
            steps.add(getStep(i, start, next));
        }
        return steps;
    }

    private String getStep(int index, Person start, Person next) {//获取具体的步骤,双方生命值都大于0
        String s = "第" + index + "回合\n" + chuzhao(start, next, index) + "   " + chuzhao(next, start, index * -1);
        return s;
    }

    private String chuzhao(Person start, Person next, int index) {
        return AttackHelper.instance().attack(start, next, index);
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
        TLog.bean("GameMainFragment", person1);
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
        TLog.bean("GameMainFragment", person2);
    }
}

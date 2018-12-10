package com.raoqian.mobprosaleapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raoqian.mobprosaleapplication.bean.BaseAdapter;
import com.raoqian.mobprosaleapplication.bean.BaseHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */

public class SizeAdapter extends BaseAdapter<SizeAdapter.SizeItemHolder, String> {
    public SizeAdapter(Activity context) {
        super(context);
    }

    @Override
    public SizeItemHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new SizeItemHolder(getView(R.layout.item_size_add, parent));
    }

    @Override
    public void onBindingHolder(SizeItemHolder holder, int position) {
//        holder.setData(getTagData());
        holder.show.setText(position + "     这是内容哼到手机打uhsfjshdfshdfhsudf ++++  " + position);
    }

    public List<ItemSpec> getTagData() {
        List<ItemSpec> list = new ArrayList<>();
        for (String item : tagData) {
            list.add(new ItemSpec(item, ""));
        }
        return list;
    }

    public void setShowTag(String... tag) {
        if (tag != null && tag.length != tagData.size()) {
            this.tagData.clear();
            this.tagData.addAll(Arrays.asList(tag));
        }
    }

    List<String> tagData = new ArrayList<>();

    @Override
    public int getItemCount() {
        return super.getItemCount() + (needAdd ? this.iii : 0);
    }

    private int iii = 1;

    boolean needAdd = false;

    public void add() {
        this.iii++;
        this.needAdd = true;
        this.notifyDataSetChanged();
    }

//        public Object getInputData() {
//            return inputData;
//        }


    class SizeItemHolder extends BaseHolder {
        //        private ItemAdapter itemAdapter;
        TextView show;

        public SizeItemHolder(View itemView) {
            super(itemView);
            show = itemView.findViewById(R.id.input);
//            itemAdapter = new ItemAdapter(getContext());
//            RecyclerView recyclerView = itemView.findViewById(R.id.parentPanel);
//            LinearLayoutManager llm = new LinearLayoutManager(itemView.getContext());
//            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
//            recyclerView.setLayoutManager(llm);
//            recyclerView.setAdapter(itemAdapter);
        }

        public void setData(List<ItemSpec> data) {
        }
    }

//    class ItemAdapter extends BaseAdapter<ItemAdapter.InputListenerHolder, ItemSpec> {
//        public ItemAdapter(Activity context) {
//            super(context);
//        }
//
//        @Override
//        public InputListenerHolder onCreateHolder(ViewGroup parent, int viewType) {
//            return new InputListenerHolder(getView(R.layout.item_item_size_add, parent));
//        }
//
//        @Override
//        public void onBindingHolder(InputListenerHolder holder, int position) {
//            holder.remind.setText(getDataItem(position).specName);
////            holder.input.setHint(getDataItem(position).specName);
////            holder.input.setTag(position);
//        }
//
//        private void setInputContent(int position, String remind, String content) {
//            Log.e("TestActivity", "position = " + position + " remind = " + remind + " content = " + content);
//        }
//
//
//        class InputListenerHolder extends BaseHolder {
//            TextView remind;
////            EditText input;
//
//            public InputListenerHolder(View itemView) {
//                super(itemView);
//                remind = itemView.findViewById(R.id.remind);
////                input = itemView.findViewById(R.id.input);
////                input.addTextChangedListener(new TextWatcher() {
////                    @Override
////                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////                    }
////
////                    @Override
////                    public void onTextChanged(CharSequence s, int start, int before, int count) {
////                        if (s != null && s.toString().length() > 0) {
////                            setInputContent((Integer) input.getTag(), remind.getText().toString(), s.toString());
////                        }
////                    }
////
////                    @Override
////                    public void afterTextChanged(Editable s) {
////
////                    }
////                });
//            }
//        }
//    }
}

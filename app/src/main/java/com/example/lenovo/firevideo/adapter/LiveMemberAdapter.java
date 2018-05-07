package com.example.lenovo.firevideo.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lenovo.firevideo.R;

/**
 * Created by liuxiaozhong on 2017-09-25.
 */

public class LiveMemberAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public LiveMemberAdapter() {
        super(R.layout.item_member);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
    }
}

package com.example.lenovo.firevideo.adapter;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.model.LineBean;

public class LiveLineAdapter extends BaseQuickAdapter<LineBean,BaseViewHolder> {
    public LiveLineAdapter() {
        super(R.layout.item_line);
    }

    @Override
    protected void convert(BaseViewHolder helper, LineBean item) {
        ForegroundColorSpan color = new ForegroundColorSpan(helper.itemView.getResources().getColor(R.color.pop_blue));
        SpannableString name=new SpannableString(item.name+"申请连麦");
        name.setSpan(color,0,name.length()-4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        TextView tv_name=helper.getView(R.id.tv_name);
        tv_name.setText(name);
        helper.addOnClickListener(R.id.tv_agree);
        helper.addOnClickListener(R.id.tv_refuse);
    }
}

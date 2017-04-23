package com.dali.dalishop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dali.dalishop.R;
import com.dali.dalishop.bean.HomeCampaign;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.ViewHolder> {

    private List<HomeCampaign> datas;
    private LayoutInflater inflater;
    private int LAYOUT_RIGHT = 0;
    private int LAYOUT_LEFT = 1;
    private Context context;

    public HomeCatgoryAdapter(List<HomeCampaign> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(context);
        View view = null;

        if (viewType == LAYOUT_LEFT) {
            view = inflater.inflate(R.layout.template_home_cardview_left, parent, false);
        } else if (viewType == LAYOUT_RIGHT) {
            view = inflater.inflate(R.layout.template_home_cardview_right, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(datas.get(position).getTitle());

        Picasso.with(context).load(datas.get(position).getCpOne().getImgUrl()).into(holder.bigImg);
        Picasso.with(context).load(datas.get(position).getCpTwo().getImgUrl()).into(holder.smallImgTop);
        Picasso.with(context).load(datas.get(position).getCpThree().getImgUrl()).into(holder.smallImgBottom);

    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0) {
            return super.getItemViewType(LAYOUT_LEFT);
        }
        return super.getItemViewType(LAYOUT_RIGHT);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView bigImg;
        private ImageView smallImgTop;
        private ImageView smallImgBottom;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            bigImg = (ImageView) itemView.findViewById(R.id.img_big);
            smallImgTop = (ImageView) itemView.findViewById(R.id.img_small_top);
            smallImgBottom = (ImageView) itemView.findViewById(R.id.img_small_bottom);

        }
    }
}

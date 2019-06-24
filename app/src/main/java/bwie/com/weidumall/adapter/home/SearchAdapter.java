package bwie.com.weidumall.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.weidumall.R;
import bwie.com.weidumall.common.App;
import bwie.com.weidumall.entity.home.SearchGoods;
import bwie.com.weidumall.view.activity.home.DetailActivity;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/23 13:30
 * Description:
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<SearchGoods> list;

    public SearchAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_search, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final SearchGoods searchGoods = list.get(position);

        holder.ivIcon.setImageURI(Uri.parse(searchGoods.getMasterPic()));
        holder.tvName.setText(searchGoods.getCommodityName());
        holder.tvPrice.setText("￥" + App.decimalFormat(searchGoods.getPrice()));
        holder.tvSalenum.setText("已售" + searchGoods.getSaleNum() + "件");

        //条目点击跳详情页
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("commodityId", searchGoods.getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<SearchGoods> result) {
        list.addAll(result);
    }

    public void clear() {
        list.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView ivIcon;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvSalenum;


        public ViewHolder(View itemView) {
            super(itemView);

            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvSalenum = itemView.findViewById(R.id.tv_salenum);

        }
    }
}

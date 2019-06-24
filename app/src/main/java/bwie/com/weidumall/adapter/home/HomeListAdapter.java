package bwie.com.weidumall.adapter.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.ListenerClass;
import bwie.com.weidumall.R;
import bwie.com.weidumall.entity.home.HomeData;
import bwie.com.weidumall.entity.home.HomeGoods;
import bwie.com.weidumall.entity.home.HomeType;

/**
 * Author:杨高峰
 * Description：
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private Context context;
    private HomeType homeType;
    private static final int RXXP_TYPE = 0;
    private static final int MLSS_TYPE = 1;
    private static final int PZSH_TYPE = 2;

    public HomeListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_home, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (i) {
            case RXXP_TYPE:
                HomeData rxxp = homeType.getRxxp();
                List<HomeGoods> rxxpList = rxxp.getCommodityList();
                viewHolder.titleBar.setBackgroundResource(R.drawable.rxxp_bitmap);
                viewHolder.titleTip.setImageResource(R.drawable.common_btn_more_yellow_n);
                viewHolder.homtTitle.setText(rxxp.getName());
                viewHolder.homtTitle.setTextColor(Color.parseColor("#FFFF7F57"));
                viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL, false));
                HomeItemAdapter rxxpListAdapter = new HomeItemAdapter(context);
                viewHolder.recyclerView.setAdapter(rxxpListAdapter);
                rxxpListAdapter.addList(rxxpList);
                rxxpListAdapter.notifyDataSetChanged();
                break;
            case MLSS_TYPE:
                HomeData mlss = homeType.getMlss();
                List<HomeGoods> mlssList = mlss.getCommodityList();
                viewHolder.titleBar.setBackgroundResource(R.drawable.mlss_bitmap);
                viewHolder.titleTip.setImageResource(R.drawable.home_btn_more_purple_n);
                viewHolder.homtTitle.setText(mlss.getName());
                viewHolder.homtTitle.setTextColor(Color.parseColor("#FF787AF6"));
                viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false));
                HomeItemAdapter mlssListAdapter = new HomeItemAdapter(context);
                viewHolder.recyclerView.setAdapter(mlssListAdapter);
                mlssListAdapter.addList(mlssList);
                mlssListAdapter.notifyDataSetChanged();
                break;
            case PZSH_TYPE:
                HomeData pzsh = homeType.getPzsh();
                List<HomeGoods> pzshList = pzsh.getCommodityList();
                viewHolder.titleBar.setBackgroundResource(R.drawable.pzsh_bitmap);
                viewHolder.viewLine.setVisibility(View.INVISIBLE);
                viewHolder.titleTip.setImageResource(R.drawable.home_btn_moer_pink_n);
                viewHolder.homtTitle.setText(pzsh.getName());
                viewHolder.homtTitle.setTextColor(Color.parseColor("#FFFF5F71"));
                viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                HomeItemAdapter pzshListAdapter = new HomeItemAdapter(context);
                viewHolder.recyclerView.setAdapter(pzshListAdapter);
                pzshListAdapter.addList(pzshList);
                pzshListAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return homeType == null ? 0 : 3;
    }

    public void add(HomeType result) {
        homeType = result;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return RXXP_TYPE;
            case 1:
                return MLSS_TYPE;
            case 2:
                return PZSH_TYPE;
        }
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_bar)
        RelativeLayout titleBar;
        @BindView(R.id.home_item)
        RecyclerView recyclerView;
        @BindView(R.id.title_tip)
        ImageView titleTip;
        @BindView(R.id.home_title)
        TextView homtTitle;
        @BindView(R.id.view_line)
        View viewLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}



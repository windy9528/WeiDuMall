package bwie.com.weidumall.adapter.user;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.weidumall.R;
import bwie.com.weidumall.entity.user.TakeList;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/21 17:56
 * Description:
 */
public class TakeAdapter extends RecyclerView.Adapter<TakeAdapter.ViewHolder> {

    private Context context;
    private List<TakeList> list;


    public TakeAdapter(Context context) {
        this.context = context;
        list = new ArrayList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TakeList takeList = list.get(position);

        holder.name.setText(takeList.getRealName());
        holder.time.setText(takeList.getCreateTime());
        holder.address.setText(takeList.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<TakeList> result) {
        if (result != null) {
            list.addAll(result);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout title;
        private TextView name;
        private TextView time;
        private RelativeLayout content;
        private TextView address;
        private TextView textLine;
        private RadioButton checks;
        private TextView udp;
        private TextView del;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (RelativeLayout) itemView.findViewById(R.id.title);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            content = (RelativeLayout) itemView.findViewById(R.id.content);
            address = (TextView) itemView.findViewById(R.id.address);
            textLine = (TextView) itemView.findViewById(R.id.text_line);
            checks = (RadioButton) itemView.findViewById(R.id.checks);
            udp = (TextView) itemView.findViewById(R.id.udp);
            del = (TextView) itemView.findViewById(R.id.del);

        }
    }
}

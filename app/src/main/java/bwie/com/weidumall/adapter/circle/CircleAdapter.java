package bwie.com.weidumall.adapter.circle;


import android.content.Context;
import android.net.ParseException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bwie.com.weidumall.R;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.circle.CircleEntity;
import bwie.com.weidumall.presenter.circle.LikePresenter;
import bwie.com.weidumall.presenter.circle.UnLikePresenter;
import bwie.com.weidumall.util.DateUtils;

/**
 * @author 杨高峰
 * @date 2019 2019/05/23 16:34
 */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {

    private Context context;
    private List<CircleEntity> list;

    public CircleAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_circle, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final CircleEntity circleEntity = list.get(i);

        //设置头像
        viewHolder.avatar.setImageURI(Uri.parse(circleEntity.getHeadPic()));
        viewHolder.name.setText(circleEntity.getNickName());
        try {
            viewHolder.time.setText(DateUtils
                    .dateTransformer(circleEntity.getCreateTime(),
                            DateUtils.MINUTE_PATTERN));
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.message.setText(circleEntity.getContent());
        //加载动态图片
        if (StringUtils.isEmpty(circleEntity.getImage())) {
            viewHolder.childRecyclerView.setVisibility(View.GONE);
        } else {
            viewHolder.childRecyclerView.setVisibility(View.VISIBLE);
            String[] images = circleEntity.getImage().split(",");
            viewHolder.circleChildAdapter.clear();
            viewHolder.circleChildAdapter.addList(Arrays.asList(images));
            if (images.length == 1) {//只有一张图片
                viewHolder.gridLayoutManager.setSpanCount(1);
            } else if (images.length == 2 || images.length == 4) {
                viewHolder.gridLayoutManager.setSpanCount(2);
            } else {
                viewHolder.gridLayoutManager.setSpanCount(3);
            }
            viewHolder.childRecyclerView.setLayoutManager(viewHolder.gridLayoutManager);
            viewHolder.circleChildAdapter.notifyDataSetChanged();
        }
        viewHolder.greatNum.setText(String.valueOf(circleEntity.greatNum));
        viewHolder.greatImage.setChecked(circleEntity.check);
        viewHolder.greatImage.setTag(circleEntity);
        viewHolder.greatNum.setText(String.valueOf(circleEntity.greatNum));
        viewHolder.greatNum.setTag(circleEntity);

        /**
         * 点赞点击
         */
        viewHolder.greatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                great(v, circleEntity);
            }
        });
    }

    /**
     * 圈子页面点赞操作
     *
     * @param v
     * @param circleEntity
     */
    private void great(View v, CircleEntity circleEntity) {
        CircleEntity bean = (CircleEntity) v.getTag();
        boolean checked = ((CheckBox) v).isChecked();
        bean.check = checked;
        //接口回调 更新数量
        if (checked) {
            //点赞成功
            bean.greatNum++;
            notifyDataSetChanged();
        } else {
            bean.greatNum--;
            notifyDataSetChanged();
        }
        //请求接口
        if (bean.check) {//选中 点赞
            new LikePresenter(new ImplView() {
                @Override
                public void success(Result data, Object... args) {
                    Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void fail(Result result, Object... args) {
                    Toast.makeText(context, result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).requestData(circleEntity.getId());
        } else {
            new UnLikePresenter(new ImplView() {
                @Override
                public void success(Result data, Object... args) {
                    Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void fail(Result result, Object... args) {
                    Toast.makeText(context, result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).requestData(circleEntity.getId());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void addList(List<CircleEntity> result) {
        if (result != null) {
            list.addAll(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView avatar;
        private TextView name;
        private TextView time;
        private TextView message;
        private RecyclerView childRecyclerView;
        private LinearLayout greatLayout;
        private CheckBox greatImage;
        private TextView greatNum;
        //图片的适配器和布局管理器
        private CircleChildAdapter circleChildAdapter;
        private GridLayoutManager gridLayoutManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            message = (TextView) itemView.findViewById(R.id.message);
            childRecyclerView = (RecyclerView) itemView.findViewById(R.id.childRecyclerView);
            greatLayout = (LinearLayout) itemView.findViewById(R.id.great_layout);
            greatImage = itemView.findViewById(R.id.great_image);
            greatNum = (TextView) itemView.findViewById(R.id.great_num);
            circleChildAdapter = new CircleChildAdapter(itemView.getContext());
            gridLayoutManager = new GridLayoutManager(itemView.getContext(), 3);
            childRecyclerView.setLayoutManager(gridLayoutManager);
            childRecyclerView.setAdapter(circleChildAdapter);
            circleChildAdapter.notifyDataSetChanged();
        }
    }
}

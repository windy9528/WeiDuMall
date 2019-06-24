package bwie.com.weidumall.adapter.circle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.weidumall.R;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.util.UIUtils;

/**
 * date:2019/5/27
 * name:windy
 * function:
 */
public class CircleChildAdapter extends RecyclerView.Adapter<CircleChildAdapter.ViewHolder> {

    private Context context;
    private List<Object> list;
    private int sign;//0:普通点击，1自定义

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public CircleChildAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cricle_iamge, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //holder.imageView.setImageURI(Uri.parse(list.get(position)));

        if (list.get(position) instanceof String) {
            String imageUrl = (String) list.get(position);
            if (imageUrl.contains("http:")) {//加载http
                holder.imageView.setImageURI(Uri.parse(imageUrl));
            } else {//加载sd卡
                Uri uri = Uri.parse("file://" + imageUrl);
                holder.imageView.setImageURI(uri);
            }
        } else {//加载资源文件
            int id = (int) (list.get(position));
            Uri uri = Uri.parse("res://com.dingtao.rrmmp/" + id);
            holder.imageView.setImageURI(uri);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == 1) {//自定义点击
                    if (position == 0) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        BaseActivity
                                .getForegroundActivity()
                                .startActivityForResult(intent, BaseActivity.PHOTO);
                    } else {
                        UIUtils.showToastSafe("点击了图片");
                    }
                } else {
                    UIUtils.showToastSafe("点击了图片");
                }
            }
        });
    }

    public void add(Object image) {
        if (image != null) {
            list.add(image);
        }
    }

    public List<Object> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<String> imageList) {
        if (imageList != null)
            list.addAll(imageList);
    }

    public void clear() {
        list.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.qz_image);
        }
    }
}

package bwie.com.weidumall.view.activity.circle;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.circle.CircleChildAdapter;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.presenter.circle.PublishCirclePresenter;
import bwie.com.weidumall.util.UIUtils;
import bwie.com.weidumall.view.fragment.circle.CircleFragment;

public class PublishActivity extends BaseActivity implements ImplView<Result> {

    @BindView(R.id.bo_text)
    EditText mText;
    PublishCirclePresenter presenter;
    @BindView(R.id.bo_image_list)
    RecyclerView mImageList;
    CircleChildAdapter circleChildAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initView() {
        circleChildAdapter = new CircleChildAdapter(this);
        circleChildAdapter.setSign(1);
        circleChildAdapter.add(R.drawable.app_icon);
        mImageList.setLayoutManager(new GridLayoutManager(this, 3));
        mImageList.setAdapter(circleChildAdapter);

        presenter = new PublishCirclePresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void destoryData() {

    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.send)
    public void publish() {
        presenter.requestData(1, mText.getText().toString()
                , circleChildAdapter.getList());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null, requestCode, data);
            if (!StringUtils.isEmpty(filePath)) {
                circleChildAdapter.add(filePath);
                circleChildAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void success(Result data, Object... args) {
        if (data.getStatus().equals("0000")) {
            CircleFragment.addCircle = true;
            finish();
        } else {
            UIUtils.showToastSafe(data.getStatus() + "  " + data.getMessage());
        }
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }

}

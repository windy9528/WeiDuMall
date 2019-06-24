package bwie.com.weidumall.view.fragment.circle;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.circle.CircleAdapter;
import bwie.com.weidumall.common.BaseFragment;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.circle.CircleEntity;
import bwie.com.weidumall.presenter.circle.CirclePresenter;
import bwie.com.weidumall.view.activity.circle.PublishActivity;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/5/20 21:19
 * Description:
 */
public class CircleFragment extends BaseFragment implements ImplView<List<CircleEntity>> {

    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.publish)
    ImageView publish;   //发表圈子
    private CirclePresenter circlePresenter;
    private CircleAdapter circleAdapter;
    public static boolean addCircle; //如果添加成功，则为true
    private Result<List<CircleEntity>> result;
    private List<CircleEntity> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initView() {
        circlePresenter = new CirclePresenter(this);
        circleAdapter = new CircleAdapter(getActivity());

        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xRecyclerView.setAdapter(circleAdapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
                circlePresenter.requestData(true, "10");
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.loadMoreComplete();
                circlePresenter.requestData(false, "20");
            }
        });
    }

    @Override
    protected void initData() {
        xRecyclerView.refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (addCircle) {//publish new message,so you have to refresh
            addCircle = false;
            xRecyclerView.refresh();
        }
    }

    @Override
    public void success(Result data, Object... args) {
        List<CircleEntity> result = (List<CircleEntity>) data.getResult();
        xRecyclerView.refreshComplete();
        xRecyclerView.loadMoreComplete();
        if (circlePresenter.getPage() == 1) {
            circleAdapter.clear();
        }
        circleAdapter.addList(result);
        circleAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }

    //发表圈子
    @OnClick(R.id.publish)
    public void publish() {
        //跳转到发表圈子的页面
        // Intent intent = new Intent(getActivity(),PublishActivity.class);
       /* intent.putExtra("userId",userId);
        intent.putExtra("session",sessionId);*/
        startActivity(new Intent(getActivity(), PublishActivity.class));
    }

    @Override
    protected void destoryData() {

    }
}

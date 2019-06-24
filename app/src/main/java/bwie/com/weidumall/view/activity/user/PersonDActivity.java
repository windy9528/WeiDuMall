package bwie.com.weidumall.view.activity.user;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.user.TakeAdapter;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.user.TakeList;
import bwie.com.weidumall.presenter.user.TakeAddressPresenter;

/**
 * 收货地址全查功能
 */
public class PersonDActivity extends BaseActivity implements ImplView<List<TakeList>> {

    @BindView(R.id.take_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.complete)
    TextView complete;
    private TakeAddressPresenter takeAddressPresenter;
    private TakeAdapter takeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_persond;
    }

    @Override
    protected void initView() {
        takeAddressPresenter = new TakeAddressPresenter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        takeAdapter = new TakeAdapter(this);
        recyclerView.setAdapter(takeAdapter);
    }

    @Override
    protected void initData() {
        takeAddressPresenter.requestData();
    }

    @Override
    public void success(Result data, Object... args) {
        List<TakeList> result = (List<TakeList>) data.getResult();
        takeAdapter.addList(result);
        takeAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }

    @Override
    protected void destoryData() {
        if (takeAddressPresenter != null) {
            takeAddressPresenter.unBind();
        }
    }
    @OnClick(R.id.complete)
    public void completeAndFinish(){
        finish();
    }
}

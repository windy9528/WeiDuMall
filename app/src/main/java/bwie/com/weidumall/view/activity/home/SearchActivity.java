package bwie.com.weidumall.view.activity.home;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.home.SearchAdapter;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.home.SearchGoods;
import bwie.com.weidumall.presenter.home.SearchPresenter;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/23 12:40
 * Description: 通过关键字来查询
 */
public class SearchActivity extends BaseActivity implements ImplView<List<SearchGoods>> {


    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.tv_search) //点击搜索
            TextView tv_search;
    @BindView(R.id.error_tips) //没有数据时展示图片
            ImageView error_tips;
    @BindView(R.id.ed_text)  //输入框
            EditText ed_text;
    @BindView(R.id.text_tip)
    RelativeLayout text_tip;
    private SearchAdapter searchAdapter;
    private SearchPresenter searchPresenter;
    private String keyword = "A";
    private List<SearchGoods> judge;
    private List<SearchGoods> judgeTwo;
    private boolean flag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        //默认加载一些商品
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        searchAdapter = new SearchAdapter(this);
        xRecyclerView.setAdapter(searchAdapter);
        searchPresenter = new SearchPresenter(this);
        judge = new ArrayList<>();
        judgeTwo = new ArrayList<>();
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
    }

    @Override
    protected void initData() {
        //点击搜索
        clickSearch();

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                xRecyclerView.refreshComplete();
                searchPresenter.requestData(true, keyword, "10");
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.loadMoreComplete();
                searchPresenter.requestData(false, keyword, "10");
                searchAdapter.notifyDataSetChanged();
            }
        });
        //默认显示一列数据
        searchPresenter.requestData(true, keyword, "10");
    }

    private void clickSearch() {
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                keyword = ed_text.getText().toString().trim();
                if (TextUtils.isEmpty(keyword)) {
                    showToast("搜索不能为空!");
                }
                searchPresenter.requestData(true, keyword, "10");
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void success(Result data, Object... args) {
        List<SearchGoods> result = (List<SearchGoods>) data.getResult();
        //如果没有后续数据，直接弹出xx
        //如果没有查询到该关键字
        Log.i("windy", "根据关键词搜索商品:" + result.size());

        if (result.size() == 0) {//说明请求的集合没数据
            /*
             * 两种情况
             * ①：输入了错误的keyword值，查询不到
             * ②：一直上拉加载，count为空
             **/
            if (flag) {//说明flag改变了
                //Toast.makeText(this, "flag发生改变，加载导致", Toast.LENGTH_SHORT).show();
                //后面没有数据了
                text_tip.setVisibility(View.VISIBLE);
                flag = false;
                //且不可滑动
                xRecyclerView.setLoadingMoreEnabled(false);
            } else {//说明一直没有添加过
                //Toast.makeText(this, "没变，就是没数据", Toast.LENGTH_SHORT).show();
                text_tip.setVisibility(View.GONE);
                error_tips.setVisibility(View.VISIBLE);
                xRecyclerView.setVisibility(View.GONE);
                return;
            }
        } else {
            //适配器还原
            xRecyclerView.setVisibility(View.VISIBLE);
            error_tips.setVisibility(View.GONE);
            text_tip.setVisibility(View.GONE);
            if (searchPresenter.getPage() == 1) {
                searchAdapter.clear();
            }
            searchAdapter.addList(result);
            flag = true;//如果添加进了适配器，则说明上一次有值
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }

    @Override
    protected void destoryData() {
        if (searchPresenter != null) {
            searchPresenter.unBind();
        }
    }
}

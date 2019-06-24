package bwie.com.weidumall.view.fragment.home;


import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.home.HomeListAdapter;
import bwie.com.weidumall.common.App;
import bwie.com.weidumall.common.BaseFragment;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.ImageBean;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.cart.Shop;
import bwie.com.weidumall.entity.home.HomeBanner;
import bwie.com.weidumall.entity.home.HomeType;
import bwie.com.weidumall.presenter.cart.ShopCarPresenter;
import bwie.com.weidumall.presenter.home.HomeBannerPresenter;
import bwie.com.weidumall.presenter.home.HomeDataPresenter;
import bwie.com.weidumall.view.activity.home.DetailActivity;
import bwie.com.weidumall.view.activity.home.SearchActivity;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/20 21:19
 * Description:
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_nav)
    ImageView homeNav;  //左边导航
    @BindView(R.id.home_search)
    ImageView homeSearch;  //右边关键字查询商品
    @BindView(R.id.home_banner)  //轮播图
            XBanner homeBanner;
    @BindView(R.id.home_list)
    XRecyclerView homeList;
    private HomeBannerPresenter homeBannerPresenter;
    private HomeDataPresenter homeDataPresenter;
    private HomeListAdapter homeDataAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeBannerPresenter = new HomeBannerPresenter(new ShowBanner());
        homeDataPresenter = new HomeDataPresenter(new ShowData());
        homeDataAdapter = new HomeListAdapter(getContext());
        homeList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {

        //设置xRecyclerVIew不可滑动
        //homeList.setLoadingMoreEnabled(false);
        homeList.setPullRefreshEnabled(false);

        carousel(); //轮播图
        homeList.setAdapter(homeDataAdapter);
        homeDataPresenter.requestData();

        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopCarPresenter shopCarPresenter = new ShopCarPresenter(new ShopCar());

                shopCarPresenter.requestData();
            }
        });

        homeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(SearchActivity.class);
            }
        });
    }

    class ShopCar implements ImplView<List<Shop>> {

        @Override
        public void success(Result data, Object... args) {
            List<Shop> list = (List<Shop>) data.getResult();
            for (int i = 0; i < list.size(); i++) {
                Log.i("aaaaa", "拿到了" + list.get(i).getCategoryName());
            }
        }

        @Override
        public void fail(Result result, Object... args) {

        }
    }

    /**
     * 轮播图的操作
     */
    private void carousel() {
        homeBannerPresenter.requestData();

        homeBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                showToast("点击了" + position + "项");
            }
        });
    }


    class ShowBanner implements ImplView<List<HomeBanner>> {

        List<ImageBean> listCarousel = new ArrayList<>(); //播放轮播的图片集合

        /**
         * 成功获取网络的数据回调
         *
         * @param data
         * @param args
         */
        @Override
        public void success(Result data, Object... args) {
            List<HomeBanner> result = (List<HomeBanner>) data.getResult();
            if (listCarousel.size() == 0) {//如果集合为空
                //则添加
                for (int i = 0; i < result.size(); i++) {
                    ImageBean imageBean = new ImageBean();
                    imageBean.imgUrl = result.get(i).getImageUrl();
                    listCarousel.add(imageBean);
                }
            }
            // 为XBanner绑定数据
            homeBanner.setBannerData(listCarousel);
            // XBanner适配数据
            homeBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide
                            .with(getContext())
                            .load(((ImageBean) model).getXBannerUrl())
                            .into((ImageView) view);
                }
            });
        }

        /**
         * 失败的回调
         *
         * @param result
         * @param args
         */
        @Override
        public void fail(Result result, Object... args) {
            showToast(result.getMessage());
        }
    }

    class ShowData implements ImplView<HomeType> {

        /**
         * 首页商品数据成功回调
         *
         * @param data
         * @param args
         */
        @Override
        public void success(Result data, Object... args) {
            HomeType homeType = (HomeType) data.getResult();
            homeDataAdapter.add(homeType);
            homeDataAdapter.notifyDataSetChanged();
        }

        /**
         * 失败回调
         *
         * @param result
         * @param args
         */
        @Override
        public void fail(Result result, Object... args) {
            showToast(result.getMessage());
        }
    }

    /**
     * 处理内存泄露
     */
    @Override
    protected void destoryData() {
        if (homeBannerPresenter != null) {
            homeBannerPresenter.unBind();
            homeBannerPresenter = null;
        }
        if (homeDataPresenter != null) {
            homeDataPresenter.unBind();
            homeDataPresenter = null;
        }
    }
}

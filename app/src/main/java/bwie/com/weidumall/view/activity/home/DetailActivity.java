package bwie.com.weidumall.view.activity.home;


import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.cart.ShopAdapter;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.ImageBean;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.cart.SysnGoods;
import bwie.com.weidumall.entity.home.DetailData;
import bwie.com.weidumall.presenter.cart.SyncCartPresenter;
import bwie.com.weidumall.presenter.home.DetailPresenter;

public class DetailActivity extends BaseActivity implements ImplView<DetailData> {

    @BindView(R.id.detail_back)
    ImageView detailBack;
    @BindView(R.id.sp)
    RadioButton sp;
    @BindView(R.id.xq)
    RadioButton xq;
    @BindView(R.id.pl)
    RadioButton pl;
    @BindView(R.id.detail_radio_group)
    RadioGroup detailRadioGroup;
    @BindView(R.id.detail_title)
    RelativeLayout detailTitle;
    @BindView(R.id.detailbanner)
    XBanner detailbanner;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.detail_price)
    RelativeLayout detailPrice;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text_line)
    View textLine;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.addShopCar)
    ImageView addShopCar;

    DetailPresenter detailPresenter;
    private boolean flag;
    private SysnGoods sysnGoods;
    List<SysnGoods> list = new ArrayList<>();
    private int id;
    private List<ImageBean> imgs = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {

        //接收传过来的id
        Intent intent = getIntent();
        id = intent.getIntExtra("commodityId", 0);

        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        final WebSettings settings = webview.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        detailPresenter = new DetailPresenter(this);
        detailPresenter.requestData(String.valueOf(id));

        addToShopCar();
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void destoryData() {
        //刷新购物车
//        CartFragment.requestData(true);
        new ShopAdapter().notifyDataSetChanged();
    }

    /**
     * 点击图标同步购物车
     */
    public void addToShopCar() {
        final SyncCartPresenter syncCartPresenter = new SyncCartPresenter(new ImplView() {
            @Override
            public void success(Result data, Object... args) {
                showToast("请求成功");
            }

            @Override
            public void fail(Result result, Object... args) {
                showToast(result.getMessage());
            }
        });

        addShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

           /* private SysnGoodsDao sysnGoodsDao;

            @Override
            public void onClick(View view) {

                sysnGoodsDao = DaoMaster
                        .newDevSession(DetailActivity.this, SysnGoodsDao.TABLENAME)
                        .getSysnGoodsDao();
                //保存数据   去重
                for (int i = 0; i < sysnGoodsDao.loadAll().size(); i++) {
                    if (sysnGoodsDao.loadAll().get(i).commodityId == id) {
                        //sysnGoods.count++;
                        *//*int currentCount = sysnGoodsDao.loadAll().get(i).count++;

                        sysnGoods.count = currentCount;*//*

                        SysnGoods bean = sysnGoodsDao.loadAll().get(i);
                        bean.count++;
                        Log.i("windy", "详情页:数据库当前个数" + bean.count);
//                        sysnGoodsDao.update(sysnGoods);
                        sysnGoodsDao.insertOrReplaceInTx(bean);
                        flag = true;
                    }
                }

                if (!flag) {
                    //当前所点击的商品
                    sysnGoods = new SysnGoods(id, 1);
                    sysnGoodsDao.insertOrReplaceInTx(sysnGoods);
                    Log.i("windy", "详情页:bean类当前个数" + sysnGoods.count);
                }
                //sysnGoodsDao.insertOrReplaceInTx(sysnGoods);
                //导出数据库
                List<SysnGoods> old_list = sysnGoodsDao.loadAll();
                //把所有数据添加进集合  先清除再添加
                list.clear();
                list.addAll(old_list);

                final Gson gson = new Gson();
                final String s = gson.toJson(list);

                //s是数据
                Toast.makeText(DetailActivity.this, "" + s + list.size(), Toast.LENGTH_SHORT).show();

                addShopCarPresenter.requestData(String.valueOf(AppUser.getUserId()),
                        AppUser.getSessionId(), s);*/


    @Override
    public void success(Result data, Object... args) {
        DetailData result = (DetailData) data.getResult();
        if (result.getDetails() == null) { //如果详情信息为空
            //则直接吐司
            showToast("详情为空!");
        } else {
            String js = "<script type=\"text/javascript\">" +
                    "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                    "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                    "imgs[i].style.width = '100%';" +  // 宽度改为100%
                    "imgs[i].style.height = 'auto';" +
                    "}" +
                    "</script>";
            webview.loadDataWithBaseURL(null, result.getDetails() + js, "text/html", "utf-8", null);
        }
        final String picture = result.getPicture();
        price.setText("￥" + result.getPrice());
        name.setText(result.getCommodityName());

        String[] images = picture.split(",");
        if (imgs.size() < 1) {
            for (int i = 0; i < images.length; i++) {
                final ImageBean bannerBean = new ImageBean();
                bannerBean.imgUrl = images[i];
                imgs.add(bannerBean);
            }
        }
        detailbanner.setBannerData(imgs);
        detailbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailActivity.this).load(((ImageBean) model).getXBannerUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)).override(240, 240))
                        .into((ImageView) view);
            }
        });
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }
}

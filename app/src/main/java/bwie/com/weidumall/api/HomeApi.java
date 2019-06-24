package bwie.com.weidumall.api;

import java.util.List;

import bwie.com.weidumall.common.Constant;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.home.DetailData;
import bwie.com.weidumall.entity.home.HomeBanner;
import bwie.com.weidumall.entity.home.HomeType;
import bwie.com.weidumall.entity.home.SearchGoods;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2019/6/18
 * name:windy
 * function:
 * 关于首页的一些请求
 */
public interface HomeApi {

    //首页展示轮播图
    @GET(Constant.HOME_BANNER_URL)
    Observable<Result<List<HomeBanner>>> showBanner();

    //首页数据详情
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<Result<DetailData>> findDetailsById(@Query("commodityId") String commodityId);

    //根据关键字查找数据
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchGoods>>> findGoodsByKeyword(@Query("keyword") String keword,
                                                             @Query("page") String page,
                                                             @Query("count") String count);

    //展示首页数据
    @GET("commodity/v1/commodityList")
    Observable<Result<HomeType>> showIndexData();
}

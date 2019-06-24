package bwie.com.weidumall.api;

import java.util.List;

import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.cart.Shop;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * date:2019/6/18
 * name:windy
 * function:
 * 关于购物车的一些请求
 */
public interface CartApi {

    //同步购物车
    @FormUrlEncoded
    @PUT("order/verify/v1/syncShoppingCart")
    Observable<Result> syncShoppingCart(@Field("data") String data);

    //查询购物车数据
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<Shop>>> shopCar();

}

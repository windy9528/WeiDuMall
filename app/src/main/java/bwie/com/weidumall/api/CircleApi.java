package bwie.com.weidumall.api;


import java.util.List;

import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.circle.CircleEntity;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * date:2019/6/18
 * name:windy
 * function:
 * 关于圈子的一些请求
 */
public interface CircleApi {

    //圈子列表
    @GET("circle/v1/findCircleList")
    Observable<Result<List<CircleEntity>>> showCircle(@Query("page") String page,
                                                      @Query("count") String count);

    //圈子点赞
    @FormUrlEncoded
    @POST("circle/verify/v1/addCircleGreat")
    Observable<Result> like(@Field("circleId") String circleId);

    //取消点赞
    @DELETE("circle/verify/v1/cancelCircleGreat")
    Observable<Result> unLike(@Query("circleId") String circleId);

    //发表圈子
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> publishCircle(@Body MultipartBody body);
}

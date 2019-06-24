package bwie.com.weidumall.api;

import java.util.List;

import bwie.com.weidumall.common.Constant;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.user.TakeList;
import bwie.com.weidumall.entity.user.UserData;
import bwie.com.weidumall.entity.user.UserInfo;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * date:2019/6/18
 * name:windy
 * function:
 * 关于用户的一些请求
 */
public interface UserApi {

    //登录
    @FormUrlEncoded
    @POST(Constant.LOGIN_URL)
    Observable<Result<UserInfo>> login(@Field("phone") String phone, @Field("pwd") String password);

    //注册
    @FormUrlEncoded
    @POST(Constant.REGISTER_URL)
    Observable<Result> register(@Field("phone") String phone, @Field("pwd") String password);

    //查询用户
    @GET("user/verify/v1/getUserById")
    Observable<Result<UserData>> userData();

    //展示收货地址列表
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<TakeList>>> takeAddressList();

    //上传头像
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result> upIncon(@Body MultipartBody body);
}

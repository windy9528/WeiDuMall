package bwie.com.weidumall.api;

import bwie.com.weidumall.common.Constant;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.user.UserInfo;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}

package bwie.com.weidumall.presenter.user;

import bwie.com.weidumall.api.UserApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.UserModel;
import io.reactivex.Observable;

/**
 * date:2019/6/19
 * name:windy
 * function:登录p层
 */
public class LoginPresenter extends UserModel {

    public LoginPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(UserApi userApi, Object... args) {
        return userApi.login(
                String.valueOf(args[0]),
                String.valueOf(args[1])
        );
    }
}

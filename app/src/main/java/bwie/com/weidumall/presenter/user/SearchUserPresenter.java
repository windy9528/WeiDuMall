package bwie.com.weidumall.presenter.user;

import bwie.com.weidumall.api.UserApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.UserModel;
import io.reactivex.Observable;

/**
 * date:2019/6/24
 * name:windy
 * function:
 */
public class SearchUserPresenter extends UserModel {

    public SearchUserPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(UserApi userApi, Object... args) {
        return userApi.userData();
    }
}

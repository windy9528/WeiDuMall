package bwie.com.weidumall.presenter.cart;

import bwie.com.weidumall.api.CartApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.CartModel;
import io.reactivex.Observable;

/**
 * date:2019/6/20
 * name:windy
 * function:
 */
public class SyncCartPresenter extends CartModel {

    public SyncCartPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(CartApi cartApi, Object... args) {
        return cartApi.syncShoppingCart(String.valueOf(args[0]));
    }
}

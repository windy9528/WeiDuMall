package bwie.com.weidumall.presenter.cart;



import bwie.com.weidumall.api.CartApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.CartModel;
import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/23 11:18
 */
public class ShopCarPresenter extends CartModel {


    public ShopCarPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(CartApi cartApi, Object... args) {
        return cartApi.shopCar();

    }
}

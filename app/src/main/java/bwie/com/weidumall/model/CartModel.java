package bwie.com.weidumall.model;

import bwie.com.weidumall.api.CartApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.util.RetrofitUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/6/19
 * name:windy
 * function:
 */
public abstract class CartModel {

    private ImplView implView;
    private Disposable disposable;

    public CartModel(ImplView implView) {
        this.implView = implView;
    }

    /**
     * retrofit动态代理去访问网络请求数据
     * rxJava线程调度拿到数据
     *
     * @param args
     */
    public void requestData(final Object... args) {

        CartApi cartApi = RetrofitUtil.getHttpUtil().create(CartApi.class);

        disposable = getModel(cartApi, args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Result>() {
                    @Override
                    public Result apply(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        return new Result();
                    }
                })
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        if ("0000".equals(result.getStatus())) {
                            implView.success(result, args);
                        } else {
                            implView.fail(result, args);
                        }
                    }
                });
    }

    /**
     * p层继承该类 重写此方法
     *
     * @param cartApi
     * @param args
     * @return
     */
    protected abstract Observable getModel(CartApi cartApi, Object... args);

    /**
     * 避免内存泄露
     */
    public void unBind() {
        if (disposable != null) {
            disposable = null;
        }
        if (implView != null){
            implView = null;
        }
    }
}

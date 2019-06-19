package com.example.kson.base.base.mvp;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/04
 * Description:
 */
public abstract class BasePresenter<M, V> {
    public M mModel;
    public V mView;
    private WeakReference<V> weakReference;

    public abstract M getmModel();

    /**
     * 绑定view
     *
     * @param mModel
     * @param mView
     */
    public void attach(M mModel, V mView) {
        this.mModel = mModel;
        weakReference = new WeakReference<>(mView);
        this.mView = weakReference.get();
    }

    /**
     * 解绑
     */
    public void detach() {

        if (weakReference != null) {
            weakReference.clear();//清空若引用对象
            weakReference = null;
        }


    }


}

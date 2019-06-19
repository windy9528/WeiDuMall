package bwie.com.weidumall.constract;

import bwie.com.weidumall.entity.Result;

/**
 * date:2019/6/19
 * name:windy
 * function: v层的接口
 */
public interface ImplView<T> {

    void success(Result data, Object... args);

    void fail(Result result, Object... args);

}

package bwie.com.weidumall.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.weidumall.entity.user.UserInfo;

public abstract class BaseFragment extends Fragment {
    public Gson mGson = new Gson();

    private Unbinder unbinder;

    public static UserInfo AppUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      /*  UserInfoDao userInfoDao = DaoMaster.newDevSession(getActivity(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.LoginStatus.eq(1)).list();
        AppUser = userInfos.get(0);//读取第一项*/

        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();

        Log.i("windy", (this.toString() + "页面加载使用："
                + (System.currentTimeMillis() - time)));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        destoryData();
        System.gc();
    }

    /**
     * 设置layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 清除数据
     */
    protected abstract void destoryData();

    /**
     * 显示toast、
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示带时间的toast
     *
     * @param msg
     */
    public void showToast(String msg, int time) {
        Toast.makeText(getContext(), msg, time).show();
    }
}

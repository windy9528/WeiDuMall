package bwie.com.weidumall.view;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.RadioGroup;

import java.util.List;

import butterknife.BindView;
import bwie.com.weidumall.R;
import bwie.com.weidumall.adapter.FragmentAdapter;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.view.fragment.cart.CartFragment;
import bwie.com.weidumall.view.fragment.circle.CircleFragment;
import bwie.com.weidumall.view.fragment.home.HomeFragment;
import bwie.com.weidumall.view.fragment.order.OrderFragment;
import bwie.com.weidumall.view.fragment.user.UserFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.all_radiogroup)
    RadioGroup allRadiogroup;
    private List<Fragment> list;
    private FragmentAdapter fragmentAdapter;
    private HomeFragment homeFragment;
    private CircleFragment circleFragment;
    private CartFragment cartFragment;
    private OrderFragment orderFragment;
    private UserFragment userFragment;
    Fragment currentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {

        //动态申请权限

        homeFragment = new HomeFragment();
        circleFragment = new CircleFragment();
        cartFragment = new CartFragment();
        orderFragment = new OrderFragment();
        userFragment = new UserFragment();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        currentFragment = homeFragment;
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.container, homeFragment)
                .show(homeFragment).commit();

        allRadiogroup.setOnCheckedChangeListener(this);

    }

    /**
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.home) {
            showFragment(homeFragment);
        } else if (checkedId == R.id.circle) {
            showFragment(circleFragment);
        } else if (checkedId == R.id.cart) {
            showFragment(cartFragment);
        } else if (checkedId == R.id.order) {
            showFragment(orderFragment);
        } else if (checkedId == R.id.user) {
            showFragment(userFragment);
        }
    }

    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null, requestCode, data);
            if (!TextUtils.isEmpty(filePath)) {
//                userFragment.add(filePath);
            }
        }
    }
}

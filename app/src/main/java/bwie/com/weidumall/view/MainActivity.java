package bwie.com.weidumall.view;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import bwie.com.weidumall.R;
import bwie.com.weidumall.view.fragment.home.HomeFragment;
import bwie.com.weidumall.view.fragment.user.MyFragment;
import bwie.com.weidumall.view.fragment.ScanFragment;

import static bwie.com.weidumall.R.mipmap.common_tab_btn_home_n;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    //    private TextView mTextMessage;
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private MyFragment mMyFragment;
    private ScanFragment mScanFragment;
    private HomeFragment mHomeFragment;

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {


//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    //mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_circle:
//                    //mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_car:
//                   // mTextMessage.setText(R.string.title_notifications);
//                    return true;
//                case R.id.navigation_list:
//                   // mTextMessage.setText(R.string.title_notifications);
//                    return true;
//                case R.id.navigation_my:
//                    //mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);


        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor("#FF107FFD") //选中颜色#FF107FFD
                .setInActiveColor("#e9e6e6") //未选中颜色#e9e6e6
                .setBarBackgroundColor("#1ccbae");//导航栏背景色

        bottomNavigationBar
                .addItem(new BottomNavigationItem(common_tab_btn_home_n, null))
                .addItem(new BottomNavigationItem(R.mipmap.common_tab_btn_circle_n, ""))
                .addItem(new BottomNavigationItem(R.mipmap.common_tab_btn_list_n, ""))
                //.setFirstSelectedPosition(lastSelectedPosition )
                .initialise(); //initialise 一定要放在 所有设置的最后一项


        //BottomNavigationViewHelper.setImageSize(R.mipmap.common_tab_btn_home_n,20,20);

        setDefaultFragment();//设置默认导航栏

    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        mHomeFragment = HomeFragment.newInstance("首页");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d("windy", "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mScanFragment == null) {
                    mScanFragment = ScanFragment.newInstance("扫一扫");
                }
                transaction.replace(R.id.tb, mScanFragment);
                break;
            case 2:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance("个人中心");
                }
                transaction.replace(R.id.tb, mMyFragment);
                break;

            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }
}
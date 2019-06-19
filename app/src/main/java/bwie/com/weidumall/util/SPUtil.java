package bwie.com.weidumall.util;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/5/15 20:53
 * Description:
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CheckBox;

public class SPUtil {

    /**
     * 保存用户名 密码的业务方法
     *
     * @param context  上下文
     * @param phone    用户名
     * @param password 密码
     * @param checkBox 复选框
     * @return true 保存成功  false 保存失败
     */
    public static void saveLoginStatus(Context context, String phone, String password, CheckBox checkBox, boolean status) {
        /**
         * SharedPreferences将用户的数据存储到该包下的shared_prefs/config.xml文件中，
         * 并且设置该文件的读取方式为私有，即只有该软件自身可以访问该文件
         */
        SharedPreferences sPreferences = context.getSharedPreferences("loginStatus", context.MODE_PRIVATE);
        Editor editor = sPreferences.edit();
        //判断复选框的状态
        if (checkBox.isChecked()) {
            editor.putString("phone", phone);
            editor.putString("password", password);
            editor.putBoolean("flag", true);
            editor.putBoolean("loginStatus", true);
        } else {
            editor.clear();
        }
        editor.commit();//提交
    }

    /**
     * @param context
     * @param userId
     * @param sessionId 保存用户信息
     */
    public static void saveUserInfo(Context context, int userId, String sessionId) {
        /**
         * SharedPreferences将用户的数据存储到该包下的shared_prefs/config.xml文件中，
         * 并且设置该文件的读取方式为私有，即只有该软件自身可以访问该文件
         */
        SharedPreferences sPreferences = context.getSharedPreferences("userInfo", context.MODE_PRIVATE);
        Editor editor = sPreferences.edit();
        //存储用户id
        editor.putInt("phone", userId);
        editor.putString("password", sessionId);
        //提交
        editor.commit();
    }
/*
    public static int getInt(Context context, String key) {
        SharedPreferences userInfo = context
                .getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        int anInt = userInfo.getInt(key, 0);

        return anInt;
    }

    public static String getString(Context context, String key) {
        SharedPreferences userInfo = context
                .getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String string = userInfo.getString(key, "");

        return string;
    }

    public static String getSP(Context context, String key) {
        SharedPreferences userInfo = context
                .getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String string = userInfo.getString(key, null);

        return string;
    }*/
}


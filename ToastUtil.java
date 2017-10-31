package com.FragmentSup.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FragmentSup.Base.BaseApplication;
import com.FragmentSup.R;

/***
 * 类描述：提示类
 * 创建人：evan.yang
 * 创建时间：2016/5/13 9:25
 * Email: william.wang@kemai.cn
 * 修改备注：
 */
public class ToastUtil {

    private static Toast mToast;

    /**
     * 根据字符串提示
     *
     * @param text
     */
/*    public static void showToast(String text) {
//        if (HttpRequestTask.ERROR_MSG.equals(text)) {
//            showDialog();
//            return;
//        }
        try{
            if (mToast == null) {
                initToast(text);
            } else {
                if (StringUtils.isEmpty(text)) {
                    text = "网络连接出现问题，请切换网络";
                }
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
        }catch (Exception e){}
    }*/

    public static final String ERROR_MSG = "网络连接出现问题，请切换网络";

    /**
     * 根据字符串提示
     *
     * @param text
     */
    public static void showToast(String text, Activity activity) {
        /*if (HttpRequestTask.ERROR_MSG.equals(text)) {
            showDialog(activity);
            return;
        }*/
        if (mToast == null) {
            initToast(text);
        } else {
            if (StringUtils.isEmpty(text)) {
                text = "网络连接出现问题，请切换网络";
            }
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    private static void initToast(String text) {
        mToast = Toast.makeText(BaseApplication.getAppContext(), text, Toast.LENGTH_SHORT);
        LinearLayout linearLayout = (LinearLayout) mToast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        messageTextView.setTextSize(BaseApplication.getAppContext().getResources().getDimension(R.dimen.font_8));
        int y = (int) BaseApplication.getAppContext().getResources().getDimension(R.dimen.height_8);
        mToast.setGravity(Gravity.BOTTOM, 0, y);
    }


    /**
     * 取消提示
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    /***
     * 根据id提示
     *
     * @param msg
     */
    public static void showToast(int msg) {
        String newMsg = BaseApplication.getAppContext().getResources().getString(msg);
//        if (HttpRequestTask.ERROR_MSG.equals(newMsg)) {
//            showDialog();
//            return;
//        }
        if (mToast == null) {
            initToast(newMsg);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    public static void showToast(String msg) {
        try {
            Context mContext = BaseApplication.getContext();
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_toast, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
            tv_msg.setText(msg);
            Toast toast = new Toast(mContext);
            //获取屏幕高度
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();
            //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
            toast.setGravity(Gravity.BOTTOM, 0, height / 10);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
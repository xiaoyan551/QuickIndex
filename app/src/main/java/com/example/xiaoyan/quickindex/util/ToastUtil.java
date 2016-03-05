package com.example.xiaoyan.quickindex.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xiaoyan on 2016/3/3 23:15.
 * 这是一个单例的土司
 */
public class ToastUtil {
    private static Toast sToast;

    public static void shouwToast(Context context, String string) {
        if (sToast == null) {
            sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        sToast.setText(string);
        sToast.show();

    }


}

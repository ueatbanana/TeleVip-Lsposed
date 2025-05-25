package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;


import com.my.televip.base.AbstractMethodHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class TelePremium {

    public static void init(){
        Class<?> userConfigClass = XposedHelpers.findClassIfExists("org.telegram.messenger.UserConfig", lpparam.classLoader);
if ( userConfigClass != null) {
    // استخدم hook لتعديل متغير isPremium في الكائن
    XposedHelpers.findAndHookMethod(userConfigClass, "isPremium", new AbstractMethodHook() {
        @Override
        public void beforeMethod(XC_MethodHook.MethodHookParam param) {

            param.setResult(true);
        }
    });
}
    }

}

package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.xSharedPreferences;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class NoStoryRead {

    public static void init() {

        Class<?> StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
     if (StoriesControllerClass != null) {
         loadClass.loadObj1();
         XC_MethodHook hook = new AbstractMethodHook() {
             @Override
             protected void beforeMethod(MethodHookParam param) {
                 param.setResult(false);
             }
         };
         XposedHelpers.findAndHookMethod(
                 StoriesControllerClass,
                 "markStoryAsRead",
                 AutomationResolver.merge(AutomationResolver.resolveObject("obj1"), hook));
     }
    }
}

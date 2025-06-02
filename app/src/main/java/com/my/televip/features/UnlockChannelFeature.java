package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.xSharedPreferences;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class UnlockChannelFeature {

    public static void init() {
        if (loadClass.MessagesControllerClass == null) {
            loadClass.MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
        }
        if (loadClass.MessagesControllerClass != null) {
            AutomationResolver.loadParameter("3");
            XposedHelpers.findAndHookMethod(loadClass.MessagesControllerClass, AutomationResolver.resolve("MessagesController","isChatNoForwards", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("Parameter3"),  new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    param.setResult(false);

                }
            }));
        }
    }

}

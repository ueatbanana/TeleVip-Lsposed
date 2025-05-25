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
        Class<?> messagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
        if (messagesControllerClass != null) {
            loadClass.loadObj8();
            XC_MethodHook hook = new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (xSharedPreferences.xSharedPre.contains("usefolow")) {
                        param.setResult(false);
                    }

                }
            };

            XposedHelpers.findAndHookMethod(messagesControllerClass, "isChatNoForwards", AutomationResolver.merge(AutomationResolver.resolveObject("obj8"), hook));
        }
    }

}

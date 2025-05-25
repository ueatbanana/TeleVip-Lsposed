package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class HidePhone {

    public static void init() {
    Class<?> MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
    if (MessagesControllerClass != null) {
        Class<?> baseFragmentClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.BaseController"), lpparam.classLoader);
        if (baseFragmentClass != null) {
            XposedHelpers.findAndHookMethod(MessagesControllerClass, "getUser", Long.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    Object userObject = param.getResult();
                    Object MessagesControllerInstance = param.thisObject;
                    if (userObject != null) {


                        Method getUserConfigMethod = baseFragmentClass.getDeclaredMethod("getUserConfig");
                        getUserConfigMethod.setAccessible(true);
                        Object userConfig = getUserConfigMethod.invoke(MessagesControllerInstance);

                        Method getClientUserIdMethod = userConfig.getClass().getDeclaredMethod("getClientUserId");
                        getClientUserIdMethod.setAccessible(true);
                        long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);
                        long userid = (long) param.args[0];

                        if (clientUserId == userid) {
                            XposedHelpers.setObjectField(userObject, "phone", null);
                        }
                    }
                }
            });
        }
    }
    }

}

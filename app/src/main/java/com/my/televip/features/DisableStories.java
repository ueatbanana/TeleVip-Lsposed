package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.ClientChecker;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import de.robv.android.xposed.XposedHelpers;

public class DisableStories {

    public static void init() {
        if (loadClass.MessagesControllerClass == null) {
            loadClass.MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
        }
        if (loadClass.MessagesControllerClass != null){
        XposedHelpers.findAndHookMethod(loadClass.MessagesControllerClass, AutomationResolver.resolve("MessagesController","storiesEnabled", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);
            }
        });
        XposedHelpers.findAndHookMethod(loadClass.MessagesControllerClass, AutomationResolver.resolve("MessagesController","storyEntitiesAllowed", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);

            }
        });
    AutomationResolver.loadParameter("8");
        XposedHelpers.findAndHookMethod(loadClass.MessagesControllerClass, AutomationResolver.resolve("MessagesController","storyEntitiesAllowed", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("Parameter8"),  new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);
            }
        }));
    }
        Class<?> StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
if (StoriesControllerClass != null) {
    if (ClientChecker.check(ClientChecker.ClientType.NagramX)){
        XposedHelpers.findAndHookMethod(StoriesControllerClass, AutomationResolver.resolve("MessagesController", "hasStories", AutomationResolver.ResolverType.Method),long.class, new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);

            }
        });
    }else {
        XposedHelpers.findAndHookMethod(StoriesControllerClass, AutomationResolver.resolve("MessagesController", "hasStories", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);

            }
        });
    }
}
    }

}

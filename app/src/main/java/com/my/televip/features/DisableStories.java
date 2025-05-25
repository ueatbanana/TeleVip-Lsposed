package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class DisableStories {

    public static void init() {
        Class<?> MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
if (MessagesControllerClass != null){
        XposedHelpers.findAndHookMethod(MessagesControllerClass, "storiesEnabled", new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);
            }
        });
        XposedHelpers.findAndHookMethod(MessagesControllerClass, "storyEntitiesAllowed", new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);

            }
        });
    loadClass.loadObj14();
    XC_MethodHook hook = new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                param.setResult(false);
            }
        };

        XposedHelpers.findAndHookMethod(MessagesControllerClass, "storyEntitiesAllowed", AutomationResolver.merge(AutomationResolver.resolveObject("obj14"), hook));
    }
        Class<?> StoriesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), lpparam.classLoader);
if (StoriesControllerClass != null) {
    XposedHelpers.findAndHookMethod(StoriesControllerClass, "hasStories", new AbstractMethodHook() {
        @Override
        protected void beforeMethod(MethodHookParam param) {
            param.setResult(false);

        }
    });
}
    }

}

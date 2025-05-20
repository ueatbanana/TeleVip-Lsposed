package com.my.televip.features;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.ClientChecker;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.configs.Configs;
import com.my.televip.obfuscate.AutomationResolver;

public class NoSponsoredMessages {
    public static void init(ClassLoader classLoader)
    {
        Class<?> messagesController = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), classLoader);
        Class<?> chatActivity = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), classLoader);

            XposedHelpers.findAndHookMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "addSponsoredMessages", AutomationResolver.ResolverType.Method), boolean.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (Configs.isNoSponsoredMessages())
                        param.setResult(null);
                }
            });

            XposedHelpers.findAndHookMethod(messagesController, AutomationResolver.resolve("MessagesController", "getSponsoredMessages", AutomationResolver.ResolverType.Method), long.class, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (Configs.isNoSponsoredMessages())
                        param.setResult(null);
                }
            });
            /*List<Method> methods = new ArrayList<>();

            for (Method method : messagesController.getDeclaredMethods()) {
                if (method.getName().contains("SponsoredMessages"))
                    methods.add(method);
            }

            for (Method method : chatActivity.getDeclaredMethods()) {
                if (method.getName().contains("SponsoredMessages"))
                    methods.add(method);
            }

            for (Method method : methods) {
                XposedBridge.hookMethod(method, new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (Configs.isNoSponsoredMessages())
                            param.setResult(null);
                    }
                });
            }*/
        
    }
}

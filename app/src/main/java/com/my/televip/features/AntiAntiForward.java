package com.my.televip.features;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.HookUtils;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.configs.Configs;
import com.my.televip.obfuscate.AutomationResolver;

public class AntiAntiForward {
    public static void init(ClassLoader classLoader)
    {
        Class<?> messagesController = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), classLoader);
        if (messagesController != null)
        {
            String isChatNoForwardsMethod = AutomationResolver.resolve("MessagesController", "isChatNoForwards", AutomationResolver.ResolverType.Method);
            HookUtils.findAndHookAllMethod(messagesController, isChatNoForwardsMethod, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (Configs.isAntiAntiForward())
                        param.setResult(false);
                }
            });
        }
        else
        {
            Utils.log("Not found MessagesController, " + Utils.issue);
        }

        Class<?> chatActivity = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), classLoader);
        if (chatActivity != null)
        {
            String hasSelectedNoforwardsMessageMethod = AutomationResolver.resolve("ChatActivity", "hasSelectedNoforwardsMessage", AutomationResolver.ResolverType.Method);
            XposedHelpers.findAndHookMethod(chatActivity, hasSelectedNoforwardsMessageMethod, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (Configs.isAntiAntiForward())
                        param.setResult(false);
                }
            });
        }
        else
        {
            Utils.log("Not found ChatActivity, " + Utils.issue);
        }

        Class<?> messageObject = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), classLoader);
        if (messageObject != null)
        {
            String canForwardMessageMethod = AutomationResolver.resolve("MessageObject", "canForwardMessage", AutomationResolver.ResolverType.Method);
            XposedHelpers.findAndHookMethod(messageObject, canForwardMessageMethod, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (Configs.isAntiAntiForward())
                        param.setResult(true);
                }
            });
        }
        else
        {
            Utils.log("Not found MessageObject, " + Utils.issue);
        }
    }
}
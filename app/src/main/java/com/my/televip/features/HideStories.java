package com.my.televip.features;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.configs.Configs;
import com.my.televip.obfuscate.AutomationResolver;

public class HideStories {
    public static void init(ClassLoader classLoader)
    {
        Class<?> StoriesController = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Stories.StoriesController"), classLoader);
        if (StoriesController != null)
            XposedHelpers.findAndHookMethod(StoriesController, AutomationResolver.resolve("StoriesController", "hasStories", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam methodHookParam)
                {
                    if (Configs.isHideStories())
                        methodHookParam.setResult(false);
                }
            });

        Class<?> MessagesController = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), classLoader);
        if (MessagesController != null)
            XposedHelpers.findAndHookMethod(MessagesController, AutomationResolver.resolve("MessagesController", "storiesEnabled", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam methodHookParam)
                {
                    if (Configs.isHideStories())
                        methodHookParam.setResult(false);
                }
            });
    }
}

package com.my.televip.features;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.ClientChecker;
import com.my.televip.HookUtils;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.configs.Configs;
import com.my.televip.obfuscate.AutomationResolver;

public class AllowMoveAllChatFolder {
    public static void init(ClassLoader classLoader)
    {
        String onDefaultTabMoved = AutomationResolver.resolve("DialogsActivity", "onDefaultTabMoved", AutomationResolver.ResolverType.Method);
        Class<?> dialogsActivity = null;
        String dialogsActivityName = AutomationResolver.resolve("org.telegram.ui.DialogsActivity");
        if (dialogsActivityName.equals("org.telegram.ui.DialogsActivity")) {
            for (int i = 0; i < 51; i++)
            {
                Class<?> dialogsActivity$ = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.DialogsActivity$" + i), classLoader);
                if (dialogsActivity$ != null)
                    for (Method declaredMethod : dialogsActivity$.getDeclaredMethods()) {
                        if (declaredMethod.getName().equals(onDefaultTabMoved))
                        {
                            dialogsActivity = dialogsActivity$;
                            break;
                        }
                    }
            }
        }
        else
            dialogsActivity = XposedHelpers.findClassIfExists(dialogsActivityName, classLoader);

        if (dialogsActivity != null)
        {
            //HookUtils.findAndHookAllMethod(
            XposedHelpers.findAndHookMethod(dialogsActivity, onDefaultTabMoved, new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                    if (Configs.isAllowMoveAllChatFolder())
                        param.setResult(null);
                }
            });
        }
        else
        {
            Utils.log("Not found DialogsActivity, " + Utils.issue);
        }

        if (!ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
            Class<?> filtersSetupActivity = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.FilterTabsView$TouchHelperCallback"), classLoader);
            if (filtersSetupActivity != null) {
                String run = AutomationResolver.resolve("FilterTabsView$TouchHelperCallback", "onSelectedChanged", AutomationResolver.ResolverType.Method);

                HookUtils.findAndHookMethod(filtersSetupActivity, run, new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (Configs.isAllowMoveAllChatFolder())
                            param.setResult(null);
                    }
                });
            } else {
                Utils.log("Not found FiltersSetupActivity, " + Utils.issue);
            }
        }
        else
        {
            Class<?> filtersSetupActivity = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.FiltersSetupActivity$TouchHelperCallback"), classLoader);
            if (filtersSetupActivity != null) {
                String onSelectedChanged = AutomationResolver.resolve("FiltersSetupActivity$TouchHelperCallback", "resetDefaultPosition", AutomationResolver.ResolverType.Method);
                XposedHelpers.findAndHookMethod(filtersSetupActivity, onSelectedChanged, new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                        if (Configs.isAllowMoveAllChatFolder())
                            param.setResult(null);
                    }
                });
            } else {
                Utils.log("Not found FiltersSetupActivity$TouchHelperCallback, you maybe using an unsupported Nekogram version.");
            }
        }
    }
}

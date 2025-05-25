package com.my.televip.virtuals;

import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class ActiveTheme {

    public static boolean isCurrentThemeDay=false;
    public static void setActiveTheme() {
        try {

            // الحصول على الكائن الحالي من ThemeInfo
            Object currentThemeInfo = XposedHelpers.callStaticMethod(
                    XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.ui.ActionBar.Theme"), MainHook.lpparam.classLoader),
                    AutomationResolver.resolve("Theme","getActiveTheme", AutomationResolver.ResolverType.Method));

            if (currentThemeInfo != null) {
                // التحقق من قيمة isCurrentThemeDay
                isCurrentThemeDay = (boolean) XposedHelpers.callMethod(currentThemeInfo, "isDark");
            } else {
                XposedBridge.log("getActiveTheme returned null.");
            }
        } catch (Exception e) {
            XposedBridge.log("getActiveTheme: Error while checking isDark - " + e.getMessage());
        }
    }
}

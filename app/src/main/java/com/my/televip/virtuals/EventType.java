package com.my.televip.virtuals;

import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class EventType {

    public static int eventType;

    public static int IconSettings() {
        if (loadClass.drawableClass == null) {
            loadClass.drawableClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.R$drawable"), MainHook.lpparam.classLoader);
        }
        int drawableResource = 3;

            eventType = (int) XposedHelpers.callStaticMethod(XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.Theme"), MainHook.lpparam.classLoader), AutomationResolver.resolve("Theme", "getEventType", AutomationResolver.ResolverType.Method));
        if (loadClass.drawableClass != null) {
            if (eventType == 0) {
                drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "msg_settings_ny");
            } else if (eventType == 1) {
                drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "msg_settings_14");
            } else if (eventType == 2) {
                drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "msg_settings_hw");
            } else {
                drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "msg_settings_old");
            }
        }else {
            Utils.log("Not found rg.telegram.messenger.R$drawable, " + Utils.issue);
        }
        return drawableResource;
    }
}

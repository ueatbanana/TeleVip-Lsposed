package com.my.televip.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.EditText;

import com.my.televip.ClientChecker;
import com.my.televip.MainHook;
import com.my.televip.StrVip;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.xSharedPreferences;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class onClickDialog extends StrVip {
    public static void onClickOpenUrl(Context applicationContext, XC_MethodHook.MethodHookParam param){
        Object drawerLayoutContainer = null;
        if (ClientChecker.check(ClientChecker.ClientType.NagramX)){
            drawerLayoutContainer = XposedHelpers.getObjectField(param.args[0], AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
        }else {
            drawerLayoutContainer = XposedHelpers.getObjectField(param.thisObject, AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
        }
         if (drawerLayoutContainer != null) {
            XposedHelpers.callStaticMethod(
                    XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.browser.Browser"), MainHook.lpparam.classLoader),
                    AutomationResolver.resolve("Browser","openUrl", AutomationResolver.ResolverType.Method), applicationContext, "https://t.me/t_l0_e"
            );
            XposedHelpers.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutAdapter","closeDrawer", AutomationResolver.ResolverType.Method));
            //XposedHelpers.callMethod(dialog, "dismiss");
        }
    }
    public static void onClickSave(List<CheckBox> checkBoxes){
            // الكود الذي يتم تنفيذه عند الضغط على الزر
            for (int i = 0; i < checkBoxes.size(); i++) {
                CheckBox checkBox = checkBoxes.get(i);
                if (checkBox.isChecked()) {
                    if (checkBox.getText().toString().equals(pre)) {
                        xSharedPreferences.SharedPre.edit().putString("prem", "true").apply();
                    } else if (checkBox.getText().toString().equals(noRead)) {
                        xSharedPreferences.SharedPre.edit().putString("noRead", "true").apply();
                    } else if (checkBox.getText().toString().equals(noRead2)) {
                        xSharedPreferences.SharedPre.edit().putString("noRead2", "true").apply();
                    } else if (checkBox.getText().toString().equals(NoTyping)) {
                        xSharedPreferences.SharedPre.edit().putString("NoTyping", "true").apply();
                    } else if (checkBox.getText().toString().equals(noStoryRead)) {
                        xSharedPreferences.SharedPre.edit().putString("noStoryRead", "true").apply();
                    } else if (checkBox.getText().toString().equals(usefolow)) {
                        xSharedPreferences.SharedPre.edit().putString("usefolow", "true").apply();
                    } else if (checkBox.getText().toString().equals(allowShare)) {
                        xSharedPreferences.SharedPre.edit().putString("allowShare", "true").apply();
                    } else if (checkBox.getText().toString().equals(HideOnline)) {
                        xSharedPreferences.SharedPre.edit().putString("HideOnline", "true").apply();
                    } else if (checkBox.getText().toString().equals(PreventMedia)) {
                        xSharedPreferences.SharedPre.edit().putString("PreventMedia", "true").apply();
                    } else if (checkBox.getText().toString().equals(HidePhone)) {
                        xSharedPreferences.SharedPre.edit().putString("HidePhone", "true").apply();
                    } else if (checkBox.getText().toString().equals(shmsdel)) {
                        xSharedPreferences.SharedPre.edit().putString("shmsdel", "true").apply();
                    } else if (checkBox.getText().toString().equals(hidestore)) {
                        xSharedPreferences.SharedPre.edit().putString("hidestore", "true").apply();
                    }
                } else {
                    if (checkBox.getText().toString().equals(pre)) {
                        xSharedPreferences.SharedPre.edit().remove("prem").apply();
                    } else if (checkBox.getText().toString().equals(noRead)) {
                        xSharedPreferences.SharedPre.edit().remove("noRead").apply();
                    } else if (checkBox.getText().toString().equals(noRead2)) {
                        xSharedPreferences.SharedPre.edit().remove("noRead2").apply();
                    } else if (checkBox.getText().toString().equals(NoTyping)) {
                        xSharedPreferences.SharedPre.edit().remove("NoTyping").apply();
                    } else if (checkBox.getText().toString().equals(noStoryRead)) {
                        xSharedPreferences.SharedPre.edit().remove("noStoryRead").apply();
                    } else if (checkBox.getText().toString().equals(usefolow)) {
                        xSharedPreferences.SharedPre.edit().remove("usefolow").apply();
                    } else if (checkBox.getText().toString().equals(allowShare)) {
                        xSharedPreferences.SharedPre.edit().remove("allowShare").apply();
                    } else if (checkBox.getText().toString().equals(HideOnline)) {
                        xSharedPreferences.SharedPre.edit().remove("HideOnline").apply();
                    } else if (checkBox.getText().toString().equals(PreventMedia)) {
                        xSharedPreferences.SharedPre.edit().remove("PreventMedia").apply();
                    } else if (checkBox.getText().toString().equals(HidePhone)) {
                        xSharedPreferences.SharedPre.edit().remove("HidePhone").apply();
                    } else if (checkBox.getText().toString().equals(shmsdel)) {
                        xSharedPreferences.SharedPre.edit().remove("shmsdel").apply();
                    } else if (checkBox.getText().toString().equals(hidestore)) {
                        xSharedPreferences.SharedPre.edit().remove("hidestore").apply();
                    }
                }
            }

            // غلق الـ AlertDialog بعد التحقق
          //  XposedHelpers.callMethod(dialog, "dismiss");

        StrVip.readFeature();
    }
    public static void onClickToMessageId(EditText editText, Object chatActivity){
        String inputText = editText.getText().toString().trim();
        // التحقق من المدخلات
        if (!inputText.isEmpty()) {
            int msid = Integer.parseInt(inputText);
            XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "scrollToMessageId", AutomationResolver.ResolverType.Method), msid, 0, true, 0, true, 0);
            // XposedBridge.log("scrollToMessageId is call.");

        }
    }
    public static void onClickDismiss(DialogInterface dialog){
        XposedHelpers.callMethod(dialog, AutomationResolver.resolve("AlertDialog", "dismiss", AutomationResolver.ResolverType.Method));
    }
}

package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class HideSeen {

    public static void init() {
        loadClass.loadObj7();
         Class<?> messagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
        if (messagesControllerClass != null){
         XC_MethodHook hook = new AbstractMethodHook() {
            @Override
            protected void beforeMethod(MethodHookParam param) {
                // التحقق من الإعدادات
                if (FeatureManager.isHideSeenPrivate() && FeatureManager.isHideSeenGroup()) {
                    XposedBridge.log("completeReadTask method is blocked.");
                    param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                    return;
                }

                // الحصول على الكائن task
                Object task = param.args[0];
                if (task != null) {
                    // استخراج dialogId من الكائن task
                    long dialogId = (long) XposedHelpers.getObjectField(task, AutomationResolver.resolve("MessagesController$ReadTask","dialogId", AutomationResolver.ResolverType.Field));
                    if (dialogId != 0){
                        // الحصول على الكائن الحالي لـ MessagesController
                        Object messagesControllerInstance = param.thisObject;

                        try {
                            Method getUserMethod = messagesControllerClass.getDeclaredMethod(AutomationResolver.resolve("MessagesController","getUser", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("obj2"));
                            getUserMethod.setAccessible(true);

                            // تحويل dialogId إلى Long
                            Long useridObject = dialogId;

                            // استدعاء getChat باستخدام الكائن messagesControllerInstance
                            Object user = getUserMethod.invoke(messagesControllerInstance, useridObject);

                            if (user != null) {
                                if (FeatureManager.isHideSeenPrivate()){
                                    XposedBridge.log("completeReadTask method is blocked.");
                                    param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                                }
                            } else {
                                if (FeatureManager.isHideSeenGroup()){
                                    XposedBridge.log("completeReadTask method is blocked.");
                                    param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                                }
                            }
                        } catch (Exception e) {
                            XposedBridge.log("Error invoking getUser: " + e.getMessage());
                        }
                    }else {
                        XposedBridge.log("dialogId is 0.");
                    }
                } else {
                    XposedBridge.log("Task is null.");
                }
            }

        };

            XposedHelpers.findAndHookMethod(
                    messagesControllerClass,
                    "completeReadTask", // اسم الدالة
                    AutomationResolver.merge(AutomationResolver.resolveObject("obj7"), hook));
        }
    }
    }

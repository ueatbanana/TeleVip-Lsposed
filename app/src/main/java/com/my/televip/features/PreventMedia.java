package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class PreventMedia {

    public static void init() {
if (loadClass.MessageObjectClass == null) {
    loadClass.MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
}
        Class<?> ChatActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), lpparam.classLoader);

        if (loadClass.MessageObjectClass != null) {
            if (ChatActivityClass != null) {
                loadClass.loadObj10();
                loadClass.loadObj11();
                XC_MethodHook hook = new AbstractMethodHook() {
                    @Override
                    protected void beforeMethod(MethodHookParam param) {
                            param.setResult(null);
                    }
                };
                XposedHelpers.findAndHookMethod(ChatActivityClass, "sendSecretMessageRead",AutomationResolver.merge(AutomationResolver.resolveObject("obj10"), hook));
                XposedHelpers.findAndHookMethod(ChatActivityClass, "sendSecretMediaDelete",AutomationResolver.merge(AutomationResolver.resolveObject("obj11"), hook));
            }
            loadClass.loadObj12();
            XC_MethodHook hook = new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) throws Throwable {
                    param.args[2] = null;
                    param.args[3] = null;
                    // الحصول على كائن ChatActivity
                    Object forwardingMessage = param.args[0];

                    if (forwardingMessage != null) {
                        // الوصول إلى الحقل messageOwner داخل forwardingMessage
                        Class<?> forwardingMessageClass = forwardingMessage.getClass();
                        Field messageOwnerField = forwardingMessageClass.getDeclaredField(AutomationResolver.resolve("MessageObject","messageOwner", AutomationResolver.ResolverType.Field));
                        messageOwnerField.setAccessible(true);
                        Object messageOwner = messageOwnerField.get(forwardingMessage);

                        if (messageOwner != null) {
                            XposedHelpers.setObjectField(messageOwner, AutomationResolver.resolve("TLRPC$Message","ttl", AutomationResolver.ResolverType.Field), 0x7FFFFFFF);


                        }
                    }
                }
                };
            Class<?> SecretMediaViewerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.SecretMediaViewer"), lpparam.classLoader);

            XposedHelpers.findAndHookMethod(SecretMediaViewerClass,"openMedia", AutomationResolver.merge(AutomationResolver.resolveObject("obj12"), hook));
            XC_MethodHook hook2 = new AbstractMethodHook() {
                @Override
                protected void beforeMethod(MethodHookParam param) {
                        Object thisObject = param.thisObject;
                        XposedHelpers.setObjectField(thisObject, AutomationResolver.resolve("SecretMediaViewer","onClose", AutomationResolver.ResolverType.Field), null);
            }
            };

            XposedHelpers.findAndHookMethod(SecretMediaViewerClass,"closePhoto",AutomationResolver.merge(AutomationResolver.resolveObject("obj13"), hook2));
        }

    }

}

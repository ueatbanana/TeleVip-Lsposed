package com.my.televip;



import static com.my.televip.MainHook.lpparam;

import android.content.Context;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.obfuscate.resolves.Telegram;
import com.my.televip.obfuscate.resolves.TelegramWeb;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class loadClass {
    public static  Class<?> ChatActivityClass;
    public static  Class<?> MessageObjectClass;
    public static  Class<?> ProfileActivityClass;
    public static  Class<?> BaseFragmentClass;
    public static  Class<?> drawableClass;
    public static Context applicationContext;

    public static void loadObj1() {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> classStories$StoryItem = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$StoryItem"), MainHook.lpparam.classLoader);
            Class<?> classsStories$PeerStories = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$PeerStories"), MainHook.lpparam.classLoader);
            Telegram.ObjectResolver.register("obj1", new Class[]{classsStories$PeerStories, classStories$StoryItem, boolean.class});
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> classStories$StoryItem = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$StoryItem"), MainHook.lpparam.classLoader);
            Class<?> classsStories$PeerStories = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$PeerStories"), MainHook.lpparam.classLoader);
            TelegramWeb.ObjectResolver.register("obj1", new Class[]{classsStories$PeerStories, classStories$StoryItem, boolean.class});
        }
    }
    public static void loadObj7() {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> readTaskClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController$ReadTask"), lpparam.classLoader);
            Telegram.ObjectResolver.register("obj7", new Class[]{readTaskClass});
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> readTaskClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController$ReadTask"), lpparam.classLoader);
            TelegramWeb.ObjectResolver.register("obj7", new Class[]{readTaskClass});
        }

    }
    public static void loadObj8() {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> TLRPC$ChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Chat"), lpparam.classLoader);
            Telegram.ObjectResolver.register("obj8", new Class[]{TLRPC$ChatClass});
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> TLRPC$ChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Chat"), lpparam.classLoader);
            TelegramWeb.ObjectResolver.register("obj8", new Class[]{TLRPC$ChatClass});
        }

    }
    public static void loadObj9() {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> tlObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
            Class<?> requestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), lpparam.classLoader);
            Class<?> requestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), lpparam.classLoader);
            Class<?> quickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), lpparam.classLoader);
            Class<?> writeToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), lpparam.classLoader);

            Telegram.ObjectResolver.register("obj9", new Class[]{tlObjectClass, requestDelegateClass, requestDelegateTimestampClass, quickAckDelegateClass, writeToSocketDelegateClass, int.class, int.class, int.class, boolean.class, int.class});
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> tlObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
            Class<?> requestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), lpparam.classLoader);
            Class<?> requestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), lpparam.classLoader);
            Class<?> quickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), lpparam.classLoader);
            Class<?> writeToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), lpparam.classLoader);

            TelegramWeb.ObjectResolver.register("obj9", new Class[]{tlObjectClass, requestDelegateClass, requestDelegateTimestampClass, quickAckDelegateClass, writeToSocketDelegateClass, int.class, int.class, int.class, boolean.class, int.class});
        }
        }
    public static void loadObj10() {
        if (MessageObjectClass == null) {
            MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
        }
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Telegram.ObjectResolver.register("obj10", new Class[]{MessageObjectClass, boolean.class});
        } else  if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            TelegramWeb.ObjectResolver.register("obj10", new Class[]{MessageObjectClass, boolean.class});
        }

    }
    public static void loadObj11() {
        if (MessageObjectClass == null) {
            MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
        }
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Telegram.ObjectResolver.register("obj11", new Class[]{MessageObjectClass});
        } else  if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            TelegramWeb.ObjectResolver.register("obj11", new Class[]{MessageObjectClass});
        }

    }
    public static void loadObj12() {
        if (MessageObjectClass == null) {
            MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
        }
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> photoViewerproviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), lpparam.classLoader);
            Telegram.ObjectResolver.register("obj12", new Class[]{MessageObjectClass, photoViewerproviderClass, java.lang.Runnable.class, java.lang.Runnable.class});
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> photoViewerproviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), lpparam.classLoader);
            TelegramWeb.ObjectResolver.register("obj12", new Class[]{MessageObjectClass, photoViewerproviderClass, java.lang.Runnable.class, java.lang.Runnable.class});
        }
    }
    public static void loadObj14() {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> Userlass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), lpparam.classLoader);
            Telegram.ObjectResolver.register("obj14", new Class[]{Userlass});
        }else  if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> Userlass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), lpparam.classLoader);
            TelegramWeb.ObjectResolver.register("obj14", new Class[]{Userlass});
        }
        }
    public static void loadObj15() {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName)) {
            Class<?> conClass = XposedHelpers.findClassIfExists("android.content.Context", lpparam.classLoader);
            Telegram.ObjectResolver.register("obj15", new Class[]{conClass});
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName)) {
            Class<?> conClass = XposedHelpers.findClassIfExists("android.content.Context", lpparam.classLoader);
            TelegramWeb.ObjectResolver.register("obj15", new Class[]{conClass});
        }

    }

}

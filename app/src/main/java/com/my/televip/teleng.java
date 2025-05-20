package com.my.televip;


import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.content.Context;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.*;
import de.robv.android.xposed.*;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import android.content.ClipData;
import android.content.ClipboardManager;


public class teleng extends StrVip {

public static void Start(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
                  

final Class<?> profileActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ProfileActivity");
final        Class<?> messagesControllerClass = lpparam.classLoader.loadClass("org.telegram.messenger.MessagesController");
final    Class<?> alertDialogBuilderClass = XposedHelpers.findClass(
        "org.telegram.ui.ActionBar.AlertDialog.Builder",
        lpparam.classLoader
    );
    final Class<?> itemClass = Class.forName("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item", true, lpparam.classLoader);

XposedHelpers.findAndHookMethod("org.telegram.ui.ProfileActivity", lpparam.classLoader, "createActionBarMenu", boolean.class, new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object profileActivityInstance = param.thisObject;
        Class<?> profileActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ProfileActivity");
        Class<?> baseFragmentClass = lpparam.classLoader.loadClass("org.telegram.ui.ActionBar.BaseFragment");
        Class<?> longClass = Long.class;

        Method getMessagesControllerMethod = baseFragmentClass.getDeclaredMethod("getMessagesController");
        getMessagesControllerMethod.setAccessible(true);
        Object messagesController = getMessagesControllerMethod.invoke(profileActivityInstance);

        if (messagesController != null) {
            // الحصول على chatId
            Field chatIdField = profileActivityClass.getDeclaredField("chatId");
            chatIdField.setAccessible(true);
            final long chatId = chatIdField.getLong(profileActivityInstance);

            // تحويل chatId إلى Long
            Object chatIdObject = longClass.getDeclaredMethod("valueOf", long.class).invoke(null, chatId);

            // استدعاء getChat
            Method getChatMethod = messagesController.getClass().getDeclaredMethod("getChat", Long.class);
            getChatMethod.setAccessible(true);
            Object chat = getChatMethod.invoke(messagesController, chatIdObject);

            // الحصول على userId
            Field userIdField = profileActivityClass.getDeclaredField("userId");
            userIdField.setAccessible(true);
            final long userId = userIdField.getLong(profileActivityInstance);

            // تحويل userId إلى Long
            Object userIdObject = longClass.getDeclaredMethod("valueOf", long.class).invoke(null, userId);

            // استدعاء getUser
            Method getUserMethod = messagesController.getClass().getDeclaredMethod("getUser", Long.class);
            getUserMethod.setAccessible(true);
            Object user = getUserMethod.invoke(messagesController, userIdObject);

            // الحصول على otherItem
            Field otherItemField = profileActivityClass.getDeclaredField("otherItem");
            otherItemField.setAccessible(true);
            Object otherItem = otherItemField.get(profileActivityInstance);

            // استدعاء getUserConfig للحصول على clientUserId
            Method getUserConfigMethod = baseFragmentClass.getDeclaredMethod("getUserConfig");
            getUserConfigMethod.setAccessible(true);
            Object userConfig = getUserConfigMethod.invoke(profileActivityInstance);

            Method getClientUserIdMethod = userConfig.getClass().getDeclaredMethod("getClientUserId");
            getClientUserIdMethod.setAccessible(true);
            long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);

            // استدعاء addSubItem على otherItem
            if (otherItem != null) {
                Method addSubItemMethod = otherItem.getClass().getDeclaredMethod(
                        "addSubItem",
                        int.class,
                        int.class,
                        CharSequence.class
                );
                addSubItemMethod.setAccessible(true);
                int drawableResource =0x7f0806b8;
                Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
                        XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                        "applicationContext"
                );

                if (chat != null) {
                    if (applicationContext != null) {
                        SharedPreferences te = applicationContext.getSharedPreferences("televip", Activity.MODE_PRIVATE);
                        te.edit().putString("id", String.valueOf(chatId)).commit();
                    }
                   addSubItemMethod.invoke(otherItem, 45, drawableResource, String.valueOf(chatId));
                } else if (user != null) {
                    if (applicationContext != null) {
                        SharedPreferences te = applicationContext.getSharedPreferences("televip", Activity.MODE_PRIVATE);
                        te.edit().putString("id", String.valueOf(userId)).commit();
                          addSubItemMethod.invoke(otherItem, 45, drawableResource, String.valueOf((long)(userId)));


                    }
                }
            }
        }
        XposedBridge.log("createActionBarMenu method is true.");
    }
});
        final    Class<?> profileActivityClass3 = XposedHelpers.findClass(
                "org.telegram.ui.ProfileActivity$6", 
                lpparam.classLoader
            );
            
            XposedHelpers.findAndHookMethod(
                profileActivityClass3,
                "onItemClick", // اسم الدالة        
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        // تنفيذ الكود قبل استدعاء الدالة
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    int id =(int)param.args[0];
                                            Object profileActivityInstance = param.thisObject;
                  final      Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                                    "applicationContext"
                                );
                    if (id==45){                        
                                    if (applicationContext != null) {                                    
final SharedPreferences te = applicationContext.getSharedPreferences("televip", Activity.MODE_PRIVATE);	
               ((ClipboardManager) applicationContext.getSystemService(applicationContext.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", te.getString("id", "")));
                                        Toast.makeText(applicationContext,te.getString("id", ""),Toast.LENGTH_LONG).show();
                    }  
                          }
}
            });
TeleVip(lpparam);
TeleOne(lpparam);
    // استدعاء الطريقة من الكلاس Theme مباشرة
    XposedHelpers.findAndHookMethod(
    "org.telegram.ui.Adapters.DrawerLayoutAdapter", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "resetItems",                                   // اسم الدالة
    new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            // الكود الذي تريد تنفيذه قبل الدالة                    
        }
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            Object drawerLayoutAdapterInstance = param.thisObject;

            // العثور على المتغير الخاص
            Class<?> drawerLayoutAdapterClass = drawerLayoutAdapterInstance.getClass();
            Field itemsField = drawerLayoutAdapterClass.getDeclaredField("items");
            itemsField.setAccessible(true);
            ArrayList<?> items = (ArrayList<?>) itemsField.get(drawerLayoutAdapterInstance);

            // استدعاء الكلاس Item باستخدام Class.forName
                     Constructor<?> itemConstructor = itemClass.getDeclaredConstructor(int.class, CharSequence.class, int.class);
            itemConstructor.setAccessible(true);
 // استدعاء الطريقة مباشرة
 int eventType = (int) XposedHelpers.callStaticMethod(
    XposedHelpers.findClass("org.telegram.ui.ActionBar.Theme", lpparam.classLoader),
    "getEventType"
);
int drawableResource =3;
if (eventType == 0) {
  drawableResource = 0x7f0807a4;
        } else if (eventType == 1) {
          drawableResource = 0x7f0807a2;
            
        } else if (eventType == 2) {
            drawableResource = 0x7f0807a3;
        } else {
            drawableResource = 0x7f0807a5;
        }
                          final      Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                                    "applicationContext"
                                );
Strck(applicationContext);  

            Object newItem = itemConstructor.newInstance(13048, GhostMode, drawableResource);

            // إضافة الكائن الجديد إلى القائمة
            if (items instanceof ArrayList<?>) {
    ArrayList<Object> typedItems = (ArrayList<Object>) items;
    typedItems.add(newItem); // إضافة العنصر في الموقع الأول
}
        }
    }
);

Class<?> launchActivityClass = XposedHelpers.findClass(
                "org.telegram.ui.LaunchActivity", 
                lpparam.classLoader
            );

            XposedHelpers.findAndHookMethod(
                launchActivityClass,
                "lambda$onCreate$8", // اسم الدالة
                android.view.View.class, // نوع الوسيط الأول
                int.class,
                float.class, // نوع الوسيط الثاني
                float.class, // نوع الوسيط الثالث
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        // تنفيذ الكود قبل استدعاء الدالة
                    }

                    @Override
                    protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
ondilag(param,lpparam);
                    }
                }
            );
            
        
      

}


}
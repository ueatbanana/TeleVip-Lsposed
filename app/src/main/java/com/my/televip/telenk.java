package com.my.televip;



import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.content.Context;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.*;
import de.robv.android.xposed.*;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import android.content.ClipData;
import android.content.ClipboardManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class telenk extends StrVip {

public static void Start(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

final Class<?> drawableClass = Class.forName("kV2", true, lpparam.classLoader);


    final Class<?> itemClass = Class.forName("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item", true, lpparam.classLoader);

XposedHelpers.findAndHookMethod("org.telegram.ui.ProfileActivity", lpparam.classLoader, "dd", boolean.class, new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object profileActivityInstance = param.thisObject;
        Class<?> profileActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ProfileActivity");
        Class<?> baseFragmentClass = lpparam.classLoader.loadClass("org.telegram.ui.ActionBar.g");
        Class<?> longClass = Long.class;

        Method getMessagesControllerMethod = baseFragmentClass.getDeclaredMethod("R0");
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
            Method getChatMethod = messagesController.getClass().getDeclaredMethod("J9", Long.class);
            getChatMethod.setAccessible(true);
            Object chat = getChatMethod.invoke(messagesController, chatIdObject);

            // الحصول على userId
            Field userIdField = profileActivityClass.getDeclaredField("userId");
            userIdField.setAccessible(true);
            final long userId = userIdField.getLong(profileActivityInstance);

            // تحويل userId إلى Long
            Object userIdObject = longClass.getDeclaredMethod("valueOf", long.class).invoke(null, userId);

            // استدعاء getUser
            Method getUserMethod = messagesController.getClass().getDeclaredMethod("hb", Long.class);
            getUserMethod.setAccessible(true);
            Object user = getUserMethod.invoke(messagesController, userIdObject);

            // الحصول على otherItem
            Field otherItemField = profileActivityClass.getDeclaredField("otherItem");
            otherItemField.setAccessible(true);
            Object otherItem = otherItemField.get(profileActivityInstance);

            // استدعاء getUserConfig للحصول على clientUserId
            Method getUserConfigMethod = baseFragmentClass.getDeclaredMethod("g1");
            getUserConfigMethod.setAccessible(true);
            Object userConfig = getUserConfigMethod.invoke(profileActivityInstance);

            Method getClientUserIdMethod = userConfig.getClass().getDeclaredMethod("n");
            getClientUserIdMethod.setAccessible(true);
            long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);

            // استدعاء addSubItem على otherItem
            if (otherItem != null) {
            final Class<?> DrawableClass = lpparam.classLoader.loadClass("android.graphics.drawable.Drawable");

                Method addSubItemMethod = otherItem.getClass().getDeclaredMethod(
                        "g1",
                        int.class,
                        int.class,
                        DrawableClass,
                        CharSequence.class,
                        boolean.class,
                        boolean.class
                );
                addSubItemMethod.setAccessible(true);
                int drawableResource = XposedHelpers.getStaticIntField(drawableClass, "nb");
                Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
                        XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                        "b"
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
                "org.telegram.ui.ProfileActivity$Z", 
                lpparam.classLoader
            );
            
            XposedHelpers.findAndHookMethod(
                profileActivityClass3,
                "b", // اسم الدالة        
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
XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                                    "b"
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
TeleVip2(lpparam);
    // استدعاء الطريقة من الكلاس Theme مباشرة
    XposedHelpers.findAndHookMethod(
    "org.telegram.ui.Adapters.DrawerLayoutAdapter", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "V",                                   // اسم الدالة
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
            Field itemsField = drawerLayoutAdapterClass.getDeclaredField("d");
            itemsField.setAccessible(true);
            ArrayList<?> items = (ArrayList<?>) itemsField.get(drawerLayoutAdapterInstance);

            // استدعاء الكلاس Item باستخدام Class.forName
                     Constructor<?> itemConstructor = itemClass.getDeclaredConstructor(int.class, CharSequence.class, int.class);
            itemConstructor.setAccessible(true);
 // استدعاء الطريقة مباشرة
 int eventType = (int) XposedHelpers.callStaticMethod(
    XposedHelpers.findClass("org.telegram.ui.ActionBar.q", lpparam.classLoader),
    "W1"
);
int drawableResource =3;
if (eventType == 0) {
  drawableResource = XposedHelpers.getStaticIntField(drawableClass, "uf");
        } else if (eventType == 1) {
            drawableResource = XposedHelpers.getStaticIntField(drawableClass, "sf");        
            
        } else if (eventType == 2) {
            drawableResource = XposedHelpers.getStaticIntField(drawableClass, "tf");
            
        } else {
            drawableResource = XposedHelpers.getStaticIntField(drawableClass, "vf");
        }
                          final      Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                                    "b"
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
                "M5", // اسم الدالة
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
ondilag2(param,lpparam);
                    }
                }
            );
            
        
      

}

public static void ondilag2(final de.robv.android.xposed.XC_MethodHook.MethodHookParam param,final XC_LoadPackage.LoadPackageParam lpparam){
final    Class<?> alertDialogBuilderClass = XposedHelpers.findClass(
        "org.telegram.ui.ActionBar.AlertDialog.Builder",
        lpparam.classLoader
    );
                        // تنفيذ الكود بعد استدعاء الدالة
                        //ckDarck2(lpparam);
                        Object launchActivity = param.thisObject;

                        Object drawerLayoutAdapter = XposedHelpers.getObjectField(param.thisObject, "drawerLayoutAdapter");
                        if (drawerLayoutAdapter != null) {
                       
                            // استدعاء getId وطباعته
                            int result = (int) XposedHelpers.callMethod(drawerLayoutAdapter, "S", param.args[1]);
                            if (result ==13048){
                                   Object contextapp = param.thisObject;
                        final Context applicationContext = (Context)contextapp;
                        final      Context applicationContext2= (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                                    "b"
                                );

                    Object alertDialog = XposedHelpers.newInstance(alertDialogBuilderClass, applicationContext);
final SharedPreferences te = applicationContext.getSharedPreferences("televip", Activity.MODE_PRIVATE);	
                    // عرض رسالة أو تخصيص النافذة                     
Strck(applicationContext2);  
                    ArrayList<String> list = new ArrayList<>();
                   list.add(noRead);
                   list.add(noRead2);
                   list.add(noStoryRead);
                   list.add(HideOnline);
                   list.add(HidePhone);
                   list.add(hidestore);
                   list.add(NoTyping);
                   list.add(shmsdel);
                   list.add(PreventMedia);
                   list.add(usefolow);
                   list.add(allowShare);
                     list.add(pre);
                    final String[] items = list.toArray(new String[0]);
                    XposedHelpers.callMethod(alertDialog, "D", strTitle);
                    // إنشاء تخطيط جديد
LinearLayout layout = new LinearLayout(applicationContext);
layout.setOrientation(LinearLayout.VERTICAL);

// إضافة CheckBox لكل عنصر في القائمة مع إعدادات النص
final List<CheckBox> checkBoxes = new ArrayList<>();
for (String item : items) {
    CheckBox checkBox = new CheckBox(applicationContext);
    if (item.equals(pre) && te.contains("prem")) {
checkBox.setChecked(true);
}
else if (item.equals(noRead) && te.contains("noRead")) {
checkBox.setChecked(true);
}else if (item.equals(noRead2) && te.contains("noRead2")) {
checkBox.setChecked(true);
}    if (item.equals(NoTyping) && te.contains("NoTyping")) {
checkBox.setChecked(true);
}else if (item.equals(noStoryRead) && te.contains("noStoryRead")) {
checkBox.setChecked(true);
}else if (item.equals(usefolow) && te.contains("usefolow")) {
checkBox.setChecked(true);
}else if (item.equals(allowShare) && te.contains("allowShare")) {
checkBox.setChecked(true);
}else if (item.equals(HideOnline) && te.contains("HideOnline")) {
checkBox.setChecked(true);
}else if (item.equals(PreventMedia) && te.contains("PreventMedia")) {
checkBox.setChecked(true);
}else if (item.equals(HidePhone) && te.contains("HidePhone")) {
checkBox.setChecked(true);
}else if (item.equals(shmsdel) && te.contains("shmsdel")) {
checkBox.setChecked(true);
}else if (item.equals(hidestore) && te.contains("hidestore")) {
checkBox.setChecked(true);
}
	
    checkBox.setText(item);
    //if (!isCurrentThemeDay){
  //  checkBox.setTextColor(Color.BLACK); // تغيير لون النص إلى الأبيض
   // }else {
    //checkBox.setTextColor(Color.WHITE);
    //}
    checkBox.setPadding(10, 10, 10, 10); // إضافة هامش صغير حول النص
    checkBox.setTypeface(Typeface.DEFAULT_BOLD); // جعل النص مائلًا قليلاً
    checkBoxes.add(checkBox);
    layout.addView(checkBox);
}

// إعداد AlertDialog واستخدام setView
XposedHelpers.callMethod(alertDialog, "K", layout);

// إضافة زر للتحقق من الحالات

// يمكن إضافة منطق إضافي هنا للتحقق من التحديدات عند النقر على CheckBox

                    // إعداد الزر الموجب
                    Class<?> listenerClass = XposedHelpers.findClass(
    "org.telegram.ui.ActionBar.AlertDialog$k", 
    lpparam.classLoader
);

// ننشئ كائن من هذا الكلاس باستخدام Proxy (لأنه interface)
Object onDoneListener = Proxy.newProxyInstance(
    lpparam.classLoader,
    new Class[]{ listenerClass },
    (proxy, method, args) -> {
        if (method.getName().equals("a")) {
            Object dialog = args[0]; // هذا AlertDialog
            int which = (int) args[1];
                        try {
                // الكود الذي يتم تنفيذه عند الضغط على الزر
                for (int i = 0; i < checkBoxes.size(); i++) {
                    CheckBox checkBox = checkBoxes.get(i);
                    if (checkBox.isChecked()) {
                        if (checkBox.getText().toString().equals(pre)) {
	te.edit().putString("prem", "true").commit();
}else if (checkBox.getText().toString().equals(noRead)) {
	te.edit().putString("noRead", "true").commit();
}else if (checkBox.getText().toString().equals(noRead2)) {
	te.edit().putString("noRead2", "true").commit();
}else if (checkBox.getText().toString().equals(NoTyping)) {
	te.edit().putString("NoTyping", "true").commit();
}else if (checkBox.getText().toString().equals(noStoryRead)) {
	te.edit().putString("noStoryRead", "true").commit();
}else if (checkBox.getText().toString().equals(usefolow)) {
	te.edit().putString("usefolow", "true").commit();
}else if (checkBox.getText().toString().equals(allowShare)) {
	te.edit().putString("allowShare", "true").commit();
}else if (checkBox.getText().toString().equals(HideOnline)) {
	te.edit().putString("HideOnline", "true").commit();
}else if (checkBox.getText().toString().equals(PreventMedia)) {
	te.edit().putString("PreventMedia", "true").commit();
}else if (checkBox.getText().toString().equals(HidePhone)) {
	te.edit().putString("HidePhone", "true").commit();
}else if (checkBox.getText().toString().equals(shmsdel)) {
	te.edit().putString("shmsdel", "true").commit();
}else if (checkBox.getText().toString().equals(hidestore)) {
	te.edit().putString("hidestore", "true").commit();
}
                    } else {
                        if (checkBox.getText().toString().equals(pre)) {
	te.edit().remove("prem").commit();
} else if (checkBox.getText().toString().equals(noRead)) {
	te.edit().remove("noRead").commit();
}else if (checkBox.getText().toString().equals(noRead2)) {
	te.edit().remove("noRead2").commit();
}else if (checkBox.getText().toString().equals(NoTyping)) {
	te.edit().remove("NoTyping").commit();
}else if (checkBox.getText().toString().equals(noStoryRead)) {
	te.edit().remove("noStoryRead").commit();
}else if (checkBox.getText().toString().equals(usefolow)) {
	te.edit().remove("usefolow").commit();
}else if (checkBox.getText().toString().equals(allowShare)) {
	te.edit().remove("allowShare").commit();
}else if (checkBox.getText().toString().equals(HideOnline)) {
	te.edit().remove("HideOnline").commit();
}else if (checkBox.getText().toString().equals(PreventMedia)) {
	te.edit().remove("PreventMedia").commit();
}else if (checkBox.getText().toString().equals(HidePhone)) {
	te.edit().remove("HidePhone").commit();
}else if (checkBox.getText().toString().equals(shmsdel)) {
	te.edit().remove("shmsdel").commit();
}else if (checkBox.getText().toString().equals(hidestore)) {
	te.edit().remove("hidestore").commit();
}
                    }
                }
                
                // غلق الـ AlertDialog بعد التحقق
           //     XposedHelpers.callMethod(dialog, "dismiss");
            } catch (Throwable t) {
                t.printStackTrace();
            }
           TeleVip2(lpparam);
           }
        return null;
    
});

XposedHelpers.callMethod(alertDialog, "B", 
    btnsave,onDoneListener
);
Object onDoneListener2 = Proxy.newProxyInstance(
    lpparam.classLoader,
    new Class[]{ listenerClass },
    (proxy, method, args) -> {
        if (method.getName().equals("a")) {
            Object dialog = args[0]; // هذا AlertDialog
            int which = (int) args[1];
                              Object drawerLayoutContainer = XposedHelpers.getObjectField(param.thisObject, "drawerLayoutContainer");
if (drawerLayoutContainer != null) {
XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("Vx", lpparam.classLoader),
                "G",applicationContext,"https://t.me/t_l0_e"
            );
           XposedHelpers.callMethod(drawerLayoutContainer, "g");
             //   XposedHelpers.callMethod(dialog, "dismiss");
        }
        }
        return null;
    
});
XposedHelpers.callMethod(alertDialog, "v", 
   chena, onDoneListener2
);
                
                    XposedHelpers.callMethod(alertDialog, "N");
                            }
                        
                        }
}




        public static void TeleVip2(final XC_LoadPackage.LoadPackageParam lpparam) {
        
         XSharedPreferences sharedPreferences = new XSharedPreferences(lpparam.packageName, "televip");    

       if (sharedPreferences.contains("prem")) {
	Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.X", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass, "B", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                }
            });

           
           
}

try {
 Class<?> readTaskClass = Class.forName("org.telegram.messenger.H$t", true, lpparam.classLoader);
Class<?> messagesControllerClass = XposedHelpers.findClass("org.telegram.messenger.H", lpparam.classLoader);

XposedHelpers.findAndHookMethod(
    messagesControllerClass,
    "H8", // اسم الدالة
    readTaskClass,       // نوع المعامل الأول (task)
    new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            // التحقق من الإعدادات
            if (sharedPreferences.contains("noRead") && sharedPreferences.contains("noRead2")) {
                XposedBridge.log("completeReadTask method is blocked.");
                param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
                return;
            }

            // الحصول على الكائن task
            Object task = param.args[0];
            if (task != null) {
                // استخراج dialogId من الكائن task
                long dialogId = (long) XposedHelpers.getObjectField(task, "a");
if (dialogId != 0){
                // الحصول على الكائن الحالي لـ MessagesController
                Object messagesControllerInstance = param.thisObject;

                // استدعاء الدالة getChat
                try {
                    Method getChatMethod = messagesControllerClass.getDeclaredMethod("hb", Long.class);
                    getChatMethod.setAccessible(true);

                    // تحويل dialogId إلى Long
                    Long chatIdObject = Long.valueOf(dialogId);

                    // استدعاء getChat باستخدام الكائن messagesControllerInstance
                    Object user = getChatMethod.invoke(messagesControllerInstance, chatIdObject);

                    if (user != null) {
                    if (sharedPreferences.contains("noRead")){
                         XposedBridge.log("completeReadTask method is blocked.");
                param.setResult(null); // إيقاف تنفيذ الدالة الأصلية
              }
                          } else {
                        if (sharedPreferences.contains("noRead2")){
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

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            // يمكن تنفيذ أي عمليات إضافية بعد الدالة الأصلية هنا
        }
    }
);         
                } catch (ClassNotFoundException e) {
    e.printStackTrace();

}
if (sharedPreferences.contains("NoTyping")) {

XposedHelpers.findAndHookMethod(
    "org.telegram.ui.p$h2",
    lpparam.classLoader,    
    "G",                                  
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("needSendTyping method is blocked.");
                            param.setResult(null); 
              
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    }
                });
                
}
  
if (sharedPreferences.contains("noStoryRead")) {
try {
Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.ui.Stories.g", lpparam.classLoader);

 Class<?> clastesk = Class.forName("JX3", true, lpparam.classLoader);
Class<?> clastesk2 = Class.forName("MX3", true, lpparam.classLoader);

XposedHelpers.findAndHookMethod(
    userConfigClass,
    "O1",  
    clastesk,
    clastesk2,
    boolean.class,
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                            param.setResult(false); 
              
                    }

                   
                });
                } catch (ClassNotFoundException e) {
    e.printStackTrace();

}
}

if (sharedPreferences.contains("usefolow")) {
try {
Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.H", lpparam.classLoader);

 Class<?> clastesk = Class.forName("org.telegram.tgnet.TLRPC$p", true, lpparam.classLoader);

XposedHelpers.findAndHookMethod(
    userConfigClass,
    "Eb",  
    clastesk,
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                            param.setResult(false); 
              
                    }

                   
                });
                } catch (ClassNotFoundException e) {
    e.printStackTrace();

}
} 
                          
if (sharedPreferences.contains("allowShare")) {
 	Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.ui.Stories.c$P", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass, "c", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                }
            });  
                                                    
 }   

Class<?> FileLoadOperationClass = XposedHelpers.findClass("org.telegram.messenger.p", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(FileLoadOperationClass, "b1", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {               
            int downloadChunkSizeBig = 0;
            int maxDownloadRequests = 4;
            int maxDownloadRequestsBig = 8;
            int maxCdnParts = 0;
            long totalBytesCount;
            long currentTimeMillis;
            long hookTimeMillis;

           
                downloadChunkSizeBig = 0x100000; // 1MB

            maxCdnParts = (int) (0x7D000000L / downloadChunkSizeBig);

            XposedHelpers.setIntField(param.thisObject, "l", downloadChunkSizeBig);
            XposedHelpers.setObjectField(param.thisObject, "n", maxDownloadRequests);
            XposedHelpers.setObjectField(param.thisObject, "o", maxDownloadRequestsBig);
            XposedHelpers.setObjectField(param.thisObject, "q", maxCdnParts);
      
                
            
                }
            });

            // العثور على الكلاسات المطلوبة للمعاملات
if (sharedPreferences.contains("HideOnline")) {
            Class<?> connectionsManagerClass = XposedHelpers.findClass(
                "org.telegram.tgnet.ConnectionsManager",
                lpparam.classLoader
            );
            Class<?> tlObjectClass = XposedHelpers.findClass("LM3", lpparam.classLoader);
            Class<?> requestDelegateClass = XposedHelpers.findClass("org.telegram.tgnet.RequestDelegate", lpparam.classLoader);
            Class<?> requestDelegateTimestampClass = XposedHelpers.findClass("org.telegram.tgnet.a", lpparam.classLoader);
            Class<?> quickAckDelegateClass = XposedHelpers.findClass("FT2", lpparam.classLoader);
            Class<?> writeToSocketDelegateClass = XposedHelpers.findClass("Gw4", lpparam.classLoader);

 XposedBridge.hookMethod(
    XposedHelpers.findMethodExact(
        connectionsManagerClass,
        "sendRequestInternal",
        tlObjectClass,
        requestDelegateClass,
        requestDelegateTimestampClass,
        quickAckDelegateClass,
        writeToSocketDelegateClass,
        int.class,    // Parameter 6
        int.class,    // Parameter 7
        int.class,    // Parameter 8
        boolean.class, // Parameter 9
        int.class     // Parameter 10
    ),
    new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            try {
                Class<?> tlAccountUpdateStatusClass = XposedHelpers.findClass(
                    "bO3", 
                    param.thisObject.getClass().getClassLoader()
                );

                // التحقق من النوع وتعديله
                Object object = param.args[0]; // أول معامل في الطريقة
                if (tlAccountUpdateStatusClass.isInstance(object)) {
                    Object tlAccountUpdateStatus = object;
                    
                    // تعديل الخاصية offline إلى true
                    XposedHelpers.setBooleanField(tlAccountUpdateStatus, "a", true);
              //      XposedBridge.log("Modified TL_account_updateStatus: offline set to true.");
                }
            } catch (Exception e) {
                XposedBridge.log("Error while handling TL_account_updateStatus: " + e.getMessage());
            }
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            
        }
    }
);
   }    
   
    
if (sharedPreferences.contains("PreventMedia")) {
                Class<?> messageobjectClass = XposedHelpers.findClass("org.telegram.messenger.F", lpparam.classLoader);

                XposedHelpers.findAndHookMethod(
    "org.telegram.ui.p", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "OD", 
    messageobjectClass,
     boolean.class,  
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {                     
                            param.setResult(null); 
              
              
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    }
                });
                                XposedHelpers.findAndHookMethod(
    "org.telegram.ui.p", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "ND", 
    messageobjectClass,
 
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {                     
                            param.setResult(null); 
              
              
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    }
                });     
                }
                
                
                
                try {
                final Class<?> drawableClass = Class.forName("kV2", true, lpparam.classLoader);
                
    Class<?> conClass = Class.forName("android.content.Context", true, lpparam.classLoader);
final Class<?> ChatActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.p");
               if (!isshow){
XposedHelpers.findAndHookMethod(ChatActivityClass, "t0", conClass, new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object ChatActivityInstance = param.thisObject;
        Field headerItemField = ChatActivityClass.getDeclaredField("headerItem");
            headerItemField.setAccessible(true);
            Object headerItem = headerItemField.get(ChatActivityInstance);
            if (headerItem != null) {
            final Class<?> DrawableClass = lpparam.classLoader.loadClass("android.graphics.drawable.Drawable");

                Method addSubItemMethod = headerItem.getClass().getDeclaredMethod(
                        "g1",
                        int.class,
                        int.class,
                        DrawableClass,
                        CharSequence.class,
                        boolean.class,
                        boolean.class
                );
                addSubItemMethod.setAccessible(true);
                 int drawableResource = XposedHelpers.getStaticIntField(drawableClass, "Kb");
                 
                 Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                                    "b"
                                );
                             
    addSubItemMethod.invoke(headerItem, 70, drawableResource, onemsg);
                     drawableResource = XposedHelpers.getStaticIntField(drawableClass, "ii");      
                addSubItemMethod.invoke(headerItem, 71, drawableResource, tothmsg);
                isshow=true;
      }
    }
});
}
Class<?>  ChatActivityClass3 = XposedHelpers.findClass(
                "org.telegram.ui.p$H", 
                lpparam.classLoader
            ); 
            XposedHelpers.findAndHookMethod(
                ChatActivityClass3,
                "b", // اسم الدالة        
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    int id =(int)param.args[0];
                    Object chatActivityInstance = param.thisObject;
                final  Object chatActivity = XposedHelpers.getObjectField(chatActivityInstance, "this$0");
                    if (id == 70){
// استدعاء الدالة
XposedHelpers.callMethod(chatActivity, "m",1,0,true,0,true,0);
XposedBridge.log("scrollToMessageId is call.");

                }else if (id == 71){
              final Context applicationContext = (Context) XposedHelpers.callMethod(chatActivity, "D0");
       if (applicationContext != null) {
        Object getResourceProvider = XposedHelpers.callMethod(chatActivity, "z");

            Object alertDialog = XposedHelpers.newInstance(
                XposedHelpers.findClass("org.telegram.ui.ActionBar.AlertDialog$Builder", lpparam.classLoader),
                applicationContext,
                getResourceProvider
            );
                            Context applicationContext2= (Context) XposedHelpers.getStaticObjectField(
                        XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                        "b"
                );
           
            Strck(applicationContext2);  

                                      XposedHelpers.callMethod(alertDialog, "D", inpMsg);                                    
                                      // إنشاء EditText مع تصميم جميل
final EditText editText = new EditText(applicationContext);
editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//ckDarck2(lpparam);
  //  if (!isCurrentThemeDay){
  //  editText.setTextColor(0xFF000000);
   // editText.setHintTextColor(0xFF424242);
   // }else {
    editText.setTextColor(0xFFFFFFFF);
editText.setHintTextColor(0xFFBDBDBD);
 //   }
editText.setTextSize(18); // تكبير النص
editText.setPadding(20, 20, 20, 20); // إضافة هوامش داخلية

// تحديد حجم EditText ليكون أكبر
LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.MATCH_PARENT,
    LinearLayout.LayoutParams.WRAP_CONTENT
);
params.setMargins(20, 20, 20, 20); // إضافة هوامش خارجية
editText.setLayoutParams(params);

// إنشاء تخطيط (Layout) لتضمين EditText
LinearLayout layout = new LinearLayout(applicationContext);
layout.setOrientation(LinearLayout.VERTICAL);
layout.setPadding(30, 30, 30, 30); // هوامش إضافية داخل التخطيط
layout.addView(editText);

XposedHelpers.callMethod(alertDialog, "K", layout);

// إعداد زر الحفظ


      XposedHelpers.callMethod(alertDialog, "B", serc, 
    new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            try {

                String inputText  = editText.getText().toString().trim();

                // التحقق من المدخلات
                if (!inputText.isEmpty()) {
                int msid = Integer.parseInt(inputText);
XposedHelpers.callMethod(chatActivity, "m",msid,0,true,0,true,0);
XposedBridge.log("scrollToMessageId is call.");

                }

            } catch (Throwable t) {
                t.printStackTrace();
            }         
        }
    }
);

XposedHelpers.callMethod(alertDialog, "v", 
   syno, 
    new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            try {               
          //      XposedHelpers.callMethod(dialog, "dismiss");
            } catch (Throwable t) {
                t.printStackTrace();
            }         
        }
    }
);
                
                    XposedHelpers.callMethod(alertDialog, "N");
}       
                }
    }
});

if (sharedPreferences.contains("shmsdel")) {

      final  Class<?> messagesControllerClass = XposedHelpers.findClass(
            "org.telegram.messenger.H",
            lpparam.classLoader
        );

final Class<?> MessagesStorageClass = XposedHelpers.findClass(
            "org.telegram.messenger.I",
            lpparam.classLoader
        );
 
         // اعتراض الطريقة المحددة
        XposedHelpers.findAndHookMethod(
            messagesControllerClass,
            "T8",
            java.util.ArrayList.class,     // المعامل الأول
            java.util.ArrayList.class,     // المعامل الثاني
            XposedHelpers.findClass("org.telegram.tgnet.TLRPC$N", lpparam.classLoader), // الفئة المحددة
            long.class,                    // معاملة long
            boolean.class,                 // معاملة boolean
            int.class,                     // معاملة int
            boolean.class,                 // معاملة boolean أخرى
            long.class,                    // معاملة long أخرى
            XposedHelpers.findClass("LM3", lpparam.classLoader), // TLObject
            int.class,                     // int أخرى
            boolean.class,                 // boolean ثالثة
            int.class,                     // int رابعة
            new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
isdele =true;
//XposedBridge.log("deleteMessages method executed successfully!");
                }

                
            }
        );
                XposedHelpers.findAndHookMethod(
    MessagesStorageClass,
    "Ta",
    long.class,
    java.util.ArrayList.class,
    boolean.class,
    boolean.class,
    int.class,
    int.class,
    new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            java.util.ArrayList<?> messages = (java.util.ArrayList<?>) param.args[1];
            Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                                    "b"
                                );

            if (applicationContext != null) {
            if (!isdele){
                long user = (long) param.args[0];
                    if (user !=0){
                // التحقق من وجود بيانات
if (messages != null && !messages.isEmpty()) {
    // تحويل ArrayList إلى JSONArray
SharedPreferences te = applicationContext.getSharedPreferences("televip", Activity.MODE_PRIVATE);
String stuser = String.valueOf(user);
stuser = stuser.replace("-", "");

// استرجاع الرسائل المحفوظة مسبقًا
String savedMessages = te.getString("dele" + stuser, null);
List<Object> savedMessagesList = new ArrayList<>();

if (savedMessages != null) {
    try {
        // تحويل الرسائل المحفوظة مسبقًا إلى قائمة باستخدام Gson
        Gson gson = new Gson();
        savedMessagesList = gson.fromJson(savedMessages, new TypeToken<List<Object>>(){}.getType());
    } catch (Exception e) {
        XposedBridge.log("Error loading saved messages: " + e.getMessage());
    }
}

// إذا كانت الرسائل جديدة في صورة ArrayList، حولها إلى JSON String باستخدام Gson
Gson gson = new Gson();
String messagesJson = gson.toJson(messages); // إذا كانت الرسائل من نوع ArrayList

// تحويل البيانات إلى قائمة باستخدام Gson
List<Object> messagesList = gson.fromJson(messagesJson, new TypeToken<List<Object>>(){}.getType());

// دمج الرسائل الجديدة مع القديمة بدون تكرار
for (Object newMessage : messagesList) {
    boolean isDuplicate = false;

    for (Object savedMessage : savedMessagesList) {
        if (newMessage.equals(savedMessage)) {
            isDuplicate = true;
            break;
        }
    }

    // إذا لم تكن الرسالة مكررة، أضفها
    if (!isDuplicate) {
        savedMessagesList.add(newMessage);
    }
}

// تخزين الرسائل المدمجة
stuser = "dele" + stuser;
te.edit().putString(stuser, gson.toJson(savedMessagesList)).commit();
//   XposedBridge.log("Updated messages for user " + stuser + ": " + gson.toJson(savedMessagesList));
} else {
    XposedBridge.log("No messages found in ArrayList.");
}
                }
                }
            }

            if (!isdele) {
                param.setResult(null);
            }
        }
    }
);
        XposedHelpers.findAndHookMethod(
            MessagesStorageClass,
            "Dc",
            long.class,
            long.class,
            java.util.ArrayList.class,
            java.util.ArrayList.class,
            boolean.class,
            new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
if (!isdele){
param.setResult(null);
}
//XposedBridge.log("updateDialogsWithDeletedMessages method executed successfully!");
                }

            }
        );
        
        

        XposedHelpers.findAndHookMethod(
            messagesControllerClass,
            "wl",
            long.class,
            java.util.ArrayList.class,
            new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
if (!isdele){
param.setResult(null);
}

//XposedBridge.log("markDialogMessageAsDeleted method executed successfully!");
                }

            }
        );

            Class<?> PhotoViewerClass = XposedHelpers.findClass("org.telegram.messenger.I", lpparam.classLoader);
Class<?> objArrayClass = Object[].class;
XposedHelpers.findAndHookMethod(PhotoViewerClass, "L",int.class, objArrayClass ,new XC_MethodHook() {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
    if (!isdele){
            int id =(int) param.args[0];
int messagesDeleted = XposedHelpers.getStaticIntField(
    XposedHelpers.findClass("org.telegram.messenger.I", lpparam.classLoader),
    "x"
);
            if (id ==messagesDeleted){
param.setResult(null);
}
    }
    
    }
    @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    isdele =false;
                }
});
    Class<?> lonClass = Class.forName("Qz1", true, lpparam.classLoader);
            Class<?> PhotoViewerClass2 = XposedHelpers.findClass("org.telegram.messenger.J", lpparam.classLoader);

XposedHelpers.findAndHookMethod(PhotoViewerClass2, "b2", lonClass ,boolean.class ,new XC_MethodHook() {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

param.setResult(null);
    }
    
});
Class<?> meobClass = Class.forName("org.telegram.messenger.F", true, lpparam.classLoader);
            Class<?> ChatMessageCellClass = XposedHelpers.findClass("X50", lpparam.classLoader);

XposedHelpers.findAndHookMethod(
            ChatMessageCellClass,
            "h7",
            meobClass,
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
Object forwardingMessage = param.args[0];
Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                                    "b"
                                );
            if (applicationContext != null) {
            if (forwardingMessage != null) {
                // الوصول إلى الحقل messageOwner داخل forwardingMessage
                Class<?> forwardingMessageClass = forwardingMessage.getClass();
                Field messageOwnerField = forwardingMessageClass.getDeclaredField("messageOwner");
                messageOwnerField.setAccessible(true);
                Object messageOwner = messageOwnerField.get(forwardingMessage);

                if (messageOwner != null) {
                Class<?> messageOwnerClass = XposedHelpers.findClass("aA3", lpparam.classLoader);
                Field fromidField = messageOwnerClass.getDeclaredField("b");
                fromidField.setAccessible(true);
                Object from_d = fromidField.get(messageOwner);
if (from_d != null){
long user = 0;
if ((long) XposedHelpers.getObjectField(from_d, "a") != 0){
user =(long) XposedHelpers.getObjectField(from_d, "a");
}else if ((long) XposedHelpers.getObjectField(from_d, "b") != 0){
user =(long) XposedHelpers.getObjectField(from_d, "b");
}else {
user =(long) XposedHelpers.getObjectField(from_d, "c");
}
int id = (int) XposedHelpers.getObjectField(messageOwner, "a");
String stuser =String.valueOf(user);
stuser = stuser.replace("-", "");

SharedPreferences te = applicationContext.getSharedPreferences("televip", Activity.MODE_PRIVATE);
String msg = te.getString("dele" + stuser, null);
String idu ="dele" + stuser;
if (te.contains(idu)) {
if (msg != null) {
    try {
    Gson gson = new Gson();
        // تحويل النص إلى JSONArray
        List<Integer> messagesList = gson.fromJson(msg, new TypeToken<List<Integer>>(){}.getType());

// التحقق إذا كان الـ id موجودًا
for (Integer messageId : messagesList) {
    if (messageId == id) {
        // إذا كان id موجودًا، أظهر رسالة
                            
                     SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(delmsg);
XposedHelpers.setObjectField(param.thisObject, "currentTimeString", spannableStringBuilder);
           // XposedBridge.log("Message ID " + id + " already exists for user " + user);
        break;
    }
}
    } catch (Exception e) {
        XposedBridge.log("Error parsing messages: " + e.getMessage());
    }
}
 }   
  
}

                }

                }
                }
}
            }
        );
}

XposedHelpers.findAndHookMethod(
    "org.telegram.ui.ProfileActivity", // اسم الكلاس
    lpparam.classLoader, // الـ ClassLoader
    "t0",
    conClass,
    new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
                XposedHelpers.findClass("org.telegram.messenger.b", lpparam.classLoader),
                "b"
            );
            
            final Object profileActivityInstance = param.thisObject;

            // الحصول على الحقل nameTextView (كمصفوفة Objects لأنه لا يمكننا تعريف SimpleTextView مباشرة)
            Object[] nameTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, "nameTextView");

            if (nameTextViewArray != null && nameTextViewArray.length > 1) {
                // الحصول على SimpleTextView[1]
                Object simpleTextView1 = nameTextViewArray[1];

                if (simpleTextView1 != null) {
                    // إضافة حدث النقر باستخدام callMethod
                    XposedHelpers.callMethod(simpleTextView1, "setOnClickListener", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                // تحميل الكلاسات المطلوبة
        Class<?> profileActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ProfileActivity");
        Class<?> baseFragmentClass = lpparam.classLoader.loadClass("org.telegram.ui.ActionBar.g");
                                Class<?> userObjectClass = lpparam.classLoader.loadClass("org.telegram.messenger.X");

                                // استدعاء MessagesController
                                Method getMessagesControllerMethod = baseFragmentClass.getDeclaredMethod("R0");
                                getMessagesControllerMethod.setAccessible(true);
                                Object messagesController = getMessagesControllerMethod.invoke(profileActivityInstance);

                                if (messagesController != null) {
                                    // الحصول على userId
                                    Field userIdField = profileActivityClass.getDeclaredField("userId");
                                    userIdField.setAccessible(true);
                                    final long userId = userIdField.getLong(profileActivityInstance);

                                    // تحويل userId إلى Long
                                    Object userIdObject = Long.class.getDeclaredMethod("valueOf", long.class).invoke(null, userId);

                                    // استدعاء getUser
                                    Method getUserMethod = messagesController.getClass().getDeclaredMethod("hb", Long.class);
                                    getUserMethod.setAccessible(true);
                                    Object user = getUserMethod.invoke(messagesController, userIdObject);
            Field chatIdField = profileActivityClass.getDeclaredField("chatId");
            chatIdField.setAccessible(true);
            final long chatId = chatIdField.getLong(profileActivityInstance);

                                    if (user != null && chatId ==0) {
                                        // استدعاء getUserName
                                        Class<?> userClass = lpparam.classLoader.loadClass("hg4");
                                        Method getUserNameMethod = userObjectClass.getDeclaredMethod("m", userClass);
                                        getUserNameMethod.setAccessible(true);
                                        String userName = (String) getUserNameMethod.invoke(null, user);
                                        if (userName != null){
                                        String copnames=copname+userName+copname2;
                                                       ((ClipboardManager) applicationContext.getSystemService(applicationContext.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", userName));
                                        // عرض اسم المستخدم
                                            Toast.makeText(applicationContext,copnames,Toast.LENGTH_LONG).show();
                                   }
                                     }
                                }
                            } catch (Exception e) {
                                XposedBridge.log("Error: " + e.getMessage());
                            }
                        }
                    });
                }
            }
        }
    }
);



                Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.X", lpparam.classLoader);

XposedHelpers.findAndHookMethod(userConfigClass, "o", new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object userObject = param.getResult();
           if (sharedPreferences.contains("HidePhone")) {
        if (userObject != null) {          
                            XposedHelpers.setObjectField(userObject, "f", null);
                       //     XposedBridge.log("phone is null.");
                        }
                        }
    }
});        
if (sharedPreferences.contains("hidestore")) {
        XposedHelpers.findAndHookMethod("org.telegram.messenger.H", lpparam.classLoader, "Fn", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                param.setResult(false);
            }
        });
        XposedHelpers.findAndHookMethod("org.telegram.messenger.H", lpparam.classLoader, "Gn", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                param.setResult(false);
            }
        });
        XposedHelpers.findAndHookMethod("org.telegram.messenger.H", lpparam.classLoader, "Hn", "hg4", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                param.setResult(false);
            }
        });
        XposedHelpers.findAndHookMethod("org.telegram.ui.Stories.g", lpparam.classLoader, "P0", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                param.setResult(false);
            }
        });

}

                      } catch (ClassNotFoundException e) {
    e.printStackTrace();

}    
  
 
                              }

}
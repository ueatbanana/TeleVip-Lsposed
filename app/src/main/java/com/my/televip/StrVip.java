package com.my.televip;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.util.Locale;
import de.robv.android.xposed.*;
import android.content.res.Configuration;
import android.app.Activity;

import android.content.SharedPreferences;
import android.graphics.*;
import android.media.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import android.content.DialogInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.media.MediaPlayer;
import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.features.NEWAntiRecall;
import java.lang.reflect.Proxy;

public class StrVip {

public static String noRead;
public static String noRead2;
public static String NoTyping;
public static String noStoryRead;
public static String pre;
public static String usefolow;
public static String allowShare;
public static String strTitle;
public static String btnsave;
public static String GhostMode;
public static boolean isCurrentThemeDay=false;
public static String howdow;
public static String styes;
public static String syno;
public static String chena;
public static String HideOnline;
public static String PreventMedia;
public static String HidePhone;
public static String onemsg;
public static String tothmsg;
public static String inpMsg;
public static String serc;
public static String deltm;
public static String tm = "Change to";
public static String shmsdel;
public static boolean isdele;
public static String delmsg;
public static Class<?> drawableClass;
public static String copname;
public static String copname2;
public static String onlinestatic;
public static boolean isshow=false;
public static boolean istru=false;
public static boolean playing=false;
public static int regr=0;
public static MediaPlayer mediaPlayer;
public static String audioUrl;
public static String hidestore;
public static String strTelevip="televip";
public static void Strck(Context con){
isdele=false;
        if (MainHook.getAppLanguage(con).equals("ar")) {
                   onemsg="اذهب إلى أول رسالة";
                   tothmsg="إلى الرسالة";
                   inpMsg="ادخل معرف الرسالة";
                    serc="حسناً";
                         howdow="الاسم الجديد";
                         styes="تغير";
                         syno="الغاء";
                         tm="تم تغير الى";
                         deltm="تم حذف الاسم";
                         GhostMode="وضع الشبح 👻";
                         noRead="اخفاء علامة الاستلام من المحادثة الخاصة";
                         noRead2="اخفاء علامة الاستلام من المجموعات والقنوات";                
                     noStoryRead="اخفاء مشاهدة قصة";
                      NoTyping="اخفاء مؤشر الكتاب";
                     pre="فتح تيليجرام المميز";
                   usefolow="فتح جميع الخصائص المشفره والمغلقه";
                   allowShare="سماح حفظ الفيديو في معرض";
                   strTitle="مميزات وضع شبح";
                   btnsave="حفظ"; 
                   chena="قناة المطور";   
                   HideOnline="إخفاء حالة الاتصال بالإنترنت";   
                   PreventMedia ="تعطيل حذف الوسائط السرية";
                   HidePhone="اخفاء رقم هاتف";
                   shmsdel="اضهار الرسائل المحذوفة";
                   delmsg="محذوفه";
                   copname = "تم نسخ '";
                   copname2 = "' إلى الحافظة";
                   onlinestatic ="لست متصلاً بالإنترنت";
                   hidestore="اخفاء القصص";
                 }else if (MainHook.getAppLanguage(con).equals("zh")) {
onemsg = "跳转到第一条消息";
tothmsg = "跳转到消息";
inpMsg = "输入消息 ID";
serc = "完成";
howdow = "新名字";
styes = "更改";
syno = "取消";
tm = "更改为";
deltm = "名称已删除";
GhostMode = "幽灵模式 👻";
noRead = "隐藏私人聊天的已读状态";  
noRead2 = "隐藏群组和频道的已读状态";
noStoryRead = "隐藏 '故事观看' 状态";
NoTyping = "隐藏正在输入...";
pre = "启用 Telegram 本地会员";
usefolow = "解锁频道的所有受限和加密功能";
allowShare = "允许将视频保存到图库";
strTitle = "幽灵模式";
btnsave = "保存";
chena = "开发者频道";
HideOnline = "隐藏 '在线' 状态";
PreventMedia = "防止删除秘密媒体";
HidePhone = "隐藏 '电话' 号码";
shmsdel = "显示已删除的消息";
delmsg = "已删除";
copname = "已复制 '";
copname2 = "' 到剪贴板";
onlinestatic="您当前处于离线状态";
hidestore = "禁用 故事";
                 }else {
                 
onemsg = "Go to First Message";
tothmsg="To The Message";
inpMsg = "Input Message Id";
serc = "Done";
howdow = "New Name";
styes = "Change";
syno = "Cancel";
tm="Change to";
deltm = "Name deleted";
GhostMode="Ghost Mode 👻";
noRead = "Hide 'Seen' status for private chats";  
noRead2 = "Hide 'Seen' status for groups and channels";
noStoryRead = "Hide 'Story View' status";
NoTyping = "Hide Typing...";
pre = "Enable Telegram Premium";
usefolow = "Unlock all restricted and encrypted features for channels";
allowShare = "Allow saving videos to the gallery";
strTitle = "Ghost Mode";
btnsave="Save";
chena="Developer Channel";
HideOnline="Hide 'Online' status";
PreventMedia ="Prevent Deletion of Secret Media.";
HidePhone ="Hide 'Phone' number";
shmsdel = "Show 'Deleted Messages'";
delmsg = "deleted";
copname = "Copied '";
copname2 = "' to the clipboard";
onlinestatic = "You are currently offline";
hidestore ="Disable 'Stories'";

                 }
}

public static String getAppLanguage(Context context) {
        Configuration config = context.getResources().getConfiguration();
        Locale locale;
        
        // تحقق من إصدار النظام للحصول على Locale بالطريقة الصحيحة
    locale = config.getLocales().get(0); // في الإصدارات الجديدة

    // إرجاع اسم اللغة
        return locale.getLanguage(); // مثال: "ar" للعربية
    }
public static void ckDarck(final XC_LoadPackage.LoadPackageParam lpparam) {
        try {

            // الحصول على الكائن الحالي من ThemeInfo
            Object currentThemeInfo = XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("org.telegram.ui.ActionBar.Theme", lpparam.classLoader),
                "getActiveTheme"
            );

            if (currentThemeInfo != null) {
                // التحقق من قيمة isDark
                isCurrentThemeDay = (boolean) XposedHelpers.callMethod(currentThemeInfo, "isDark");
            } else {
                XposedBridge.log("ckDarck: getActiveTheme returned null.");
            }
        } catch (Exception e) {
            XposedBridge.log("ckDarck: Error while checking isDark - " + e.getMessage());
        }
    }
public static void ondilag(final de.robv.android.xposed.XC_MethodHook.MethodHookParam param,final XC_LoadPackage.LoadPackageParam lpparam){
try {
final    Class<?> alertDialogBuilderClass = XposedHelpers.findClass(
        "org.telegram.ui.ActionBar.AlertDialog.Builder",
        lpparam.classLoader
    );
                        // تنفيذ الكود بعد استدعاء الدالة
                        ckDarck(lpparam);

    Object drawerLayoutAdapter = XposedHelpers.getObjectField(param.thisObject, "drawerLayoutAdapter");
                        if (drawerLayoutAdapter != null) {
                       
                            // استدعاء getId وطباعته
                            int result = (int) XposedHelpers.callMethod(drawerLayoutAdapter, "getId", param.args[1]);
                            if (result ==13048){
                                   Object contextapp = param.thisObject;
                        final Context applicationContext = (Context)contextapp;
                        final      Context applicationContext2= (Context) XposedHelpers.getStaticObjectField(
XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                                    "applicationContext"
                                );

                    Object alertDialog = XposedHelpers.newInstance(alertDialogBuilderClass, applicationContext);
final SharedPreferences te = applicationContext.getSharedPreferences(strTelevip, Activity.MODE_PRIVATE);	
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
                    XposedHelpers.callMethod(alertDialog, "setTitle", strTitle);
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
}if (item.equals(NoTyping) && te.contains("NoTyping")) {
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
try {
checkBox.setOnLongClickListener(_view -> {
     if (!playing){
     regr = (int) (Math.random() * 3);
if (regr ==0 ){
audioUrl = "https://qurango.net/radio/abdulbasit_abdulsamad_mojawwad";
} else if (regr == 1){
audioUrl = "https://qurango.net/radio/yasser_aldosari";
}else {
audioUrl = "https://backup.qurango.net/radio/maher";
}
mediaPlayer = new MediaPlayer();
         //noinspection deprecation
         mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    AudioAttributes audioAttributes = new AudioAttributes.Builder()
    .setUsage(AudioAttributes.USAGE_MEDIA)
    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
    .build();

    mediaPlayer.setAudioAttributes(audioAttributes);
    try {
            mediaPlayer.setDataSource(audioUrl);
    } catch (IllegalArgumentException | IllegalStateException | IOException e) {
        throw new RuntimeException(e);
    }
         mediaPlayer.prepareAsync();

    mediaPlayer.setOnPreparedListener(mp -> {
            mediaPlayer.start();
            playing=true;
    });
    }else {
    if (mediaPlayer.isPlaying()) {
mediaPlayer.stop();
mediaPlayer.release();

mediaPlayer = new MediaPlayer();
        //noinspection deprecation
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    AudioAttributes audioAttributes = new AudioAttributes.Builder()
    .setUsage(AudioAttributes.USAGE_MEDIA)
    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
    .build();

    mediaPlayer.setAudioAttributes(audioAttributes);
    try {
            mediaPlayer.setDataSource(audioUrl);
    } catch (IllegalArgumentException | IllegalStateException | IOException e) {
        throw new RuntimeException(e);
    }

        playing=false;

}
    }
    return true;
});
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
}else if (item.equals(HidePhone) && te.contains("HidePhone")) {
checkBox.setChecked(true);
}else if (item.equals(shmsdel) && te.contains("shmsdel")) {
checkBox.setChecked(true);
}else if (item.equals(hidestore) && te.contains("hidestore")) {
checkBox.setChecked(true);
}
	
    checkBox.setText(item);
    if (!isCurrentThemeDay){
    checkBox.setTextColor(Color.BLACK); // تغيير لون النص إلى الأبيض
    }else {
    checkBox.setTextColor(Color.WHITE); 
    }
    checkBox.setPadding(10, 10, 10, 10); // إضافة هامش صغير حول النص
    checkBox.setTypeface(Typeface.DEFAULT_BOLD); // جعل النص مائلًا قليلاً
    checkBoxes.add(checkBox);
    layout.addView(checkBox);
}

// إعداد AlertDialog واستخدام setView
XposedHelpers.callMethod(alertDialog, "setView", layout);

// إضافة زر للتحقق من الحالات

// يمكن إضافة منطق إضافي هنا للتحقق من التحديدات عند النقر على CheckBox



// نحصل على الكلاس الداخلي OnButtonClickListener
Object onDoneListener;
Object onCnelListener;
 if (lpparam.packageName.equals("com.tgconnect.android") || lpparam.packageName.equals("org.telegram.messenger.beta")){  
  onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> {
                  try {
          // الكود الذي يتم تنفيذه عند الضغط على الزر
          for (int i = 0; i < checkBoxes.size(); i++) {
              CheckBox checkBox = checkBoxes.get(i);
              if (checkBox.isChecked()) {
                  if (checkBox.getText().toString().equals(pre)) {
te.edit().putString("prem", "true").apply();
}else if (checkBox.getText().toString().equals(noRead)) {
te.edit().putString("noRead", "true").apply();
}else if (checkBox.getText().toString().equals(noRead2)) {
te.edit().putString("noRead2", "true").apply();
}else if (checkBox.getText().toString().equals(NoTyping)) {
te.edit().putString("NoTyping", "true").apply();
}else if (checkBox.getText().toString().equals(noStoryRead)) {
te.edit().putString("noStoryRead", "true").apply();
}else if (checkBox.getText().toString().equals(usefolow)) {
te.edit().putString("usefolow", "true").apply();
}else if (checkBox.getText().toString().equals(allowShare)) {
te.edit().putString("allowShare", "true").apply();
}else if (checkBox.getText().toString().equals(HideOnline)) {
te.edit().putString("HideOnline", "true").apply();
}else if (checkBox.getText().toString().equals(PreventMedia)) {
te.edit().putString("PreventMedia", "true").apply();
}else if (checkBox.getText().toString().equals(HidePhone)) {
te.edit().putString("HidePhone", "true").apply();
}else if (checkBox.getText().toString().equals(shmsdel)) {
te.edit().putString("shmsdel", "true").apply();
}else if (checkBox.getText().toString().equals(hidestore)) {
te.edit().putString("hidestore", "true").apply();
}
              } else {
                  if (checkBox.getText().toString().equals(pre)) {
te.edit().remove("prem").apply();
} else if (checkBox.getText().toString().equals(noRead)) {
te.edit().remove("noRead").apply();
}else if (checkBox.getText().toString().equals(noRead2)) {
te.edit().remove("noRead2").apply();
}else if (checkBox.getText().toString().equals(NoTyping)) {
te.edit().remove("NoTyping").apply();
}else if (checkBox.getText().toString().equals(noStoryRead)) {
te.edit().remove("noStoryRead").apply();
}else if (checkBox.getText().toString().equals(usefolow)) {
te.edit().remove("usefolow").apply();
}else if (checkBox.getText().toString().equals(allowShare)) {
te.edit().remove("allowShare").apply();
}else if (checkBox.getText().toString().equals(HideOnline)) {
te.edit().remove("HideOnline").apply();
}else if (checkBox.getText().toString().equals(PreventMedia)) {
te.edit().remove("PreventMedia").apply();
}else if (checkBox.getText().toString().equals(HidePhone)) {
te.edit().remove("HidePhone").apply();
}else if (checkBox.getText().toString().equals(shmsdel)) {
te.edit().remove("shmsdel").apply();
}else if (checkBox.getText().toString().equals(hidestore)) {
te.edit().remove("hidestore").apply();
}
              }
          }

          // غلق الـ AlertDialog بعد التحقق
          XposedHelpers.callMethod(dialog, "dismiss");
      } catch (Throwable t) {
                      throw new RuntimeException(t);
                  }
     TeleVip(lpparam);
  };
    onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> {
              Object drawerLayoutContainer = XposedHelpers.getObjectField(param.thisObject, "drawerLayoutContainer");
if (drawerLayoutContainer != null) {
XposedHelpers.callStaticMethod(
            XposedHelpers.findClass("org.telegram.messenger.browser.Browser", lpparam.classLoader),
            "openUrl",applicationContext,"https://t.me/t_l0_e"
        );
       XposedHelpers.callMethod(drawerLayoutContainer, "closeDrawer");
            XposedHelpers.callMethod(dialog, "dismiss");
    }
    };
 }else {
 Class<?> listenerClass = XposedHelpers.findClass(
    "org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", 
    lpparam.classLoader
);

// ننشئ كائن من هذا الكلاس باستخدام Proxy (لأنه interface)
 onDoneListener = Proxy.newProxyInstance(
    lpparam.classLoader,
    new Class[]{ listenerClass },
    (proxy, method, args) -> {
        if (method.getName().equals("onClick")) {
            Object dialog = args[0]; // هذا AlertDialog
            try {
                // الكود الذي يتم تنفيذه عند الضغط على الزر
                for (int i = 0; i < checkBoxes.size(); i++) {
                    CheckBox checkBox = checkBoxes.get(i);
                    if (checkBox.isChecked()) {
                        if (checkBox.getText().toString().equals(pre)) {
	te.edit().putString("prem", "true").apply();
}else if (checkBox.getText().toString().equals(noRead)) {
	te.edit().putString("noRead", "true").apply();
}else if (checkBox.getText().toString().equals(noRead2)) {
	te.edit().putString("noRead2", "true").apply();
}else if (checkBox.getText().toString().equals(NoTyping)) {
	te.edit().putString("NoTyping", "true").apply();
}else if (checkBox.getText().toString().equals(noStoryRead)) {
	te.edit().putString("noStoryRead", "true").apply();
}else if (checkBox.getText().toString().equals(usefolow)) {
	te.edit().putString("usefolow", "true").apply();
}else if (checkBox.getText().toString().equals(allowShare)) {
	te.edit().putString("allowShare", "true").apply();
}else if (checkBox.getText().toString().equals(HideOnline)) {
	te.edit().putString("HideOnline", "true").apply();
}else if (checkBox.getText().toString().equals(PreventMedia)) {
	te.edit().putString("PreventMedia", "true").apply();
}else if (checkBox.getText().toString().equals(HidePhone)) {
	te.edit().putString("HidePhone", "true").apply();
}else if (checkBox.getText().toString().equals(shmsdel)) {
	te.edit().putString("shmsdel", "true").apply();
}else if (checkBox.getText().toString().equals(hidestore)) {
	te.edit().putString("hidestore", "true").apply();
}
                    } else {
                        if (checkBox.getText().toString().equals(pre)) {
	te.edit().remove("prem").apply();
} else if (checkBox.getText().toString().equals(noRead)) {
	te.edit().remove("noRead").apply();
}else if (checkBox.getText().toString().equals(noRead2)) {
	te.edit().remove("noRead2").apply();
}else if (checkBox.getText().toString().equals(NoTyping)) {
	te.edit().remove("NoTyping").apply();
}else if (checkBox.getText().toString().equals(noStoryRead)) {
	te.edit().remove("noStoryRead").apply();
}else if (checkBox.getText().toString().equals(usefolow)) {
	te.edit().remove("usefolow").apply();
}else if (checkBox.getText().toString().equals(allowShare)) {
	te.edit().remove("allowShare").apply();
}else if (checkBox.getText().toString().equals(HideOnline)) {
	te.edit().remove("HideOnline").apply();
}else if (checkBox.getText().toString().equals(PreventMedia)) {
	te.edit().remove("PreventMedia").apply();
}else if (checkBox.getText().toString().equals(HidePhone)) {
	te.edit().remove("HidePhone").apply();
}else if (checkBox.getText().toString().equals(shmsdel)) {
	te.edit().remove("shmsdel").apply();
}else if (checkBox.getText().toString().equals(hidestore)) {
	te.edit().remove("hidestore").apply();
}
                    }
                }
                
                // غلق الـ AlertDialog بعد التحقق
                XposedHelpers.callMethod(dialog, "dismiss");
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
           TeleVip(lpparam);
        }
        //noinspection SuspiciousInvocationHandlerImplementation
        return null;
    }
);
 onCnelListener = Proxy.newProxyInstance(
    lpparam.classLoader,
    new Class[]{ listenerClass },
    (proxy, method, args) -> {
        if (method.getName().equals("onClick")) {
            Object dialog = args[0]; // هذا AlertDialog
            Object drawerLayoutContainer = XposedHelpers.getObjectField(param.thisObject, "drawerLayoutContainer");
if (drawerLayoutContainer != null) {
XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("org.telegram.messenger.browser.Browser", lpparam.classLoader),
                "openUrl",applicationContext,"https://t.me/t_l0_e"
            );
           XposedHelpers.callMethod(drawerLayoutContainer, "closeDrawer");
                XposedHelpers.callMethod(dialog, "dismiss");
        }
            
        }
        //noinspection SuspiciousInvocationHandlerImplementation
        return null;
    }
);

}
                    // إعداد الزر الموجب
XposedHelpers.callMethod(alertDialog, "setPositiveButton", 
    btnsave, onDoneListener
);
XposedHelpers.callMethod(alertDialog, "setNegativeButton", 
   chena, onCnelListener
);
/*
XposedHelpers.callMethod(alertDialog, "setNeutralButton", 
    "سجل القنوات", 
    new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        XposedHelpers.callMethod(dialog, "dismiss");
                  }
    }
);
*/

                
                    XposedHelpers.callMethod(alertDialog, "show");
                            }
                        
                        }
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
}




        public static void TeleVip(final XC_LoadPackage.LoadPackageParam lpparam) {
            XSharedPreferences sharedPreferences = new XSharedPreferences(lpparam.packageName, strTelevip);

            try {
            if (lpparam.packageName.equals("uz.unnarsx.cherrygram")){  
            
            strTelevip = "cherrygram";
            
            }else {
            strTelevip = "televip";
            }


      if (lpparam.packageName.equals("com.iMe.android") || lpparam.packageName.equals("com.iMe.android.web")){  
            Class<?> userConfigClass3 = XposedHelpers.findClass("com.iMe.storage.data.locale.prefs.impl.ForkPremiumPreference", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass3, "isPremium", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                       if (sharedPreferences.contains("prem")) {
                    // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                    }
                }
            });
            
            Class<?> userConfigClass4 = XposedHelpers.findClass("com.iMe.utils.helper.ForkPremiumHelper", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass4, "isPremium", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                       if (sharedPreferences.contains("prem")) {
                        // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                    }
                }
            });
  
}
	Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.UserConfig", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass, "isPremium", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                     if (sharedPreferences.contains("prem")) {
                          // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                    }
                }
            });
                  if (!lpparam.packageName.equals("com.skyGram.bestt")){  

            
            Class<?> userConfigClass2 = XposedHelpers.findClass("org.telegram.messenger.MessagesController", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass2, "premiumFeaturesBlocked", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)  {
                    // قم بتعيين القيمة دائمًا إلى true
                       if (sharedPreferences.contains("prem")) {    
                    param.setResult(false);
                    }
                }
            });
           }
        } catch (Exception ex){
        ErrorShow(ex.getMessage());
    }

try {
XposedHelpers.findAndHookMethod(
    "org.telegram.ui.ChatActivity$ChatActivityEnterViewDelegate",
    lpparam.classLoader,    
    "needSendTyping",                                  
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)  {
                 if (sharedPreferences.contains("NoTyping")) {
                               XposedBridge.log("needSendTyping method is blocked.");
                            param.setResult(null); 
              }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {

                    }
                });
        } catch (Exception ex){
        ErrorShow(ex.getMessage());
    }


try {
Class<?> userConfigClass6 = XposedHelpers.findClass("org.telegram.ui.Stories.StoriesController", lpparam.classLoader);

 Class<?> clastesk = Class.forName("org.telegram.tgnet.tl.TL_stories$StoryItem", true, lpparam.classLoader);
Class<?> clastesk2 = Class.forName("org.telegram.tgnet.tl.TL_stories$PeerStories", true, lpparam.classLoader);

XposedHelpers.findAndHookMethod(
    userConfigClass6,
    "markStoryAsRead",  
    clastesk2,
    clastesk,
    boolean.class,
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
if (sharedPreferences.contains("noStoryRead")) {
                            param.setResult(false); 
                            }
              
                    }

                   
                });
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
try {
Class<?> userConfigClass7 = XposedHelpers.findClass("org.telegram.messenger.MessagesController", lpparam.classLoader);

 Class<?> clastesk = Class.forName("org.telegram.tgnet.TLRPC$Chat", true, lpparam.classLoader);

XposedHelpers.findAndHookMethod(
    userConfigClass7,
    "isChatNoForwards",  
    clastesk,
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
if (sharedPreferences.contains("usefolow")) {
                            param.setResult(false); 
                            }
              
                    }

                   
                });
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}

try {
 	Class<?> userConfigClass8 = XposedHelpers.findClass("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass8, "allowScreenshots", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                   
if (sharedPreferences.contains("allowShare")) {
                     // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                    }
                }
            });
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
                          try {
            // العثور على الكلاسات المطلوبة للمعاملات
            Class<?> connectionsManagerClass = XposedHelpers.findClass(
                "org.telegram.tgnet.ConnectionsManager",
                lpparam.classLoader
            );
            Class<?> tlObjectClass = XposedHelpers.findClass("org.telegram.tgnet.TLObject", lpparam.classLoader);
            Class<?> requestDelegateClass = XposedHelpers.findClass("org.telegram.tgnet.RequestDelegate", lpparam.classLoader);
            Class<?> requestDelegateTimestampClass = XposedHelpers.findClass("org.telegram.tgnet.RequestDelegateTimestamp", lpparam.classLoader);
            Class<?> quickAckDelegateClass = XposedHelpers.findClass("org.telegram.tgnet.QuickAckDelegate", lpparam.classLoader);
            Class<?> writeToSocketDelegateClass = XposedHelpers.findClass("org.telegram.tgnet.WriteToSocketDelegate", lpparam.classLoader);

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
        protected void beforeHookedMethod(MethodHookParam param) {
        if (sharedPreferences.contains("HideOnline")) {
            try {
                Class<?> tlAccountUpdateStatusClass;
                if (lpparam.packageName.equals("com.tgconnect.android")|| lpparam.packageName.equals("org.telegram.messenger.beta")){  
                            tlAccountUpdateStatusClass = XposedHelpers.findClass(
                    "org.telegram.tgnet.TLRPC$TL_account_updateStatus", 
                    param.thisObject.getClass().getClassLoader()
                );
    
                }else {
                tlAccountUpdateStatusClass = XposedHelpers.findClass(
                    "org.telegram.tgnet.tl.TL_account$updateStatus", 
                    param.thisObject.getClass().getClassLoader()
                );                
}
                // التحقق من النوع وتعديله
                Object object = param.args[0]; // أول معامل في الطريقة
                if (tlAccountUpdateStatusClass.isInstance(object)) {
                    // تعديل الخاصية offline إلى true
                    XposedHelpers.setBooleanField(object, "offline", true);
                   // XposedBridge.log("Modified TL_account_updateStatus: offline set to true.");
                }
            } catch (Exception e) {
                XposedBridge.log("Error while handling TL_account_updateStatus: " + e.getMessage());
            }
            }
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            
        }
    }
);

XposedHelpers.findAndHookMethod(
    "org.telegram.ui.ProfileActivity", // اسم الكلاس
    lpparam.classLoader, // الـ ClassLoader
    "updateProfileData",
    boolean.class,
    new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        if (sharedPreferences.contains("HideOnline")) {
            final Object profileActivityInstance = param.thisObject;
            Class<?> profileActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ProfileActivity");
            Class<?> baseFragmentClass = lpparam.classLoader.loadClass("org.telegram.ui.ActionBar.BaseFragment");
            Method getUserConfigMethod = baseFragmentClass.getDeclaredMethod("getUserConfig");
            getUserConfigMethod.setAccessible(true);
            Object userConfig = getUserConfigMethod.invoke(profileActivityInstance);

            Method getClientUserIdMethod;
            if (userConfig != null) {
                getClientUserIdMethod = userConfig.getClass().getDeclaredMethod("getClientUserId");

                getClientUserIdMethod.setAccessible(true);
                //noinspection DataFlowIssue
                long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);
                Field userIdField = profileActivityClass.getDeclaredField("userId");
                userIdField.setAccessible(true);

                final long userId = userIdField.getLong(profileActivityInstance);
                if (userId != 0 && userId == clientUserId) {
                    Object[] onlineTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, "onlineTextView");

                    if (onlineTextViewArray != null && onlineTextViewArray.length > 1) {
                        // الحصول على SimpleTextView[1]
                        Object simpleTextView1 = onlineTextViewArray[1];

                        if (simpleTextView1 != null) {
                            // استدعاء setText باستخدام LSPosed
                            XposedHelpers.callMethod(simpleTextView1, "setText", onlinestatic);
                        }
                    }
                }
            }
}
        }
    }
);
 } catch (Exception ex){
     ErrorShow(ex.getMessage());
 }
try {
                Class<?> messageobjectClass = XposedHelpers.findClass("org.telegram.messenger.MessageObject", lpparam.classLoader);

                XposedHelpers.findAndHookMethod(
    "org.telegram.ui.ChatActivity", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "sendSecretMessageRead", 
    messageobjectClass,
     boolean.class,  
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                         if (sharedPreferences.contains("PreventMedia")) {
                               param.setResult(null); 
                               }
              
              
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {

                    }
                });
                                XposedHelpers.findAndHookMethod(
    "org.telegram.ui.ChatActivity", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "sendSecretMediaDelete", 
    messageobjectClass,
 
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                            if (sharedPreferences.contains("PreventMedia")) {
                            param.setResult(null); 
                            }
              
              
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {

                    }
                });     
Class<?> photoViewerproviderClass = XposedHelpers.findClass("org.telegram.ui.PhotoViewer$PhotoViewerProvider", lpparam.classLoader);
XposedHelpers.findAndHookMethod(
    "org.telegram.ui.SecretMediaViewer", // اسم الكلاس
    lpparam.classLoader,           // الـ ClassLoader
    "openMedia",
    messageobjectClass,
    photoViewerproviderClass,
    java.lang.Runnable.class,
    java.lang.Runnable.class,    
    new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
       if (sharedPreferences.contains("PreventMedia")) {
param.args[2]=null;
param.args[3]=null;
            // الحصول على كائن ChatActivity
           Object forwardingMessage = param.args[0];

            if (forwardingMessage != null) {
                // الوصول إلى الحقل messageOwner داخل forwardingMessage
                Class<?> forwardingMessageClass = forwardingMessage.getClass();
                Field messageOwnerField = forwardingMessageClass.getDeclaredField("messageOwner");
                messageOwnerField.setAccessible(true);
                Object messageOwner = messageOwnerField.get(forwardingMessage);

                if (messageOwner != null) {
XposedHelpers.setObjectField(messageOwner, "ttl", 0x7FFFFFFF);


                }
            }
            }
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            
        }
    }
);

XposedHelpers.findAndHookMethod(
    "org.telegram.ui.SecretMediaViewer", // اسم الكلاس
    lpparam.classLoader,                           // الـ ClassLoader
    "closePhoto", 
    boolean.class,
    boolean.class,                                  // اسم الدالة
    new XC_MethodHook() {                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                    if (sharedPreferences.contains("PreventMedia")) {                
                //            param.args[1] =true;
                      Object profileActivityInstance = param.thisObject;
        XposedHelpers.setObjectField(profileActivityInstance, "onClose", null);
}
                    }
                });
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
                try {
if (sharedPreferences.contains("shmsdel") && !istru) {
istru =true;
            ClassLoader classLoader = lpparam.classLoader;
            
                NEWAntiRecall.initUI(classLoader);
             //   if (lpparam.packageName.equals("com.skyGram.bestt")){    
           //     NEWAntiRecall.initProcessing2(classLoader);
          //      }else {
                NEWAntiRecall.initProcessing(classLoader);
             //   }
                NEWAntiRecall.init(classLoader);
}
                } catch (Exception ex){
                    ErrorShow(ex.getMessage());
                }
try {
        XposedHelpers.findAndHookMethod("org.telegram.messenger.MessagesController", lpparam.classLoader, "storiesEnabled", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
               if (sharedPreferences.contains("hidestore")) {
                 param.setResult(false);
                 }
            }
        });
        XposedHelpers.findAndHookMethod("org.telegram.messenger.MessagesController", lpparam.classLoader, "storyEntitiesAllowed", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
              if (sharedPreferences.contains("hidestore")) {
                  param.setResult(false);
                  }
            }
        });
        XposedHelpers.findAndHookMethod("org.telegram.messenger.MessagesController", lpparam.classLoader, "storyEntitiesAllowed", "org.telegram.tgnet.TLRPC.User", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
              if (sharedPreferences.contains("hidestore")) {
                  param.setResult(false);
                  }
            }
        });
        XposedHelpers.findAndHookMethod("org.telegram.ui.Stories.StoriesController", lpparam.classLoader, "hasStories", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
              if (sharedPreferences.contains("hidestore")) {
                  param.setResult(false);
                  }
            }
        });
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
                              }
               public static void HidePhone(final XC_LoadPackage.LoadPackageParam lpparam) {
    try {
                                    XSharedPreferences sharedPreferences = new XSharedPreferences(lpparam.packageName, strTelevip);    
Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.UserConfig", lpparam.classLoader);

XposedHelpers.findAndHookMethod(userConfigClass, "getCurrentUser", new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        Object userObject = param.getResult();
           if (sharedPreferences.contains("HidePhone")) {
        if (userObject != null) {
                            XposedHelpers.setObjectField(userObject, "phone", null);
                       //     XposedBridge.log("phone is null.");
                        }
                        }

}
});
    } catch (Exception ex){
        ErrorShow(ex.getMessage());
    }
  }
            public static void TeleOne(final XC_LoadPackage.LoadPackageParam lpparam) {
            ClassLoader classLoader = lpparam.classLoader;
            ApplicationLoaderHook.init(classLoader);
                     XSharedPreferences sharedPreferences = new XSharedPreferences(lpparam.packageName, strTelevip);    
try {
Class<?> FileLoadOperationClass = XposedHelpers.findClass("org.telegram.messenger.FileLoadOperation", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(FileLoadOperationClass, "updateParams", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
            int downloadChunkSizeBig;
            int maxDownloadRequests = 4;
            int maxDownloadRequestsBig = 8;
            int maxCdnParts;
                downloadChunkSizeBig = 0x100000; // 1MB

            maxCdnParts = (int) (0x7D000000L / downloadChunkSizeBig);

            XposedHelpers.setIntField(param.thisObject, "downloadChunkSizeBig", downloadChunkSizeBig);
            XposedHelpers.setObjectField(param.thisObject, "maxDownloadRequests", maxDownloadRequests);
            XposedHelpers.setObjectField(param.thisObject, "maxDownloadRequestsBig", maxDownloadRequestsBig);
            XposedHelpers.setObjectField(param.thisObject, "maxCdnParts", maxCdnParts);
      
                
            
                }
            });
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
                Class<?> conClass;
            try {
                conClass = Class.forName("android.content.Context", true, lpparam.classLoader);

                if (!lpparam.packageName.equals("xyz.nextalone.nagram")){
                 drawableClass = Class.forName("org.telegram.messenger.R$drawable", true, lpparam.classLoader);              
               }


final Class<?> ChatActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ChatActivity");
               if (!isshow){

XposedHelpers.findAndHookMethod(ChatActivityClass, "createView", conClass, new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object ChatActivityInstance = param.thisObject;
        Field headerItemField = ChatActivityClass.getDeclaredField("headerItem");
            headerItemField.setAccessible(true);
            Object headerItem = headerItemField.get(ChatActivityInstance);
            if (headerItem != null) {
                Method addSubItemMethod = headerItem.getClass().getDeclaredMethod(
                        "lazilyAddSubItem",
                        int.class,
                        int.class,
                        CharSequence.class
                );
                addSubItemMethod.setAccessible(true);
                       if (!lpparam.packageName.equals("xyz.nextalone.nagram")){ 
                 
                 int drawableResource = XposedHelpers.getStaticIntField(drawableClass, "msg_go_up");

                           if (!lpparam.packageName.equals("com.skyGram.bestt") && !lpparam.packageName.equals("uz.unnarsx.cherrygram") &&
 !lpparam.packageName.equals("com.iMe.android")  && !lpparam.packageName.equals("com.iMe.android.web") && !lpparam.packageName.equals("app.nicegram") && !lpparam.packageName.equals("org.telegram.plus") && !lpparam.packageName.equals("com.xplus.messenger") && !lpparam.packageName.equals("org.forkgram.messenger")&&!lpparam.packageName.equals("org.forkclient.messenger.beta")) {
    addSubItemMethod.invoke(headerItem, 70, drawableResource, onemsg);
}
                     drawableResource = XposedHelpers.getStaticIntField(drawableClass, "player_new_order");                 
                addSubItemMethod.invoke(headerItem, 71, drawableResource, tothmsg);
}
isshow=true;
      }
      
    }
});
}
            } catch (Exception ex){
                ErrorShow(ex.getMessage());
            }
Class<?> ChatActivityClass3;
            try {
                switch (lpparam.packageName) {
                    case "com.iMe.android":
                        ChatActivityClass3 = XposedHelpers.findClass(
                                "org.telegram.ui.ChatActivity$24",
                                lpparam.classLoader
                        );
                        break;
                    case "com.iMe.android.web":
                        ChatActivityClass3 = XposedHelpers.findClass(
                                "org.telegram.ui.ChatActivity$28",
                                lpparam.classLoader
                        );
                        break;
                    case "org.telegram.plus":
                        ChatActivityClass3 = XposedHelpers.findClass(
                                "org.telegram.ui.ChatActivity$14",
                                lpparam.classLoader
                        );
                        break;
                    case "org.forkclient.messenger.beta":
                    case "org.forkgram.messenger":
                        ChatActivityClass3 = XposedHelpers.findClass(
                                "org.telegram.ui.ChatActivity$15",
                                lpparam.classLoader
                        );
                        break;
                    case "org.telegram.messenger.beta":
                        ChatActivityClass3 = XposedHelpers.findClass(
                                "org.telegram.ui.ChatActivity$12",
                                lpparam.classLoader
                        );
                        break;
                    default:
                        ChatActivityClass3 = XposedHelpers.findClass(
                                "org.telegram.ui.ChatActivity$13",
                                lpparam.classLoader
                        );
                        break;
                }
            XposedHelpers.findAndHookMethod(
                ChatActivityClass3,
                "onItemClick", // اسم الدالة        
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                    int id =(int)param.args[0];
                    Object chatActivityInstance = param.thisObject;
                final  Object chatActivity = XposedHelpers.getObjectField(chatActivityInstance, "this$0");
                    if (id == 70){
// استدعاء الدالة
XposedHelpers.callMethod(chatActivity, "scrollToMessageId",1,0,true,0,true,0);
XposedBridge.log("scrollToMessageId is call.");

                }else if (id == 71){
              final Context applicationContext = (Context) XposedHelpers.callMethod(chatActivity, "getContext");
       if (applicationContext != null) {
        Object getResourceProvider = XposedHelpers.callMethod(chatActivity, "getResourceProvider");

            Object alertDialog = XposedHelpers.newInstance(
                XposedHelpers.findClass("org.telegram.ui.ActionBar.AlertDialog$Builder", lpparam.classLoader),
                applicationContext,
                getResourceProvider
            );
                            Context applicationContext2= (Context) XposedHelpers.getStaticObjectField(
                        XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                        "applicationContext"
                );
           
            Strck(applicationContext2);  

                                      XposedHelpers.callMethod(alertDialog, "setTitle", inpMsg);                                    
                                      // إنشاء EditText مع تصميم جميل
final EditText editText = new EditText(applicationContext);
editText.setInputType(InputType.TYPE_CLASS_NUMBER);
ckDarck(lpparam);
    if (!isCurrentThemeDay){
    editText.setTextColor(0xFF000000);
    editText.setHintTextColor(0xFF424242);
    }else {
    editText.setTextColor(0xFFFFFFFF);
editText.setHintTextColor(0xFFBDBDBD);
    }
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

XposedHelpers.callMethod(alertDialog, "setView", layout);

// إعداد زر الحفظ

Object onDoneListener;
Object onCnelListener;
 if (lpparam.packageName.equals("com.tgconnect.android") || lpparam.packageName.equals("org.telegram.messenger.beta")){  
  onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> {
                              try {

          String inputText  = editText.getText().toString().trim();

          // التحقق من المدخلات
          if (!inputText.isEmpty()) {
          int msid = Integer.parseInt(inputText);
XposedHelpers.callMethod(chatActivity, "scrollToMessageId",msid,0,true,0,true,0);
XposedBridge.log("scrollToMessageId is call.");

          }

      } catch (Throwable t) {
                                  throw new RuntimeException(t);
                              }
                         };
  onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> {
                  try {
          XposedHelpers.callMethod(dialog, "dismiss");
      } catch (Throwable t) {
                      throw new RuntimeException(t);
                  }
  };

}else {
Class<?> listenerClass = XposedHelpers.findClass(
    "org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", 
    lpparam.classLoader
);
 onDoneListener = Proxy.newProxyInstance(
    lpparam.classLoader,
    new Class[]{ listenerClass },
    (proxy, method, args) -> {
        if (method.getName().equals("onClick")) {
            // هذا AlertDialog
            try {

                String inputText  = editText.getText().toString().trim();

                // التحقق من المدخلات
                if (!inputText.isEmpty()) {
                int msid = Integer.parseInt(inputText);
XposedHelpers.callMethod(chatActivity, "scrollToMessageId",msid,0,true,0,true,0);
XposedBridge.log("scrollToMessageId is call.");

                }

            } catch (Throwable t) {
                throw new RuntimeException(t);
            }          
        }
        //noinspection SuspiciousInvocationHandlerImplementation
        return null;
    }
);
 onCnelListener = Proxy.newProxyInstance(
    lpparam.classLoader,
    new Class[]{ listenerClass },
    (proxy, method, args) -> {
        if (method.getName().equals("onClick")) {
            Object dialog = args[0]; // هذا AlertDialog
            try {
                XposedHelpers.callMethod(dialog, "dismiss");
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }         
            
        }
        //noinspection SuspiciousInvocationHandlerImplementation
        return null;
    }
);
}
      XposedHelpers.callMethod(alertDialog, "setPositiveButton", serc,onDoneListener
);

XposedHelpers.callMethod(alertDialog, "setNegativeButton", 
   syno, 
    onCnelListener
);
                
                    XposedHelpers.callMethod(alertDialog, "show");
}       
                }
    }
});
            } catch (Exception ex){
                ErrorShow(ex.getMessage());
            }
            try {
                conClass = Class.forName("android.content.Context", true, lpparam.classLoader);

                XposedHelpers.findAndHookMethod(
    "org.telegram.ui.ProfileActivity", // اسم الكلاس
    lpparam.classLoader, // الـ ClassLoader
    "createView",
    conClass,
    new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
                XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                "applicationContext"
            );
            
            final Object profileActivityInstance = param.thisObject;

            // الحصول على الحقل nameTextView (كمصفوفة Objects لأنه لا يمكننا تعريف SimpleTextView مباشرة)
            Object[] nameTextViewArray = (Object[]) XposedHelpers.getObjectField(profileActivityInstance, "nameTextView");

            if (nameTextViewArray != null && nameTextViewArray.length > 1) {
                // الحصول على SimpleTextView[1]
                Object simpleTextView1 = nameTextViewArray[1];

                if (simpleTextView1 != null) {
                    // إضافة حدث النقر باستخدام callMethod
                    XposedHelpers.callMethod(simpleTextView1, "setOnClickListener", (View.OnClickListener) v -> {
                        try {
                            // تحميل الكلاسات المطلوبة
                            Class<?> profileActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.ProfileActivity");
                            Class<?> baseFragmentClass = lpparam.classLoader.loadClass("org.telegram.ui.ActionBar.BaseFragment");
                            Class<?> userObjectClass = lpparam.classLoader.loadClass("org.telegram.messenger.UserObject");

                            // استدعاء MessagesController
                            Method getMessagesControllerMethod = baseFragmentClass.getDeclaredMethod("getMessagesController");
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
                                Method getUserMethod = messagesController.getClass().getDeclaredMethod("getUser", Long.class);
                                getUserMethod.setAccessible(true);
                                //noinspection JavaReflectionInvocation
                                Object user = getUserMethod.invoke(messagesController, userIdObject);
        Field chatIdField = profileActivityClass.getDeclaredField("chatId");
        chatIdField.setAccessible(true);
        final long chatId = chatIdField.getLong(profileActivityInstance);

                                if (user != null && chatId ==0) {
                                    // استدعاء getUserName
                                    Class<?> userClass = lpparam.classLoader.loadClass("org.telegram.tgnet.TLRPC$User");
                                    Method getUserNameMethod = userObjectClass.getDeclaredMethod("getUserName", userClass);
                                    getUserNameMethod.setAccessible(true);
                                    String userName = (String) getUserNameMethod.invoke(null, user);
                                    if (userName != null){
                                    String copnames=copname+userName+copname2;
                                                   ((ClipboardManager) applicationContext.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", userName));
                                    // عرض اسم المستخدم
                                    Toast.makeText(applicationContext,copnames,Toast.LENGTH_LONG).show();
                               }
                                 }
                            }
                        } catch (Exception e) {
                            XposedBridge.log("Error: " + e.getMessage());
                        }
                    });
                }
            }
        }
    }
);
            } catch (Exception ex){
                ErrorShow(ex.getMessage());
            }
 if (!lpparam.packageName.equals("com.skyGram.bestt") && !lpparam.packageName.equals("xyz.nextalone.nagram")){ 
try {
  Class<?> peerColorActivityClass = lpparam.classLoader.loadClass("org.telegram.ui.PeerColorActivity");
          
   XposedHelpers.findAndHookMethod(
   peerColorActivityClass,
    "apply", // اسم الميثود التي تريد اعتراضها
  new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            // الحصول على Context
            Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
                XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                "applicationContext"
            );

            // الحصول على كائن PeerColorActivity الحالي
            final Object peerColorActivityInstance = param.thisObject;

            // الوصول إلى الحقل profilePage
            Field profilePageField = peerColorActivityClass.getDeclaredField("profilePage");
            profilePageField.setAccessible(true); // السماح بالوصول إلى الحقل الخاص
            Object profilePage = profilePageField.get(peerColorActivityInstance); // استخدام get() بدلاً من getLong()

            // التحقق من selectedColor داخل profilePage
            if (profilePage != null) {
                // الوصول إلى حقل selectedColor في profilePage
                Field selectedColorField = profilePage.getClass().getDeclaredField("selectedColor");
               selectedColorField.setAccessible(true); // السماح بالوصول إلى الحقل الخاص
                int selectedColor = selectedColorField.getInt(profilePage); // الحصول على قيمة selectedColor
                Field selectedEmojiField = profilePage.getClass().getDeclaredField("selectedEmoji");
                selectedEmojiField.setAccessible(true); // السماح بالوصول إلى الحقل الخاص
                long selectedEmoji = selectedEmojiField.getLong(profilePage); // الحصول على قيمة selectedEmoji
                final SharedPreferences teleprofilepage = applicationContext.getSharedPreferences("teleProfilePage", Activity.MODE_PRIVATE);	
                // التحقق من القيمة
                if (selectedColor != 0) {
                teleprofilepage.edit().putString("selectedColor", String.valueOf(selectedColor)).apply();
                    // تنفيذ ما تريد عند تحقق الشرط
                   // XposedBridge.log("selectedColor is less than "+selectedColor);
                }else {
                teleprofilepage.edit().remove("selectedColor").apply();
                }
                if (selectedEmoji != 0) {
                 teleprofilepage.edit().putString("selectedEmoji", String.valueOf(selectedEmoji)).apply();
                    // تنفيذ ما تريد عند تحقق الشرط
          //          XposedBridge.log("selectedEmoji is less than "+selectedEmoji);
                }else {
                teleprofilepage.edit().remove("selectedEmoji").apply();
                }
            }
            
            Field namePageField = peerColorActivityClass.getDeclaredField("namePage");
            namePageField.setAccessible(true); // السماح بالوصول إلى الحقل الخاص
            Object namePage = namePageField.get(peerColorActivityInstance); // استخدام get() بدلاً من getLong()

            // التحقق من selectedColor داخل namePage
            if (namePage != null) {
                // الوصول إلى حقل selectedColor في namePage
                Field selectedColorField = namePage.getClass().getDeclaredField("selectedColor");
               selectedColorField.setAccessible(true); // السماح بالوصول إلى الحقل الخاص
                int selectedColor = selectedColorField.getInt(namePage); // الحصول على قيمة selectedColor
                Field selectedEmojiField = namePage.getClass().getDeclaredField("selectedEmoji");
                selectedEmojiField.setAccessible(true); // السماح بالوصول إلى الحقل الخاص
                long selectedEmoji = selectedEmojiField.getLong(namePage); // الحصول على قيمة selectedEmoji
                final SharedPreferences telenamePage = applicationContext.getSharedPreferences("telenamePage", Activity.MODE_PRIVATE);	
                // التحقق من القيمة
                if (selectedColor != 0) {
                telenamePage.edit().putString("selectedColor", String.valueOf(selectedColor)).apply();
                    // تنفيذ ما تريد عند تحقق الشرط
                   // XposedBridge.log("selectedColor is less than "+selectedColor);
                }else {
                telenamePage.edit().remove("selectedColor").apply();
                }
                if (selectedEmoji != 0) {
                 telenamePage.edit().putString("selectedEmoji", String.valueOf(selectedEmoji)).apply();
                    // تنفيذ ما تريد عند تحقق الشرط
          //          XposedBridge.log("selectedEmoji is less than "+selectedEmoji);
                }else {
                telenamePage.edit().remove("selectedEmoji").apply();
                }
            } 
           
            
        }
    }
);
                Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.UserConfig", lpparam.classLoader);

XposedHelpers.findAndHookMethod(userConfigClass, "getCurrentUser", new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        ColorProfile(param,lpparam);

    }
});
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
}else {
HidePhone(lpparam);
}
try {
                       Class<?> userConfigClass = XposedHelpers.findClass("org.telegram.messenger.MessagesController", lpparam.classLoader);
XposedHelpers.findAndHookMethod(userConfigClass, "getUser",Long.class, new XC_MethodHook() {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object userObject = param.getResult();
Object MessagesControllerInstance = param.thisObject;
        if (userObject != null) {  
          
                Class<?> baseFragmentClass = lpparam.classLoader.loadClass("org.telegram.messenger.BaseController");
            Method getUserConfigMethod = baseFragmentClass.getDeclaredMethod("getUserConfig");
            getUserConfigMethod.setAccessible(true);
            Object userConfig = getUserConfigMethod.invoke(MessagesControllerInstance);

            Method getClientUserIdMethod = userConfig.getClass().getDeclaredMethod("getClientUserId");
            getClientUserIdMethod.setAccessible(true);
            long clientUserId = (long) getClientUserIdMethod.invoke(userConfig);
            long userid = (long) param.args[0];

            if (clientUserId == userid){
             if (!lpparam.packageName.equals("com.skyGram.bestt") && !lpparam.packageName.equals("xyz.nextalone.nagram")){ 
      ColorProfile(param,lpparam);
      }
                 if (sharedPreferences.contains("HidePhone")){
                            XposedHelpers.setObjectField(userObject, "phone", null);
                }   
                           //     XposedBridge.log("phone is null.");
                     }
                           }                        

}
});
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}
try {
 Class<?> readTaskClass = Class.forName("org.telegram.messenger.MessagesController$ReadTask", true, lpparam.classLoader);
Class<?> messagesControllerClass = XposedHelpers.findClass("org.telegram.messenger.MessagesController", lpparam.classLoader);

XposedHelpers.findAndHookMethod(
    messagesControllerClass,
    "completeReadTask", // اسم الدالة
    readTaskClass,       // نوع المعامل الأول (task)
    new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
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
                long dialogId = (long) XposedHelpers.getObjectField(task, "dialogId");
if (dialogId != 0){
                // الحصول على الكائن الحالي لـ MessagesController
                Object messagesControllerInstance = param.thisObject;

                // استدعاء الدالة getChat
                try {
                    Method getChatMethod = messagesControllerClass.getDeclaredMethod("getUser", Long.class);
                    getChatMethod.setAccessible(true);

                    // تحويل dialogId إلى Long
                    Long chatIdObject = dialogId;

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
        protected void afterHookedMethod(MethodHookParam param){
            // يمكن تنفيذ أي عمليات إضافية بعد الدالة الأصلية هنا
        }
    }
);
} catch (Exception ex){
    ErrorShow(ex.getMessage());
}


 

}

               public static void ColorProfile(final de.robv.android.xposed.XC_MethodHook.MethodHookParam param,final XC_LoadPackage.LoadPackageParam lpparam){
               try {
                   Object userObject = param.getResult();
                   HidePhone(lpparam);
                   Context applicationContext = (Context) XposedHelpers.getStaticObjectField(
                           XposedHelpers.findClass("org.telegram.messenger.ApplicationLoader", lpparam.classLoader),
                           "applicationContext"
                   );
                   if (applicationContext != null) {
                       if (userObject != null) {

                           // الحصول على profileColor من الكائن userObject
                           Object profileColor = XposedHelpers.getObjectField(userObject, "profile_color");
                           if (profileColor == null) {
                               //      XposedBridge.log("profileColor is null. Creating new instance.");

                               // إنشاء كائن جديد من TLRPC.TL_peerColor
                               Class<?> peerColorClass = XposedHelpers.findClass(
                                       "org.telegram.tgnet.TLRPC$TL_peerColor",
                                       lpparam.classLoader
                               );
                               profileColor = XposedHelpers.newInstance(peerColorClass);

                               // تعيين الكائن الجديد في userObject
                               XposedHelpers.setObjectField(userObject, "profile_color", profileColor);

                           }
                           final SharedPreferences teleprofilepage = applicationContext.getSharedPreferences("teleProfilePage", Activity.MODE_PRIVATE);
                           if (teleprofilepage.contains("selectedColor")) {
                               int selectedColor = Integer.parseInt(teleprofilepage.getString("selectedColor", "0"));

                               // إذا كان profileColor null، إنشاء كائن جديد

                               Object flags2 = XposedHelpers.getObjectField(userObject, "flags2");
                               if (flags2 != null) {
                                   int currentFlags = (Integer) flags2;
                                   currentFlags |= 512;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(userObject, "flags2", currentFlags); // تحديث القيمة الجديدة
                                   //      XposedBridge.log("flags2 is "+currentFlags);
                               }
                               // تعديل background_emoji_id داخل profileColor
                               XposedHelpers.setObjectField(profileColor, "color", selectedColor);

                               // تعديل flags داخل profileColor باستخدام OR البتية
                               Object flags = XposedHelpers.getObjectField(profileColor, "flags");
                               if (flags != null) {
                                   int currentFlags = (Integer) flags;
                                   currentFlags |= 1;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(profileColor, "flags", currentFlags); // تحديث القيمة الجديدة
                                   //        XposedBridge.log("flags is "+currentFlags);
                               }
                           }
                           if (teleprofilepage.contains("selectedEmoji")) {
                               long selectedEmoji = Long.parseLong(teleprofilepage.getString("selectedEmoji", "0"));

                               Object flags2 = XposedHelpers.getObjectField(userObject, "flags2");
                               if (flags2 != null) {
                                   int currentFlags = (Integer) flags2;
                                   currentFlags |= 512;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(userObject, "flags2", currentFlags); // تحديث القيمة الجديدة
                                   //      XposedBridge.log("flags2 is "+currentFlags);
                               }
                               // تعديل background_emoji_id داخل profileColor
                               XposedHelpers.setObjectField(profileColor, "background_emoji_id", selectedEmoji);

                               // تعديل flags داخل profileColor باستخدام OR البتية
                               Object flags = XposedHelpers.getObjectField(profileColor, "flags");
                               if (flags != null) {
                                   int currentFlags = (Integer) flags;
                                   currentFlags |= 2;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(profileColor, "flags", currentFlags); // تحديث القيمة الجديدة
                                   //        XposedBridge.log("flags is "+currentFlags);
                               }
                           }

                           Object color = XposedHelpers.getObjectField(userObject, "color");


                           if (color == null) {
                               //      XposedBridge.log("color is null. Creating new instance.");

                               // إنشاء كائن جديد من TLRPC.TL_peerColor
                               Class<?> peerColorClass = XposedHelpers.findClass(
                                       "org.telegram.tgnet.TLRPC$TL_peerColor",
                                       lpparam.classLoader
                               );
                               color = XposedHelpers.newInstance(peerColorClass);

                               // تعيين الكائن الجديد في userObject
                               XposedHelpers.setObjectField(userObject, "color", color);
                               long id = (long) XposedHelpers.getObjectField(userObject, "id");
                               int color2 = (int) (id % 7);
                               XposedHelpers.setObjectField(color, "color", color2);

                           }

                           final SharedPreferences telenamePage = applicationContext.getSharedPreferences("telenamePage", Activity.MODE_PRIVATE);
                           if (telenamePage.contains("selectedColor")) {
                               int selectedColor = Integer.parseInt(telenamePage.getString("selectedColor", "0"));

                               // إذا كان color null، إنشاء كائن جديد

                               Object flags2 = XposedHelpers.getObjectField(userObject, "flags2");
                               if (flags2 != null) {
                                   int currentFlags = (Integer) flags2;
                                   currentFlags |= 256;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(userObject, "flags2", currentFlags); // تحديث القيمة الجديدة
                                   //      XposedBridge.log("flags2 is "+currentFlags);
                               }
                               // تعديل background_emoji_id داخل color
                               XposedHelpers.setObjectField(color, "color", selectedColor);

                               // تعديل flags داخل color باستخدام OR البتية
                               Object flags = XposedHelpers.getObjectField(color, "flags");
                               if (flags != null) {
                                   int currentFlags = (Integer) flags;
                                   currentFlags |= 1;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(color, "flags", currentFlags); // تحديث القيمة الجديدة
                                   //        XposedBridge.log("flags is "+currentFlags);
                               }
                           }
                           if (telenamePage.contains("selectedEmoji")) {
                               long selectedEmoji = Long.parseLong(telenamePage.getString("selectedEmoji", "0"));

                               Object flags2 = XposedHelpers.getObjectField(userObject, "flags2");
                               if (flags2 != null) {
                                   int currentFlags = (Integer) flags2;
                                   currentFlags |= 256;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(userObject, "flags2", currentFlags); // تحديث القيمة الجديدة
                                   //      XposedBridge.log("flags2 is "+currentFlags);
                               }
                               // تعديل background_emoji_id داخل color
                               XposedHelpers.setObjectField(color, "background_emoji_id", selectedEmoji);

                               // تعديل flags داخل color باستخدام OR البتية
                               Object flags = XposedHelpers.getObjectField(color, "flags");
                               if (flags != null) {
                                   int currentFlags = (Integer) flags;
                                   currentFlags |= 2;  // تطبيق OR البتية
                                   XposedHelpers.setObjectField(color, "flags", currentFlags); // تحديث القيمة الجديدة
                                   //        XposedBridge.log("flags is "+currentFlags);
                               }
                           }
// XposedBridge.log("profileColor modifications completed.");
                       }
                   }
               } catch (Exception ex){
                   ErrorShow(ex.getMessage());
               }
  }
  public static void ErrorShow(String str){
      XposedBridge.log(str);
  }

}
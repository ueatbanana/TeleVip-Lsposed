package com.my.televip;

import static com.my.televip.MainHook.lpparam;

import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.util.Locale;
import de.robv.android.xposed.*;

import android.content.res.Configuration;

import android.media.MediaPlayer;

import com.my.televip.features.AllowSaveToGallery;
import com.my.televip.features.DisableStories;
import com.my.televip.features.FeatureManager;
import com.my.televip.features.HideSeen;
import com.my.televip.features.HideTyping;
import com.my.televip.features.NEWAntiRecall;
import com.my.televip.features.NoStoryRead;
import com.my.televip.features.TelePremium;
import com.my.televip.features.UnlockChannelFeature;

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
    public static boolean playing=false;
public static int regr=0;
public static String audioUrl;
public static String hidestore;
public static String strTelevip="televip";
public static final boolean DEBUG_MODE=false;
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

public static void ondilag(final de.robv.android.xposed.XC_MethodHook.MethodHookParam param,final XC_LoadPackage.LoadPackageParam lpparam){

}

public static void readFeature(){
    xSharedPreferences.xSharedPre = new XSharedPreferences(lpparam.packageName, strTelevip);
    if (FeatureManager.isTelePremium()) {TelePremium.init();}
    if (FeatureManager.isHideSeenGroup() || FeatureManager.isHideSeenPrivate()) { HideSeen.init(); }
    if (FeatureManager.isNoStoryRead()){ NoStoryRead.init(); }
    if (FeatureManager.isHideTyping()){ HideTyping.init(); }
    if (FeatureManager.isUnlockChannelFeature()){ UnlockChannelFeature.init(); }
    if (FeatureManager.isAllowSaveToGallery()){ AllowSaveToGallery.init(); }
    if (FeatureManager.isHideOnline()){ com.my.televip.features.HideOnline.init(); }
    if (FeatureManager.isPreventMedia()){ com.my.televip.features.PreventMedia.init(); }
    if (FeatureManager.ishowDeletedMessages()){

        NEWAntiRecall.initProcessing(lpparam.classLoader);
        NEWAntiRecall.init(lpparam.classLoader);
        NEWAntiRecall.initAutoDownload(lpparam.classLoader);
    }
    if (FeatureManager.isDisableStories()){ DisableStories.init(); }
    if (FeatureManager.isHidePhone()){
        com.my.televip.features.HidePhone.init();
    }

}


        public static void TeleVip(final XC_LoadPackage.LoadPackageParam lpparam) {
      if (lpparam.packageName.equals("com.iMe.android") || lpparam.packageName.equals("com.iMe.android.web")){  

            
            Class<?> userConfigClass4 = XposedHelpers.findClass("com.iMe.utils.helper.ForkPremiumHelper", lpparam.classLoader);

            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(userConfigClass4, "isPremium", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                       if (xSharedPreferences.xSharedPre.contains("prem")) {
                        // قم بتعيين القيمة دائمًا إلى true
                    param.setResult(true);
                    }
                }
            });
  
}

                  if (!lpparam.packageName.equals("com.skyGram.bestt")){  

            if (loadClass.MessagesControllerClass == null) {
                loadClass.MessagesControllerClass= XposedHelpers.findClass("org.telegram.messenger.MessagesController", lpparam.classLoader);
            }
            // استخدم hook لتعديل متغير isPremium في الكائن
            XposedHelpers.findAndHookMethod(loadClass.MessagesControllerClass, "premiumFeaturesBlocked", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)  {
                    // قم بتعيين القيمة دائمًا إلى true
                       if (xSharedPreferences.xSharedPre.contains("prem")) {
                    param.setResult(false);
                    }
                }
            });
           }



                              }

            public static void TeleOne(final XC_LoadPackage.LoadPackageParam lpparam) {



}

  public static void ErrorShow(String str){
      XposedBridge.log(str);
  }

}
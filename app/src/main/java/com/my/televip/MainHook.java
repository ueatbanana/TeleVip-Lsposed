package com.my.televip;



import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.content.Context;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.*;
import de.robv.android.xposed.*;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.List;

import androidx.annotation.NonNull;

import com.my.televip.AlertDialog.onClickDialog;
import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.features.NEWAntiRecall;
import com.my.televip.features.OtherFeatures;
import com.my.televip.features.downloadSpeed;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.virtuals.EventType;

public class MainHook extends StrVip implements IXposedHookLoadPackage {
public static XC_LoadPackage.LoadPackageParam lpparam;

    private static @NonNull ArrayList<String> getArrayList() {
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
        return list;
    }
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        MainHook.lpparam =lpparam;
        // استهداف تطبيق معين لتعديل ANDROID_ID
        Utils.pkgName = lpparam.packageName;
        xSharedPreferences.xSharedPre = new XSharedPreferences(lpparam.packageName, strTelevip);
                final Class<?> itemClass = Class.forName("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item", true, lpparam.classLoader);

        ClassLoader classLoader = lpparam.classLoader;
        ApplicationLoaderHook.init(classLoader);
        NEWAntiRecall.initUI(lpparam.classLoader);
                // استدعاء الطريقة من الكلاس Theme مباشرة
                XposedHelpers.findAndHookMethod(
                        AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter"), // اسم الكلاس
                        lpparam.classLoader,                           // الـ ClassLoader
                        "resetItems",                                   // اسم الدالة
                        new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                Object drawerLayoutAdapterInstance = param.thisObject;

                                // العثور على المتغير الخاص
                                Class<?> drawerLayoutAdapterClass = drawerLayoutAdapterInstance.getClass();
                                Field itemsField = drawerLayoutAdapterClass.getDeclaredField(AutomationResolver.resolve("DrawerLayoutAdapter","items", AutomationResolver.ResolverType.Field));
                                itemsField.setAccessible(true);
                                ArrayList<?> items = (ArrayList<?>) itemsField.get(drawerLayoutAdapterInstance);

                                // استدعاء الكلاس Item باستخدام Class.forName
                                Constructor<?> itemConstructor = itemClass.getDeclaredConstructor(AutomationResolver.resolveObject("obj5"));
                                itemConstructor.setAccessible(true);
                                // استدعاء الطريقة مباشرة
                                if (loadClass.applicationContext == null) {
                                    loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                                            XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                                            AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                                    );
                                }
                                Strck(loadClass.applicationContext);

                                //noinspection JavaReflectionInvocation
                                Object newItem = itemConstructor.newInstance(13048, GhostMode, EventType.IconSettings());

                                // إضافة الكائن الجديد إلى القائمة
                                if (items instanceof ArrayList<?>) {
                                    //noinspection unchecked
                                    ArrayList<Object> typedItems = (ArrayList<Object>) items;
                                    typedItems.add(newItem);
                                }
                            }
                        }
                );

                Class<?> launchActivityClass = XposedHelpers.findClass(
                        AutomationResolver.resolve("org.telegram.ui.LaunchActivity"),
                        lpparam.classLoader
                );
                XC_MethodHook hook2 = new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(final MethodHookParam param) {

                            final    Class<?> alertDialogBuilderClass = XposedHelpers.findClassIfExists(
                                    AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"),
                                    lpparam.classLoader
                            );
                            if (alertDialogBuilderClass != null) {
                                // تنفيذ الكود بعد استدعاء الدالة
                                ActiveTheme.setActiveTheme();

                                Object drawerLayoutAdapter = XposedHelpers.getObjectField(param.thisObject, AutomationResolver.resolve("LaunchActivity","drawerLayoutAdapter", AutomationResolver.ResolverType.Field));
                                if (drawerLayoutAdapter != null) {

                                    // استدعاء getId وطباعته
                                    int result = (int) XposedHelpers.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter","getId", AutomationResolver.ResolverType.Method), param.args[1]);
                                    if (result == 13048) {
                                        Object contextapp = param.thisObject;
                                        final Context applicationContext = (Context) contextapp;
                                        if (loadClass.applicationContext == null) {
                                            loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                                                    XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                                                    AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                                            );
                                        }
                                        xSharedPreferences.SharedPre = applicationContext.getSharedPreferences(strTelevip,Activity.MODE_PRIVATE);
                                        readFeature();
                                        Object alertDialog = XposedHelpers.newInstance(alertDialogBuilderClass, applicationContext);
                                          // عرض رسالة أو تخصيص النافذة
                                        Strck(applicationContext);
                                        ArrayList<String> list = getArrayList();
                                        final String[] items = list.toArray(new String[0]);
                                        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setTitle", AutomationResolver.ResolverType.Method), strTitle);
                                        // إنشاء تخطيط جديد
                                        LinearLayout layout = new LinearLayout(applicationContext);
                                        layout.setOrientation(LinearLayout.VERTICAL);

// إضافة CheckBox لكل عنصر في القائمة مع إعدادات النص
                                        final List<CheckBox> checkBoxes = new ArrayList<>();
                                        for (String item : items) {
                                            CheckBox checkBox = new CheckBox(applicationContext);
                                            if (item.equals(pre) && FeatureManager.isTelePremium()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(noRead) && FeatureManager.isHideSeenPrivate()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(noRead2) && FeatureManager.isHideSeenGroup()) {
                                                checkBox.setChecked(true);
                                            }
                                            if (item.equals(NoTyping) && FeatureManager.isHideTyping()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(noStoryRead) && FeatureManager.isNoStoryRead()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(usefolow) && FeatureManager.isUnlockChannelFeature()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(allowShare) && FeatureManager.isAllowSaveToGallery()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(HideOnline) && FeatureManager.isHideOnline()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(PreventMedia) && FeatureManager.isPreventMedia()) {
                                                checkBox.setChecked(true);
                                                try {
                                                    checkBox.setOnLongClickListener(_view -> {
                                                        if (!playing) {
                                                            regr = (int) (Math.random() * 3);
                                                            if (regr == 0) {
                                                                audioUrl = "https://qurango.net/radio/abdulbasit_abdulsamad_mojawwad";
                                                            } else if (regr == 1) {
                                                                audioUrl = "https://qurango.net/radio/yasser_aldosari";
                                                            } else {
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
                                                            } catch (IllegalArgumentException |
                                                                     IllegalStateException |
                                                                     IOException e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                            mediaPlayer.prepareAsync();

                                                            mediaPlayer.setOnPreparedListener(mp -> {
                                                                mediaPlayer.start();
                                                                playing = true;
                                                            });
                                                        } else {
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
                                                                } catch (IllegalArgumentException |
                                                                         IllegalStateException |
                                                                         IOException e) {
                                                                    throw new RuntimeException(e);
                                                                }

                                                                playing = false;

                                                            }
                                                        }
                                                        return true;
                                                    });
                                                } catch (Exception ex) {
                                                    ErrorShow(ex.getMessage());
                                                }
                                            } else if (item.equals(HidePhone) && FeatureManager.isHidePhone()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(shmsdel) && FeatureManager.ishowDeletedMessages()) {
                                                checkBox.setChecked(true);
                                            } else if (item.equals(hidestore) && FeatureManager.isDisableStories()) {
                                                checkBox.setChecked(true);
                                            }

                                            checkBox.setText(item);
                                            if (!ActiveTheme.isCurrentThemeDay) {
                                                checkBox.setTextColor(Color.BLACK); // تغيير لون النص إلى الأبيض
                                            } else {
                                                checkBox.setTextColor(Color.WHITE);
                                            }
                                            checkBox.setPadding(10, 10, 10, 10); // إضافة هامش صغير حول النص
                                            checkBox.setTypeface(Typeface.DEFAULT_BOLD); // جعل النص مائلًا قليلاً
                                            checkBoxes.add(checkBox);
                                            layout.addView(checkBox);
                                        }

// إعداد AlertDialog واستخدام setView
                                        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setView", AutomationResolver.ResolverType.Method), layout);


// نحصل على الكلاس الداخلي OnButtonClickListener
                                        Object onDoneListener;
                                        Object onCnelListener;
                                        if (lpparam.packageName.equals("com.tgconnect.android") || lpparam.packageName.equals("org.telegram.messenger.beta")) {
                                            onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickSave(checkBoxes);
                                            onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickOpenUrl(applicationContext,param);
                                        } else {
                                            Class<?> listenerClass = XposedHelpers.findClass(
                                                    AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"),
                                                    lpparam.classLoader
                                            );

// ننشئ كائن من هذا الكلاس باستخدام Proxy (لأنه interface)
                                            onDoneListener = Proxy.newProxyInstance(
                                                    lpparam.classLoader,
                                                    new Class[]{listenerClass},
                                                    (proxy, method, args) -> {
                                                        if (method.getName().equals("onClick")) {
                                                            onClickDialog.onClickSave(checkBoxes);
                                                        }
                                                        //noinspection SuspiciousInvocationHandlerImplementation
                                                        return null;
                                                    }
                                            );
                                            onCnelListener = Proxy.newProxyInstance(
                                                    lpparam.classLoader,
                                                    new Class[]{listenerClass},
                                                    (proxy, method, args) -> {
                                                        if (method.getName().equals("onClick")) {
                                                            onClickDialog.onClickOpenUrl(applicationContext,param);
                                                        }
                                                        //noinspection SuspiciousInvocationHandlerImplementation
                                                        return null;
                                                    }
                                            );

                                        }
                                        // إعداد الزر الموجب
                                        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setPositiveButton", AutomationResolver.ResolverType.Method),
                                                btnsave, onDoneListener
                                        );
                                        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNegativeButton", AutomationResolver.ResolverType.Method),
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


                                        XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "show", AutomationResolver.ResolverType.Method));
                                    }

                                }else {
                                    Utils.log("Not found DrawerLayoutAdapter, " + Utils.issue);
                                }
                            }else {
                                Utils.log("Not found org.telegram.ui.ActionBar.AlertDialog.Builder, " + Utils.issue);
                            }
                        }
                };
                XposedHelpers.findAndHookMethod(
                        launchActivityClass,
                        "lambda$onCreate$6", AutomationResolver.merge(AutomationResolver.resolveObject("obj6"),hook2));
                readFeature();
                downloadSpeed.init();
                OtherFeatures.init();

                /*

                  break;
            case "org.telegram.plus":
                teleplus.Start(lpparam);
                break;
            case "com.tgconnect.android":
                teletg.Start(lpparam);
                //    }else if (lpparam.packageName.equals("com.skyGram.bestt")){
                //     telesky.Start(lpparam);
                break;
            case "com.iMe.android":
            case "com.iMe.android.web":
                teleim.Start(lpparam);
                break;
            case "com.xplus.messenger":
                telexplus.Start(lpparam);
                break;
            case "xyz.nextalone.nagram":
                teleng.Start(lpparam);
                break;
            case "uz.unnarsx.cherrygram":
                telech.Start(lpparam);
                break;
            case "app.nicegram":
                teleni.Start(lpparam);
                break;
            case "com.exteragram.messenger":
                telexe.Start(lpparam);
                break;
            case "tw.nekomimi.nekogram":
                telenk.Start(lpparam);
                break;
            case "org.forkclient.messenger.beta":
            case "org.forkgram.messenger":
                telefo.Start(lpparam);
                break;
            case "nu.gpu.nagram":
                telengX.Start(lpparam);
                break;
            default:
        }

                 */

    }

}


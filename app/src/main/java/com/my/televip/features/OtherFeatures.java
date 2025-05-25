package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.televip.AlertDialog.onClickDialog;
import com.my.televip.MainHook;
import com.my.televip.StrVip;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.xSharedPreferences;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class OtherFeatures extends StrVip {

    public static void init() {

     loadClass.loadObj15();
        if (loadClass.ProfileActivityClass == null){
            loadClass.ProfileActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity"),lpparam.classLoader);
        }
        if (loadClass.BaseFragmentClass == null) {
            loadClass.BaseFragmentClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.BaseFragment"),lpparam.classLoader);
        }
        if (loadClass.ProfileActivityClass != null && loadClass.BaseFragmentClass != null) {
            XposedHelpers.findAndHookMethod(loadClass.ProfileActivityClass, "createActionBarMenu", boolean.class, new AbstractMethodHook() {
                @Override
                protected void afterMethod(MethodHookParam param) throws Throwable {
                    Object profileActivityInstance = param.thisObject;
                    Class<?> longClass = Long.class;

                    Method getMessagesControllerMethod = loadClass.BaseFragmentClass.getDeclaredMethod(AutomationResolver.resolve("BaseFragment", "getMessagesController", AutomationResolver.ResolverType.Method));
                    getMessagesControllerMethod.setAccessible(true);
                    Object messagesController = getMessagesControllerMethod.invoke(profileActivityInstance);

                    if (messagesController != null) {
                        // الحصول على chatId
                        Field chatIdField = loadClass.ProfileActivityClass.getDeclaredField(AutomationResolver.resolve("ProfileActivity", "chatId", AutomationResolver.ResolverType.Field));
                        chatIdField.setAccessible(true);
                        final long chatId = chatIdField.getLong(profileActivityInstance);

                        // تحويل chatId إلى Long
                        Object chatIdObject = longClass.getDeclaredMethod("valueOf", long.class).invoke(null, chatId);

                        // استدعاء getChat
                        Method getChatMethod = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getChat", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("obj2"));
                        getChatMethod.setAccessible(true);
                        Object chat = getChatMethod.invoke(messagesController, chatIdObject);

                        // الحصول على userId
                        Field userIdField = loadClass.ProfileActivityClass.getDeclaredField(AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
                        userIdField.setAccessible(true);
                        final long userId = userIdField.getLong(profileActivityInstance);

                        // تحويل userId إلى Long
                        Object userIdObject = longClass.getDeclaredMethod("valueOf", long.class).invoke(null, userId);

                        // استدعاء getUser
                        Method getUserMethod = messagesController.getClass().getDeclaredMethod(AutomationResolver.resolve("MessagesController", "getUser", AutomationResolver.ResolverType.Method), AutomationResolver.resolveObject("obj2"));
                        getUserMethod.setAccessible(true);
                        Object user = getUserMethod.invoke(messagesController, userIdObject);

                        // الحصول على otherItem
                        Field otherItemField = loadClass.ProfileActivityClass.getDeclaredField(AutomationResolver.resolve("ProfileActivity", "otherItem", AutomationResolver.ResolverType.Field));
                        otherItemField.setAccessible(true);
                        Object otherItem = otherItemField.get(profileActivityInstance);

                        // استدعاء getUserConfig للحصول على clientUserId
                        Method getUserConfigMethod = loadClass.BaseFragmentClass.getDeclaredMethod(AutomationResolver.resolve("BaseFragment", "getUserConfig", AutomationResolver.ResolverType.Method));
                        getUserConfigMethod.setAccessible(true);
                        Object userConfig = getUserConfigMethod.invoke(profileActivityInstance);

                        Method getClientUserIdMethod = userConfig.getClass().getDeclaredMethod(AutomationResolver.resolve("BaseFragment", "getClientUserId", AutomationResolver.ResolverType.Method));
                        getClientUserIdMethod.setAccessible(true);

                        // استدعاء addSubItem على otherItem
                        if (otherItem != null) {
                            Method addSubItemMethod = otherItem.getClass().getDeclaredMethod(
                                    AutomationResolver.resolve("ActionBarMenuItem", "addSubItem", AutomationResolver.ResolverType.Method),
                                    AutomationResolver.resolveObject("obj3")
                            );
                            addSubItemMethod.setAccessible(true);
                            if (loadClass.drawableClass == null) {
                                loadClass.drawableClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.R$drawable"), MainHook.lpparam.classLoader);
                            }
                            if (loadClass.drawableClass != null) {
                                int drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "msg_filled_menu_users");
                                if (loadClass.applicationContext == null) {
                                    loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                                            XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                                            AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                                    );
                                }
                                if (loadClass.applicationContext != null){
                                    if (xSharedPreferences.SharedPre == null){
                                        xSharedPreferences.SharedPre = loadClass.applicationContext.getSharedPreferences(strTelevip,Activity.MODE_PRIVATE);
                                    }
                                    if (xSharedPreferences.SharedPre != null){
                                    if (chat != null) {
                                        xSharedPreferences.SharedPre.edit().putString("id", String.valueOf(chatId)).apply();
                                        //noinspection JavaReflectionInvocation
                                        addSubItemMethod.invoke(otherItem, 45, drawableResource, String.valueOf(chatId));
                                    } else if (user != null) {
                                        xSharedPreferences.SharedPre.edit().putString("id", String.valueOf(userId)).apply();
                                            //noinspection JavaReflectionInvocation
                                            addSubItemMethod.invoke(otherItem, 45, drawableResource, String.valueOf(userId));
                                    }
                                    }
                            }
                            }
                        }
                    }
                }
            });
            final Class<?> profileActivityClass6 = XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.ui.ProfileActivity$6"), lpparam.classLoader);
            XC_MethodHook hook = new AbstractMethodHook() {
                @Override
                protected void afterMethod(MethodHookParam param) {
                    int id = (int) param.args[0];
                    if (loadClass.applicationContext == null) {
                        loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                                XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                                AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                        );
                    }
                    if (loadClass.applicationContext != null) {
                    if (id == 45) {
                        if (xSharedPreferences.SharedPre == null){
                            xSharedPreferences.SharedPre = loadClass.applicationContext.getSharedPreferences(strTelevip,Activity.MODE_PRIVATE);
                        }
                        if (xSharedPreferences.SharedPre != null) {
                            ((ClipboardManager) loadClass.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", xSharedPreferences.SharedPre.getString("id", "")));
                            Toast.makeText(loadClass.applicationContext, xSharedPreferences.SharedPre.getString("id", ""), Toast.LENGTH_LONG).show();
                        }
                        }
                    }

                }
            };
            XposedHelpers.findAndHookMethod(
                    profileActivityClass6,
                    "onItemClick", // اسم الدالة
                    AutomationResolver.merge(AutomationResolver.resolveObject("obj4"), hook));
        }

        if (!lpparam.packageName.equals("xyz.nextalone.nagram") && loadClass.drawableClass == null) {
            loadClass.drawableClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.R$drawable"), lpparam.classLoader);
        }

        if (loadClass.ChatActivityClass == null) {
            loadClass.ChatActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"),lpparam.classLoader);
    }
        if (loadClass.ChatActivityClass != null) {
            Class<?> ChatActivityClass$13 = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity$13"), lpparam.classLoader);
            if (ChatActivityClass$13 != null) {
                XC_MethodHook hook = new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) throws Throwable {
                        Object ChatActivityInstance = param.thisObject;
                        Field headerItemField = loadClass.ChatActivityClass.getDeclaredField(AutomationResolver.resolve("ChatActivity", "headerItem", AutomationResolver.ResolverType.Field));
                        headerItemField.setAccessible(true);
                        Object headerItem = headerItemField.get(ChatActivityInstance);
                        if (headerItem != null) {
                            Method addSubItemMethod = headerItem.getClass().getDeclaredMethod(
                                    AutomationResolver.resolve("ActionBarMenuItem", "lazilyAddSubItem", AutomationResolver.ResolverType.Method),
                                    AutomationResolver.resolveObject("obj16"));
                            addSubItemMethod.setAccessible(true);
                            if (loadClass.drawableClass != null) {
                                int drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "msg_go_up");

                                if (!lpparam.packageName.equals("com.skyGram.bestt") && !lpparam.packageName.equals("uz.unnarsx.cherrygram") &&
                                        !lpparam.packageName.equals("com.iMe.android") && !lpparam.packageName.equals("com.iMe.android.web") && !lpparam.packageName.equals("app.nicegram") && !lpparam.packageName.equals("org.telegram.plus") && !lpparam.packageName.equals("com.xplus.messenger") && !lpparam.packageName.equals("org.forkgram.messenger") && !lpparam.packageName.equals("org.forkclient.messenger.beta")) {
                                    addSubItemMethod.invoke(headerItem, 70, drawableResource, onemsg);
                                }
                                drawableResource = XposedHelpers.getStaticIntField(loadClass.drawableClass, "player_new_order");
                                //noinspection JavaReflectionInvocation
                                addSubItemMethod.invoke(headerItem, 71, drawableResource, tothmsg);
                            }

                        }

                    }
                };
                XposedHelpers.findAndHookMethod(loadClass.ChatActivityClass, "createView", AutomationResolver.merge(AutomationResolver.resolveObject("obj15"), hook));

                XposedHelpers.findAndHookMethod(
                        ChatActivityClass$13,
                        "onItemClick", // اسم الدالة
                        int.class,
                        new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                int id = (int) param.args[0];
                                Object chatActivityInstance = param.thisObject;
                                final Object chatActivity = XposedHelpers.getObjectField(chatActivityInstance, AutomationResolver.resolve("ChatActivity$13","this$0", AutomationResolver.ResolverType.Field));
                                if (id == 70) {
// استدعاء الدالة
                                    XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity","scrollToMessageId", AutomationResolver.ResolverType.Method), 1, 0, true, 0, true, 0);
                                  //  XposedBridge.log("scrollToMessageId is call.");

                                } else if (id == 71) {
                                    final Context applicationContext = (Context) XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("BaseFragment","getContext", AutomationResolver.ResolverType.Method));
                                    if (applicationContext != null) {
                                        Object getResourceProvider = XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity","getResourceProvider", AutomationResolver.ResolverType.Method));

                                        Object alertDialog = XposedHelpers.newInstance(
                                                XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"), lpparam.classLoader),
                                                applicationContext,
                                                getResourceProvider
                                        );
                                        if (alertDialog != null) {
                                            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setTitle", AutomationResolver.ResolverType.Method), inpMsg);
                                            // إنشاء EditText مع تصميم جميل
                                            final EditText editText = new EditText(applicationContext);
                                            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                            ActiveTheme.setActiveTheme();
                                            if (!ActiveTheme.isCurrentThemeDay) {
                                                editText.setTextColor(0xFF000000);
                                                editText.setHintTextColor(0xFF424242);
                                            } else {
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

                                            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setView", AutomationResolver.ResolverType.Method), layout);

// إعداد زر الحفظ

                                            Object onDoneListener;
                                            Object onCnelListener;
                                            if (lpparam.packageName.equals("com.tgconnect.android") || lpparam.packageName.equals("org.telegram.messenger.beta")) {
                                                onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickToMessageId(editText,chatActivity);
                                                onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickDismiss(dialog);

                                            } else {
                                                Class<?> listenerClass = XposedHelpers.findClass(
                                                        AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"),
                                                        lpparam.classLoader
                                                );
                                                onDoneListener = Proxy.newProxyInstance(
                                                        lpparam.classLoader,
                                                        new Class[]{listenerClass},
                                                        (proxy, method, args) -> {
                                                            if (method.getName().equals("onClick")) {
                                                                // هذا AlertDialog
                                                                onClickDialog.onClickToMessageId(editText,chatActivity);
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
                                                                Object object = args[0]; // هذا AlertDialog
                                                                    if (object instanceof DialogInterface) {
                                                                        DialogInterface dialog = (DialogInterface) object;
                                                                        onClickDialog.onClickDismiss(dialog);
                                                                    }
                                                            }
                                                            //noinspection SuspiciousInvocationHandlerImplementation
                                                            return null;
                                                        }
                                                );
                                            }
                                            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setPositiveButton", AutomationResolver.ResolverType.Method), serc, onDoneListener
                                            );

                                            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNegativeButton", AutomationResolver.ResolverType.Method),
                                                    syno,
                                                    onCnelListener
                                            );

                                            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "show", AutomationResolver.ResolverType.Method));
                                        } else {
                                        Utils.log("Not found org.telegram.ui.ActionBar.AlertDialog.Builder, " + Utils.issue);
                                    }
                                    }
                                }
                            }
                        });
            }
        }
        if (loadClass.ProfileActivityClass == null){
            loadClass.ProfileActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity"),lpparam.classLoader);
        }
        if (loadClass.ProfileActivityClass != null) {
            XC_MethodHook hook = new AbstractMethodHook() {
                @Override
                protected void afterMethod(MethodHookParam param) {
                    if (loadClass.applicationContext == null) {
                        loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                                XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                                AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
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

                                            if (user != null && chatId == 0) {
                                                // استدعاء getUserName
                                                Class<?> userClass = lpparam.classLoader.loadClass("org.telegram.tgnet.TLRPC$User");
                                                Method getUserNameMethod = userObjectClass.getDeclaredMethod("getUserName", userClass);
                                                getUserNameMethod.setAccessible(true);
                                                String userName = (String) getUserNameMethod.invoke(null, user);
                                                if (userName != null) {
                                                    String copnames = copname + userName + copname2;
                                                    if (loadClass.applicationContext != null) {
                                                        ((ClipboardManager) loadClass.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", userName));
                                                        Toast.makeText(loadClass.applicationContext, copnames, Toast.LENGTH_LONG).show();
                                                    }
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

            };

            XposedHelpers.findAndHookMethod(loadClass.ProfileActivityClass,"createView",AutomationResolver.merge(AutomationResolver.resolveObject("obj15"),hook));
        }

    }

}

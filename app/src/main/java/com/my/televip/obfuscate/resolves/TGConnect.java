package com.my.televip.obfuscate.resolves;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.LoaderParameter;
import com.my.televip.MainHook;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XposedHelpers;

public class TGConnect {
    private static final List<ClassInfo> classList = new ArrayList<>();
    private static final List<FieldInfo> fieldList = new ArrayList<>();
    private static final List<MethodInfo> methodList = new ArrayList<>();

    static {
        classList.add(new ClassInfo("org.telegram.messenger.ApplicationLoader", "org.telegram.messenger.ApplicationLoader"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationsController", "org.telegram.messenger.NotificationsController"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationCenter", "org.telegram.messenger.NotificationCenter"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController", "org.telegram.messenger.MessagesController"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesStorage", "org.telegram.messenger.MessagesStorage"));
        classList.add(new ClassInfo("org.telegram.messenger.MessageObject", "org.telegram.messenger.MessageObject"));
        classList.add(new ClassInfo("org.telegram.messenger.UserConfig", "org.telegram.messenger.UserConfig"));
        classList.add(new ClassInfo("org.telegram.ui.Cells.ChatMessageCell", "org.telegram.ui.Cells.ChatMessageCell"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.Theme", "org.telegram.ui.ActionBar.Theme"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity", "org.telegram.ui.ChatActivity"));
        classList.add(new ClassInfo("org.telegram.messenger.AndroidUtilities", "org.telegram.messenger.AndroidUtilities"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Peer", "org.telegram.tgnet.TLRPC$Peer"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Message", "org.telegram.tgnet.TLRPC$Message"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_updateDeleteMessages", "org.telegram.tgnet.TLRPC$TL_updateDeleteMessages"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_updateDeleteChannelMessages", "org.telegram.tgnet.TLRPC$TL_updateDeleteChannelMessages"));
        classList.add(new ClassInfo("org.telegram.ui.ProfileActivity", "org.telegram.ui.ProfileActivity"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.BaseFragment", "org.telegram.ui.ActionBar.BaseFragment"));
        classList.add(new ClassInfo("org.telegram.ui.ProfileActivity$6", "org.telegram.ui.ProfileActivity$6"));
        classList.add(new ClassInfo("org.telegram.ui.Adapters.DrawerLayoutAdapter", "org.telegram.ui.Adapters.DrawerLayoutAdapter"));
        classList.add(new ClassInfo("org.telegram.messenger.R$drawable", "org.telegram.messenger.R$drawable"));
        classList.add(new ClassInfo("org.telegram.ui.LaunchActivity", "org.telegram.ui.LaunchActivity"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.AlertDialog.Builder", "org.telegram.ui.ActionBar.AlertDialog.Builder"));
        classList.add(new ClassInfo("org.telegram.messenger.browser.Browser", "org.telegram.messenger.browser.Browser"));
        classList.add(new ClassInfo("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener", "org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"));
        classList.add(new ClassInfo("org.telegram.messenger.MessagesController$ReadTask", "org.telegram.messenger.MessagesController$ReadTask"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_stories$StoryItem", "org.telegram.tgnet.tl.TL_stories$StoryItem"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_stories$PeerStories", "org.telegram.tgnet.tl.TL_stories$PeerStories"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity$ChatActivityEnterViewDelegate", "org.telegram.ui.ChatActivity$ChatActivityEnterViewDelegate"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.StoriesController", "org.telegram.ui.Stories.StoriesController"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$Chat", "org.telegram.tgnet.TLRPC$Chat"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder", "org.telegram.ui.Stories.PeerStoriesView$StoryItemHolder"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLObject", "org.telegram.tgnet.TLObject"));
        classList.add(new ClassInfo("org.telegram.tgnet.RequestDelegate", "org.telegram.tgnet.RequestDelegate"));
        classList.add(new ClassInfo("org.telegram.tgnet.RequestDelegateTimestamp", "org.telegram.tgnet.RequestDelegateTimestamp"));
        classList.add(new ClassInfo("org.telegram.tgnet.QuickAckDelegate", "org.telegram.tgnet.QuickAckDelegate"));
        classList.add(new ClassInfo("org.telegram.tgnet.WriteToSocketDelegate", "org.telegram.tgnet.WriteToSocketDelegate"));
        classList.add(new ClassInfo("org.telegram.tgnet.ConnectionsManager", "org.telegram.tgnet.ConnectionsManager"));
        classList.add(new ClassInfo("org.telegram.tgnet.tl.TL_account$updateStatus", "org.telegram.tgnet.tl.TL_account$updateStatus"));
        classList.add(new ClassInfo("org.telegram.ui.PhotoViewer$PhotoViewerProvider", "org.telegram.ui.PhotoViewer$PhotoViewerProvider"));
        classList.add(new ClassInfo("org.telegram.ui.SecretMediaViewer", "org.telegram.ui.SecretMediaViewer"));
        classList.add(new ClassInfo("org.telegram.ui.Stories.StoriesController", "org.telegram.ui.Stories.StoriesController"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$User", "org.telegram.tgnet.TLRPC$User"));
        classList.add(new ClassInfo("org.telegram.messenger.FileLoadOperation", "org.telegram.messenger.FileLoadOperation"));
        classList.add(new ClassInfo("org.telegram.ui.ChatActivity$13", "org.telegram.ui.ChatActivity$13"));
        classList.add(new ClassInfo("org.telegram.messenger.DownloadController", "org.telegram.messenger.DownloadController"));
        classList.add(new ClassInfo("org.telegram.messenger.BaseController", "org.telegram.messenger.BaseController"));
        classList.add(new ClassInfo("org.telegram.messenger.UserObject", "org.telegram.messenger.UserObject"));
        classList.add(new ClassInfo("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item", "org.telegram.ui.Adapters.DrawerLayoutAdapter$Item"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_account_updateStatus", "org.telegram.tgnet.TLRPC$TL_account_updateStatus"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$EncryptedChat", "org.telegram.tgnet.TLRPC$EncryptedChat"));
        classList.add(new ClassInfo("org.telegram.tgnet.TLObject", "org.telegram.tgnet.TLObject"));
        classList.add(new ClassInfo("org.telegram.messenger.NotificationCenter", "org.telegram.messenger.NotificationCenter"));

        fieldList.add(new FieldInfo("UserConfig", "selectedAccount", "selectedAccount"));
        fieldList.add(new FieldInfo("TLRPC$User", "id", "id"));
        fieldList.add(new FieldInfo("TLRPC$Peer", "channel_id", "channel_id"));
        fieldList.add(new FieldInfo("TLRPC$Message", "id", "id"));
        fieldList.add(new FieldInfo("TLRPC$Message", "flags", "flags"));
        fieldList.add(new FieldInfo("TLRPC$Message", "peer_id", "peer_id"));
        fieldList.add(new FieldInfo("TLRPC$TL_updateDeleteMessages", "messages", "messages"));
        fieldList.add(new FieldInfo("TLRPC$TL_updateDeleteChannelMessages", "channel_id", "channel_id"));
        fieldList.add(new FieldInfo("TLRPC$TL_updateDeleteChannelMessages", "messages", "messages"));
        fieldList.add(new FieldInfo("Theme", "chat_timePaint", "chat_timePaint"));
        fieldList.add(new FieldInfo("MessagesController", "dialogMessagesByIds", "dialogMessagesByIds"));
        fieldList.add(new FieldInfo("MessagesController", "dialogMessage", "dialogMessage"));
        fieldList.add(new FieldInfo("NotificationCenter", "messagesDeleted", "messagesDeleted"));
        fieldList.add(new FieldInfo("AndroidUtilities", "typefaceCache", "typefaceCache"));
        fieldList.add(new FieldInfo("ProfileActivity", "chatId", "chatId"));
        fieldList.add(new FieldInfo("ProfileActivity", "userId", "userId"));
        fieldList.add(new FieldInfo("ProfileActivity", "otherItem", "otherItem"));
        fieldList.add(new FieldInfo("ApplicationLoader", "applicationContext", "applicationContext"));
        fieldList.add(new FieldInfo("DrawerLayoutAdapter", "items", "items"));
        fieldList.add(new FieldInfo("LaunchActivity", "drawerLayoutAdapter", "drawerLayoutAdapter"));
        fieldList.add(new FieldInfo("MessagesController$ReadTask", "dialogId", "dialogId"));
        fieldList.add(new FieldInfo("TL_account$updateStatus", "offline", "offline"));
        fieldList.add(new FieldInfo("ProfileActivity", "onlineTextView", "onlineTextView"));
        fieldList.add(new FieldInfo("SecretMediaViewer", "onClose", "onClose"));
        fieldList.add(new FieldInfo("MessageObject", "messageOwner", "messageOwner"));
        fieldList.add(new FieldInfo("TLRPC$Message", "ttl", "ttl"));
        fieldList.add(new FieldInfo("User", "phone", "phone"));
        fieldList.add(new FieldInfo("FileLoadOperation", "downloadChunkSizeBig", "downloadChunkSizeBig"));
        fieldList.add(new FieldInfo("FileLoadOperation", "maxDownloadRequests", "maxDownloadRequests"));
        fieldList.add(new FieldInfo("FileLoadOperation", "maxDownloadRequestsBig", "maxDownloadRequestsBig"));
        fieldList.add(new FieldInfo("FileLoadOperation", "maxCdnParts", "maxCdnParts"));
        fieldList.add(new FieldInfo("ChatActivity$13", "this$0", "this$0"));
        fieldList.add(new FieldInfo("ChatActivity", "headerItem", "headerItem"));
        fieldList.add(new FieldInfo("ProfileActivity", "nameTextView", "nameTextView"));
        fieldList.add(new FieldInfo("UserConfig", "phone", "phone"));
        fieldList.add(new FieldInfo("LaunchActivity", "drawerLayoutContainer", "drawerLayoutContainer"));
        fieldList.add(new FieldInfo("NotificationCenter", "messagesDeleted", "messagesDeleted"));

        methodList.add(new MethodInfo("NotificationCenter", "postNotificationName", "postNotificationName"));
        methodList.add(new MethodInfo("MessagesStorage", "markMessagesAsDeletedInternal", "markMessagesAsDeletedInternal"));
        methodList.add(new MethodInfo("MessagesStorage", "updateDialogsWithDeletedMessagesInternal", "updateDialogsWithDeletedMessagesInternal"));
        methodList.add(new MethodInfo("MessagesStorage", "getDatabase", "getDatabase"));
        methodList.add(new MethodInfo("MessageObject", "updateMessageText", "updateMessageText"));
        methodList.add(new MethodInfo("MessageObject", "canForwardMessage", "canForwardMessage"));
        methodList.add(new MethodInfo("MessageObject", "getDialogId", "getDialogId"));
        methodList.add(new MethodInfo("MessagesController", "isChatNoForwards", "isChatNoForwards"));
        methodList.add(new MethodInfo("MessagesController", "markDialogMessageAsDeleted", "markDialogMessageAsDeleted"));
        methodList.add(new MethodInfo("MessagesController", "deleteMessages", "deleteMessages"));
        methodList.add(new MethodInfo("MessagesController", "getSponsoredMessages", "getSponsoredMessages"));
        methodList.add(new MethodInfo("ChatMessageCell", "measureTime", "measureTime"));
        methodList.add(new MethodInfo("ChatMessageCell", "setVisibleOnScreen", "setVisibleOnScreen"));
        methodList.add(new MethodInfo("UserConfig", "getInstance", "getInstance"));
        methodList.add(new MethodInfo("NotificationsController", "removeNotificationsForDialog", "removeNotificationsForDialog"));
        methodList.add(new MethodInfo("NotificationsController", "removeDeletedMessagesFromNotifications", "removeDeletedMessagesFromNotifications"));
        methodList.add(new MethodInfo("ChatActivity", "addSponsoredMessages", "addSponsoredMessages"));
        methodList.add(new MethodInfo("ChatActivity", "hasSelectedNoforwardsMessage", "hasSelectedNoforwardsMessage"));
        methodList.add(new MethodInfo("AndroidUtilities", "getTypeface", "getTypeface"));
        methodList.add(new MethodInfo("SQLiteDatabase", "queryFinalized", "queryFinalized"));
        methodList.add(new MethodInfo("SQLiteDatabase", "executeFast", "executeFast"));
        methodList.add(new MethodInfo("SQLiteCursor", "next", "next"));
        methodList.add(new MethodInfo("SQLiteCursor", "byteBufferValue", "byteBufferValue"));
        methodList.add(new MethodInfo("SQLiteCursor", "intValue", "intValue"));
        methodList.add(new MethodInfo("SQLiteCursor", "longValue", "longValue"));
        methodList.add(new MethodInfo("SQLiteCursor", "dispose", "dispose"));
        methodList.add(new MethodInfo("SQLitePreparedStatement", "dispose", "dispose"));
        methodList.add(new MethodInfo("SQLitePreparedStatement", "requery", "requery"));
        methodList.add(new MethodInfo("SQLitePreparedStatement", "bindByteBuffer", "bindByteBuffer"));
        methodList.add(new MethodInfo("SQLitePreparedStatement", "bindLong", "bindLong"));
        methodList.add(new MethodInfo("SQLitePreparedStatement", "bindInteger", "bindInteger"));
        methodList.add(new MethodInfo("SQLitePreparedStatement", "step", "step"));
        methodList.add(new MethodInfo("LongSparseArray", "get", "get"));
        methodList.add(new MethodInfo("LaunchActivity", "lambda$onCreate$6", "lambda$onCreate$6"));
        methodList.add(new MethodInfo("BaseFragment", "getMessagesController", "getMessagesController"));
        methodList.add(new MethodInfo("MessagesController", "getChat", "getChat"));
        methodList.add(new MethodInfo("MessagesController", "getUser", "getUser"));
        methodList.add(new MethodInfo("BaseFragment", "getUserConfig", "getUserConfig"));
        methodList.add(new MethodInfo("BaseFragment", "getClientUserId", "getClientUserId"));
        methodList.add(new MethodInfo("ActionBarMenuItem", "addSubItem", "addSubItem"));
        methodList.add(new MethodInfo("Theme", "getEventType", "getEventType"));
        methodList.add(new MethodInfo("Theme", "getActiveTheme", "getActiveTheme"));
        methodList.add(new MethodInfo("DrawerLayoutAdapter", "getId", "getId"));
        methodList.add(new MethodInfo("AlertDialog", "setTitle", "setTitle"));
        methodList.add(new MethodInfo("AlertDialog", "setView", "setView"));
        methodList.add(new MethodInfo("AlertDialog", "setPositiveButton", "setPositiveButton"));
        methodList.add(new MethodInfo("AlertDialog", "setNegativeButton", "setNegativeButton"));
        methodList.add(new MethodInfo("AlertDialog", "show", "show"));
        methodList.add(new MethodInfo("Browser", "openUrl", "openUrl"));
        methodList.add(new MethodInfo("DrawerLayoutAdapter", "closeDrawer", "closeDrawer"));
        methodList.add(new MethodInfo("BaseFragment", "getUserConfig", "getUserConfig"));
        methodList.add(new MethodInfo("UserConfig", "getClientUserId", "getClientUserId"));
        methodList.add(new MethodInfo("SimpleTextView", "setText", "setText"));
        methodList.add(new MethodInfo("ChatActivity", "scrollToMessageId", "scrollToMessageId"));
        methodList.add(new MethodInfo("ChatActivity", "getResourceProvider", "getResourceProvider"));
        methodList.add(new MethodInfo("BaseFragment", "getContext", "getContext"));
        methodList.add(new MethodInfo("AlertDialog", "dismiss", "dismiss"));
        methodList.add(new MethodInfo("ActionBarMenuItem", "lazilyAddSubItem", "lazilyAddSubItem"));
        methodList.add(new MethodInfo("DownloadController", "canDownloadMedia", "canDownloadMedia"));
        methodList.add(new MethodInfo("SimpleTextView", "setOnClickListener", "setOnClickListener"));
        methodList.add(new MethodInfo("UserObject", "getUserName", "getUserName"));
        methodList.add(new MethodInfo("DrawerLayoutAdapter", "resetItems", "resetItems"));
        methodList.add(new MethodInfo("ProfileActivity", "createView", "createView"));
        methodList.add(new MethodInfo("ProfileActivity", "createActionBarMenu", "createActionBarMenu"));
        methodList.add(new MethodInfo("ProfileActivity", "onItemClick", "onItemClick"));
        methodList.add(new MethodInfo("ChatActivity", "createView", "createView"));
        methodList.add(new MethodInfo("PeerStoriesView$StoryItemHolder", "allowScreenshots", "allowScreenshots"));
        methodList.add(new MethodInfo("MessagesController", "storiesEnabled", "storiesEnabled"));
        methodList.add(new MethodInfo("MessagesController", "storyEntitiesAllowed", "storyEntitiesAllowed"));
        methodList.add(new MethodInfo("MessagesController", "hasStories", "hasStories"));
        methodList.add(new MethodInfo("FileLoadOperation", "updateParams", "updateParams"));
        methodList.add(new MethodInfo("ConnectionsManager", "sendRequestInternal", "sendRequestInternal"));
        methodList.add(new MethodInfo("ProfileActivity", "updateProfileData", "updateProfileData"));
        methodList.add(new MethodInfo("UserConfig", "getClientUserId", "getClientUserId"));
        methodList.add(new MethodInfo("BaseController", "getUserConfig", "getUserConfig"));
        methodList.add(new MethodInfo("MessagesController", "completeReadTask", "completeReadTask"));
        methodList.add(new MethodInfo("ChatActivity$ChatActivityEnterViewDelegate", "needSendTyping", "needSendTyping"));
        methodList.add(new MethodInfo("StoriesController", "markStoryAsRead", "markStoryAsRead"));
        methodList.add(new MethodInfo("ChatActivity", "sendSecretMessageRead", "sendSecretMessageRead"));
        methodList.add(new MethodInfo("ChatActivity", "sendSecretMediaDelete", "sendSecretMediaDelete"));
        methodList.add(new MethodInfo("SecretMediaViewer", "openMedia", "openMedia"));
        methodList.add(new MethodInfo("SecretMediaViewer", "closePhoto", "closePhoto"));
        methodList.add(new MethodInfo("UserConfig", "isPremium", "isPremium"));
        methodList.add(new MethodInfo("MessagesController", "isChatNoForwards", "isChatNoForwards"));
        methodList.add(new MethodInfo("MessagesController", "deleteMessages", "deleteMessages"));
        methodList.add(new MethodInfo("MessagesStorage", "markMessagesAsDeleted", "markMessagesAsDeleted"));
        methodList.add(new MethodInfo("NotificationCenter", "postNotificationName", "postNotificationName"));

        ParameterResolver.register("para1",new Class[]{Long.class});
        ParameterResolver.register("para2",new Class[]{int.class, int.class, CharSequence.class});
        ParameterResolver.register("para3",new Class[]{int.class});
        ParameterResolver.register("para4",new Class[]{int.class, CharSequence.class, int.class});
        ParameterResolver.register("para5",new Class[]{android.view.View.class,int.class, float.class, float.class});
        ParameterResolver.register("para6",new Class[]{boolean.class, boolean.class});
        ParameterResolver.register("para7",new Class[]{int.class, int.class, CharSequence.class});
        ParameterResolver.register("para8",new Class[]{boolean.class});
        ParameterResolver.register("para9",new Class[]{long.class, java.util.ArrayList.class, boolean.class, boolean.class, int.class, int.class,});
        ParameterResolver.register("para10",new Class[]{int.class, Object[].class});
    }

    public static class ClassResolver
    {
        public static String resolve(String name) {
            for (ClassInfo info : classList)
                if (info.getOriginal().equals(name))
                    return info.getResolved();

            return null;
        }

        public static boolean has(String name)
        {
            boolean has = false;
            for (ClassInfo info : classList) {
                if (info.getOriginal().equals(name)) {
                    has = true;
                    break;
                }
            }
            return has;
        }
    }

    public static class FieldResolver
    {
        public static String resolve(String className, String name) {
            for (FieldInfo info : fieldList)
                if (info.getClassName().equals(className) && info.getOriginal().equals(name))
                    return info.getResolved();

            return null;
        }

        public static boolean has(String className, String name)
        {
            boolean has = false;
            for (FieldInfo info : fieldList) {
                if (info.getClassName().equals(className) && info.getOriginal().equals(name)) {
                    has = true;
                    break;
                }
            }
            return has;
        }
    }

    public static class MethodResolver
    {
        public static String resolve(String className, String name) {
            for (MethodInfo info : methodList)
                if (info.getClassName().equals(className) && info.getOriginal().equals(name))
                    return info.getResolved();

            return null;
        }

        public static boolean has(String className, String name)
        {
            boolean has = false;
            for (MethodInfo info : methodList) {
                if (info.getClassName().equals(className) && info.getOriginal().equals(name)) {
                    has = true;
                    break;
                }
            }
            return has;
        }
    }
    public static class ParameterResolver
    {
        static Map<String,Class<?>[]> objectList = new HashMap<>();

        public static void register(String name,  Class<?>[] classes){
            objectList.put(name,classes);
        }

        public static Class<?>[] resolve(String name) {
            return objectList.get(name);
        }

        public static boolean has(String name)
        {
            boolean has = false;
            Class<?>[] classes = objectList.get(name);
            if (classes != null){
                has = true;
            }
            return has;
        }
    }
    public static class loadParameter implements LoaderParameter {

        public void loadParameter1() {
            Class<?> classStories$StoryItem = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$StoryItem"), MainHook.lpparam.classLoader);
            Class<?> classsStories$PeerStories = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.tl.TL_stories$PeerStories"), MainHook.lpparam.classLoader);
            ParameterResolver.register("Parameter1", new Class[]{classsStories$PeerStories, classStories$StoryItem, boolean.class});
        }
        public void loadParameter2() {
            Class<?> readTaskClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController$ReadTask"), lpparam.classLoader);
            ParameterResolver.register("Parameter2", new Class[]{readTaskClass});

        }
        public void loadParameter3() {
            Class<?> TLRPC$ChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Chat"), lpparam.classLoader);
            ParameterResolver.register("Parameter3", new Class[]{TLRPC$ChatClass});

        }
        public void loadParameter4() {
            Class<?> tlObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
            Class<?> requestDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegate"), lpparam.classLoader);
            Class<?> requestDelegateTimestampClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.RequestDelegateTimestamp"), lpparam.classLoader);
            Class<?> quickAckDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.QuickAckDelegate"), lpparam.classLoader);
            Class<?> writeToSocketDelegateClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.WriteToSocketDelegate"), lpparam.classLoader);

            ParameterResolver.register("Parameter4", new Class[]{tlObjectClass, requestDelegateClass, requestDelegateTimestampClass, quickAckDelegateClass, writeToSocketDelegateClass, int.class, int.class, int.class, boolean.class, int.class});

        }
        public void loadParameter5() {
            if (com.my.televip.loadClass.MessageObjectClass == null) {
                com.my.televip.loadClass.MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
            }
            ParameterResolver.register("Parameter5", new Class[]{com.my.televip.loadClass.MessageObjectClass, boolean.class});
        }
        public void loadParameter6() {
            if (com.my.televip.loadClass.MessageObjectClass == null) {
                com.my.televip.loadClass.MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
            }
            ParameterResolver.register("Parameter6", new Class[]{com.my.televip.loadClass.MessageObjectClass});
        }
        public void loadParameter7() {
            if (com.my.televip.loadClass.MessageObjectClass == null) {
                com.my.televip.loadClass.MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
            }
            Class<?> photoViewerproviderClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.PhotoViewer$PhotoViewerProvider"), lpparam.classLoader);
            ParameterResolver.register("Parameter7", new Class[]{com.my.televip.loadClass.MessageObjectClass, photoViewerproviderClass, Runnable.class, Runnable.class});
        }
        public void loadParameter8() {
            Class<?> Userlass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$User"), lpparam.classLoader);
            ParameterResolver.register("Parameter8", new Class[]{Userlass});
        }
        public void loadParameter9() {
            Class<?> conClass = XposedHelpers.findClassIfExists("android.content.Context", lpparam.classLoader);
            ParameterResolver.register("Parameter9", new Class[]{conClass});
        }
        public void loadParameter10() {}

        public void loadParameter11() {
            Class<?> EncryptedChatClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$EncryptedChat"), lpparam.classLoader);
            Class<?> TLObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.tgnet.TLObject"), lpparam.classLoader);
            ParameterResolver.register("Parameter11", new Class[]{java.util.ArrayList.class,
                    java.util.ArrayList.class,
                    EncryptedChatClass,
                    long.class,
                    boolean.class,
                    int.class,
                    boolean.class,
                    long.class,
                    TLObjectClass,
                    int.class});
        }
    }
}

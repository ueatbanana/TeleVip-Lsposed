package com.my.televip.obfuscate.resolves;

import com.my.televip.obfuscate.struct.ClassInfo;
import com.my.televip.obfuscate.struct.FieldInfo;
import com.my.televip.obfuscate.struct.MethodInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;

public class Telegram {
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
        classList.add(new ClassInfo("org.telegram.tgnet.TLRPC$TL_updateDeleteMessages", "rg.telegram.tgnet.TLRPC$TL_updateDeleteMessages"));
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

        ObjectResolver.register("obj2",new Class[]{Long.class});
        ObjectResolver.register("obj3",new Class[]{int.class, int.class, CharSequence.class});
        ObjectResolver.register("obj4",new Class[]{int.class});
        ObjectResolver.register("obj5",new Class[]{int.class, CharSequence.class, int.class});
        ObjectResolver.register("obj6",new Class[]{android.view.View.class,int.class, float.class, float.class});
        ObjectResolver.register("obj13",new Class[]{boolean.class, boolean.class});
        ObjectResolver.register("obj16",new Class[]{int.class, int.class, CharSequence.class});
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
    public static class ObjectResolver
    {
        static Map<String,Class<?>[]> objectList = new HashMap<>();

        public static void register(String name,  Class<?>[] classes){
            objectList.put(name,classes);
        }

        public static Class<?>[] resolve(String name) {
            Class<?>[] classes = objectList.get(name);
            //noinspection RedundantIfStatement
            if (classes != null){ return classes; }

            return null;
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
        public static Object[] merge(Class<?>[] classes, XC_MethodHook hook) {
            Object[] result = new Object[classes.length + 1];
            System.arraycopy(classes, 0, result, 0, classes.length);
            result[classes.length] = hook;
            return result;
        }
    }
}

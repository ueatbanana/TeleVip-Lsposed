package com.my.televip.features;

import com.my.televip.xSharedPreferences;

public class FeatureManager {
    public static boolean isTelePremium() {
        return xSharedPreferences.xSharedPre.contains("prem");
    }
    public static boolean isHideSeenPrivate() {
        return xSharedPreferences.xSharedPre.contains("noRead");
    }
    public static boolean isHideSeenGroup() {
        return xSharedPreferences.xSharedPre.contains("noRead2");
    }
    public static boolean isNoStoryRead() {
        return xSharedPreferences.xSharedPre.contains("noStoryRead");
    }
    public static boolean isHideTyping() {
        return xSharedPreferences.xSharedPre.contains("NoTyping");
    }
    public static boolean isUnlockChannelFeature() {
        return xSharedPreferences.xSharedPre.contains("usefolow");
    }
    public static boolean isAllowSaveToGallery() {
        return xSharedPreferences.xSharedPre.contains("allowShare");
    }
    public static boolean isHideOnline() {
        return xSharedPreferences.xSharedPre.contains("HideOnline");
    }
    public static boolean isPreventMedia() {
        return xSharedPreferences.xSharedPre.contains("PreventMedia");
    }
    public static boolean isHidePhone() {
        return xSharedPreferences.xSharedPre.contains("HidePhone");
    }
    public static boolean ishowDeletedMessages() {
        return xSharedPreferences.xSharedPre.contains("shmsdel");
    }
    public static boolean isDisableStories() {
        return xSharedPreferences.xSharedPre.contains("hidestore");
    }
}

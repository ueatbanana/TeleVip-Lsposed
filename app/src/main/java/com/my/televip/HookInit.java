package com.my.televip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.features.AllowMoveAllChatFolder;
import com.my.televip.features.AntiAntiForward;
import com.my.televip.features.FakePremium;
import com.my.televip.features.HideStories;
import com.my.televip.features.NEWAntiRecall;
import com.my.televip.features.NoSponsoredMessages;
import com.my.televip.features.ProhibitChannelSwitching;
import com.my.televip.features.UseSystemTypeface;

public class HookInit {
    private static final List<String> hookPackages = Arrays.asList("org.telegram.messenger", "org.telegram.messenger.web", "org.telegram.messenger.beta", "org.telegram.plus", "org.telegram.mdgram",
            "tw.nekomimi.nekogram",
            "com.cool2645.nekolite",
            "com.exteragram.messenger",
            "org.forkgram.messenger",
            "org.forkclient.messenger",
            "org.forkclient.messenger.beta",
            "me.onlyfire.yukigram.beta",
            "com.iMe.android.web",
            "com.radolyn.ayugram",
            "it.octogram.android",
            "xyz.nextalone.nnngram",
            "it.belloworld.mercurygram");
    private static final List<String> notNeedHideStories = Arrays.asList("tw.nekomimi.nekogram", "com.exteragram.messenger");
    private static final List<String> hookPackagesCustomization = Arrays.asList("xyz.nextalone.nagram",
            "nekox.messenger", "com.xtaolabs.pagergram", "nekox.messenger.broken");
    public static final boolean DEBUG_MODE = false;

    public static final List<String> getHookPackages()
    {
        List<String> hookPackagesLocal = new ArrayList<>(hookPackages);
        hookPackagesLocal.addAll(hookPackagesCustomization);
        return hookPackagesLocal;
    }

    private static boolean onlyNeedAR(String pkgName)
    {
        return hookPackagesCustomization.contains(pkgName);
    }

   
    public static void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
            Utils.pkgName = lpparam.packageName;
            ClassLoader classLoader = lpparam.classLoader;
            ApplicationLoaderHook.init(classLoader);
                NEWAntiRecall.initUI(classLoader);
                if (lpparam.packageName.equals("com.skyGram.bestt")){    
                NEWAntiRecall.initProcessing2(classLoader);
                }else {
                NEWAntiRecall.initProcessing(classLoader);
                }
                NEWAntiRecall.init(classLoader);
    }
}

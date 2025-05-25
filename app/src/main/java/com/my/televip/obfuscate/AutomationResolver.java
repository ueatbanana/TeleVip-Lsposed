package com.my.televip.obfuscate;



import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.obfuscate.resolves.Telegram;
import com.my.televip.obfuscate.resolves.TelegramWeb;

import de.robv.android.xposed.XC_MethodHook;

public class AutomationResolver {
    public static String resolve(String className, String pkgName)
    {
         if (ClientChecker.check(ClientChecker.ClientType.Telegram, pkgName))
        {
            if (Telegram.ClassResolver.has(className))
                return Telegram.ClassResolver.resolve(className);
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, pkgName))
         {
             if (TelegramWeb.ClassResolver.has(className))
                 return TelegramWeb.ClassResolver.resolve(className);
         }

        return className;
    }

    public static Class<?>[] resolveObject(String name, String pkgName)
    {
       if (ClientChecker.check(ClientChecker.ClientType.Telegram, pkgName))
        {
            if (Telegram.ObjectResolver.has(name)) {
                return Telegram.ObjectResolver.resolve(name);
            }
        } else  if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, pkgName))
       {
           if (TelegramWeb.ObjectResolver.has(name)) {
               return TelegramWeb.ObjectResolver.resolve(name);
           }
       }

        return null;
    }

    public static String resolve(String className, String name, ResolverType type, String pkgName)
    {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, pkgName))
    {
        if (type == ResolverType.Field)
        {
            if (Telegram.FieldResolver.has(className, name))
                return Telegram.FieldResolver.resolve(className, name);
        }
        else if (type == ResolverType.Method)
        {
            if (Telegram.MethodResolver.has(className, name))
                return Telegram.MethodResolver.resolve(className, name);
        }
    } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, pkgName))
       {
           if (type == ResolverType.Field)
           {
               if (TelegramWeb.FieldResolver.has(className, name))
                   return TelegramWeb.FieldResolver.resolve(className, name);
           }
           else if (type == ResolverType.Method)
           {
               if (TelegramWeb.MethodResolver.has(className, name))
                   return TelegramWeb.MethodResolver.resolve(className, name);
           }
       }



        return name;
    }



    public static String resolve(String className)
    {
        return resolve(className, Utils.pkgName);
    }
    public static Class<?>[] resolveObject(String name)
    {
        return resolveObject(name, Utils.pkgName);
    }

    public static String resolve(String className, String name, ResolverType type)
    {
        return resolve(className, name, type, Utils.pkgName);
    }
    public static Object[] merge(Class<?>[] classes, XC_MethodHook hook)
    {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, Utils.pkgName))
        {
                return Telegram.ObjectResolver.merge(classes,hook);

        } else  if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, Utils.pkgName))
        {
            return TelegramWeb.ObjectResolver.merge(classes,hook);

        }
        return null;

    }

    public enum ResolverType
    {
        Field,
        Method
    }
}

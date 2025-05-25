package com.my.televip;

import java.util.Arrays;

public class ClientChecker {
    public static boolean check(ClientType client, String pkgName)
    {
        return Arrays.asList(client.getPackageNames()).contains(pkgName);
    }

    public static boolean check(ClientType client)
    {
        return check(client, Utils.pkgName);
    }

    public enum ClientType {
        Telegram("org.telegram.messenger"),
        TelegramWeb("org.telegram.messenger.web"),
        Nekogram("org.telegr]");

        final String[] packageNames;

        ClientType(String packageName)
        {
            this.packageNames = new String[]{ packageName };
        }

        public String[] getPackageNames()
        {
            return packageNames;
        }
    }
}

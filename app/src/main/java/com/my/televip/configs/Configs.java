package com.my.televip.configs;

public class Configs {
    private static boolean antiRecall = true;
    private static final String antiRecallText = "";
    private static final boolean antiRecallTextColorful = true;
    private static final int antiRecallTextRed = 255;


    public static boolean isAntiRecall() {
        return antiRecall;
    }

    public static String getAntiRecallText() {
        return antiRecallText;
    }

    public static boolean isAntiRecallTextColorful() {
        return antiRecallTextColorful;
    }

    public static int getAntiRecallTextRed() {
        return Math.max(Math.min(255, antiRecallTextRed), 0);
    }

    public static int getAntiRecallTextGreen() {
        return 0;
    }

    public static int getAntiRecallTextBlue() {
        return 0;
    }

    public static void setAntiRecall(boolean value) {
        antiRecall = value;
        ConfigManager.save();
    }

}

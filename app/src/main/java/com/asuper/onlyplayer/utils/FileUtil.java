package com.asuper.onlyplayer.utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Super on 2016/10/21.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";
    public static final String[] VIDEO_EXTENSIONS = {"3gp", "wmv", "ts", "3gp2", "rmvb",
            "mp4","mov", "m4v", "avi", "3gpp", "3gpp2", "mkv", "flv", "divx", "f4v", "rm",
            "avb", "asf", "ram", "avs", "mpg", "v8", "swf", "m2v", "asx", "ra", "ndivx", "box", "xvid"};
    private static final double KB = 1024.0;
    private static final double MB = KB * KB;
    private static final double GB = KB * KB * KB;

    private static HashSet<String> hashSet;

    static {
        hashSet = new HashSet<String>(Arrays.asList(VIDEO_EXTENSIONS));
    }

    public static boolean isVideo(File file) {
        return hashSet.contains(getExtension(file));
    }

    private static String getExtension(File file) {
        if (file != null) {
            String fileName = file.getName();
            int point = fileName.lastIndexOf(".");
            if (point > 0 && point < fileName.length() - 1) {
                return fileName.substring(point + 1).toLowerCase();
            }
        }
        return null;
    }

    private String getFileSize(long size) {
        if (size < KB) {
            return String.format("%.1f", size) + "B";
        } else if (size < MB) {
            return String.format("%.1f", size / KB) + "KB";
        } else if (size < GB) {
            return String.format("%.1f", size / MB) + "MB";
        } else {
            return String.format("%.1f", size / GB) + "GB";
        }
    }
}

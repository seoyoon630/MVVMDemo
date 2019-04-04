package com.exmp.mvvm.util;

import android.content.Context;

import java.io.*;

public class FileUtil {
    private static Context mContext;
    private static String dirPath;

    public static void CREATE(Context context) {
        mContext = context;
        dirPath = mContext.getFilesDir().getAbsolutePath();
    }

    public static File getNoteJsonFile() {
        File file = new File(dirPath + "/notes.txt");
        if (file.exists()) {
            return file;
        } else return createFile();
    }

    private static File createFile() {
        File file = new File(dirPath); // 일치하는 폴더가 없으면 생성
        if (!file.exists()) {
            file.mkdirs();
        } // txt 파일 생성
        File resultFile = new File(dirPath + "/notes.txt");
        return resultFile;
    }

    public static void writeFile(File file, String content) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException ignored) {
        }
    }

    // 파일 내용 읽어오기
    public static String readFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder content = new StringBuilder();
            String temp;
            while ((temp = bufferReader.readLine()) != null) {
                content.append(temp);
            }
            return content.toString();
        } catch (Exception e) {
            return null;
        }
    }
}

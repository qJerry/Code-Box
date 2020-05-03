package com.github.qjerry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/19
 */
public class FileUtils {

    public static boolean create(String fileName) {
        File file = new File(fileName);
        if(! file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean write(String path, String content) {
        try(FileWriter writer = new FileWriter(path,true);) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

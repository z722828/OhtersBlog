package com.my.blog.website.utils;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * 工具类
 */
public class Tools {

    private static final Random random = new Random();

    public static void copyFIleUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally{
            assert inputChannel != null;
            inputChannel.close();
            assert outputChannel != null;
            outputChannel.close();
        }
    }

    public static int rand(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static String flowAutoShow(int value) {
        int kb = 1024;
        int mb = 1048576;
        int gb = 1073741824;

        if (Math.abs(value) > gb) {
            return Math.round(value / gb) + "GB";
        } else if (Math.abs(value) > mb) {
            return Math.round((value / mb)) + "MB";
        } else if (Math.abs(value) >kb) {
            return Math.round(value) + "KB";
        }
        return Math.round(value) + "";
    }

    /**
     * 编码
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String enAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
//        return new Base64Encoder().encode(encryptedBytes);
        /**
         sun.misc.BASE64Encoder 是sun内部使用类，没有公开，所以正式使用时候也不建议使用

         如果使用该类maven 构建的时候会报错，网上有方法在maven-compiler-plugin 插件中指定jar包，但改方法中路径是死的，所以对于正式项目没有意义。

         最好的方式就是用其他jar包替换：
         比如：commons-codec


         maven: poml.xml 添加下面依赖
         <dependency>
         <groupId>commons-codec</groupId>
         <artifactId>commons-codec</artifactId>
         <version>20041127.091804</version>
         </dependency>

         用org.apache.commons.codec.binary.Base64类进行编码
         */
        return new Base64().encodeToString(encryptedBytes);
    }

    /**
     * 解码
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String deAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(data);
        /**
         * sun.misc.BASE64Encoder 是sun内部使用类，没有公开，所以正式使用时候也不建议使用
         * 用org.apache.commons.codec.binary.Base64类进行解码
         * 用org.apache.commons.codec.binary.Base64类进行解码
         */
        byte[] cipherTextBytes = new Base64().decodeBase64(data);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }

    public static boolean isNumber(String str) {
        if (str != null && str.trim().length() != 0 && str.matches("\\d*")) {
            return true;
        }
        return false;
    }

}

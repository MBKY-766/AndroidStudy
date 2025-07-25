package com.example.shopping01.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    //把字符串保存到指定路径的文本文件
    public static void saveText(String path ,String txt){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(txt);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(bw!=null){
                try{
                    bw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    //从指定路径的文本文件中读取内容字符串
    public static String openText(String path){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try{
            br = new BufferedReader(new FileReader(path));
            String line = null;
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(br!=null){
                try{
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return  sb.toString();
    }

    public static void saveImage(String path, Bitmap b1) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            //把位图数据压缩到文件输出流中
            b1.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Bitmap openImage(String path) {
        //从指定资源文件读取位图数据
        Bitmap bitmap= null;
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(path);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bitmap;
    }
}

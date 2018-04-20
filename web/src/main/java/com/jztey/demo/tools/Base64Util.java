package com.jztey.demo.tools;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hongyuhao on 2018-01-11.
 */
@Component
public class Base64Util {
    private HttpURLConnection httpUrl = null;

    public void closeHttpConn(){
        httpUrl.disconnect();
    }
    /**
     * 从URL中读取图片,转换成流形式.
     * @param destUrl
     * @return
     */
    public InputStream saveToFile(String destUrl){

        URL url = null;
        InputStream in = null;
        try{
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            in = httpUrl.getInputStream();
            return in;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取输入流,转换为Base64字符串
     * @param input
     * @return
     */
    public String GetImageStrByInPut(InputStream input) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            data = new byte[input.available()];
            input.read(data);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }


    /**
     * 图片转化成base64字符串 将图片文 件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @return
     */
    public static String GetImageStr(File file) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * base64字符串转化成图片 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr
     *            数据内容(字符串)
     * @param path
     *            输出路径
     * @return
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);// Base64解码
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //将图片装为base64字符串
    public String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public  InputStream GetImageStr(String imgFilePath) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null; // 读取图片字节数组
        try {


            URL url = new URL(imgFilePath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            return in ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //将服务器中的图片转为base64
    public String getURLImage(String imageUrl) throws Exception {
        //new一个URL对象
        URL url = new URL(imageUrl);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        BASE64Encoder encode = new BASE64Encoder();
        String s = encode.encode(data);
        return s;
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    //base64字符串转byte[]
    public  byte[] base64String2ByteFun(String base64Str){
        return Base64.decodeBase64(base64Str);
    }
     //byte[]转base64
    public  String byte2Base64StringFun(byte[] b){
       return Base64.encodeBase64String(b);
    }
}

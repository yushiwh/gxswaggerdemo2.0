package com.jztey.demo.tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Administrator on 2017/11/6.
 */
public class ChineseToPinYin {

    private static final String[] numArray = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        // System.out.println(t1.length);
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.UPPERCASE); //大写UPPERCASE
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                // System.out.println(t1[i]);
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        HanyuPinyinOutputFormat t = new HanyuPinyinOutputFormat();
        t.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = new String[0];
            try {
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,t);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff)+" ");
        }
        return strBuf.toString();
    }

    //提取字符串整的数字
    public static String extractNumber(String a) {
        char[] b = a.toCharArray();
        String result = "";
        for (int i = 0; i < b.length; i++) {
            if (("0123456789.").indexOf(b[i] + "") != -1) {
                result += b[i];
            }
        }
        //System.out.println(result);
        return result;
    }

    private static String toChinese(int amountPart) {

        if(amountPart < 0 || amountPart > 10000) {
            throw new IllegalArgumentException("参数必须是大于等于 0，小于 10000 的整数！");
        }

        String[] units = new String[] {"", "十", "百", "千"};

        int temp = amountPart;

        String amountStr = new Integer(amountPart).toString();
        int amountStrLength = amountStr.length();
        boolean lastIsZero = true; //在从低位往高位循环时，记录上一位数字是不是 0
        String chineseStr = "";

        for(int i=0; i<amountStrLength; i++) {
            if(temp == 0)  // 高位已无数据
                break;
            int digit = temp % 10;
            if(digit == 0) { // 取到的数字为 0
                if(!lastIsZero)  //前一个数字不是 0，则在当前汉字串前加“零”字;
                    chineseStr = "零" + chineseStr;
                lastIsZero = true;
            }
            else { // 取到的数字不是 0
                chineseStr = numArray[digit] + units[i] + chineseStr;
                lastIsZero = false;
            }
            temp = temp / 10;
        }
        return chineseStr;
    }
    //数字转为中文
    public static String numToChinese(double amount) {

        if(amount > 99999999999999.99 || amount < -99999999999999.99)
            throw new IllegalArgumentException("参数值超出允许范围 (-99999999999999.99 ～ 99999999999999.99)！");

        boolean negative = false;
        if(amount < 0) {
            negative = true;
            amount = amount * (-1);
        }

        long temp = Math.round(amount * 100);
        int numFen = (int)(temp % 10);
        temp = temp / 10;
        int numJiao = (int)(temp % 10);
        temp = temp / 10;

        int[] parts = new int[20];
        int numParts = 0;
        for(int i=0; ; i++) {
            if(temp ==0)
                break;
            int part = (int)(temp % 10000);
            parts[i] = part;
            numParts ++;
            temp = temp / 10000;
        }

        boolean beforeWanIsZero = true; // 标志“万”下面一级是不是 0

        String chineseStr = "";
        for(int i=0; i<numParts; i++) {

            String partChinese = toChinese(parts[i]);
            if(i % 2 == 0) {
                if("".equals(partChinese))
                    beforeWanIsZero = true;
                else
                    beforeWanIsZero = false;
            }

            if(i != 0) {
                if(i % 2 == 0)
                    chineseStr = "亿" + chineseStr;
                else {
                    if("".equals(partChinese) && !beforeWanIsZero)   // 如果“万”对应的 part 为 0，而“万”下面一级不为 0，则不加“万”，而加“零”
                        chineseStr = "零" + chineseStr;
                    else {
                        if(parts[i-1] < 1000 && parts[i-1] > 0) // 如果"万"的部分不为 0, 而"万"前面的部分小于 1000 大于 0， 则万后面应该跟“零”
                            chineseStr = "零" + chineseStr;
                        chineseStr = "万" + chineseStr;
                    }
                }
            }
            chineseStr = partChinese + chineseStr;
        }

        if("".equals(chineseStr))  // 整数部分为 0, 则表达为"零"
            chineseStr = numArray[0];
        else if(negative) // 整数部分不为 0
            chineseStr = "负" + chineseStr;

        chineseStr = chineseStr + "";

        if(numFen == 0 && numJiao == 0) {
            chineseStr = chineseStr + "";
        }else if(numFen == 0) {
            chineseStr = chineseStr +"点"+ numArray[numJiao] + "";
        }else { // “分”数不为 0
            if(numJiao == 0)
                chineseStr = chineseStr + "零" + numArray[numFen] + "";
            else
                chineseStr = chineseStr +"点"+ numArray[numJiao] + numArray[numFen] + "";
        }

        return chineseStr;

    }

//    public static void main(String[] args) {
//        //ChineseToPinYin chineseToPinYin = new ChineseToPinYin();
//      //  String cnStr = "好药师集团";
//        //System.out.println(chineseToPinYin.getPinYin(cnStr));
//        //System.out.println(chineseToPinYin.getPinYinHeadChar(cnStr));
////      System.out.println(getCnASCII(cnStr));
//    }
}

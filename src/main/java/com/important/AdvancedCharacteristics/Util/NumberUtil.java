package com.important.AdvancedCharacteristics.Util;

import java.math.BigDecimal;

/**
 * BigDecimal都是不可变的（immutable）的，在进行每一步运算时， 都会产生一个新的对象，所以在做加减乘除运算时千万要保存操作后的值。
 */
public class NumberUtil {

    public static void main(String[] args) {
        // 1.219999999999999973...... 结果不精确
        BigDecimal a = new BigDecimal(1.22);
        System.out.println("construct with a String value: " + a);

        // 2.通常建议优先使用String构造方法
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.1");
        System.out.println(bd1.add(bd2));
    }

    /**
     * double 比较大小
     * 
     * @param d1
     * @param d2
     * @return 返回的结果是int类型,-1表示小于,0是等于,1是大于.
     */
    public static int compareTo(Double d1, Double d2) {
        d1 = d1 == null ? 0 : d1;
        d2 = d2 == null ? 0 : d2;
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.compareTo(bd2);
    }

    /**
     * 提供精确的加法运算
     * 
     * @param v1被加数
     * @param v2加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? BigDecimal.ZERO : v1;
        v2 = v2 == null ? BigDecimal.ZERO : v2;
        return v1.add(v2);
    }

    /**
     * 提供精确的减法运算
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? BigDecimal.ZERO : v1;
        v2 = v2 == null ? BigDecimal.ZERO : v2;
        return v1.subtract(v2);
    }

    /**
     * 提供精确的乘法运算
     * 
     * @param v1被乘数
     * @param v2乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        v1 = v1 == null ? BigDecimal.ZERO : v1;
        v2 = v2 == null ? BigDecimal.ZERO : v2;
        return v1.multiply(v2);
    }

    /**
     * 提供(相对)精确的除法运算.当发生除不尽的情况时,由scale参数指 定精度,以后的数字四舍五入
     * 
     * @param v1被除数
     * @param v2除数
     * @param scale表示表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        v1 = v1 == null ? BigDecimal.ZERO : v1;
        v2 = v2 == null ? BigDecimal.ZERO : v2;
        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
    }

}

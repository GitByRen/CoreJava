package com.important.java9;

/**
 * 1、模块化
 * module-info中
 * 	exports 包名
 *  requires module名
 * 
 * 2、多版本兼容 JAR
 * 同名的两个类在不同的环境下执行，会输出不同的结果
 * 
 * 3、禁止直接使用下划线
 * 
 * 4、jdk8及之前：String以UTF-16作为默认字符集进行存储，UTF-16使用两个字节表示一个字符串
 * jdk9 根据不同的字符串使用不同的字符集，ISO-8859-1/Latin-1(one byte per character)，
 * or UTF-16(two bytes per character)
 * 
 */
public class Module {

}

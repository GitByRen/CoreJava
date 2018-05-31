package com.important.Common;

import org.junit.Test;

public class StringTest {
	
	public static void main(String[] args) {
		// 1.==:对于基本数据类型，比较的是内容；对于引用数据类型，比较的是地址
		// 2.equals:只能比较引用数据类型，对于Object的equals比较的地址，对于String等比较的是内容
		Person p1 = new Person("AA", 11L, 11);
		Person p2 = new Person("AA", 11L, 11);
		System.out.println(p1 == p2); // false
		System.out.println(p1.equals(p2)); // false-重写后->true

		System.out.println(p1.name == p2.name); // true
		System.out.println(p1);

		Boolean b1 = new Boolean(null); // false
		System.out.println(b1);
		Person person3 = new Person();
		System.out.println(person3);
		
		// 1.String类是final类，也即意味着String类不能被继承，并且它的成员方法都默认为final方法，线程安全。

		// 2.String对象一旦被创建就是固定不变的了，对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象。

		/*
		 * 3.Java中的常量池，实际上分为两种形态：静态常量池和运行时常量池。
		 * 静态常量池，即*.class文件中的常量池，class文件中的常量池不仅仅包含字符串(数字)字面量，还包含类、方法的信息，占用class文件绝大部分空间。
		 * 运行时常量池，则是jvm虚拟机在完成类装载操作后，将class文件中的常量池载入到内存中，并保存在方法区中，我们常说的常量池，
		 * 就是指方法区中的运行时常量池。
		 */

		/*
		 * 4.
		 * 采用new关键字新建一个字符串对象时，JVM首先在字符串池中查找有没有"aaa"这个字符串对象，如果有，直接将常量池的"aaa"对象的地址返回给堆中的对象，
		 * 然后将堆中的这个"aaa"对象的地址返回赋给引用str3，这样，str3就指向了堆中创建的这个"aaa"字符串对象；如果没有，则首先在字符串池中创建一个
		 * "aaa"字符串对象，然后将常量池的"aaa"对象的地址返回给堆中的对象，然后将堆中这个"aaa"字符串对象的地址返回赋给str3引用，这样，str3指向了
		 * 堆中创建的这个"aaa"字符串对象
		 * 
		 * In simple words, there can not be two string objects with same content in the
		 * string constant pool. But, there can be two string objects with the same
		 * content in the heap memory.
		 */
	}
	
	@Test
	public void test() {
		String str1 = "北京尚硅谷教育";
		String str2 = "上海尚硅谷教育";
		String substring = str1.substring(2,5);
		System.out.println(substring);
		String replace = str1.replace("北京", "东京");
		System.out.println(replace);
		String str3 = "   abc  d  ";
		System.out.println(str3.trim());
		String concat = str1.concat(str2);
		System.out.println(concat);
		String str4 = "abc*d-ef-ke";
		String[] split = str4.split("-");
		for (String string : split) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test1() {
		byte[] bytes = "abcd".getBytes();
		for (int i = 0; i < bytes.length; i++) {
			System.out.print((char) bytes[i]);
		}

		char[] charArray = "af中国".toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			System.out.print(charArray[i]);
		}
	}
}

class StringTests {
	// StringBuilder("xxyy").append(a).append("zz").append("mm").append(b).toString();
	public void test1() {
		String a = "aa";
		String b = "bb";
		String c = "xx" + "yy " + a + "zz" + "mm" + b;
		System.out.println("===========test1============");
		System.out.println(c);
	}
}

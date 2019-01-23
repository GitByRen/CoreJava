package com.important.AdvancedCharacteristics.Inner;

/**
 * 为什么局部内部类和匿名内部类只能访问局部final变量？
 */
public class InnerClass2 extends Extends{

	public static void main(String[] args) {
		
	}

	/**
	 * 当test方法执行完毕之后，变量a的生命周期就结束了，而此时Thread对象的生命周期很可能还没有结束，
	 * 那么在Thread的run方法中继续访问变量a就变成不可能了，但是又要实现这样的效果，怎么办呢？
	 *  《Java采用了 复制 的手段来解决这个问题》
	 */

	/**
	 * 如果这个变量的值在编译期间可以确定，则编译器默认会在匿名内部类（局部内部类）的常量池中添加一个内容相等的字面量或直接将相应的字节码嵌入到执行字节码中。
	 * 这样一来，匿名内部类使用的变量是另一个局部变量，只不过值和方法中局部变量的值相等，因此和方法中的局部变量完全独立开。
	 */
	public void test1() {
		final int a = 10;

		new Thread() {
			public void run() {
				System.out.println(a);
			}
		}.start();
	}

	/**
	 * 如果局部变量的值在编译期间就可以确定，则直接在匿名内部里面创建一个拷贝。
	 * 如果局部变量的值无法在编译期间确定，则通过构造器传参的方式来对拷贝进行初始化赋值。
	 */

	/**
	 * 既然在run方法中访问的变量a和test方法中的变量a不是同一个变量，当在run方法中改变变量a的值的话，会出现什么情况？ 对，会造成数据不一致性
	 * 为了解决这个问题，java编译器就限定必须将变量a限制为final变量，不允许对变量a进行更改
	 */
	public void test2(final int b) {
		new Thread() {
			public void run() {
				System.out.println(b);
			}
		}.start();
	}
}

class Extends {
	
	public int a = 10;
	
}

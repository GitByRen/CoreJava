package com.important.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * 序列化会通过反射调用无参的构造方法创建一个新的对象，所以反序列化普通的单例会返回新的对象
 */
public class TestSerializableSingleTon {

	@Test
	public void testObjectOutStream() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("testFile.txt"));
			oos.writeObject(Eay.INSTANCE);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testObjectInputStream() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("testFile.txt"));
			Object readObject = ois.readObject();
			System.out.println(readObject);
			System.out.println(Eay.INSTANCE == readObject);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

// 方式一：
/*class SingleTon implements Serializable {
	private static SingleTon singleTon = new SingleTon("哈哈");

	private SingleTon(String name) {
	}

	public static SingleTon getInstance() {
		return singleTon;
	}

	// 这样可以保证序列化和反序列化的都是一个对象
	public Object readResolve() {
		return getInstance();
	}
}*/


// 方式二:
// 枚举可以自己处理序列化、枚举是线程安全的

// 反射在通过newInstance创建对象时，会检查该类是否enum修饰，如果是则抛出异常，反射失败，详见Constructor.class的newInstance方法

/*
反编译后是static和final的，并且在静态代码块中初始化，所以线程安全
public static final T SPRING;
public static final T SUMMER;
public static final T AUTUMN;
public static final T WINTER;
private static final T ENUM$VALUES[];
static
{
    SPRING = new T("SPRING", 0);
    SUMMER = new T("SUMMER", 1);
    AUTUMN = new T("AUTUMN", 2);
    WINTER = new T("WINTER", 3);
    ENUM$VALUES = (new T[] {
        SPRING, SUMMER, AUTUMN, WINTER
    });
}*/

// 在序列化的时候Java仅仅是将枚举对象的name属性输出到结果中，
// 反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象，所以对序列化有保证
enum Eay{
	INSTANCE;
}
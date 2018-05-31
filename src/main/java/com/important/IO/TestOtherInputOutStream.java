package com.important.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class TestOtherInputOutStream {

	@Test
	public void testData() {
		DataOutputStream dos = null;
		try {
			FileOutputStream fos = new FileOutputStream("testFile.txt");
			dos = new DataOutputStream(fos);
			dos.writeUTF("哈哈呵呵嘿嘿！");
			// 会乱码
			dos.writeBoolean(true);
			dos.writeLong(14032312312l);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testData1() throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream("testFile.txt"));
		String str = dis.readUTF();
		System.out.println(str);
		boolean b = dis.readBoolean();
		System.out.println(b);
		long l = dis.readLong();
		System.out.println(l);
		dis.close();
	}

	/**
	 * 序列化：将对象通过ObjectOutputStream转换为二进制流，存储在硬盘上
	 * 反序列化：将硬盘中的文件通过ObjectInputStream转换为相应的对象
	 * ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
	 */
	@Test
	public void testObjectOutStream() {
		Person p1 = new Person("小米", 23, new Pet("阿敏"));
		Person p2 = new Person("小名", 26, new Pet("阿敏2"));
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("testFile.txt"));
			oos.writeObject(p1);
			oos.writeObject(p2);
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
			Person p1 = (Person) ois.readObject();
			System.out.println(p1);
			Person p2 = (Person) ois.readObject();
			System.out.println(p2);
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

/**
 * 1.要求此列是可序列化的，实现Serializable接口 
 * 2.要求类的属性同样的要实现Serializable接口 
 * 3.提供一个版本号
 */
class Person implements Serializable {
	// 用来表明类的不同版本之间的兼容性，如果类没有显示定义这个静态变量，它的值是java运行时自动生成的。
	// 若类的源代码发生修改，serialVersionUID可能发生变化，故建议显示声明
	private static final long serialVersionUID = 1L;

	String name;
	Integer age;
	Pet pet;

	public Person(String name, Integer age, Pet pet) {
		this.name = name;
		this.age = age;
		this.pet = pet;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", pet=" + pet + "]";
	}

}

class Pet implements Serializable{
	String name;

	public Pet(String name) {
		this.name = name; 
	}

	@Override
	public String toString() {
		return "Pet [name=" + name + "]";
	}

}

package com.important.IO;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * 1.File是一个类，对应着(.txt .mp3)或文件目录
 * 2.File中的方法，仅涉及到如何创建删除重命名等
 */
public class TestFile {

	/**
	 * 绝对路径：包括盘符在内的完整的文件路径
	 * 相对路径：在当前文件夹目录下的文件的路径
	 * 
	 * getName()
	 * getPath()
	 * getAbsoluteFile()
	 * getAbsolutePath()
	 * getParent()
	 * renameTo(File newName)
	 */
	@Test
	public void test1() {
		File file1 = new File("g:/io/hello.txt");
		File file2 = new File("testFile.txt");
		System.out.println(file1.getName());
		System.out.println(file1.getPath());
		System.out.println(file1.getAbsoluteFile());
		System.out.println(file1.getParent());
		System.out.println(file1.getAbsolutePath());
		
		System.out.println("**********************");
		
		System.out.println(file2.getName());
		System.out.println(file2.getPath());
		System.out.println(file2.getAbsoluteFile());
		System.out.println(file2.getParent());
		System.out.println(file2.getAbsolutePath());
		
		// 文件：file1.renameTo(file2)（要求file1一定存在，file2一定不存在）
		System.out.println(file1.renameTo(file2));
	}
	
	/**
	 * exists()
	 * canWrite()
	 * canRead()
	 * isFile()
	 * isDirectory()
	 * lastModified()
	 * length()
	 */
	@Test
	public void test2() {
		File file1 = new File("g:/io/hello.txt");
		File file2 = new File("g:/io/io1");
		
		System.out.println(file1.exists());
		System.out.println(file1.canWrite());
		System.out.println(file1.canRead());
		System.out.println(file1.isFile());
		System.out.println(file1.isDirectory());
		System.out.println(file1.lastModified());
		System.out.println(file1.length());
		
		System.out.println("*****************");
		
		System.out.println(file2.exists());
		System.out.println(file2.canWrite());
		System.out.println(file2.canRead());
		System.out.println(file2.isFile());
		System.out.println(file2.isDirectory());
		System.out.println(file2.lastModified());
		System.out.println(file2.length());
	}
	
	/**
	 * createNewFile()
	 * delete()
	 * mkDir():创建最后一层
	 * mkDirs():创建上层
	 * list()
	 * listFiles()
	 * @throws IOException 
	 */
	@Test
	public void test3() throws IOException {
		File file1 = new File("g:/io/hello.txt");
		System.out.println(file1.delete());
		
		if(!file1.exists()) {
			System.out.println(file1.createNewFile());
		}
		
		File file2 = new File("g:/io/io2");
		if(!file2.exists()) {
			System.out.println(file2.mkdirs());
		}
		
		File file3 = new File("g:/文档");
		String[] list = file3.list();
		for (String string : list) {
			System.out.println(string);
		}
		
		System.out.println("&**********&");
		
		// listFiles可以操作文件
		File[] listFiles = file3.listFiles();
		for (File file : listFiles) {
			System.out.println(file.getName());
		}
		
	}
}

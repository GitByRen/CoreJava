package com.important.IO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * RandomAccessFile:支持随机访问
 * 1.既可以充当输入流，也可以充当输出流
 * 2.支持从文件的开头读取、写入
 * 3.支持从任意位置的读取、写入
 */
public class TestRandomAccessFile {

	@Test
	public void testRandomAccessFile() {
		RandomAccessFile raf1 = null;
		RandomAccessFile raf2 = null;
		try {
			raf1 = new RandomAccessFile(new File("hello.txt"), "r");
			raf2 = new RandomAccessFile(new File("hello1.txt"), "rw");
			byte[] b = new byte[20];
			int len;
			while ((len = raf1.read(b)) != -1) {
				raf2.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (raf2 != null) {
				try {
					raf2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (raf1 != null) {
				try {
					raf1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void test2() {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(new File("hello.txt"), "rw");
			raf.seek(3);
			// 1.覆盖操作
			// raf.write("xy".getBytes());

			// 2.插入操作
//			String str = raf.readLine();
//			raf.seek(3);
//			raf.write("cc".getBytes());
//			raf.write(str.getBytes());

			// 可能有换行的情况
			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			while ((len = raf.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			raf.seek(3);
			raf.write("dd".getBytes());
			raf.write(sb.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

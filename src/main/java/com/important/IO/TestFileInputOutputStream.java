package com.important.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * 抽象基类：InputStream、OutputStream、Reader、Writer
 */
public class TestFileInputOutputStream {

	/**
	 * 要读取的文件一定要存在
	 */
	@Test
	public void test1() throws IOException {
		File file = new File("testFile.txt");
		FileInputStream fis = new FileInputStream(file);
		int b;
		while ((b = fis.read()) != -1) {
			System.out.print((char) b);
		}
		fis.close();
	}

	/**
	 * 使用try-catch处理异常
	 */
	@Test
	public void test2() {
		FileInputStream fis = null;
		try {
			File file = new File("testFile.txt");
			fis = new FileInputStream(file);
			int b;
			while ((b = fis.read()) != -1) {
				System.out.print((char) b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testFileInputStream() {
		FileInputStream fis = null;
		try {
			File file = new File("testFile.txt");
			fis = new FileInputStream(file);
			byte[] b = new byte[10]; // 读取的数据要写入的数组
			int len;
			while ((len = fis.read(b)) != -1) {
				System.out.print(new String(b, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testFileOutputStream() {
		File file = new File("g:/io/hello2.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(new String("白阿敏").getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 文件复制
	@Test
	public void testFileInputOutputStream() {
		// 1.提供读入、写出的文件
		File inFile = new File("testFile.txt");
		File outFile = new File("g:/io/hello3.txt");
		// 2.提供相应的流
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(inFile);
			fos = new FileOutputStream(outFile);
			// 3.实现文件复制
			byte[] b = new byte[5];
			int len = 0;
			// fow.write()也能写入但是会多字节
			while ((len = fis.read(b)) != -1) {
				fos.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

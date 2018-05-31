package com.important.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class TestBuffered {

	/**
	 * 使用BufferedInputStream和BufferedOutpurStream实现文件复制
	 */
	@Test
	public void testBufferedInputOutStream() {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File src = new File("g:/io/1.jpg");
			File dest = new File("g:/io/io2/2.jpg");
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dest);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			byte[] b = new byte[1024];
			int len;
			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
				bos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testBufferedReader() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File src = new File("testFile.txt");
			File dest = new File("g:/io/Fill.txt");
			FileReader fr = new FileReader(src);
			FileWriter fw = new FileWriter(dest);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);

			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				bw.write(str);
				bw.newLine();
				bw.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

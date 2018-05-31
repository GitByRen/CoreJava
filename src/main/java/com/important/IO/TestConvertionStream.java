package com.important.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class TestConvertionStream {

	/**
	 * 转换流 InputStreamReader:字节到字符 OutputStreamWriter:字符到字节
	 */
	@Test
	public void test1() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File file = new File("testFile.txt");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "utf8");
			br = new BufferedReader(isr);

			File file1 = new File("g:/io/hello2.txt");
			FileOutputStream fos = new FileOutputStream(file1);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf8");
			bw = new BufferedWriter(osw);

			String str;
			while ((str = br.readLine()) != null) {
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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

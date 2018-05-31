package com.important.IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class TestFileReaderWriter {

	@Test
	public void testFileReader() {
		FileReader fr = null;
		try {
			File file = new File("testFile.txt");
			fr = new FileReader(file);
			char[] c = new char[24];
			int len;
			while ((len = fr.read(c)) != -1) {
				System.out.println(new String(c, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testFileReaderWriter() {
		FileReader fr = null;
		FileWriter fw = null;

		try {
			File src = new File("testFile.txt");
			File dest = new File("g:/io/Fill.txt");
			fr = new FileReader(src);
			fw = new FileWriter(dest);
			char[] c = new char[24];
			int len;
			while ((len = fr.read(c)) != -1) {
				fw.write(c, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

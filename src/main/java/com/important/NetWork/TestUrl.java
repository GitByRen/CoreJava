package com.important.NetWork;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class TestUrl {

	public static void main(String[] args) {
		InputStream is = null;
		InputStream ism = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL("http://127.0.0.1:8080/examples/HelloWorld.txt?a=b");
			// System.out.println(url.getProtocol());
			// System.out.println(url.getHost());
			// System.out.println(url.getPort());
			// System.out.println(url.getFile());
			// System.out.println(url.getRef());
			// System.out.println(url.getQuery()); // a=b
			is = url.openStream();
			byte[] b = new byte[20];
			int len;
			while ((len = is.read(b)) != -1) {
				String str = new String(b, 0, len);
				System.out.println(str);
			}

			// 如果既有数据的输入，又有数据的输出，考虑URLConnection类
			URLConnection urlConn = url.openConnection();
			ism = urlConn.getInputStream();
			fos = new FileOutputStream(new File("abc.txt"));
			byte[] b1 = new byte[20];
			int len1;
			while ((len1 = ism.read(b1)) != -1) {
				fos.write(b1, 0, len1);
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
			if (ism != null) {
				try {
					ism.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

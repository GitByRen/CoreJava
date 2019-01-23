package com.important.Exception;

public class Return_Finally {

	public static void main(String[] args) {
		System.out.println(m_1());
	}

	public static int m_1() {
		int i = 10;
		try {
			System.out.println("start");
			return i += 10;
		} catch (Exception e) {
			System.out.println("error: " + e);
		} finally {
			if (i > 10) {
				System.out.println(i);
			}
			System.out.println("finally");
		}
		return i;
	}
}
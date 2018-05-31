package com.important.Exception;

public class TestException {
	public static void main(String[] args) {
		Student s = new Student();
		try {
			s.register(-1);
		} catch (ArithmeticException | MyException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(s);
	}
}

class MyException extends Exception {
	static final long serialVersionUID = -703489745766939L;

	public MyException() {
	}

	public MyException(String msg) {
		super(msg);
	}
}

class Student {
	int id;

	public void register(int id) throws MyException, ArithmeticException {
		if (id > 0) {
			this.id = id;
		} else {
			throw new MyException("学号有误！");
		}
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}
}
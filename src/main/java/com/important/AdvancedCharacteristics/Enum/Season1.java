package com.important.AdvancedCharacteristics.Enum;

public class Season1 {
	public static void main(String[] args) {
		Seasons[] s = Seasons.values();
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
		
		Seasons summer = Seasons.SUMMER;
		Seasons spring = Seasons.SPRING;
		summer.show();
		spring.show();
	}
}

interface Show{
	void show();
}
// 枚举类可以实现接口，也可以让不同的对象重写方法
// enum Seasons是抽象类，SPRING相当与是Seasons的匿名子类
enum Seasons implements Show{
	SPRING("spring", "春暖花开"){
		public void show() {
			System.out.println("牛逼");
		}

		@Override
		public String getX() {
			return null;
		}
	},
	SUMMER("summer", "夏日炎炎") {
		@Override
		public String getX() {
			return null;
		}
	},
	AUTHMN("autumn", "秋高气爽") {
		@Override
		public String getX() {
			return null;
		}
	},
	WINTER("winter", "白雪皑皑") {
		@Override
		public String getX() {
			return null;
		}
	};

	private final String name;
	private final String desc;

	private Seasons(String name, String desc) {
		// 常量需要赋值
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "Season [name=" + name + ", desc=" + desc + "]";
	}

	@Override
	public void show() {
		System.out.println("这是一个季节!");
	}
	
	public abstract String getX();
}

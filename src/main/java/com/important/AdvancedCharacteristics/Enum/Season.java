package com.important.AdvancedCharacteristics.Enum;

/**
 * 枚举类：一个类的对象是有限而且固定的
 */
public class Season {

	// 2.因为对象是固定的，所以属性是常量
	private final String name;
	private final String desc;

	// 1.因为枚举类的对象是有限个，所以不能在类的外部创建类的对象
	private Season(String name, String desc) {
		// 常量需要赋值
		this.name = name;
		this.desc = desc;
	}

	// 3.在类的内部创建对象，需要在类的外部能够访问到该对象，而且不能被修改
	public static final Season SPRING = new Season("春天", "春风又绿江南岸");
	public static final Season SUMMER = new Season("夏天", "映日荷花别样红");
	public static final Season FALL = new Season("秋天", "秋水共长天一色");
	public static final Season WINTER = new Season("冬天", "窗含西岭千秋雪");

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

}

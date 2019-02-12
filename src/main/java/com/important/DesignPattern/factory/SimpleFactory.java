package com.important.DesignPattern.factory;

/**
 * 
 * 特点 1 它是一个具体的类，非接口 抽象类。有一个重要的create()方法，利用if或者 switch创建产品并返回。
 * 
 * 2 create()方法通常是静态的，所以也称之为静态工厂。
 * 
 * 缺点 1 扩展性差（我想增加一种面条，除了新增一个面条产品类，还需要修改工厂类方法）
 * 
 * 2 不同的产品需要不同额外参数的时候 不支持。
 *
 */
public class SimpleFactory {

	public static void main(String[] args) {
		INoodles createNoodles = SimpleNodesFactory.createNoodles(SimpleNodesFactory.TYPE_LZ);
		createNoodles.desc();
	}

}

class SimpleNodesFactory {

	public static final int TYPE_LZ = 1;// 兰州拉面
	public static final int TYPE_PM = 2;// 泡面
	public static final int TYPE_GK = 3;// 干扣面

	public static INoodles createNoodles(int type) {
		switch (type) {
		case TYPE_LZ:
			return new LzNoodles();
		case TYPE_PM:
			return new PaoNoodles();
		default:
			return null;
		}
	}
}

abstract class INoodles {
	public abstract void desc();
}

class LzNoodles extends INoodles {
	@Override
	public void desc() {
		System.out.println("兰州拉面 上海的好贵 家里才5 6块钱一碗");
	}
}

class PaoNoodles extends INoodles {
	@Override
	public void desc() {
		System.out.println("泡面好吃 可不要贪杯");
	}
}

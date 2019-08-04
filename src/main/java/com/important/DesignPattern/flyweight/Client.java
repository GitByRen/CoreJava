package com.important.DesignPattern.flyweight;

/**
 * 1、定义：
 * 运用共享技术来有效地支持大量细粒度对象的复用。
 * 它通过共享已经存在的对象来大幅度减少需要创建的对象数量、避免大量相似类的开销，从而提高系统资源的利用率。
 * 
 * 2、应用场景：
 * ①系统中存在大量相同或相似的对象，这些对象耗费大量的内存资源。
 * ②大部分的对象可以按照内部状态进行分组，且可将不同部分外部化，这样每一个组只需保存一个内部状态。
 * ③由于享元模式需要额外维护一个保存享元的数据结构，所以应当在有足够多的享元实例时才值得使用享元模式。
 */
public class Client {

	public static void main(String[] args) {
		WebSiteFactory factory = new WebSiteFactory();
		Website webSiteCategory = factory.getWebSiteCategory("新闻");
		webSiteCategory.use(new User("tom"));

		Website webSiteCategory2 = factory.getWebSiteCategory("博客");
		webSiteCategory2.use(new User("tim"));
	}

}

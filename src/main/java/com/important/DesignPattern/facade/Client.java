package com.important.DesignPattern.facade;

/**
 * 1、是一种通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。
 * 该模式对外有一个统一接口，外部应用程序不用关心内部子系统的具体的细节，
 * 这样会大大降低应用程序的复杂度，提高了程序的可维护性。
 * 
 * 2、应用场景：
 * ①对分层结构系统构建时，使用外观模式定义子系统中每层的入口点可以简化子系统之间的依赖关系。
 * ②当一个复杂系统的子系统很多时，外观模式可以为系统设计一个简单的接口供外界访问。
 * ③当客户端与多个子系统之间存在很大的联系时，引入外观模式可将它们分离，从而提高子系统的独立性和可移植性。
 */
public class Client {

	public static void main(String[] args) {
		HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
		homeTheaterFacade.ready();
		homeTheaterFacade.play();
		
		homeTheaterFacade.end();
	}

}

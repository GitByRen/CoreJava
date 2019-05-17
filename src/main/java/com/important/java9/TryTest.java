package com.important.java9;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class TryTest {

	/**
	 * jdk8
	 */
	@Test
	public void testTry1() {
		try (InputStreamReader reader = new InputStreamReader(System.in);
				OutputStreamWriter writer = new OutputStreamWriter(System.out)) {
			reader.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * jdk9
	 */
	@Test
	public void testTry2() {
		InputStreamReader reader = new InputStreamReader(System.in);
		OutputStreamWriter writer = new OutputStreamWriter(System.out);
		try (reader; writer) {
			reader.read();
			
			// Local variable reader defined in an enclosing scope must be final or effectively final
//			reader = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

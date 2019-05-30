package java8.no1.interfaceNew;

public interface DefalutTest {
	static int a = 5;

	default void defaultMethod() {
		System.out.println("DefalutTest defalut 方法");
	}

	int sub(int a, int b);

	static void staticMethod() {
		System.out.println("DefalutTest static 方法");
	}
}

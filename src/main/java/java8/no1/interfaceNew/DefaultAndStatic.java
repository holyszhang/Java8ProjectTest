package java8.no1.interfaceNew;

public class DefaultAndStatic implements DefalutTest {
	@Override
	public void defaultMethod() {
		System.out.println("impl fun");
	}

	@Override
	public int sub(int a, int b) {
		return a+b;
	}

	public void dd(){}
}

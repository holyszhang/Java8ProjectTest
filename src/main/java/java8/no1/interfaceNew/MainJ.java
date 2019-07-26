package java8.no1.interfaceNew;

import java.util.Scanner;

public class MainJ {
	public static void main(String[] args) throws Exception {
		System.out.println("a");
		Scanner scan = new Scanner(System.in);
		try {
			while (true) {
				System.out.println("Input: a = ");
				int a = scan.nextInt();
				if (1 == a) {
					System.out.println("true");
				} else {
					throw new Exception("false");
				}
				System.out.println("true aaaaaa");
			}
		} catch (Exception e) {
			System.out.println("false bbbbb");
			throw new Exception("bbb");
		}
	}
}

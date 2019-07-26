import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class Java8Lambda {
	/**
	 * 实现runnable案例
	 */
	@Test
	public void a() {
		//java
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("java new Runnable");
			}
		}).start();

		//lambda
		new Thread(() -> System.out.println("lambda new Runnable")).start();
	}

	/**
	 * Swing时间监听器
	 */
	@Test
	public void b() {
		JButton jButton = new JButton("show");
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("without lambda expression is boring");
			}
		});

		//lambda
		jButton.addActionListener((e) -> System.out.println("Action!! Lambda Expression Rocks"));
	}

	/**
	 * 遍历list
	 */
	@Test
	public void c() {
		List list = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (Object s : list) {
			System.out.println(s);
		}
		System.out.println("ABCDEFGHIJKLMN".substring(2, 6));
	}

	/**
	 * 数值计算
	 */
	@Test
	public void d() {
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}

	@Test
	public void e() {

	}
}

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Java8No2 {

	@Test
	public void a() throws IOException {
		File a = new File("E:\\zhang.txt");
		if (a.exists()) {
			System.out.println(a.length());
			System.out.println("aaa");
		} else {
			System.out.println("bbb");
		}

	}
}

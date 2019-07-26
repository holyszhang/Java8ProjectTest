import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	@Test
	public void f(){
		String start = new SimpleDateFormat("yyyyMMdd").format(new Date(Long.valueOf("115646768733")));
		System.out.println("12".compareTo("102221"));
		System.out.println(start);
	}

}

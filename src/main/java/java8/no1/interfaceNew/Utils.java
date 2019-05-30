package java8.no1.interfaceNew;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static User convert(User user){
		User ret=new User();
		ret.setId(user.getId()*10);
		ret.setName(user.getName()+"_end");
		return ret;
	}

	public static User stringToUser(String s){
		return new User(1111,s);
	}

	public static Map<String,User> toMap(User user){
		Map<String,User> map=new HashMap<>();
		map.put(user.getName(),user);
		return map;
	}
}

import java8.no1.interfaceNew.DefalutTest;
import java8.no1.interfaceNew.DefaultAndStatic;
import java8.no1.interfaceNew.Utils;
import java8.no1.interfaceNew.User;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;

import static java.util.stream.Collectors.summarizingDouble;
import static java.util.stream.Collectors.toList;

public class Java8No1 {
	@Test
	public void a() {
		DefaultAndStatic a = new DefaultAndStatic();
		a.defaultMethod();
		System.out.println(a.sub(1, 3));
		DefalutTest.staticMethod();
	}

	@Test
	public void b() {
		List<String> s = Lists.newArrayList("ab", "bbb", "abc", "a");
		List<Integer> i = Lists.newArrayList(14, 5, 43, 89, 64, 112, 55, 55, 58);
		//1:count、min、max
		System.out.println(s.parallelStream().min((a, b) -> {
			return a.length() - b.length();
		}));
		System.out.println(i.parallelStream().count());
		System.out.println(i.parallelStream().max((a, b) -> {
			return a % 5 - b % 5;
		}));
		//2:filter
		i.parallelStream().filter(inte -> inte != null).filter(inte -> inte % 5 == 0).forEach(inte -> System.out.print(inte + ","));
		System.out.println();
		//3:distinct
		i.stream().distinct().forEach(inte -> System.out.print(inte + ","));
		System.out.println();
		//4:sort
		s.stream().sorted((p, q) -> q.length() - p.length()).filter(inte -> inte.startsWith("a")).forEach(System.out::println);
		//5:Map:mapToInt，mapToLong和mapToDouble

		//reduce
		s.stream().reduce((m, n) -> m + "_" + n).ifPresent(System.out::println);
		Optional<String> sss = s.stream().reduce((m, n) -> m + "_" + n);
		System.out.println(sss.get());
		Optional<String> aaa = Optional.empty();
		System.out.println(aaa);
		Optional<String> bbb = Optional.ofNullable(sss.equals(aaa) ? "1" : null);
		System.out.println(bbb.isPresent());
//		System.out.println(bbb.get());
		Optional<String> ccc = Optional.ofNullable(sss.get());
	}

	@Test
	public void c() {
		List<String> a = Lists.newArrayList("a", "c", "d");
		List<String> b = Lists.newArrayList("1", "2", "3");
		System.out.println(a.stream().reduce((m, n) -> m + "_" + n));
		a.stream().reduce((m, n) -> m + "_" + n).ifPresent(System.out::print);
		System.out.println();
		a.addAll(b);
		a.stream().reduce((m, n) -> m + "_" + n).ifPresent(System.out::print);

		List<String> c = Lists.newArrayList();
		Optional<List<String>> cc = Optional.of(c);
		cc.isPresent();
		System.out.println(cc.get());
//		c.addAll(null);
//		Optional<List<String>> ccc=Optional.of(c);
//		System.out.println(ccc.get());
	}

	@Test
	public void d() {
		Optional<List<String>> a = Optional.of(Lists.newArrayList());
		List<String> b = a.get();
		System.out.println(b);
		System.out.println(b.size());
	}

	@Test
	public void map() {
		List<String> list = Lists.newArrayList("a", "bbb", "cc", "12b", "56ewer5610");
		Stream<Integer> stream = list.stream().map(String::length);
//		stream.forEach(System.out::print);
//		Arrays.stream("a-b-c".split("-")).forEach(System.out::print);
//		Stream<List<CharArray>> listStream=list.stream().map(String::toCharArray);

		list.stream().sorted((a, b) -> {
			return a.hashCode() - b.hashCode();
		}).distinct().map(s -> {
			return s.length();
		}).filter(s -> s > 1).forEach(System.out::println);
	}

	@Test
	public void mapFun() {
		List<String> list = Lists.newArrayList("a", "bb", "45s", "stoekljfsdt", "8sf");
		List<Integer> inte = list.stream().map(this::convert).collect(toList());
		inte.stream().forEach(System.out::println);

		List<User> users = Lists.newArrayList(new User(1, "zhang"), new User(2, "zhen"));
		List<Integer> userIds = users.stream().map(User::getId).collect(toList());
		userIds.stream().forEach(System.out::println);
		List<User> retUser = users.stream().map(Utils::convert).collect(toList());
		retUser.stream().forEach(System.out::println);
		List<User> st = list.stream().map(Utils::stringToUser).collect(toList());
		st.stream().forEach(System.out::println);
		Map<String, List<User>> m = users.stream().collect(Collectors.groupingBy(User::getName, toList()));
		System.out.println(m.toString());
		Map<String, User> n = users.stream().collect(Collectors.toMap(User::getName, Function.identity()));
		System.out.println(n.toString());

		//partitioningBy 其实是一种特殊的 groupingBy，它依照条件测试的是否两种结果来构造返回的数据结构，get(true) 和 get(false) 能即为全部的元素对象。
		//https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
		Map<Boolean, List<User>> mm = users.stream().collect(Collectors.partitioningBy(u->u.getName().equals("zhang")));
		List<User> trueUser=mm.get(true);
		System.out.println(trueUser.toString());
		List<User> falseUser=mm.get(false);
		System.out.println(falseUser.toString());


	}

	protected Integer convert(String s) {
		if (s == null) {
			return -1;
		} else {
			return s.length();
		}
	}

	@Test
	public void aa() {
		List<Object> objectList = Lists.newArrayList("a", 1, "abc", 32, 58, "87ew", new User(222, "zhang"), new User(333, "zhen"));

		Stream<Integer> integerStream = objectList.stream().filter(o -> o instanceof Integer).map(o -> {return Integer.valueOf(o.toString());});
		integerStream.forEach(System.out::println);

		Stream<String> stringStream=objectList.stream().filter(s->s instanceof  String).map(Object::toString);
		stringStream.forEach(System.out::println);

		Stream<User> userStream=objectList.stream().filter(o->o instanceof User).map(o->{return (User)o;});
		List<User> userList=userStream.collect(toList());
		userList.forEach(System.out::println);

	}

	@Test
	public void spliPeek(){
		String str="a_b_c_1_d_22";
		List list=new ArrayList();
		Stream.of(str.split("_")).peek(System.out::println).forEach(e->list.add(e));
		list.stream().forEach(System.out::println);
	}

	/**
	 * fllatmap方法与map方法的比较
	 */
	@Test
	public void peek2(){
		String[] words=new String[]{"Hello","World"};
		List<String[]> a= Arrays.stream(words).map(word->word.split("")).distinct().collect(toList());
		a.forEach(System.out::print);
		System.out.println();
		List<String>  wordList=Lists.newArrayList("Hello","World");
		wordList.stream().flatMap(w->Stream.of(w.split(""))).collect(toList()).forEach(System.out::print);

	}

	@Test
	public void dd(){
		Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e)).collect(toList());

	}

	//Predicate接口:可以理解为一个比较器
	//and、nagate、or
	@Test
	public void ccc(){
		Predicate<String> predicate1=s->s.length()>0;
		Predicate<String> predicate2=s->Integer.valueOf(s)>0;
		System.out.println(predicate1.and(predicate2).test("123"));//&&用比较器对“123”进行判断，是否两个条件都满足
		System.out.println(predicate1.negate().test("123"));//！的效果
		System.out.println(predicate1.or(predicate2).test("123"));//||的效果


		Predicate<String> predicate = (s) -> s.length() > 0;
		System.out.println(predicate.test("foo"));              // true
		System.out.println(predicate.negate().test("foo"));     // false
		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;
		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
		System.out.println(nonNull.test(null));
		System.out.println(isNull.test(null));
		System.out.println(isEmpty.test("sss"));
		System.out.println(isNotEmpty.test(""));

		Predicate<Integer> a=i->i>0;
		Predicate<Integer> b=i->i<10;

		//isEquals方法为static方法，只能如下调用，用途是和null值进行比较，如果方法参数为null就返回Objects::isNull，否则返回参数本身
		System.out.println("==="+Predicate.isEqual(null).test(null));
		System.out.println("==="+Predicate.isEqual(45).test(null));
		System.out.println("==="+Predicate.isEqual(45).test(45));
	}

	//Function
	@Test
	public void aba(){
		Function<Date ,String> a = d -> new SimpleDateFormat("yyyyMMdd").format(d);
		System.out.println(a.apply(new Date()));
	}

	//Supplier
	@Test
	public void sfosf(){
		Supplier<User> s=User::new;
		System.out.println(s.get());
	}

	@Test
	public void t(){
		System.out.println("git push 之后的第一次push");
		Function<String ,Integer> s= Integer::valueOf;
		System.out.println(s.apply("123"));
	}


}

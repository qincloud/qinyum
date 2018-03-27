package qinyum;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestList {
	@Test
	public void test1() {
		List<String> lists = new ArrayList<String>();
		lists.add("1");
		lists.add("2");
		lists.add("3");
		System.out.println(lists.contains("4"));
	}
}

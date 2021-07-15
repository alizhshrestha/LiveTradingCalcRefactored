package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class demo_example {

	public static void main(String[] args) {

		// Create a list and filter all even numbers from list
//		List<Integer> list1 = List.of(2,4,5,6,7,8);

		// using stream api
//		Stream<Integer> stream = list1.stream();
//		List<Integer> newList = stream.filter(i->i %2==0).collect(Collectors.toList());
//		System.out.println(newList);

//		List<Integer> newList = list1.stream().filter(i->i%2==0).collect(Collectors.toList());
//		System.out.println(newList);

//		Stream<Object> emptyStream = Stream.empty();
//		String names[] = {"Alish", "Hari", "Jerry"};
//		
//		Stream<String> stream1 =  Stream.of(names);
//		stream1.forEach(e->{
//			System.out.println(e);
//		});
//		
//		
//		Stream<Object> streamBuilder = Stream.builder().build();
//		
//		IntStream stream = Arrays.stream(new int[] {2,4,5,6});
//		

		// get the result
//		Integer integer = numbers.stream().min((x,y)-> x.compareTo(y)).get();

//		System.out.println(list1);

		calculateStream();

	}

	public static void calculateStream() {
		LocalDate date1 = LocalDate.parse("2017-06-21");
		
		Map<String, LocalDate> m1 = new HashMap();
		m1.put("date", date1);
//		m1.put("number", "9840");
//		m1.put("tel", "40");
//		m1.put("office", "68");
//		m1.put("date", "68");

//		Map<String, String> m2 = new HashMap();
//		m2.put("number", "9818");
//		m2.put("tel", "98");
//		m2.put("office", "86");
//		
//		Map<String, String> m3 = new HashMap();
//		m3.put("number", "8888");
//		m3.put("tel", "70");
//		m3.put("office", "60");
//
//		Map<String, String> m4 = new HashMap();
//		m4.put("number", "7777");
//		m4.put("tel", "12");
//		m4.put("office", "22");

		List<Map<String, LocalDate>> row1 = new ArrayList();
		row1.add(m1);
//		
//		List<Map<String, String>> row2 = new ArrayList();
//		row2.add(m3);
//		row2.add(m4);
		
		Staff st = new Staff();
		st.setId(1);
		st.setRows(row1); 

//
//		Staff st2 = new Staff();
//		st2.setId(2);
//		st2.setRows(row2);  
		
//		List<Staff> list = Arrays.asList(new Staff(1, row1), new Staff(2, row2));
		List<Staff> lt = new ArrayList<Staff>();
		lt.add(st);
//		lt.add(st2);
		
//		System.out.println(lt);

//		Stream.iterate(0, n -> n + 1)
//        .limit(10)
//        .forEach(x -> System.out.println(x));

		List<Staff> l = lt.stream().filter(s -> s.getId() == 1).collect(Collectors.toList());
//		List<Stream<Stream<Map.Entry<String, String>>>> count = l.stream().map(s->s.getRows().stream().map(m->m.entrySet().stream().filter(f->f.getKey().equals("colz")))).collect(Collectors.toList());
//		List<List<Map<String,String>>> n = l.stream().map(m->m.getRows()).collect(Collectors.toList());

//		l.stream().map(m -> m.getRows()).forEach(f -> {
//			List<Map<String, String>> filteredList = f.stream()
//					.filter(map -> map.entrySet().stream().anyMatch(e -> e.getKey().equals("asdf") && e.getValue().equals("8888")))
//					.collect(Collectors.toList());
//			System.out.println(filteredList);
//		});
//		
		
		LocalDate date2 = LocalDate.parse("2017-06-22");
		
		List<Map<String, LocalDate>> finalList = new ArrayList<Map<String,LocalDate>>();
		
		l.stream().map(m->m.getRows()).forEach(f->f.stream().filter(o->o.get("date").compareTo(date2)<=0).forEach(k->{
//			System.out.println(k.get("number"));
//			System.out.println(k);
			finalList.add(k);
			}));
		
		
		for(Map<String, LocalDate> i : finalList) {
			System.out.println(i);
		}

	}
}

package com.trial.test.data_metica;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Hashmap2nd {

	public static HashMap<String, HashMap<String, Integer>> sortByVal(HashMap<String, HashMap<String, Integer>> hmm) {

		List<Map.Entry<String, HashMap<String, Integer>>> list = new LinkedList<Map.Entry<String, HashMap<String, Integer>>>(
				hmm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, HashMap<String, Integer>>>() {
			public int compare(Map.Entry<String, HashMap<String, Integer>> o1,
					Map.Entry<String, HashMap<String, Integer>> o2) {
				HashMap<String, Integer> h1 = o1.getValue();
				HashMap<String, Integer> h2 = o2.getValue();
				int val = 0;
				for (Map.Entry<String, Integer> s1 : h1.entrySet()) {
					for (Map.Entry<String, Integer> s2 : h2.entrySet()) {
						val = s1.getValue() - s2.getValue();
					}
				}
				return val;
			}

		});

		// put data from sorted list to hashmap
		HashMap<String, HashMap<String, Integer>> temp = new LinkedHashMap<String, HashMap<String, Integer>>();
		for (Map.Entry<String, HashMap<String, Integer>> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	// Driver Code
	public static void main(String[] args) {
		HashMap<String, HashMap<String, Integer>> org = new HashMap<String, HashMap<String, Integer>>();

		HashMap<String, Integer> add = new HashMap<String, Integer>();
		HashMap<String, Integer> add1 = new HashMap<String, Integer>();
		HashMap<String, Integer> add2 = new HashMap<String, Integer>();
		HashMap<String, Integer> add3 = new HashMap<String, Integer>();

		add.put("Norway", 60);
		add1.put("Toronto", 75);
		add2.put("California", 30);
		add3.put("LA", 10);

		org.put("Tony", add);
		org.put("Dave", add1);
		org.put("Alex", add2);
		org.put("Dom", add3);
		
		Map<String, HashMap<String, Integer>> sorted_map = sortByVal(org);

		for (Map.Entry<String, HashMap<String, Integer>> entry : sorted_map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

}

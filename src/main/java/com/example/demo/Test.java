package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Test {

	public static void main(String[] args) {
		
		List<Data> floorsheetliveData = new ArrayList<Data>();
		floorsheetliveData.add(new Data("a", 2.0));
		floorsheetliveData.add(new Data("b", 4.0));
		floorsheetliveData.add(new Data("c", 1.0));
		floorsheetliveData.add(new Data("d", 6.0));
		floorsheetliveData.add(new Data("e", 5.0));
		
		Double percentile = 0.0;

		floorsheetliveData.stream().forEach(f -> {
			List<Double> rateList = new ArrayList<Double>();

			
			List<Data> sortedDaysHighLowPercentileList = floorsheetliveData.stream()
					.sorted(Comparator.comparingDouble(Data::getValue)) //sorts floorsheetliveData according to rate asc
					.collect(Collectors.toList());

			sortedDaysHighLowPercentileList.stream().forEach(rate -> {
				Double rt = Double.valueOf(rate.getValue());
				rateList.add(rt);
			});

			Double tckClsPrc = 6.0;
			System.out.println();
			if (tckClsPrc != null) {
				Double pct = getPercentileDataSet(rateList, tckClsPrc).get(1);
				System.out.println("Percentile: " + pct);
			}
		});

		
		
	}
	
	static List<Double> getPercentileDataSet(List<Double> variableList, double variable) {
		double upperBound = 0;
		double lowerBound = 0;
		double percentile = 0;
		double position = 0;
		for (int count = 0; count < variableList.size(); count++) {
			// System.out.println("PerCompare:"+variable+":"+variableList.get(count));
			if (variable == variableList.get(count)) {
				// System.out.println("Yes");
				position = (double) count + 1;
			}
		}
		// System.out.println("Position:"+position);
		if (variableList.size() > 0) {
			percentile = (position / variableList.size()) * 100;
			// System.out.println(percentile);
			upperBound = variableList.get(variableList.size() - 1);
			lowerBound = variableList.get(0);
		}

		List<Double> dataSet = new ArrayList<>();
		dataSet.addAll(Arrays.asList(upperBound, percentile, lowerBound));

		return dataSet;
	}
	
}

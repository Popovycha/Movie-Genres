package com.gmail.mybmcc22;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Hash {
    private Map<String, Integer> map = new HashMap<>();
    public void countFrequencies(List<String> list) {
        //Hash map to store the frequency of element
        for (String i : list) {
            Integer j = map.get(i);
            map.put(i, (j == null) ? 1 : j + 1);
        }
    }

    //Sort hash map by values
    public void sortByValue() {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(map.entrySet());

        //Sorts the list by descending order
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        //Reverts list back to hash map format
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        map = temp;
    }

    // Prints data
    public void printOverall() {
        List<String> artistList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("Genre");
        sb.append(',');
        sb.append("Count");
        sb.append('\n');

        //Displays the occurrence of elements in the array list
        for (Map.Entry<String, Integer> val : map.entrySet()) {
            artistList.add(val.getKey());
            sb.append(val.getKey());
            sb.append(',');
            sb.append(val.getValue());
            sb.append('\n');
        }

        File csvOutputFile = new File("../data/reports/Last_data.csv");
        try (PrintWriter writer= new PrintWriter(csvOutputFile)) {
            writer.write(sb.toString());
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }
    }

    //Prints data for the last 5 years
    public void printPastFiveYears() {
        List<String> artistList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("Genre");
        sb.append(',');
        sb.append("Count");
        sb.append('\n');

        /* Displays the occurrence of elements in the array list */
        for (Map.Entry<String, Integer> val : map.entrySet()) {
            artistList.add(val.getKey());
            sb.append(val.getKey());
            sb.append(',');
            sb.append(val.getValue());
            sb.append('\n');
        }

        File csvOutputFile = new File("../data/reports/Past_5_Years.csv");
        try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
            writer.write(sb.toString());
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }
    }

    //Prints data for a given year
    public void printYear(Integer year) {
        List<String> artistList = new ArrayList<>();;
        StringBuilder sb = new StringBuilder();
        sb.append("Genre");
        sb.append(',');
        sb.append("Count");
        sb.append('\n');

        //Displays the occurrence of elements in the array list
        for (Map.Entry<String, Integer> val : map.entrySet()) {
            artistList.add(val.getKey());
            sb.append(val.getKey());
            sb.append(',');
            sb.append(val.getValue());
            sb.append('\n');
        }

        File csvOutputFile = new File("../data/reports/annual/" + year + ".csv");
        try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
            writer.write(sb.toString());
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }
    }
}

package com.gmail.mybmcc22;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String[] myFiles;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        String path = "../data/";
        File f = new File(path);

        // This filter will only include files ending with .csv
        FilenameFilter filter = (f1, name) -> name.endsWith(".csv");
        // This is how to apply the filter
        myFiles = f.list(filter);

        //appends the path to the filename
        assert myFiles != null;
        for (int i = 0; i < myFiles.length; i++) {
            myFiles[i] = path + myFiles[i];
        }

        for (String element: myFiles) {
            System.out.println(element);
        }

        String csvFile = myFiles[0];
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        List<String> values = new ArrayList<>();
        List<String> valuesForPastFiveYears = new ArrayList<>();

        //Creates ArrayLists corresponding to years from 1900-2020
        @SuppressWarnings("unchecked")
        ArrayList<String>[] Year = new ArrayList[2021];
        for( int i = 0; i < 2021; i++) {
            Year[i] = new ArrayList<>();
        }

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // Uses comma as a delimiter
                String[] country = line.split(cvsSplitBy);
                String[] genres = country[2].split("\\|");
                String temp = country[1];

                //Regex patterns for year
                Pattern p = Pattern.compile("\\((\\d{4}?)\\)$");
                Matcher m = p.matcher(temp);

                while(m.find()) {
                    if (country[1].equals("title")) {
                    } else {
                        //data for past 5 years
                        int year = Integer.parseInt(m.group(0).replace("(", "").replace(")", ""));
                        if (year == 2018 || year == 2017 || year == 2016 || year == 2015 || year == 2014) {
                            valuesForPastFiveYears.addAll(Arrays.asList(genres));
                        }

                        //data per year
                        if (year < 2021) {
                            for (String blah: genres) {
                                Year[year].add(blah);
                            }
                        }

                        // overall data
                        values.addAll(Arrays.asList(genres));
                    }
                }

                //generates reports
                Hash count = new Hash();
                count.countFrequencies(values);
                count.sortByValue();
                count.printOverall();

                Hash fiveYears = new Hash();
                fiveYears.countFrequencies(valuesForPastFiveYears);
                fiveYears.sortByValue();
                fiveYears.printPastFiveYears();

                //data
                for (int i = 1900; i < 2021; i++) {
                    Hash twenty = new Hash();
                    twenty.countFrequencies(Year[i]);
                    twenty.sortByValue();
                    twenty.printYear(i);
                }
            }
        } catch (IOException error) {
            error.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        }
    }
}

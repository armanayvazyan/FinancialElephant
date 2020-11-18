package com.example.financialelephant.Utilities;

import java.util.ArrayList;

public class Analyser {

    public static double[] AnaliseWithGivenParameters(ArrayList<Company> companies, Attribute attribute) {
        int size = companies.size();
        double[][] array2D = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int first = companies.get(i).getAttributeValue(attribute);
                int second = companies.get(j).getAttributeValue(attribute);

                array2D[i][j] = first >= second ? (first == second ? 1 : 1.5) : 0.5;
            }
        }

        double[] result = new double[size];
        for (int m = 0; m < array2D.length; m++) {
            double total = 0;
            for (int n = 0; n < array2D.length; n++)
                total += array2D[m][n];
            result[m] = total;
        }

        double[] p = new double[size];

        for (int i = 0; i < size; i++) {
            p[i] = 0;
            for (int j = 0; j < size; j++) {
                p[i] += result[j] * array2D[i][j];
            }
        }

        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += p[i];
        }

        double[] PNRelative = new double[size];

        for (int i = 0; i < size; i++) {
            PNRelative[i] = p[i] / sum;
        }


        return PNRelative;
    }

    public static double[] AnaliseWithGivenParameters(ArrayList<Attribute> attributes) {
        int size = attributes.size();
        double[][] array2D = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int first = attributes.get(i).getValue();
                int second = attributes.get(j).getValue();

                array2D[i][j] = first >= second ? (first == second ? 1 : 1.5) : 0.5;
            }
        }

        double[] result = new double[size];
        for (int m = 0; m < array2D.length; m++) {
            double total = 0;
            for (int n = 0; n < array2D.length; n++)
                total += array2D[m][n];
            result[m] = total;
        }

        double[] p = new double[size];

        for (int i = 0; i < size; i++) {
            p[i] = 0;
            for (int j = 0; j < size; j++) {
                p[i] += result[j] * array2D[i][j];
            }
        }

        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += p[i];
        }

        double[] PNRelative = new double[size];

        for (int i = 0; i < size; i++) {
            PNRelative[i] = p[i] / sum;
        }

        return PNRelative;
    }

    public static Company analyseData(ArrayList<Company> companiesList, ArrayList<Attribute> attributeList) {
        double[][] p = new double[attributeList.size()][companiesList.size()];
        int k = 0;
        for (Attribute attribute : attributeList) {
            p[k++] = Analyser.AnaliseWithGivenParameters(companiesList, attribute);
        }

        double[] pp = Analyser.AnaliseWithGivenParameters(attributeList);

        double[] finalResults = new double[companiesList.size()];

        for (int i = 0; i < companiesList.size(); i++) {
            finalResults[i] = 0;
            for (int j = 0; j < attributeList.size(); j++) {
                finalResults[i] += pp[j] * p[j][i];
            }
        }
        System.out.println("WINNING COMPANY NAME");
        System.out.println();
        double max = finalResults[0];
        for (int i = 0; i < finalResults.length; i++) {
            if (i > 0) {
                if (finalResults[i] > max)
                    max = finalResults[i];
            }
        }
        int index = 0;
        for (double q : finalResults) {
            if (q == max) {
                return companiesList.get(index);
            } else
                index++;
        }
        return null;
    }
}

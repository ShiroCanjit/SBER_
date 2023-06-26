import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int counter = 0;
        String fileNameDefined = "source/Задача ВС Java Сбер.csv";
        File file = new File(fileNameDefined);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDefined))) {
            while ((reader.readLine()) != null) {
                counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Scanner inputStream = new Scanner(file);
            City[] cities = new City[counter];
            counter=0;
            while (inputStream.hasNext()) {
                String inLine = inputStream.nextLine();
                    Scanner inWord = new Scanner(inLine);
                    inWord.useDelimiter(";");
                    inWord.nextInt();
                    cities[counter] = new City(inWord.next(), inWord.next(), inWord.next(),
                            inWord.next(), (inWord.hasNext()) ? inWord.next() : "");
                   // System.out.println(cities[counter].toString());
                    inWord.close();
                counter++;
                }
            inputStream.close();


            System.out.println(Arrays.toString(cities));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

class City implements Comparable<City>{
     String name, region, district, foundation, population;

    public City(String name, String region, String district, String population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }
    public String getName(){
        return name;
    }
    public String getDistrict(){
        return district;
    }
    @Override
    public int compareTo(City o) {
        return this.name.compareTo(o.getName());
    }
    @Override
    public String toString()
    {
        return "City{name ='" + this.name +
                "', region='" + this.region +
                "', district='" + this.district +
                "', population='" + this.population +
                "', foundation='" + this.foundation + "'}";
    }

}
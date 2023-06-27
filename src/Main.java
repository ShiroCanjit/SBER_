import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileNameDefined = "source/Задача ВС Java Сбер.csv";
        File file = new File(fileNameDefined);
        try {
            Scanner inputStream = new Scanner(file);
            ArrayList<City> cities = new ArrayList<>();
            while (inputStream.hasNext()) {
                String inLine = inputStream.nextLine();
                Scanner inWord = new Scanner(inLine);
                inWord.useDelimiter(";");
                inWord.nextInt();
                City currentCity = new City(inWord.next(), inWord.next(), inWord.next(),
                        inWord.next(), (inWord.hasNext()) ? inWord.next() : "");
                cities.add(currentCity);
                System.out.println(currentCity);
                inWord.close();
            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

class City {
    String name, region, district, foundation, population;

    public City(String name, String region, String district, String population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{name ='" + this.name +
                "', region='" + this.region +
                "', district='" + this.district +
                "', population='" + this.population +
                "', foundation='" + this.foundation + "'}";
    }

}
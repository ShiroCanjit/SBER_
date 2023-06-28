import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<City> cities;

    public static void main(String[] args) {
        String fileNameDefined = "source/Задача ВС Java Сбер.csv";
        File file = new File(fileNameDefined);
        Scanner inputStream;
        try {
            inputStream = new Scanner(file);
            cities = new ArrayList<>();
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
            //System.out.println(cities);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        inputStream = new Scanner(System.in);
        System.out.println("Введите 1 для сортировки по наименованию, 2  для сортировки по двум полям справочника " +
                "– федеральному округу и наименованию города, 3 для поиска города с наибольшим населением");
        int chooser = inputStream.nextInt();
        switch (chooser) {
            case 1 -> {
                Collections.sort(cities);
                System.out.println(cities);
            }
            case 2 -> {
                cities.sort(new comparatorByDistrict().thenComparing(City::getName));
                System.out.println(cities);
            }
            case 3 -> FindMaxPopulation();
            default -> System.out.println("Ошибка Вода");
        }

    }

    public static void FindMaxPopulation() {
        City[] arrayCities;
        long max = 0;
        int index = 0;
        arrayCities = cities.toArray(new City[0]);
        for (int i = 0; i < arrayCities.length; i++) {
            if (Integer.parseInt(arrayCities[i].getPopulation()) > max) {
                max = Integer.parseInt(arrayCities[i].getPopulation());
                index = i;
            }
        }
        System.out.println("[" + index + "] = " + max);
    }

}

class City implements Comparable<City> {
    String name, region, district, foundation, population;

    public City(String name, String region, String district, String population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{name ='" + this.name +
                "', region='" + this.region +
                "', district='" + this.district +
                "', population='" + this.population +
                "', foundation='" + this.foundation + "'}";
    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.name);
    }
}

class comparatorByDistrict implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        return o1.getDistrict().compareTo(o2.getDistrict());
    }
}
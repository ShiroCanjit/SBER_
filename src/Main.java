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
        System.out.println("Введите 1, для сортировки по наименованию, и 2  для сортировки по двум полям справочника " +
                "– федеральному округу и наименованию города");
        int chooser = inputStream.nextInt();
        switch (chooser) {
            case 1 -> Collections.sort(cities);
            case 2 -> cities.sort(new comparatorByDistrictName().thenComparing(City::getName));
            default -> System.out.println("Ошибка Вода");
        }
        System.out.println(cities);

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
class comparatorByDistrictName implements Comparator<City>{
    @Override
    public int compare(City o1, City o2) {
        return o1.getDistrict().compareTo( o2.getDistrict());
    }
}
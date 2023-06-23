import java.io.*;
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
            City[] city = new City[counter];
            while (inputStream.hasNext()) {
                String inLine = inputStream.nextLine();
                for (int i = 0; i < inLine.lines().count(); i++) {
                    Scanner inWord = new Scanner(inLine);
                    inWord.useDelimiter(";");
                    inWord.nextInt();
                    city[i] = new City(inWord.next(), inWord.next(), inWord.next(),
                            inWord.next(), (inWord.hasNext()) ? inWord.next() : "");
                    System.out.println(city[i].ShowObject());
                    inWord.close();
                }
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

    public String ShowObject() {
        return "City{name ='" + this.name +
                "', region='" + this.region +
                "', district='" + this.district +
                "', population='" + this.population +
                "', foundation='" + this.foundation + "'}";
    }

}
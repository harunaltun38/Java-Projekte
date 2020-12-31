import java.io.*;
import java.util.*;

public class Manager {
    private ArrayList<Town> towns;
    private TreeMap<String, Integer> laenderHaeufigkeit;
    String file = "C:/Users/admin38/IdeaProjects/townsApp/Towns.txt";

    public Manager() {
        this.towns = new ArrayList<>();
        this.laenderHaeufigkeit = new TreeMap<>();

    }

    public void menu() {

        int enter = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("-----Menü-----");
            System.out.println("[1] Textdatei einlesen und nach Land/Stadt sortieren");
            System.out.println("[2] Länder und Gesamteinwohnerzahl in Millionenstädten ausgeben");
            System.out.println("[9] Programm beenden");
            System.out.print("Ihre Wahl:");
            enter = scanner.nextInt();
            switch (enter) {
                case 1:
                    einlesen(this.file);
                    sortiereNachLandAlphabetisch(this.towns);
                    ausgeben(this.towns);
                    break;
                case 2:
                    laenderUndGesamtZahlEinwohnerBerechnen(this.towns,this.laenderHaeufigkeit);
                    laenderUndGesamtZahlEinwohnerAusgeben(this.laenderHaeufigkeit);
                    break;
                case 9:
                    System.out.println("Beenden...");
                    break;
            }
        } while (enter != 9);
    }

    public void einlesen(String file) {
        String name = "";
        int einwohnerzahl = 0;
        String land = "";
        String kontinent = "";
        String einwohnerzahlInString = "";
        Scanner scan = null;
        try {
            scan = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//skippe die ersten zwei Einträge
        for (int i = 0; i < 2; i++) {
            assert scan != null;
            if (!scan.hasNext()) break;
            String line = scan.nextLine();
        }

        while (scan.hasNext()) {
            //   assert scan != null;
            //   if (!scan.hasNext()) break;
            String line = scan.nextLine();
            //kick whitespace and comma
            List<String> result = Arrays.asList(line.split("\\s*;\\s*"));
            //   List<String> elephantList = Arrays.asList(str.split(","));
            name = result.get(0);
            einwohnerzahlInString = result.get(1);
            einwohnerzahlInString = einwohnerzahlInString.replace(".", "");
            einwohnerzahl = Integer.parseInt(einwohnerzahlInString);
            land = result.get(2);
            kontinent = result.get(3);

            Town town = new Town(name, einwohnerzahl, land, kontinent);
            this.towns.add(town);

        }
        scan.close();
        System.out.println("Es wurden "+ towns.size()+" Städte eingelesen" );
    }


    public void sortiereNachLandAlphabetisch(ArrayList<Town> towns) {
        Collections.sort(towns, new Comparator<Town>() {
            @Override
            public int compare(Town town1, Town town2) {
                //Bei gleichen Ländern --> Sortiere nach Stadtnamen aufsteigend
                if (town1.getLand().equals(town2.getLand())) {
                    return town1.getName().compareTo(town2.getName());
                }
                //aufsteigend sortieren nach Land
                else {
                    return town1.getLand().compareTo(town2.getLand());
                }
            }
        });
    }

    public void ausgeben(ArrayList<Town> towns) {

        for (int i = 0; i < towns.size(); i++) {
            System.out.print(towns.get(i).getLand() + " ");
            System.out.println(towns.get(i).getName());
        }

    }

    public void laenderUndGesamtZahlEinwohnerBerechnen(ArrayList<Town> towns,TreeMap<String, Integer> laenderHaeufigkeit) {
        for (int i = 0; i < towns.size(); i++) {

            String name = towns.get(i).getLand();
            Integer sum = laenderHaeufigkeit.get(name);
            if (sum == null) {
                sum = 0;
            }
            sum = sum + towns.get(i).getEinwohnerzahl();
            laenderHaeufigkeit.put(name, sum);
        }
    }


    public void laenderUndGesamtZahlEinwohnerAusgeben(TreeMap<String, Integer> laenderHaeufigkeit) {
        for (Map.Entry<String, Integer> entry : laenderHaeufigkeit.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }
}

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void sortiereNachLandAlphabetisch() {
        ArrayList<Town> townsActual = new ArrayList<>();
        ArrayList<Town> townsExpected = new ArrayList<>();
        Manager manager = new Manager();

        Town town = new Town("Berlin", 23456, "Deutschland", "Europa");
        Town town2 = new Town("New York", 98743, "USA", "Amerika");
        Town town3 = new Town("Paris", 565797, "Frankfreich", "Europa");

        townsActual.add(town);
        townsActual.add(town2);
        townsActual.add(town3);

        townsExpected.add(town);
        townsExpected.add(town3);
        townsExpected.add(town2);
        manager.sortiereNachLandAlphabetisch(townsActual);
        assertEquals(townsExpected, townsActual);

    }

    @Test
    void laenderUndGesamtZahlEinwohnerBerechnen() {
        ArrayList<Town> towns = new ArrayList<>();
        TreeMap<String, Integer> laenderHaeufigkeit = new TreeMap<>();
        String result = "";
        Manager manager = new Manager();
        Town town = new Town("Berlin", 23456, "Deutschland", "Europa");
        Town town2 = new Town("Mannheim", 98743, "Deutschland", "Europa");
        Town town3 = new Town("Frankfurt", 565797, "Deutschland", "Europa");

        towns.add(town);
        towns.add(town2);
        towns.add(town3);

        manager.laenderUndGesamtZahlEinwohnerBerechnen(towns, laenderHaeufigkeit);
        for (Map.Entry<String, Integer> entry : laenderHaeufigkeit.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
             result = key + " " + value;
        }

        assertEquals("Deutschland 687996", result);
    }
}
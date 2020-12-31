public class Town {
    private String name = "";
    private int einwohnerzahl = 0;
    private String land = "";
    private String kontinent = "";

    public Town(String name, int einwohnerzahl, String land, String kontinent) {

        this.name = name;
        this.einwohnerzahl = einwohnerzahl;
        this.land = land;
        this.kontinent = kontinent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEinwohnerzahl() {
        return einwohnerzahl;
    }

    public String getLand() {
        return land;
    }

    public String getKontinent() {
        return kontinent;
    }
}

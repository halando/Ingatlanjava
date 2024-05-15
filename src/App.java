import java.io.IOException;
import java.util.Comparator;
import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {
      
        System.out.println("Ingatlanok");

        List<Ad> hirdetesek = null;
        try {
            hirdetesek = Ad.loadFromCsv("realestates.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // List<Ad> hirdetesek = Ad.loadFromJson("realestates.json");

        double atlag = hirdetesek.stream()
                .filter(ad -> ad.getFloors() == 0)
                .mapToDouble(Ad::getArea)
                .average()
                .orElse(0.0);
        System.out.println("1. Földszinti ingatlanok átlagos alapterülete: " + String.format("%.2f", atlag) + " m2");

        Ad adat = hirdetesek.stream()
                .filter(Ad::isFreeOfCharge)
                .min(Comparator.comparingDouble(a -> a.distanceTo(47.4164220114023, 19.066342425796986)))
                .orElse(null);
        if (adat != null) {
            System.out.println("2. Mesevár óvodához légvonalban legközelebbi tehermentes ingatlan adatai: ");
            System.out.println("\tEladó neve     : " + adat.getSeller().getName());
            System.out.println("\tEladó telefonja: " + adat.getSeller().getPhone());
            System.out.println("\tAlapterület    : " + adat.getArea());
            System.out.println("\tSzobák száma   : " + adat.getRooms());
        }

    }
}

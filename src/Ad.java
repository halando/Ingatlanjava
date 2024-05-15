import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Ad {
    private int id;
    private int rooms;
    private int area;
    private int floors;
    private Category category;
    private Seller seller;
    private String description;
    private String imageUrl;
    private String latLong;
    private double latitude;
    private double longitude;
    private String createdAt;
    private boolean freeOfCharge;

    public Ad() {}

    public Ad(String line) {
        String[] data = line.split(";");
        id = Integer.parseInt(data[0]);
        rooms = Integer.parseInt(data[1]);
        latLong = data[2];
        floors = Integer.parseInt(data[3]);
        area = Integer.parseInt(data[4]);
        description = data[5];
        freeOfCharge = data[6].equals("1");
        imageUrl = data[7];
        createdAt = data[8];
        seller = new Seller(Integer.parseInt(data[9]), data[10], data[11]);
        category = new Category(Integer.parseInt(data[12]), data[13]);

        String[] coordinates = latLong.split(",");
        latitude = Double.parseDouble(coordinates[0]);
        longitude = Double.parseDouble(coordinates[1]);
    }

    public static List<Ad> loadFromCsv(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        List<Ad> ads = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            ads.add(new Ad(lines.get(i)));
        }
        return ads;
    }

    public static List<Ad> loadFromJson(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        Gson gson = new Gson();
        Ad[] ads = gson.fromJson(json, Ad[].class);
        List<Ad> adList = new ArrayList<>();
        for (Ad ad : ads) {
            adList.add(ad);
        }
        return adList;
    }

    public double distanceTo(double lat, double lon) {
        double dx = Math.abs(lat - latitude);
        double dy = Math.abs(lon - longitude);
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isFreeOfCharge() {
        return freeOfCharge;
    }

    public void setFreeOfCharge(boolean freeOfCharge) {
        this.freeOfCharge = freeOfCharge;
    }
}

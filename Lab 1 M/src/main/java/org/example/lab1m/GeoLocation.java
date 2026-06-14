package org.example.lab1m;

public class GeoLocation {

    private String coordinates;
    private String address;
    private String postalCode;

    public GeoLocation(String coordinates,
                       String address,
                       String postalCode) {

        this.coordinates = coordinates;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }
}

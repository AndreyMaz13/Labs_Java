package org.example.lab1m;

import java.util.ArrayList;
import java.util.List;

public class ConverterModel {

    private final List<GeoLocation> locations =
            new ArrayList<>();

    public ConverterModel() {

        locations.add(
                new GeoLocation(
                        "55.7558,37.6176",
                        "Москва",
                        "101000"
                )
        );

        locations.add(
                new GeoLocation(
                        "59.9343,30.3351",
                        "Санкт-Петербург",
                        "190000"
                )
        );

        locations.add(
                new GeoLocation(
                        "55.7961,49.1064",
                        "Казань",
                        "420000"
                )
        );
    }

    public String convert(String value,
                          String from,
                          String to) {

        if (value == null) {
            return "Данные не найдены";
        }

        value = value.trim();

        for (GeoLocation location : locations) {

            boolean found = false;

            if (from.equals("Координаты")
                    && location.getCoordinates()
                    .trim()
                    .equalsIgnoreCase(value)) {

                found = true;
            }

            if (from.equals("Адрес")
                    && location.getAddress()
                    .trim()
                    .equalsIgnoreCase(value)) {

                found = true;
            }

            if (from.equals("Почтовый индекс")
                    && location.getPostalCode()
                    .trim()
                    .equalsIgnoreCase(value)) {

                found = true;
            }

            if (found) {

                switch (to) {

                    case "Координаты":
                        return location.getCoordinates();

                    case "Адрес":
                        return location.getAddress();

                    case "Почтовый индекс":
                        return location.getPostalCode();
                }
            }
        }

        return "Данные не найдены";
    }
}
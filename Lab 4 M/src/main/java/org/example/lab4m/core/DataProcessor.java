package org.example.lab4m.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessor {

    public static Integer processVariant21(List<Integer> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }

        Map<Integer, Integer> frequency =
                new HashMap<>();

        for (Integer number : list) {

            frequency.put(
                    number,
                    frequency.getOrDefault(number, 0) + 1
            );
        }

        int maxCount = 0;
        Integer result = null;

        for (Map.Entry<Integer, Integer> entry :
                frequency.entrySet()) {

            int number = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount) {

                maxCount = count;
                result = number;

            } else if (count == maxCount
                    && number < result) {

                result = number;
            }
        }

        return result;
    }

    public static String[] processPipeline(
            String[] input
    ) {

        List<Integer> numbers =
                new ArrayList<>();

        for (String value : input) {

            try {

                numbers.add(
                        Integer.parseInt(
                                value.trim()
                        )
                );

            } catch (Exception ignored) {
            }
        }

        Integer result =
                processVariant21(numbers);

        if (result == null) {

            return new String[]{
                    "Нет данных"
            };
        }

        return new String[]{
                String.valueOf(result)
        };
    }
}
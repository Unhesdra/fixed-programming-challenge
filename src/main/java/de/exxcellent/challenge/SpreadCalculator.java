package de.exxcellent.challenge;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SpreadCalculator {

    private final Map<String, List<Integer>> map;

    public SpreadCalculator(Map<String, List<Integer>> map) {
        this.map = map;
    }

    public String getMinimumSpread() {
        int minimalSpread = 0;
        boolean isFirstElement = true;
        String minimalKey = "";

        Iterator<Map.Entry<String, List<Integer>>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, List<Integer>> entry = iterator.next();
            if(!isFirstElement) {
                int spread = Math.abs(entry.getValue().get(0) - entry.getValue().get(1));
                if(spread < minimalSpread) {
                    minimalSpread = spread;
                    minimalKey = entry.getKey();
                }
            } else {
                minimalSpread = Math.abs(entry.getValue().get(0) - entry.getValue().get(1));
                minimalKey = entry.getKey();
                isFirstElement = false;
            }
        }

        return minimalKey;
    }
}

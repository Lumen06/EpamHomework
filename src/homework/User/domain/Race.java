package homework.User.domain;

import java.util.HashMap;
import java.util.Map;

public enum Race {

    CAUCASOID("Europe, North America, Central Asia"),
    NEGROID("Africa"),
    ETHIOPIAN("Africa and European features"),
    MONGOLOID("Asia"),
    AMERICANONE("North and South America"),;

    private static Map<String, Race> raceMap = new HashMap<>();

    static {
        for (Race race : Race.values()) {
            raceMap.put(race.name(), race);
        }
    }

    String description;

    Race(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isStringBelongsToEnumValues(String string) {
        return raceMap.containsKey(string);
    }

    public static Race getRaceFromMap(String raceName) {
        return raceMap.get(raceName);

    }
}

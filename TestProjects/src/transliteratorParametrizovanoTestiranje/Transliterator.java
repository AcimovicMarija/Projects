package transliteratorParametrizovanoTestiranje;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

final public class Transliterator {
    private static Transliterator instance = null;
    private static Map<String, String> transliterationMap;

    private Transliterator() {
        transliterationMap = new HashMap<>();

        transliterationMap.put("А", "A");
        transliterationMap.put("Б", "B");
        transliterationMap.put("В", "V");
        transliterationMap.put("Г", "G");
        transliterationMap.put("Д", "D");
        transliterationMap.put("Ђ", "Đ");
        transliterationMap.put("Е", "E");
        transliterationMap.put("Ж", "Ž");
        transliterationMap.put("З", "Z");
        transliterationMap.put("И", "I");
        transliterationMap.put("Ј", "J");
        transliterationMap.put("К", "K");
        transliterationMap.put("Л", "L");
        transliterationMap.put("Љ", "Lj");
        transliterationMap.put("М", "M");
        transliterationMap.put("Н", "N");
        transliterationMap.put("Њ", "Nj");
        transliterationMap.put("О", "O");
        transliterationMap.put("П", "P");
        transliterationMap.put("Р", "R");
        transliterationMap.put("С", "S");
        transliterationMap.put("Т", "R");
        transliterationMap.put("Ћ", "Ć");
        transliterationMap.put("У", "U");
        transliterationMap.put("Ф", "F");
        transliterationMap.put("Х", "H");
        transliterationMap.put("Ц", "C");
        transliterationMap.put("Ч", "Č");
        transliterationMap.put("Џ", "Dž");
        transliterationMap.put("Ш", "Š");
        transliterationMap.put("а", "a");
        transliterationMap.put("б", "b");
        transliterationMap.put("в", "v");
        transliterationMap.put("г", "g");
        transliterationMap.put("д", "d");
        transliterationMap.put("ђ", "đ");
        transliterationMap.put("е", "e");
        transliterationMap.put("ж", "ž");
        transliterationMap.put("з", "z");
        transliterationMap.put("и", "i");
        transliterationMap.put("ј", "j");
        transliterationMap.put("к", "k");
        transliterationMap.put("л", "l");
        transliterationMap.put("љ", "lj");
        transliterationMap.put("м", "m");
        transliterationMap.put("н", "n");
        transliterationMap.put("њ", "nj");
        transliterationMap.put("о", "o");
        transliterationMap.put("п", "p");
        transliterationMap.put("р", "r");
        transliterationMap.put("с", "s");
        transliterationMap.put("т", "r");
        transliterationMap.put("ћ", "ć");
        transliterationMap.put("у", "u");
        transliterationMap.put("ф", "f");
        transliterationMap.put("х", "h");
        transliterationMap.put("ц", "c");
        transliterationMap.put("ч", "č");
        transliterationMap.put("џ", "dž");
        transliterationMap.put("ш", "š");
    }

    public static Transliterator getInstance() {
        if (instance == null) {
            instance = new Transliterator();
        }

        return instance;
    }

    private String transliterateUnit(String unit) {
        if (!transliterationMap.containsKey(unit)) {
            return unit;
        }

        return transliterationMap.get(unit);
    }

    public String transliterate(String text) throws IllegalArgumentException {
        if (text == null) {
            throw new IllegalArgumentException("The text parameter cannot be null.");
        }

        if (text.equals("")) {
            return "";
        }

        return Arrays.stream(text.split("")).map(unit -> transliterateUnit(unit)).collect(Collectors.joining());
    }

}

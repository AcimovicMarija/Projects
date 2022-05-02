package transliteratorParametrizovanoTestiranje;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import transliteratorParametrizovanoTestiranje.Transliterator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

class TransliteratorTest {

    @Test
    void transliterate_ShouldReturnAnEmptyString_IfEmptyStringGiven() {
        Transliterator t = Transliterator.getInstance();

        String expected = "";
        String actual = t.transliterate("");

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> getTransliterationMap(){
        return Stream.of(
                Arguments.of("A", "A"),
                Arguments.of("Б", "B"),
                Arguments.of("В", "V"),
                Arguments.of("Г", "G"),
                Arguments.of("Д", "D"),
                Arguments.of("Ђ", "Đ"),
                Arguments.of("Е", "E"),
                Arguments.of("Ж", "Ž"),
                Arguments.of("З", "Z"),
                Arguments.of("И", "I"),
                Arguments.of("Ј", "J"),
                Arguments.of("К", "K"),
                Arguments.of("Л", "L"),
                Arguments.of("Љ", "Lj"),
                Arguments.of("М", "M"),
                Arguments.of("Н", "N"),
                Arguments.of("Њ", "Nj"),
                Arguments.of("О", "O"),
                Arguments.of("П", "P"),
                Arguments.of("Р", "R"),
                Arguments.of("С", "S"),
                Arguments.of("Т", "T"),
                Arguments.of("Ћ", "Ć"),
                Arguments.of("У", "U"),
                Arguments.of("Ф", "F"),
                Arguments.of("Х", "H"),
                Arguments.of("Ц", "C"),
                Arguments.of("Ч", "Č"),
                Arguments.of("Џ", "Dž"),
                Arguments.of("Ш", "Š"),
                Arguments.of("а", "a"),
                Arguments.of("б", "b"),
                Arguments.of("в", "v"),
                Arguments.of("г", "g"),
                Arguments.of("д", "d"),
                Arguments.of("ђ", "đ"),
                Arguments.of("е", "e"),
                Arguments.of("ж", "ž"),
                Arguments.of("з", "z"),
                Arguments.of("и", "i"),
                Arguments.of("ј", "j"),
                Arguments.of("к", "k"),
                Arguments.of("л", "l"),
                Arguments.of("љ", "lj"),
                Arguments.of("м", "m"),
                Arguments.of("н", "n"),
                Arguments.of("њ", "nj"),
                Arguments.of("о", "o"),
                Arguments.of("п", "p"),
                Arguments.of("р", "r"),
                Arguments.of("с", "s"),
                Arguments.of("т", "t"),
                Arguments.of("ћ", "ć"),
                Arguments.of("у", "u"),
                Arguments.of("ф", "f"),
                Arguments.of("х", "h"),
                Arguments.of("ц", "c"),
                Arguments.of("ч", "č"),
                Arguments.of("џ", "dž"),
                Arguments.of("ш", "š")

        );
    }

    @ParameterizedTest
    @MethodSource("getTransliterationMap")
    public void transliterate_ShouldReturnExpectedResult(String from, String to){
        Transliterator t = Transliterator.getInstance();

        String expected = to;
        String actual = t.transliterate(from);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getTransliterationMap")
    public void transliterateUnit_ShouldReturnExpectedResult(String from, String to){
        Transliterator t = Transliterator.getInstance();

        try {
            Method transliterateUnit = t.getClass().getDeclaredMethod(
                    "transliterateUnit", new Class[]{String.class});

            transliterateUnit.setAccessible(true);

            String expected = to;
            String actual = (String) transliterateUnit.invoke(t, from);

            Assertions.assertEquals(expected,actual);

        } catch (NoSuchMethodException e) {
            Assertions.fail(e);
        } catch (InvocationTargetException e) {
            Assertions.fail(e);
        } catch (IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void transliterateUnit_ShouldReturnTheExactSourceText_IfSourceTextDoesNotExistInTheTranslationMap(){
        Transliterator t = Transliterator.getInstance();

        try {
            Method transliterateUnit = t.getClass().getDeclaredMethod(
                    "transliterateUnit", new Class[]{String.class});

            transliterateUnit.setAccessible(true);

            String expected = "!";
            String actual = (String) transliterateUnit.invoke(t, "!");

            Assertions.assertEquals(expected,actual);

        } catch (NoSuchMethodException e) {
            Assertions.fail(e);
        } catch (InvocationTargetException e) {
            Assertions.fail(e);
        } catch (IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void transliterate_ShouldThrowAnIllegalArgumentException_IfGivenTextIsNull(){
        Transliterator t = Transliterator.getInstance();

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            t.transliterate(null);
        });


    }
}
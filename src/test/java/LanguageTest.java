import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LanguageTest {

    @Test
    void fromStringTest() {
        Optional<Language> optLanguage = Language.fromString("French");
        assertTrue(optLanguage.isPresent(), "No language returned");
        assertEquals(Language.French, optLanguage.get(), "Incorrect language returned");
    }

    @Test
    void frenchGreetTest() {
        assertEquals("Bonjour 🇫🇷", Language.French.greet(), "Incorrect greet returned");
    }

    @Test
    void englishGreetTest() {
        assertEquals("Hello 🇬🇧", Language.English.greet(), "Incorrect greet returned");
    }
}

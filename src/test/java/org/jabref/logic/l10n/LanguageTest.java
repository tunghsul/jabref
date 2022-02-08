package org.jabref.logic.l10n;

import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LanguageTest {

    @Test
    void convertKnownLanguageOnly() {
        assertEquals(Optional.of(new Locale("en")), Language.convertToSupportedLocale(Language.ENGLISH));
    }

    @Test
    void convertKnownLanguageAndCountryCorrect() {
        // Language and country code have to be separated see: https://stackoverflow.com/a/3318598
        assertEquals(Optional.of(new Locale("pt", "BR")), Language.convertToSupportedLocale(Language.BRAZILIAN_PORTUGUESE));
    }

    @Test
    void convertToKnownLocaleNull() {
        assertThrows(NullPointerException.class, () -> Language.convertToSupportedLocale(null));
    }

    @Test
    void convertChineseCN() {
        assertEquals(Optional.of(new Locale("zh", "CN")), Language.convertToSupportedLocale(Language.SIMPLIFIED_CHINESE));
    }

    @Test
    void convertChineseTW() {
        assertEquals(Optional.of(new Locale("zh", "TW")), Language.convertToSupportedLocale(Language.TRADITIONAL_CHINESE));
    }

    @Test
    void convertJapanese() {
        assertEquals(Optional.of(new Locale("ja")), Language.convertToSupportedLocale(Language.JAPANESE));
    }

    @Test
    void convertIndonesia() {
        assertEquals(Optional.of(new Locale("in")), Language.convertToSupportedLocale(Language.BAHASA_INDONESIA));
    }

    @Test
    void convertKoreanWithDiffID() {
        assertEquals(Optional.of(new Locale("en")), Language.convertToSupportedLocale(Language.KOREAN));
    }
}

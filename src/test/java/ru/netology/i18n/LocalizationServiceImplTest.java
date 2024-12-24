package ru.netology.i18n;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {
    LocalizationService localizationService;

    @BeforeEach
    void beforeEach() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void testLocale() {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.USA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.GERMANY));
    }
}
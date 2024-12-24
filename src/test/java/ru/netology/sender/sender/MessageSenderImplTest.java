package ru.netology.sender.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @Test
    public void test_Send_Moscow() {
        String expected = "Добро пожаловать";
        GeoService moscowIP = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(moscowIP.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        LocalizationService localizationServiceRu = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceRu.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(moscowIP, localizationServiceRu);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(messageSender.IP_ADDRESS_HEADER, "172.0.32.11");
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);

        GeoService moscowIP2 = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(moscowIP2.byIp("172."))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationServiceRu2 = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceRu2.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender2 = new MessageSenderImpl(moscowIP2, localizationServiceRu2);
        Map<String, String> headers2 = new HashMap<String, String>();
        headers2.put(messageSender2.IP_ADDRESS_HEADER, "172.");
        String result2 = messageSender2.send(headers2);
        Assertions.assertEquals(expected, result2);
    }

    @Test
    public void test_Send_New_York() {
        String expected = "Welcome";
        GeoService newYorkIP = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(newYorkIP.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        LocalizationService localizationServiceUSA = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceUSA.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(newYorkIP, localizationServiceUSA);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(messageSender.IP_ADDRESS_HEADER, "96.44.183.149");
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);


        GeoService newYorkIP2 = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(newYorkIP2.byIp("96."))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationServiceUSA2 = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceUSA2.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender2 = new MessageSenderImpl(newYorkIP2, localizationServiceUSA2);
        Map<String, String> headers2 = new HashMap<String, String>();
        headers2.put(messageSender2.IP_ADDRESS_HEADER, "96.");
        String result2 = messageSender2.send(headers2);
        Assertions.assertEquals(expected, result2);
    }

    @Test
    public void test_Send_GeoService_Null() {
        String expected = "Welcome";
        GeoService USA = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(USA.byIp(""))
                .thenReturn(null);
        LocalizationService localizationServiceUSA = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceUSA.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(USA, localizationServiceUSA);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(messageSender.IP_ADDRESS_HEADER, "");
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_Send_Localhost() {
        String expected = "Welcome";
        GeoService localhost = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(localhost.byIp("127.0.0.1"))
                .thenReturn(new Location(null, null, null, 0));
        LocalizationService localizationServiceLocal = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceLocal.locale(null))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(localhost, localizationServiceLocal);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(messageSender.IP_ADDRESS_HEADER, "127.0.0.1");
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);
    }

}
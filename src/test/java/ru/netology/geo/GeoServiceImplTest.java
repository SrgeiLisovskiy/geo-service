package ru.netology.geo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {
    Location localhost;     //"127.0.0.1"
    Location moscowIP;      //"172.0.32.11"
    Location moscow;        //"172...."
    Location newYorkIP;     //"96.44.183.149"
    Location newYork;       //"96...."
    GeoService geoService;

    @BeforeEach
    void beforeEach() {
        localhost = new Location(null, null, null, 0);
        moscowIP = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        newYorkIP = new Location("New York", Country.USA, " 10th Avenue", 32);
        moscow = new Location("Moscow", Country.RUSSIA, null, 0);
        newYork = new Location("New York", Country.USA, null, 0);
        geoService = new GeoServiceImpl();
    }

    @Test
    public void testByIp() {
        Assertions.assertEquals(localhost, geoService.byIp("127.0.0.1"));
        Assertions.assertEquals(moscowIP, geoService.byIp("172.0.32.11"));
        Assertions.assertEquals(moscow, geoService.byIp("172.0.0.1"));
        Assertions.assertEquals(newYork, geoService.byIp("96.0.0.1"));
        Assertions.assertEquals(null, geoService.byIp(""));
    }

    @Test
    void byCoordinates() {
    }
}
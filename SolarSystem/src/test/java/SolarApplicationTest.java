import app.CelestialBody;
import app.Position;
import app.Solar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolarApplicationTest {
    private CelestialBody sun;
    private CelestialBody earth;
    private CelestialBody titan;
    private CelestialBody phobos;
    private CelestialBody saturn;
    private CelestialBody mars;


    @BeforeEach
    void model() {
        sun = Solar.getInstance().sun();

        earth = sun.addMoon("earth", new Position(0, 6));

        saturn = sun.addMoon("saturn", new Position(0, 4));
        titan = saturn.addMoon("titan", new Position(0, 5));

        mars = sun.addMoon("mars", new Position(0, 3));
        phobos = mars.addMoon("phobos", new Position(0, 5));
    }

    @Test
    void solarSingletonTest() {
        Solar solar1 = Solar.getInstance();
        Solar solar2 = Solar.getInstance();
        assertEquals(solar1, solar2);
    }

    @Test
    void celestialBodySingletonTest() {
        CelestialBody cb1 = CelestialBody.sun(new Position(0, 0));
        CelestialBody cb2 = CelestialBody.sun(new Position(0, 0));
        assertEquals(cb1, cb2);
    }

    @Test
    void getMoonTest() {
        assertEquals(earth, sun.getMoon("earth"));
        assertEquals(null, sun.getMoon("jupiter"));
    }

    @Test
    @DisplayName("Display from earth to mars")
    void distanceTest() {
        //distance have 3 features, one to itself =0
        //distance must always be positive
        //distance a to b must be equal b to a
        assertEquals(6, earth.distanceTo(sun));
        assertEquals(6, sun.distanceTo(earth));
        assertEquals(0, earth.distanceTo(earth));
        assertEquals(0, sun.distanceTo(sun));
        assertEquals(1, phobos.distanceTo(titan));
    }
}

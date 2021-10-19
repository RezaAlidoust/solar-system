package app;

import java.util.HashMap;

public class CelestialBody {

    private final Position position;
    // every celestial body can have more than 1 object(moon) rotated around it;
    private final HashMap<String, CelestialBody> moons;
    //object that this celestial body rotated around this
    private final CelestialBody parent;

    private static CelestialBody INSTANCE;

    private CelestialBody(Position position, CelestialBody parent) {
        this.position = position;
        this.moons = new HashMap<>();
        this.parent = parent;
    }

    public static CelestialBody sun(Position position) {
        if (INSTANCE == null) {
            INSTANCE = new CelestialBody(position, null);
        }

        return INSTANCE;
    }

    public CelestialBody addMoon(String name, Position position) {
        CelestialBody body = new CelestialBody(position, this);
        this.moons.put(name, body);
        return body;
    }

    public CelestialBody getParent() {
        return parent;
    }

    //Its like strategy pattern, because distance can change in future. for example add z to position or another distance algorithm
    public double distanceTo(CelestialBody celestialBody) throws Exception {
        return DistanceBehavior.distance(this, celestialBody);
    }

    public Position getPosition() {
        return position;
    }

    /**
     * @param name
     * @return
     */
    public CelestialBody getMoon(String name) throws Exception {
        if (moons.get(name) == null)
            throw new Exception("there is no celestial object with this name!");
        return moons.get(name);
    }
}

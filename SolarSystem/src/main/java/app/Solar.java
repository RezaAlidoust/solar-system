package app;

public class Solar {
    // in solar system, the center is sun and it only can be one sun;
    private final CelestialBody sun;
    private static Solar INSTANCE;

    private Solar(Position position) {
        this.sun = CelestialBody.sun(position);
    }

    public static Solar getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Solar(new Position(0, 0));
        }

        return INSTANCE;
    }

    public CelestialBody sun() {
        return this.sun;
    }
}

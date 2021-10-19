package app;

import java.util.ArrayList;

public class DistanceBehavior {
    public static double distance(CelestialBody from, CelestialBody to) throws Exception {
        CelestialBody common = findFirstCommon(from, to);
        if (common == null) {
            throw new Exception("there is no common node");
        }
        double fromX = from.getPosition().getX();
        double fromY = from.getPosition().getY();

        double toX = to.getPosition().getX();
        double toY = to.getPosition().getY();

        //#calculate relative distance to first common node for from
        while (from.getParent() != null && from.getParent() != common) {
            from = from.getParent();
            fromX += from.getPosition().getX();
            fromY += from.getPosition().getY();
        }

        //calculate relative distance to first common node for from
        while (to.getParent() != null && to.getParent() != common) {
            to = to.getParent();
            toX += to.getPosition().getX();
            toY += to.getPosition().getY();
        }

        return Math.sqrt((fromX - toX) * (fromX - toX) + (fromY - toY) * (fromY - toY));
    }

    // find path of node to root of tree(sun)
    private static ArrayList<CelestialBody> findPath(CelestialBody celestialBody) {
        ArrayList<CelestialBody> parents = new ArrayList<>();
        while (celestialBody != null) {
            parents.add(celestialBody);
            celestialBody = celestialBody.getParent();
        }

        return parents;
    }


    //Its a tree, and sun is root of tree, we try to find first node that is common between two node path to root(sun)
    private static CelestialBody findFirstCommon(CelestialBody fromCB, CelestialBody toCB) {
        ArrayList<CelestialBody> from = findPath(fromCB);
        ArrayList<CelestialBody> to = findPath(toCB);
        CelestialBody common = null;
        for (int i = 1; i <= Math.min(from.size(), to.size()); i++) {
            if (from.get(from.size() - i) == to.get(to.size() - i)) {
                common = from.get(from.size() - i);
            } else break;
        }

        return common;
    }
}

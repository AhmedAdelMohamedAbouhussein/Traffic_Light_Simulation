package mainprogram;

public class TrafficLightSimulation implements Runnable {

    private Intersection Intersections;

    public TrafficLightSimulation(Intersection intersection)
    {
        this.Intersections = intersection;
    }

    private boolean green = false;
    private boolean yellow = false;
    private boolean red = false;

    public boolean isGreen() {
        return green;
    }

    public boolean isYellow() {
        return yellow;
    }

    public boolean isRed() {
        return red;
    }

    @Override
    public void run() {

        try
        {
            while (true)
            {
                green = true;
                Thread.sleep(Intersections.getGreenSignal() * 1000);
                green = false;

                yellow = true;
                Thread.sleep(Intersections.getYellowSignal() * 1000);
                yellow = false;

                red = true;
                Thread.sleep(Intersections.getRedSignal() * 1000);
                red = false;
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Simulation interrupted for Intersection: " + Intersections.getIntersectionID());
        }
    }
}

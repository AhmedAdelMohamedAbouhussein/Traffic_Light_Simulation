package mainprogram;

public class Intersection
{

    private String intersectionID;
    private String location;
    private String name;

    private int greenSignal = 10;
    private int yellowSignal = 5;
    private int redSignal = 10;

    public void manageTraffic(int greenSignal, int yellowSignal, int redSignal)
    {
        this.greenSignal = greenSignal;
        this.yellowSignal = yellowSignal;
        this.redSignal = redSignal;
    }

    public Intersection(String id, String location, String name)
    {
        this.intersectionID = id;
        this.location = location;
        this.name = name;
    }

    public String getIntersectionID()
    {
        return intersectionID;
    }

    public String getLoctaion()
    {
        return location;
    }

    public String getName()
    {
        return name;
    }

    public int getGreenSignal()
    {
        return greenSignal;
    }

    public int getYellowSignal()
    {
        return yellowSignal;
    }

    public int getRedSignal()

    {
        return redSignal;
    }


}
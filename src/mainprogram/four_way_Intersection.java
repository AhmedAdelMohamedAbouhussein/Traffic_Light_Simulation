package mainprogram;

public class four_way_Intersection extends Intersection
{
    public four_way_Intersection(String id, String location,String name)
    {
        super(id, location, name);
    }

    @Override
    public void manageTraffic(int greenSignal, int yellowSignal, int redSignal)
    {
        super.manageTraffic(greenSignal, yellowSignal, redSignal);
    }
}

package mainprogram;

public class T_junction_intersection extends Intersection
{
    public T_junction_intersection(String ID, String location,String name)
    {
        super(ID ,location, name);
    }

    @Override
    public void manageTraffic(int greenSignal, int yellowSignal, int redSignal)
    {
        super.manageTraffic(greenSignal, yellowSignal, redSignal);
    }


}

import java.util.ArrayList;
import java.util.List;

public class Car {
    List<Sensor> list=new ArrayList<Sensor>();



    public void addObject(Sensor s){

        this.list.add(s);

    }

    public void StatusCheck(){
        for (Sensor s : list )
        {
            s.get();
        }
    }
}

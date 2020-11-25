public class Temperature implements Sensor{

    EnumStatus status;

    public Temperature(EnumStatus stat){
        this.status = stat;
    }
    public void get(){
        System.out.println("Temperature status: " + status);
    }
}

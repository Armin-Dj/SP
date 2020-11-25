public class Proximity implements Sensor {
    EnumStatus status;

    public Proximity(EnumStatus stat){
        this.status = stat;
    }
    public void get(){
        System.out.println("Proximity  status: " + status);
    }
}

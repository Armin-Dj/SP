public class Lidar implements Sensor{
    EnumStatus status;

    public Lidar(EnumStatus stat){
        this.status = stat;
    }
    public void get(){
        System.out.println("Lidar status: " + status);
    }
}

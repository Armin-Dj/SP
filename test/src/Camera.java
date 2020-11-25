public class Camera implements Sensor{
    EnumStatus status;

    public Camera(EnumStatus stat){
        this.status = stat;
    }
    public void get(){
        System.out.println("Camera status: " + status);
    }
}

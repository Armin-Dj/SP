public class Main {
    public static void main(String[] args) {

        Car c = new Car();
        c.addObject(new Lidar(EnumStatus.WORKING));
        c.addObject(new Camera(EnumStatus.NOT_WORKING));
        c.addObject(new Temperature(EnumStatus.PROTECTION_MODE));
        c.addObject(new Proximity(EnumStatus.WORKING));
        c.StatusCheck();

    }
}

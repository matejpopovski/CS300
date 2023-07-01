public interface Rechargeable{ public void r();}
public class Device { 
  public void d() {}
  public void b() {}
}

public class Tablet extends Device implements Rechargeable{
  public void r() {}
  public void t() {}

 public static void main(String[] args) {
     Rechargeable device = new Tablet();
     // use the device reference to call a method
     device.r();
  }
}
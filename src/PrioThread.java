public class PrioThread extends Thread {
  //MIN_PRIORITY = 1;
  //MAX_PRIORITY = 10;
  public PrioThread(int priority) {
    setPriority(priority);
  }
  public void run() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(String.format("[thread with priority %s has exited]", getPriority()));
  }
}

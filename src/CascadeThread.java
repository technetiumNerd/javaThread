public class CascadeThread extends Thread {
  private boolean waiting = true;
  private CascadeThread nextCascadeThread;
  private String name;
  public CascadeThread(String name) {
    this.name = name;
  }
  public CascadeThread(String name, CascadeThread nextThread) {
    this.name = name;
    this.nextCascadeThread = nextThread;
  }
  public void SetNextCascadeThread(CascadeThread ct) {
    nextCascadeThread = ct;
  }
  public void run() {
    while (waiting) {
      try {
        Thread.sleep(2000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    if (nextCascadeThread != null){
      nextCascadeThread.Push(name);
    }
    else {
      System.out.println("Cascade complete");
    }
  }
  public void Push(String callingName) {
    System.out.println(String.format("%s pushed  by %s", name, callingName));
    Fall();
  }
  private void Fall() {
    waiting = false;
    System.out.println(String.format("%s falling", name));
  }
}

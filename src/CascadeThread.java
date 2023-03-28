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
  //Main method, waits for being pushed
  public void run() {
    while (waiting) {
      try {
        Thread.sleep(2000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    //If next object exists:
    if (nextCascadeThread != null){
      //Push next object
      nextCascadeThread.Push(name);
    }
    //Else done
    else {
      System.out.println("Cascade complete");
    }
  }
  //Public method to be pushed
  public void Push(String callingName) {
    System.out.println(String.format("[%s pushed  by %s]", name, callingName));
    Fall();
  }
  //Private method to do the fall
  private void Fall() {
    waiting = false;
    System.out.println(String.format("* %s falling *", name));
  }
}

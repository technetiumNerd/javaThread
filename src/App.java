import java.util.ArrayList;

public class App {
  public static void main(String[] args) throws Exception {
    TennisMatch("Jens", "Niels");

    Cascade(4);

    PriorityThread(11);
  }
  public static void TennisMatch(String name1, String name2) throws InterruptedException {
    //Create player 1
    TennisPlayer p1 = new TennisPlayer(name1);
    //Create player 2, with opponent
    TennisPlayer p2 = new TennisPlayer(name2, p1);
    //Set player 1 opponent
    p1.SetOpponent(p2);

    //Start
    p1.start();
    p2.start();
    //Wait for completion
    p1.join();
    p2.join();
  }
  public static void Cascade(int amount) throws InterruptedException {
    //Create list
    ArrayList<CascadeThread> cts = new ArrayList<CascadeThread>();
    //Fill list
    for (int i = 0; i < amount; i++) cts.add(new CascadeThread("ct" + i));
    //Adjust list contents for cascade
    for (int i = 0; i < amount - 1; i++) cts.get(i).SetNextCascadeThread(cts.get(i + 1));
    //Start each thread
    for (CascadeThread ct : cts) ct.start();

    Thread.sleep(1000);
    //Push first cascadeThread
    cts.get(0).Push("main");
    //Wait for threads to complete
    for (CascadeThread ct : cts) ct.join();
  }
  //min_priority = 1, max_priority = 10 (amount)
  public static void PriorityThread(int amount) throws InterruptedException {
    //Create thread list
    ArrayList<PrioThread> pts = new ArrayList<PrioThread>();
    int max = amount;
    if (amount > 10) max = 10; System.out.println("[max priority = 10]");
    //Fill thread list
    for (int i = 1; i <= max; i++) pts.add(new PrioThread(i));

    //Start each thread
    for (PrioThread pt : pts) pt.start();
    //Wait for each thread to terminate
    for (PrioThread pt : pts) pt.join();
  }
}
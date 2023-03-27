public class App {
  public static void main(String[] args) throws Exception {
    TennisPlayer p1 = new TennisPlayer("Peter");
    TennisPlayer p2 = new TennisPlayer("Søren", p1);
    p1.SetOpponent(p2);

    for (int i = 0; i > 3; i++) {
      p1.start();
      p2.start();
      p1.join();
      p2.join();
    }
    CascadeThread ct1 = new CascadeThread("ct1");
    CascadeThread ct2 = new CascadeThread("ct2");
    CascadeThread ct3 = new CascadeThread("ct3");
    CascadeThread ct4 = new CascadeThread("ct4");
    CascadeThread ct5 = new CascadeThread("ct5");
    CascadeThread ct6 = new CascadeThread("ct6");

    ct1.SetNextCascadeThread(ct2);
    ct2.SetNextCascadeThread(ct3);
    ct3.SetNextCascadeThread(ct4);
    ct4.SetNextCascadeThread(ct5);
    ct5.SetNextCascadeThread(ct6);

    CascadeThread[] cts = { ct1, ct2, ct3, ct4, ct5, ct6 };

    for (CascadeThread ct : cts) {
      ct.start();
    }

    Thread.sleep(2000);
    ct1.Push("main");
    for (CascadeThread ct : cts) {
      ct.join();
    }

    PrioThread pt1 = new PrioThread(1);
    PrioThread pt2 = new PrioThread(2);
    PrioThread pt3 = new PrioThread(3);

    PrioThread[] pts = { pt1, pt2, pt3 };

    for (PrioThread pt : pts) {
      pt.start();
    }
  }
}
public class TennisPlayer extends Thread {
  private boolean hasBall = false;
  private TennisPlayer opponent;
  private String playerName = "default player name";
  private boolean playing = false;
  public TennisPlayer(String playerName) {
    this.hasBall = true;
    this.playerName = playerName;
  }
  public TennisPlayer(String playerName, TennisPlayer opponent) {
    this.opponent = opponent;
    this.playerName = playerName;
  }
  public void SetPlayerName(String name) {
    this.playerName = name;
  }
  public String GetPlayerName() {
    return playerName;
  }
  public void SetOpponent(TennisPlayer opponent) {
    this.opponent = opponent;
  }
  public void run() {
    Play();
    while (playing) {
      if (this.hasBall == true) {
        System.out.println(String.format("[%s has hit the ball]", playerName));
        //vent 0-2 sekunder. Math.random() returnerer double. Resultatet castes til long for at accepteres af wait()
        //wait() kan throw InterruptedException, som ignoreres.
        try {
          long waitTime = (long)(Math.random() * 2000D);
          Thread.sleep(waitTime);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        this.hasBall = false;
        opponent.ReceiveBall();
      }
      else {
        try {
        Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  private void ReceiveBall() {
    //80% chance for accept
    if (Math.random() <= 0.8) {
      this.hasBall = true;
    }
    else {
      this.LoseRound();
    }
  }
  public void Play(){
    StartPlaying();
    opponent.StartPlaying();
  }
  private void StartPlaying() {
    playing = true;
  }
  private void StopPlaying() {
    playing = false;
  }
  private void LoseRound() {
    System.out.println(String.format("[%s has dropped the marbles]", playerName));
    System.out.println(String.format("[%s has won the round!]", opponent.GetPlayerName()));
    StopPlaying();
    opponent.StopPlaying();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
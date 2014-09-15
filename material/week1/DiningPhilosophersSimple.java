/** A simple deadlock-free solution to the dining philosophers problem. 
 *  Deadlock is prevented by breaking the symmetry between the philosophers.
 *  Note, however, that in this version philosophers may still starve.
 */
public class DiningPhilosophersSimple {

  // Number of philosophers
  final static int n = 5;

  final static Philosopher[] philosophers = new Philosopher[n];
  final static Chopstick[] sticks = new Chopstick[n];

  public static void main(String[] args) {
    
    // Initialize shared objects
    for (int i = 0; i < n; i++) {
      sticks[i] = new Chopstick();
    }

    // Initialize threads
    // make first philosopher left-handed and all others right-handed
    philosophers[0] = new Philosopher(0, sticks[1], sticks[0]);
    for (int i = 1; i < n; i++) {
      philosophers[i] = new Philosopher(i, sticks[i], sticks[(i + 1) % n]);
    }

    // Start the threads
    for (Thread t : philosophers) {
      t.start();
    }
  }

  public static class Chopstick {
    private boolean taken;

    public Chopstick() {
      taken = false;
    }

    public synchronized void take() {
      while(taken) {
        try {
          wait();
        } catch (InterruptedException e) {}
      }
      taken = true;
    }

    public synchronized void drop() {
      taken = false;
      notify();
    }
  }

  public static class Philosopher extends Thread {

    private enum State {THINKING, HUNGRY, EATING};

    private int id;
    private Chopstick firstStick;
    private Chopstick secondStick;
    private State state;

    Philosopher(int id, Chopstick fst, Chopstick snd) {
      this.id = id;
      firstStick = fst;
      secondStick = snd;
      state = State.THINKING;
    }
    
    public void run() {
      while (true) {
        printState();
        switch(state) {
        case THINKING: 
          thinkOrEat();
          state = State.HUNGRY; 
          break;
        case HUNGRY:
          firstStick.take();
          secondStick.take();
          state = State.EATING;
          break;
        case EATING:
          thinkOrEat();
          firstStick.drop();
          secondStick.drop();
          state = State.THINKING;
          break;          
        }
      }
    }

    private void thinkOrEat() {
      try {
        Thread.sleep((long) Math.round(Math.random() * 5000));
      } catch (InterruptedException e) {}
    }

    private void printState() {
      System.out.println("Philosopher " + id + " is " + state);
    }
  }
}


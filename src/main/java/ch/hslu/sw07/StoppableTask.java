package ch.hslu.sw07;

public final class StoppableTask implements Runnable {
    @Override
    public void run(){
        // initialisierungsphase
        while (this.isStopped() == false) {
            // Do something
        }

        // Aufräumphase

        //Sicheres Beenden eines Threads durch Interrupt-Status
        // initialisierungsphase
        try {
            while (Thread.currentThread().isInterrupted() == false) {
                // Arbeitsphase
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            runThread.interrupt();
            // Thread wurde in in einer Wartemethode unnterbroche
        } finally {
            // Aufröumphase
        }
    }

    private volatile Thread runThread;

    private volatile boolean isStopped = false;

    public void stopRequest() {
        isStopped = true;
        if (runThread != null) {
            runThread.interrupt();
        }
    }

    public boolean isStopped() {
        return isStopped;
    }

}

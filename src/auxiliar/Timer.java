package auxiliar;

public class Timer implements Runnable {

	public void run() {
        try {
            Thread.sleep(2000); // 2 segundos
            Thread.currentThread().interrupt(); // Interrumpir el hilo principal
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

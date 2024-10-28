import java.util.concurrent.atomic.AtomicBoolean;

class ImprimirThreadShutdownHook implements Runnable {
    private final String str;
    private static final AtomicBoolean running = new AtomicBoolean(true);

    public ImprimirThreadShutdownHook(String str) {
        this.str = str;
    }
    public void setStop(boolean status) {
        running.set(status);
    }

    public void run() {
        while (running.get()) {
            System.out.print(str);
        }
    }
}

class ThreadsConcorrenteShutdownHook {
    public static void main(String[] args) {
       ImprimirThreadShutdownHook imprimirA = new ImprimirThreadShutdownHook("A");
        ImprimirThreadShutdownHook imprimirB = new ImprimirThreadShutdownHook("B");

        // Passar essas instâncias para as threads
        Thread threadA = new Thread(imprimirA);
        Thread threadB = new Thread(imprimirB);
       
        threadA.start();
        threadB.start();

        // Usar o shutdown hook para parar as threads
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nEncerrando threads Concorrente com ShutdownHook...");
            imprimirA.setStop(false);  // Parar threadA
            imprimirB.setStop(false);  // Parar threadB
        }));
    }
}
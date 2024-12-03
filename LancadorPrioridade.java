class BaixaPrioridade extends Thread {
    public void run() {
        setPriority(Thread.MIN_PRIORITY);
        for(;;) {
            System.out.println("Thread de baixa prioridade executando -> 1");
        }
    }
}


class AltaPrioridade extends Thread {
    public void run() {
        setPriority(Thread.MAX_PRIORITY);
        for(;;) {
            for(int i=0; i<5; i++)
                System.out.println("Thread de alta prioridade executando -> 10");
                try {
                    sleep(10);
                } catch(InterruptedException e) {
                    System.exit(0);
                }
        }
    }
}

class LancadorPrioridade {
    public static void main(String args[]) {
        AltaPrioridade a = new AltaPrioridade();
        BaixaPrioridade b = new BaixaPrioridade();
        System.out.println("Iniciando threads...");
        b.start();
        a.start();
        // deixa as outras threads iniciar a execução.
        Thread.currentThread().yield();
        System.out.println("Lancador Prioridade finalizado.");
        
        
        // Usar o shutdown hook para parar as threads
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nEncerrando threads Concorrente com ShutdownHook...");
            a.interrupt();  // Parar thread a
            b.interrupt();  // Parar thread b
        }));

        // Simulando um atraso antes de encerrar as threads
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }

        // Encerrando as threads após o tempo dormindo 
        a.interrupt();
        b.interrupt();
        System.out.println("Finalizando Threads...");
    }
}

class ImprimirThreadInterrupet implements Runnable {
    String str;
    public ImprimirThreadInterrupet(String str) {
        this.str = str;
    }
    public void run() {
         while (!Thread.currentThread().isInterrupted()) {
            System.out.print(str);
            Thread.currentThread().yield();
        }
        System.out.println("\nThread " + str + " interrompida.");  // Mensagem ao interromper
    }
}

class ThreadsConcorrenteInterrupet {
    public static void main(String Args[]) {
         Thread threadA = new Thread(new ImprimirThreadInterrupet("A"));
         Thread threadB = new Thread(new ImprimirThreadInterrupet("B"));

          // Usar o shutdown hook para parar as threads quando enviado SINAL SIGINT
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nEncerrando threads Concorrente com ShutdownHook...");
            // Encerrando as threads
            threadA.interrupt();
            threadB.interrupt();
        }));

         threadA.start();
         threadB.start();

        // Simulando um atraso antes de encerrar as threads
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }

        // Encerrando as threads após o tempo dormindo 
        threadA.interrupt();
        threadB.interrupt();

        
    }
}
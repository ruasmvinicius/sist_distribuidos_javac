
class ImprimirThreadInfinitas implements Runnable {
    String str;
    public ImprimirThreadInfinitas(String str) {
        this.str = str;
    }
    public void run() {
        for(;;)
          System.out.print(str);
    }   
}
class ThreadsConcorrenteInfinitas {
    public static void main(String Args[]) {
        new Thread(new ImprimirThreadInfinitas("A")).start();
        new Thread(new ImprimirThreadInfinitas("B")).start();
    }
}
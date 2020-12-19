package Practica7;


public class Main {

    public static void main(String[] args) {
        NumberReader reader = new NumberReader();
        NumberReadListener listener = new NumberReadListenerImpl();
        NumberReadListener listenerPairs = new NumberReadPairsListenerImpl();
        reader.addNumberReadListener(listener);
        reader.addNumberReadListener(listenerPairs);
        reader.start();
    }
    
}

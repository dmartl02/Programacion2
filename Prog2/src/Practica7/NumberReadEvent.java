package Practica7;

import java.util.EventObject;

public class NumberReadEvent extends EventObject {

    private double number;

   
    public NumberReadEvent(Object source, Double number) {
        super(source);
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    

}
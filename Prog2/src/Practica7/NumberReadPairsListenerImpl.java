package Practica7;

public class NumberReadPairsListenerImpl implements NumberReadListener {
	   
    double totalSoFar = 0D;
    boolean sumarPosicionPar = false;
    
    @Override
    public void numberRead(NumberReadEvent numberReadEvent) {
    	
    	/*
    	if(sumarPosicionPar && numberReadEvent.getNumber() == 2.0) {
    		numberReadEvent.getSource();
    		NumberReader.removeNumberReadListener();
    	}*/
    	
		if(sumarPosicionPar && numberReadEvent.getNumber() != 2.0) {
			totalSoFar += numberReadEvent.getNumber();
    	}
			
    	sumarPosicionPar = !sumarPosicionPar;
    	
    }
    
    
    @Override
    public void numberStreamTerminated(NumberReadEvent numberReadEvent) {
        System.out.println("Sum of the numbers in pair positions: " +totalSoFar);
    }
 
}

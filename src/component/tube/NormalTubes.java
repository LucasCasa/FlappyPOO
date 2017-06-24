package component.tube;

/**
 * Tubes esta compuesta por dos partes. Parte superior del tubo (del tipo Tube)
 * Parte inferior del tubo (del tipo Tube) Ambos son SimpleFObject.
 * 
 */
public class NormalTubes extends Tubes {

	public NormalTubes(float x) {
		super(x,false);
	}
	
	public NormalTubes(float x, int gap, int opening){
		super(x,gap,opening,false);
	}
	
}

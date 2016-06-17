package component.ground;

import com.badlogic.gdx.math.Rectangle;

import component.bird.Bird;
import component.worldComponent.CompoundFObject;

public class Grounds extends CompoundFObject{

	/** The Constant GROUND_Y_OFFSET. */
	public static final int GROUND_Y_OFFSET = -50;
	
	/** The Constant WIDTH. */
	public static final int W = 672;
	
	/** The Constant HEIGHT. */
	public static final int H = 112;

	/**
	 * Inicializa un nuevo piso. Es del mismo estilo que los tubos. 
	 * Hay siempre dos pisos. Cuando uno sale de escena, se reposiciona. Mientras,
	 * en escena, queda el otro piso.
	 * De esta manera evitamos que se generen infinitos pisos sin sentido
	 *
	 * @param pos Posicion en X de la camara
	 * @param width Width del Viewport de la camara
	 */
	public Grounds(float pos, float width) {
		simple1 = new Ground(pos - width / 2 - 100, GROUND_Y_OFFSET);
		simple2 = new Ground((pos - width / 2) + W - 100, GROUND_Y_OFFSET);
		simple1.setBounds(new Rectangle(simple1.getPosition().x, simple1.getPosition().y, W, H));
		simple2.setBounds(new Rectangle(simple2.getPosition().x, simple2.getPosition().y, W, H));

	}

	/**
	 * Actualiza el piso si se queda fuera de escena. Actualiza respecto de la posicion de la camara
	 *
	 * @param pos Posicion en X de la camara
	 * @param width Width del Viewport de la camara
	 */
	public void update(float pos, float width) {
		if(pos - width / 2 > simple1.getPosition().x + W){
			System.out.println("REPOSICION 1");
			simple1.getPosition().add(W * 2, 0);
			simple1.setBounds(new Rectangle(simple1.getPosition().x, simple1.getPosition().y, W, H));
		}
		if(pos - width / 2 > simple2.getPosition().x + W){
			System.out.println("Reposicion 2");
			simple2.getPosition().add(W * 2, 0);
			simple2.setBounds(new Rectangle(simple2.getPosition().x, simple2.getPosition().y, W, H));

		}
	}
	
	/**
	 * Uno de los pisos. Se utiliza luego para la View
	 */
	public Ground getGround1(){
		return (Ground) simple1;
	}
	
	public Ground getGround2(){
		return (Ground) simple2;
	}

	/**
	 * El piso choca con el pajaro y le reduce una vida a la misma
	 *
	 * @return true si choca
	 */
	public boolean crash(Bird b) {
		boolean crashes = b.getPosition().y <= H + GROUND_Y_OFFSET;
		if (crashes) {
			b.lifeReducer();
		}
		return crashes;
	}

}

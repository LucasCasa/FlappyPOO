package state;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
	
	private static GameStateManager self;
	
	public static GameStateManager getInstance() {
		if (self == null) {
			self = new GameStateManager();
		}
		return self;
	}
	
	private Stack<State> states;
	
	private GameStateManager() {
		states = new Stack<State>();
	}
	
	public void push(State state){
		states.push(state);
	}
	
	public void pop(){
		states.pop().dispose();
	}
	
	public void set(State state){
		states.pop().dispose();
		states.push(state);
	}
	
	public void update(float dt){
		states.peek().update(dt);
	}

	public State peek(){
		return states.peek();
	}
	
	public void render(SpriteBatch sb){
		states.peek().render(sb);
	}
}

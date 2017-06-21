package component.tube;

public class MetalTubesView extends TubesView{
	
	public MetalTubesView() {
		top = new MetalTopTubeView();
		bottom = new MetalBottomTubeView();
	}
	
}

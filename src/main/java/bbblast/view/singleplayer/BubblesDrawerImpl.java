package bbblast.view.singleplayer;

import java.util.List;

import bbblast.model.Bubble;
import bbblast.model.GridInfo;
import javafx.scene.canvas.Canvas;

public class BubblesDrawerImpl implements BubblesDrawer {

	private final GridInfo infos;
	private final Canvas canvas;
	
	public BubblesDrawerImpl(final Canvas parent, GridInfo infos) {
		this.canvas = parent;
		this.infos = infos;
	}
	
	@Override
	public void drawBubbles(List<Bubble> l) {
		// TODO Auto-generated method stub

	}

}

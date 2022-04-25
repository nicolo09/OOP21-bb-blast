package bbblast.view.singleplayer;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GameViewImpl implements GameView {

    private SingleplayerGameViewController controller;
    private final CanvasDrawer cDrawer;

    private static final double MINWIDTH = 500;
    private static final double MINHEIGHT = 300;

    final private Canvas canvas;
    final private Scene scene;
    final private Pane root;

    public GameViewImpl() {
        
        canvas = new Canvas(MINWIDTH, MINHEIGHT);
        root = new Pane();
        cDrawer = new CanvasDrawerImpl(canvas, controller.getGridInfo());
        root.getChildren().add(canvas);
        canvas.setOnKeyPressed(key -> controller.inputCheck(key));
        this.scene = new Scene(root, MINWIDTH, MINHEIGHT);
        
        final ChangeListener<Number> resizeListener = (observable, oldValue, newValue) ->{
            canvas.setHeight(this.scene.getHeight());
            canvas.setWidth(this.scene.getWidth());
        };
        this.scene.widthProperty().addListener(resizeListener);
        this.scene.heightProperty().addListener(resizeListener);
        
    }

    @Override
    public void playMusic() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundEffect() {
        // TODO Auto-generated method stub

    }
    /**
     * Set the controller.
     */
    @Override
    public void setController(final SingleplayerGameViewController controller) {
        this.controller = controller;

    }
    /**
     * Update the canvas
     */
    @Override
    public void update() {
        cDrawer.drawOnCanvas(controller.getBubbles(), controller.getCannonAngle());
    }

    @Override
    public Scene getScene() {
        // TODO Auto-generated method stub
        return this.scene;
    }

}

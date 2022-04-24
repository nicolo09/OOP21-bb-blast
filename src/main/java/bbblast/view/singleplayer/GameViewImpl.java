package bbblast.view.singleplayer;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameViewImpl implements GameView {

    private SingleplayerGameViewController controller;
    private final BackgroundImage b = new BackgroundImage(new Image("background.jpg", 500, 300, false, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    private final Background bGround = new Background(b);

    private static final double MINWIDTH = 500;
    private static final double MINHEIGHT = 300;

    final private Scene scene;

    public GameViewImpl() {
        final VBox bg = new VBox();
        final BorderPane root = new BorderPane(bg);
        bg.setFillWidth(true);
        root.setBackground(bGround);

        //serve col canvas
        //final ChangeListener<Number> resizeListener = (observable, oldValue, newValue) ->{
        //    canvas.setHeight(this.scene.getHeight());
        //    canvas.setWidth(this.scene.getWidth());
        //};  
        //this.scene.widthProperty().addListener(resizeListener);
        //this.scene.heightProperty().addListener(resizeListener);
        
        this.scene = new Scene(root, MINWIDTH, MINHEIGHT);
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

    @Override
    public void setController(final SingleplayerGameViewController controller) {
        this.controller = controller;

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public Scene getScene() {
        // TODO Auto-generated method stub
        return this.scene;
    }

}

package bbblast.view.multiplayer;

import java.util.Objects;

import bbblast.controller.gameloop.Updatable;
import bbblast.model.GridInfo;
import bbblast.view.singleplayer.BubbleCanvas;
import bbblast.view.singleplayer.CanvasDrawer;
import bbblast.view.singleplayer.CanvasDrawerImpl;
import bbblast.view.singleplayer.SingleplayerGameViewController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MultiplayerGameViewImpl implements MultiplayerGameView, Updatable {
    
    private static final double MINWIDTH = 500;
    private static final double MINHEIGHT = 300;
    private final Scene scene;
    private SingleplayerGameViewController controller1;
    private SingleplayerGameViewController controller2;
    private final BubbleCanvas bubbleCanvas1;
    private final BubbleCanvas bubbleCanvas2;
    private final CanvasDrawer canvasDrawer1;
    private final CanvasDrawer canvasDrawer2;
    private final Label scoreLabel1;
    private final Label scoreLabel2;
    
    
    public MultiplayerGameViewImpl (final GridInfo info) {
        //final BorderPane root = new BorderPane();
        final BorderPane first = new BorderPane();
        //root.setCenter(first);
        this.bubbleCanvas1 = new BubbleCanvas(info.getPointsWidth(), info.getPointsHeight());
        this.bubbleCanvas2 = new BubbleCanvas(info.getPointsWidth(), info.getPointsHeight());
        canvasDrawer1 = new CanvasDrawerImpl(bubbleCanvas1, info);
        canvasDrawer2 = new CanvasDrawerImpl(bubbleCanvas2, info);
        first.setLeft(bubbleCanvas1);
        first.setRight(bubbleCanvas2);
        
        this.scene = new Scene(first, MINWIDTH, MINHEIGHT);
        final VBox leftBox = new VBox();
        first.setCenter(leftBox);
        leftBox.setSpacing(10);
        leftBox.setPrefWidth(Double.MAX_VALUE);
        leftBox.maxWidthProperty().bind(first.widthProperty().divide(3));
        leftBox.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(leftBox, Pos.CENTER);
        
        // Score label
        scoreLabel1 = new Label("Score 1: ");
        leftBox.getChildren().add(scoreLabel1);
        VBox.setVgrow(scoreLabel1, Priority.ALWAYS);
        scoreLabel2 = new Label("Score 2: ");
        leftBox.getChildren().add(scoreLabel2);
        VBox.setVgrow(scoreLabel2, Priority.ALWAYS);
        
        // Pause button
        final Button btnPause = new Button("Pause");
        btnPause.setMaxWidth(Double.MAX_VALUE);
        BorderPane.setAlignment(btnPause, Pos.CENTER);
        btnPause.setOnMouseClicked(e -> {
            controller1.pause();
            controller2.pause();
        });
        leftBox.getChildren().add(btnPause);
        
        // Resume button
        final Button btnResume = new Button("Resume");
        btnResume.setMaxWidth(Double.MAX_VALUE);
        BorderPane.setAlignment(btnResume, Pos.CENTER);
        btnResume.setOnMouseClicked(e -> {
            controller1.resume();
            controller2.resume();
        });
        leftBox.getChildren().add(btnResume);
        
        // Button activation to play the game
        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                switch (event.getCode()) {
                case A:
                    controller1.moveCannonLeft();
                    break;
                case D:
                    controller1.moveCannonRight();
                    break;
                case W:
                    controller1.multiCannonShoot();
                    break;
                case ESCAPE:
                    controller1.pause();
                    controller2.pause();
                    break;
                case J:
                    controller2.moveCannonLeft();
                    break;
                case L:
                    controller2.moveCannonRight();
                    break;
                case I:
                    controller2.multiCannonShoot();
                    break;
                default:
                    break;
                }
            }
        });
        
        bubbleCanvas1.widthProperty()
        .bind(first.heightProperty().multiply(info.getPointsWidth() / info.getPointsHeight()));
        bubbleCanvas1.heightProperty().bind(first.heightProperty());
        
        bubbleCanvas2.widthProperty()
        .bind(first.heightProperty().multiply(info.getPointsWidth() / info.getPointsHeight()));
        bubbleCanvas2.heightProperty().bind(first.heightProperty());
        
    }

    @Override
    public void setControllers(final SingleplayerGameViewController controller1, final SingleplayerGameViewController controller2) {
        this.controller1 = controller1;
        this.controller2 = controller2;
        bubbleCanvas1.widthProperty().addListener((x, y, z) -> {
            this.drawCanvas();
        });
        bubbleCanvas1.heightProperty().addListener((x, y, z) -> {
            this.drawCanvas();
        });
        
        bubbleCanvas2.widthProperty().addListener((x, y, z) -> {
            this.drawCanvas();
        });
        bubbleCanvas2.heightProperty().addListener((x, y, z) -> {
            this.drawCanvas();
        });
        
    }
    
    private void drawCanvas() {
        if (Objects.nonNull(controller1)) {
            Platform.runLater(() -> {
                canvasDrawer1.drawOnCanvas(controller1.getBubbles(), controller1.getCannonAngle());
            });
        }
        if (Objects.nonNull(controller2)) {
            Platform.runLater(() -> {
                canvasDrawer2.drawOnCanvas(controller2.getBubbles(), controller2.getCannonAngle());
            });
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void update() {
        this.drawCanvas();
        this.scoreWriter();
        
    }
    
    // Write each player's score
    private void scoreWriter() {
        Platform.runLater(() -> {
            scoreLabel1.setText("Score 1: " + controller1.getScore());
        });
        Platform.runLater(() -> {
            scoreLabel2.setText("Score 2: " + controller2.getScore());
        });
    }
}

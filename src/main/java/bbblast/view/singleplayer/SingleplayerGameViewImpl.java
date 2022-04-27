package bbblast.view.singleplayer;

import java.util.Objects;

import bbblast.controller.gameloop.Updatable;
import bbblast.model.GridInfo;
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

/**
 * Implements {@link GameView}, this implementation is an {@link Updatable} and
 * needs to be updated to show information about the game status.
 */
public class SingleplayerGameViewImpl implements GameView, Updatable {

    private static final double MINWIDTH = 500;
    private static final double MINHEIGHT = 300;
    private final Scene scene;
    private SingleplayerGameViewController controller;
    private final BubbleCanvas bubbleCanvas;
    private final CanvasDrawer canvasDrawer;
    final Label scoreLabel;

    /**
     * 
     * @param info the information about the grid this view will represent
     */
    public SingleplayerGameViewImpl(final GridInfo info) {
        final BorderPane root = new BorderPane();
        this.bubbleCanvas = new BubbleCanvas(info.getPointsWidth(), info.getPointsHeight());
        canvasDrawer = new CanvasDrawerImpl(bubbleCanvas, info);
        root.setCenter(bubbleCanvas);

        this.scene = new Scene(root, MINWIDTH, MINHEIGHT);
        final VBox leftBox = new VBox();
        root.setLeft(leftBox);
        leftBox.setSpacing(10);
        leftBox.setPrefWidth(Double.MAX_VALUE);
        leftBox.maxWidthProperty().bind(root.widthProperty().divide(3));
        leftBox.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(leftBox, Pos.CENTER);

        // Aim label
        final Label aim = new Label("Aiming at: ");
        leftBox.getChildren().add(aim);
        VBox.setVgrow(aim, Priority.ALWAYS);

        // Score label
        scoreLabel = new Label("Score: ");
        leftBox.getChildren().add(scoreLabel);
        VBox.setVgrow(scoreLabel, Priority.ALWAYS);

        // Pause button
        final Button btnPause = new Button("Pause");
        btnPause.setMaxWidth(Double.MAX_VALUE);
        BorderPane.setAlignment(btnPause, Pos.CENTER);
        btnPause.setOnMouseClicked(e -> {
            controller.pause();
        });
        leftBox.getChildren().add(btnPause);

        // Exit button
        final Button btnExit = new Button("Exit");
        btnExit.setMaxWidth(Double.MAX_VALUE);
        BorderPane.setAlignment(btnExit, Pos.CENTER);
        btnExit.setOnMouseClicked(e -> {
            controller.exit();
        });

        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                switch (event.getCode()) {
                case A:
                    controller.moveCannonLeft();
                    break;
                case D:
                    controller.moveCannonRight();
                    break;
                case W:
                    controller.cannonShoot();
                    break;
                case ESCAPE:
                    controller.pause();
                    break;
                default:
                    break;
                }
                aim.setText("Aiming at: " + controller.getCannonAngle());
            }
        });

        // Action listeners for redrawing after resizing
        /*
         * bubbleCanvas.widthProperty().addListener((x, y, z) -> { this.drawCanvas();
         * }); bubbleCanvas.heightProperty().addListener((x, y, z) -> {
         * this.drawCanvas(); });
         */

        // Keep canvas' aspect ratio by binding its properties
        bubbleCanvas.widthProperty()
                .bind(root.heightProperty().multiply(info.getPointsWidth() / info.getPointsHeight()));
        bubbleCanvas.heightProperty().bind(root.heightProperty());
    }

    @Override
    public void playMusic() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final SingleplayerGameViewController controller) {
        this.controller = controller;
        bubbleCanvas.widthProperty().addListener((x, y, z) -> {
            this.drawCanvas();
        });
        bubbleCanvas.heightProperty().addListener((x, y, z) -> {
            this.drawCanvas();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.drawCanvas();
        this.scoreWriter();

    }

    private void drawCanvas() {
        // System.out.println(controller.getBubbles().toString() +
        // controller.getCannonAngle());
        if (Objects.nonNull(controller)) {
            Platform.runLater(() -> {
                canvasDrawer.drawOnCanvas(controller.getBubbles(), controller.getCannonAngle());
            });
        }
    }

    private void scoreWriter() {
        Platform.runLater(() -> {
            scoreLabel.setText("Score: " + controller.getScore());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }
}

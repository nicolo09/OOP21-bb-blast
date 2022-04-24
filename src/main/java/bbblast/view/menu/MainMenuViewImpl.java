package bbblast.view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 
 * A MainMenuView implemented in JavaFX.
 */
public class MainMenuViewImpl implements MainMenuView {

    private static final double TITLEFONTSIZE = 22;
    private static final double BUTTONS_MAX_WIDTH = 400;
    private MainMenuViewController controller;
    private final Scene scene;

    private static final double MINWIDTH = 500;
    private static final double MINHEIGHT = 300;
    private static final double SPACING = 10;
    private static final double TOPBOTTOMPADDING = 10;
    private static final double SIDEPADDING = 50;

    /**
     * 
     */
    public MainMenuViewImpl() {
        final VBox buttonsVBox = new VBox();
        final BorderPane root = new BorderPane(buttonsVBox);
        buttonsVBox.setFillWidth(true);
        buttonsVBox.setSpacing(SPACING);

        final Label lbl = new Label();
        lbl.setText("Main Menu");
        final Font menuFont = Font.font("Comic Sans MS", TITLEFONTSIZE);
        lbl.setFont(menuFont);
        lbl.setTextFill(Color.GOLD);

        // Singleplayer button
        final Button btnSinglePlayer = new Button("Singleplayer");
        btnSinglePlayer.setOnMouseClicked(e -> {
            this.controller.startSingleplayer();
        });

        // Multiplayer button
        final Button btnMultiPlayer = new Button("Multiplayer");
        btnMultiPlayer.setOnMouseClicked(e -> {
            this.controller.startMultiplayer();
        });

        // Options button
        final Button btnOptions = new Button("Options");
        btnOptions.setOnMouseClicked(e -> {
            this.controller.startOptionsMenu();
        });

        // Exit button
        final Button btnExit = new Button("Exit");
        btnExit.setOnMouseClicked(e -> {
            this.controller.quit();
        });

        root.setPadding(new Insets(TOPBOTTOMPADDING, SIDEPADDING, TOPBOTTOMPADDING, SIDEPADDING));

        btnSinglePlayer.setMaxWidth(BUTTONS_MAX_WIDTH);
        btnMultiPlayer.setMaxWidth(BUTTONS_MAX_WIDTH);
        btnOptions.setMaxWidth(BUTTONS_MAX_WIDTH);
        btnExit.setMaxWidth(BUTTONS_MAX_WIDTH);

        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.getChildren().addAll(lbl, btnSinglePlayer, btnMultiPlayer, btnOptions, btnExit);

        this.scene = new Scene(root, MINWIDTH, MINHEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final MainMenuViewController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    public Scene getScene() {
        return this.scene;
    }

}

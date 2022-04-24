package bbblast.view.options;

import bbblast.view.View;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Implements an OptionView.
 */
public class OptionViewImpl implements OptionView {

    private static final double MINWIDTH = 400;
    private static final double MINHEIGHT = 300;
    private static final double MINIMUM_VOLUME = 0;
    private static final double MAXIMUM_VOLUME = 100;
    private static final int LABELSCOLUMNPERCENTAGE = 20;
    private static final int SLIDERSCOLUMNPERCENTAGE = 80;
    private static final int SLIDERSMAJORTICK = 50;
    private static final int SLIDERSMINORTICK = 5;
    private static final int SLIDERSBLOCKINCREMENT = 10;
    private OptionViewController controller;
    private final Scene scene;

    /**
     * 
     * @param mainView       the main application view
     * @param controller     the controller for this view
     */
    public OptionViewImpl(final View mainView, final OptionViewController controller) {
        this.controller = controller;

        final VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));

        final Label lbl = new Label();
        lbl.setText("Options");

        // Volume sliders
        // Master volume
        final GridPane grid = new GridPane();
        final Label masterVolumeLabel = new Label("Master Volume");
        GridPane.setConstraints(masterVolumeLabel, 0, 0, 1, 1, HPos.CENTER, VPos.BASELINE);
        final Slider masterVolumeSlider = new Slider(MINIMUM_VOLUME, MAXIMUM_VOLUME, this.controller.getMasterVolume());
        beautifySlider(masterVolumeSlider);
        grid.add(masterVolumeLabel, 0, 0);
        grid.add(masterVolumeSlider, 1, 0);

        // Music volume
        final Label musicVolumeLabel = new Label("Music Volume");
        GridPane.setConstraints(musicVolumeLabel, 0, 1, 1, 1, HPos.CENTER, VPos.BASELINE);
        final Slider musicVolumeSlider = new Slider(MINIMUM_VOLUME, MAXIMUM_VOLUME, this.controller.getMusicVolume());
        beautifySlider(musicVolumeSlider);
        grid.add(musicVolumeLabel, 0, 1);
        grid.add(musicVolumeSlider, 1, 1);

        // Effects volume
        final Label effectsVolumeLabel = new Label("Effects Volume");
        GridPane.setConstraints(effectsVolumeLabel, 0, 2, 1, 1, HPos.CENTER, VPos.BASELINE);
        final Slider effectsVolumeSlider = new Slider(MINIMUM_VOLUME, MAXIMUM_VOLUME,
                this.controller.getEffectsVolume());
        beautifySlider(effectsVolumeSlider);
        grid.add(effectsVolumeLabel, 0, 2);
        grid.add(effectsVolumeSlider, 1, 2);

        // Columns sizes
        final ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(LABELSCOLUMNPERCENTAGE);
        final ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(SLIDERSCOLUMNPERCENTAGE);
        grid.getColumnConstraints().addAll(column1, column2);

        root.getChildren().addAll(grid);

        // Bottom buttons
        final HBox bottomButtonsLine = new HBox();
        final Button btnSaveAndExit = new Button("Save");
        btnSaveAndExit.setOnMouseClicked(e -> {
            controller.saveSettings(masterVolumeSlider.getValue(), musicVolumeSlider.getValue(),
                    effectsVolumeSlider.getValue());
            mainView.goToMainMenu();
        });
        final Button btnDiscardAndExit = new Button("Discard");
        btnDiscardAndExit.setOnMouseClicked(e -> {
            mainView.goToMainMenu();
        });
        bottomButtonsLine.getChildren().addAll(btnSaveAndExit, btnDiscardAndExit);

        root.getChildren().add(bottomButtonsLine);
        this.scene = new Scene(root, MINWIDTH, MINHEIGHT);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setController(final OptionViewController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    /**
     * sets slider properties.
     * @param s
     */
    private void beautifySlider(final Slider s) {
        s.setShowTickLabels(true);
        s.setShowTickMarks(true);
        s.setMajorTickUnit(SLIDERSMAJORTICK);
        s.setMinorTickCount(SLIDERSMINORTICK);
        s.setBlockIncrement(SLIDERSBLOCKINCREMENT);
        s.setSnapToTicks(true);
    }

}

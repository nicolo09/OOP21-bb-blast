package bbblast.application;

import bbblast.controller.Controller;
import bbblast.controller.ControllerImpl;
import bbblast.model.Model;
import bbblast.model.ModelImpl;
import bbblast.model.RegularHexGridInfo;
import bbblast.view.JFXViewImpl;
import bbblast.view.View;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Implements {@link ViewControllerAssembler} using a {@link JFXViewImpl} as view.
 */
public class ViewControllerAssemblerImpl extends Application {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final View view = new JFXViewImpl(primaryStage);
        final Controller controller = new ControllerImpl();
        final Model model = new ModelImpl(new RegularHexGridInfo(10, 18, 100));

        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
        model.setController(controller);

        view.show();
    }

}

package bbblast.application;

import bbblast.controller.Controller;
import bbblast.model.Model;
import bbblast.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewControllerAssemblerImpl extends Application{

    @Override
    public void start(final Stage primaryStage) throws Exception {
        //TODO Instantiate MVC components
        final View view = null;
        final Controller controller = null;
        final Model model = null;
        
        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
        model.setController(controller);
        
        view.show();
    }

}

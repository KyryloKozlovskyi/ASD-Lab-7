package ie.atu.sw;

import java.io.File;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppWindow extends Application{
	private Visitor<Integer> visitor = new Visitor<>();
	 
	@Override
    public void start(Stage stage) throws Exception {
		//Build a tree of nodes to create the GUI
		stage.setTitle("GMIT - B.Sc. in Computing (Software Development)");
		stage.setWidth(500);
		stage.setHeight(500);
		stage.setOnCloseRequest((e) -> System.exit(0)); //Kill the VM when window is closed
		
		//VBox is a Layout (a Concrete Strategy) and the Scene object is the Context.
		VBox box = new VBox();
		box.setPadding(new Insets(10));
	    box.setSpacing(8);
	    
		NodeView viewer = getNodeViewer();
		viewer.setVisitor(visitor);
		
		Scene scene = new Scene(box);
		stage.setScene(scene);
		ToolBar toolBar = new ToolBar();
		
		Button btnNegative = new Button("Negative");
		toolBar.getItems().add(btnNegative);
		btnNegative.setOnAction(e -> {
			visitor.setCommand(pixel -> ~pixel);
			viewer.paint();
		});
				
		Button btnRed = new Button("Red");
		btnRed.setOnAction(e -> {
			visitor.setCommand(pixel -> (pixel & 0x00FF0000));
			viewer.paint();
		});
		toolBar.getItems().add(btnRed);

		Button btnGreen = new Button("Green");
		btnGreen.setOnAction(e -> {
			visitor.setCommand(pixel -> (pixel & 0x0000FF00));
			viewer.paint();
		});
		toolBar.getItems().add(btnGreen);

		Button btnBlue = new Button("Blue");
		btnBlue.setOnAction(e -> {			
			visitor.setCommand(pixel -> (pixel & 0x000000FF));
			viewer.paint();
		});
		toolBar.getItems().add(btnBlue);
		
		Button btnRandom = new Button("Randomise");
		btnRandom.setOnAction(e -> {			
			visitor.setCommand(pixel -> (pixel ^ new Random().nextInt())); //Random
			viewer.paint();
		});
		toolBar.getItems().add(btnRandom);
		
		box.getChildren().add(toolBar);
		box.getChildren().add(viewer);
		
		//Display the window
		stage.show();
		stage.centerOnScreen();
	}

    private NodeView getNodeViewer() throws Exception {
    	Image image = new Image(new File("./ireland.png").toURI().toString());
    	int width = (int) image.getWidth();
    	int height = (int) image.getHeight();

    	/*
		 * Java does not support generic arrays as arrays are covariant and this can lead to runtime 
		 *  	String [] s = new String[10];
		 *		Object [] objs = s;
		 *		objs[0] = new Date(); //Runtime ArrayStoreException.
		 * 
		 * Java does allow the creation of arrays of unbounded wildcard instantiations <?>, as these are 
		 * essentially the same as Object[]:
		 *      Node<?>[][] model = new Node<?>[image.getHeight()][image.getWidth()];
		 * 
		 * The following workaround can also be applied but will generate a warning from the compiler.
		 */
		@SuppressWarnings("unchecked")
		Node<Integer>[][] model = new Node[height][width];

		PixelReader pixels = image.getPixelReader();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				model[y][x] = new Node<>(Integer.valueOf(pixels.getArgb(x, y)));
			}
		}
		return new NodeView(model, width, height);
    }
    
	public static void main(String[] args) {
		Application.launch();
	}
}
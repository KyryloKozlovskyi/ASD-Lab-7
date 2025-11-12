package ie.atu.sw;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class NodeView extends Canvas{
	private Node<Integer>[][] model = null; //This is really the same as Object[][]
	private Visitor<Integer> visitor = new Visitor<>();
	
	public NodeView(Node<Integer>[][] model, int width, int height) {
		super(width, height);
		this.model = model;
		paint();
	}

	public void setVisitor(Visitor<Integer> visitor) {
		this.visitor = visitor;
	}

	public void paint() {
		GraphicsContext g = super.getGraphicsContext2D();
		
		PixelWriter pw = g.getPixelWriter();
		for (int row = 0; row < model.length; row++) {
			for (int col = 0; col < model[row].length; col++) {
				model[row][col].accept(visitor);
				pw.setColor(col, row, valueOf(model[row][col].getValue()));
			}
		}	
	}
	
	private Color valueOf(int pixel) {
		//ARGB Mode
		int mask = 0x000000FF;
		//int alpha = (pixel >>> 24);
		int red = (pixel >>> 16) & mask;
		int green = (pixel >>> 8) & mask;
		int blue = pixel & mask;
		
		return Color.rgb(red, green, blue);
	}

	public Collection<Number> getModel(){
		//This converts the 2D Node<Integer> array to a 1D array and then sends all the ints to a list
		return Arrays.stream(model).flatMap(i -> Arrays.stream(i)).map(e -> e.getValue()).collect(Collectors.toList());
	}
}

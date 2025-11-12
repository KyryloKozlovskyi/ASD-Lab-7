package ie.atu.sw;

import java.util.function.*;

public class Visitor<T> {
	private Function<T, T> command;

	public Visitor() {
		this.command = Function.identity();
	}

	public void setCommand(Function<T, T> command) {
		this.command = command;
	}

	public void visit(Visitable<T> element) {
		element.setValue(command.apply(element.getValue()));
	}
}
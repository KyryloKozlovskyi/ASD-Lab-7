package ie.atu.sw;

public class Node<T> implements Visitable<T> {
	private T value;

	public Node(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}

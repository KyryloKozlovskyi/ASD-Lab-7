package ie.atu.sw;

public interface Visitable<T> {
	public default void accept(Visitor<T> v) {
		v.visit(this);
	}

	public T getValue();

	public void setValue(T val);
}

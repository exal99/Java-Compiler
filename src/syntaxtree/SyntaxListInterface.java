package syntaxtree;


public interface SyntaxListInterface<T extends AbstractSyntax> {
	
	public void addElement(T element);
	public T elementAt(int i);
	public int size();

}

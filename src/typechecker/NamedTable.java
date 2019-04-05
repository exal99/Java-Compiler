package typechecker;

public class NamedTable<E> extends Table<E> {
	
	private static final long serialVersionUID = -4992266344979587831L;
	protected String name;

	public NamedTable(String name, Table parrent) {
		super(parrent);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}

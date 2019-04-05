package syntaxtree;

public class MainClass extends AbstractSyntax{
	
	public Identifier className, varName;
	public Statement statement;
	
	public MainClass(int pos, Identifier className, Identifier varName, Statement statement) {
		super(pos);
		this.className = className;
		this.varName = varName;
		this.statement = statement;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}

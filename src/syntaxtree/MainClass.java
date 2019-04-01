package syntaxtree;

public class MainClass extends AbstractSyntax{
	Identifier className, varName;
	Statement statement;
	
	public MainClass(int pos, Identifier className, Identifier varName, Statement statement) {
		super(pos);
		this.className = className;
		this.varName = varName;
		this.statement = statement;
	}


	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}

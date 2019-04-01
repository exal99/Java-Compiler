package syntaxtree;

public class MethodDecl extends AbstractSyntax {
	
	private Type type;
	private Identifier name;
	private FormalList args;
	private VarDeclList vars;
	private StatementList statements;
	private Expression exp;
	
	public MethodDecl(int pos, Type type, Identifier name, FormalList args, VarDeclList vars, StatementList statements, Expression exp) {
		super(pos);
		this.type = type;
		this.name = name;
		this.args = args;
		this.vars = vars;
		this.statements = statements;
		this.exp = exp;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}

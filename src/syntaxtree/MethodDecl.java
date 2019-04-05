package syntaxtree;

public class MethodDecl extends AbstractSyntax {
	
	public Type type;
	public Identifier name;
	public FormalList args;
	public VarDeclList vars;
	public StatementList statements;
	public Expression exp;
	
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
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}

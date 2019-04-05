package syntaxtree;

public abstract class ClassDecl extends AbstractSyntax {
	
	public Identifier className;
	public VarDeclList vars;
	public MethodDeclList methods;
	
	protected ClassDecl(int pos, Identifier className, VarDeclList vars, MethodDeclList methods) {
		super(pos);
		this.className = className;
		this.vars = vars;
		this.methods = methods;
	}
}

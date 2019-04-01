package syntaxtree;

public abstract class ClassDecl extends AbstractSyntax {
	
	protected Identifier className;
	protected VarDeclList vars;
	protected MethodDeclList methods;
	
	protected ClassDecl(int pos, Identifier className, VarDeclList vars, MethodDeclList methods) {
		super(pos);
		this.className = className;
		this.vars = vars;
		this.methods = methods;
	}
}

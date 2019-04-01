package syntaxtree;

public class ClassDeclExtends extends ClassDecl {
	
	private Identifier superName;
	
	public ClassDeclExtends(int pos, Identifier className, Identifier superName, VarDeclList vars, MethodDeclList methods) {
		super(pos, className, vars, methods);
		this.superName = superName;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}

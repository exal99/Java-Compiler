package syntaxtree;

public class ClassDeclExtends extends ClassDecl {
	
	public Identifier superName;
	
	public ClassDeclExtends(int pos, Identifier className, Identifier superName, VarDeclList vars, MethodDeclList methods) {
		super(pos, className, vars, methods);
		this.superName = superName;
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}

}

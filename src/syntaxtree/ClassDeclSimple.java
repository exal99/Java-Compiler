package syntaxtree;

public class ClassDeclSimple extends ClassDecl {
	
	public ClassDeclSimple(int pos, Identifier className, VarDeclList vars, MethodDeclList methods) {
		super(pos, className, vars, methods);
	}

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}

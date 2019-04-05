package syntaxtree;

public interface Visitor<E> {
	public E visit(Program prog);
	public E visit(MainClass mainClass);
	public E visit(ClassDeclSimple classDecl);
	public E visit(ClassDeclExtends classDecl);
	public E visit(VarDecl varDecl);
	public E visit(MethodDecl methodDecl);
	public E visit(Formal formal);
	public E visit(IntArrayType intArrayType);
	public E visit(BooleanType boolType);
	public E visit(IntegerType intType);
	public E visit(IdentifierType idType);
	public E visit(Block block);
	public E visit(If i);
	public E visit(While w);
	public E visit(Print print);
	public E visit(Assign assign);
	public E visit(ArrayAssign arrayAssign);
	public E visit(And and);
	public E visit(LessThan lt);
	public E visit(Plus plus);
	public E visit(Minus minus);
	public E visit(Times times);
	public E visit(ArrayLookup arrayLookup);
	public E visit(ArrayLength arrayLength);
	public E visit(Call call);
	public E visit(IntegerLiteral intLiteral);
	public E visit(True t);
	public E visit(False f);
	public E visit(IdentifierExpression idExp);
	public E visit(This t);
	public E visit(NewArray newArray);
	public E visit(NewObject newObject);
	public E visit(Not not);
	public E visit(Identifier id);
	
}

package syntaxtree;

public interface Visitor {
	public void visit(Program prog);
	public void visit(MainClass mainClass);
	public void visit(ClassDeclSimple classDecl);
	public void visit(ClassDeclExtends classDecl);
	public void visit(VarDecl varDecl);
	public void visit(MethodDecl methodDecl);
	public void visit(Formal formal);
	public void visit(IntArrayType intArrayType);
	public void visit(BooleanType boolType);
	public void visit(IntegerType intType);
	public void visit(IdentifierType idType);
	public void visit(Block block);
	public void visit(If i);
	public void visit(While w);
	public void visit(Print print);
	public void visit(Assign assign);
	public void visit(ArrayAssign arrayAssign);
	public void visit(And and);
	public void visit(LessThan lt);
	public void visit(Plus plus);
	public void visit(Minus minus);
	public void visit(Times times);
	public void visit(ArrayLookup arrayLookup);
	public void visit(ArrayLength arrayLength);
	public void visit(Call call);
	public void visit(IntegerLiteral intLiteral);
	public void visit(True t);
	public void visit(False f);
	public void visit(IdentifierExpression idExp);
	public void visit(This t);
	public void visit(NewArray newArray);
	public void visit(NewObject newObject);
	public void visit(Not not);
	public void visit(Identifier id);
	
}

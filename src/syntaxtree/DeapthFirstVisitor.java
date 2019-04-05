package syntaxtree;


public abstract class DeapthFirstVisitor<E> implements Visitor<E> {

	@Override
	public E visit(Program prog) {
		prog.main.accept(this);
		for (int i = 0; i < prog.classList.size(); i++) {
			prog.classList.elementAt(i).accept(this);
		}
		return null;
	}

	@Override
	public E visit(MainClass mainClass) {
		mainClass.className.accept(this);
		mainClass.varName.accept(this);
		mainClass.statement.accept(this);
		return null;
	}

	@Override
	public E visit(ClassDeclSimple classDecl) {
		classDecl.className.accept(this);
		for (int i = 0; i < classDecl.vars.size(); i++) {
			classDecl.vars.elementAt(i).accept(this);
		}
		
		for (int i = 0; i < classDecl.methods.size(); i++) {
			classDecl.methods.elementAt(i).accept(this);
		}
		return null;
	}

	@Override
	public E visit(ClassDeclExtends classDecl) {
		classDecl.className.accept(this);
		classDecl.superName.accept(this);
		for (int i = 0; i < classDecl.vars.size(); i++) {
			classDecl.vars.elementAt(i).accept(this);
		}
		
		for (int i = 0; i < classDecl.methods.size(); i++) {
			classDecl.methods.elementAt(i).accept(this);
		}
		return null;
	}

	@Override
	public E visit(VarDecl varDecl) {
		varDecl.name.accept(this);
		varDecl.type.accept(this);
		return null;
	}

	@Override
	public E visit(MethodDecl methodDecl) {
		
		methodDecl.type.accept(this);
		methodDecl.name.accept(this);
			
		for (int i = 0; i < methodDecl.args.size(); i++) {
			methodDecl.args.elementAt(i).accept(this);
		}
		
		for (int i = 0; i < methodDecl.vars.size(); i++) {
			methodDecl.vars.elementAt(i).accept(this);
		}
		
		for (int i = 0; i < methodDecl.statements.size(); i++) {
			methodDecl.statements.elementAt(i).accept(this);
		}
			
		methodDecl.exp.accept(this);
			
		return null;
	}

	@Override
	public E visit(Formal formal) {
		formal.id.accept(this);
		formal.type.accept(this);
		return null;
	}

	@Override
	public E visit(IntArrayType intArrayType) {
		return null;
	}

	@Override
	public E visit(BooleanType boolType) {
		return null;
	}

	@Override
	public E visit(IntegerType intType) {
		return null;
	}

	@Override
	public E visit(IdentifierType idType) {
		return null;
	}

	@Override
	public E visit(Block block) {
		for (int i=0; i < block.statements.size(); i++) {
			block.statements.elementAt(i).accept(this);
		}
		return null;
	}

	@Override
	public E visit(If i) {
		i.exp.accept(this);
		i.s.accept(this);
		i.elseS.accept(this);
		return null;
	}

	@Override
	public E visit(While w) {
		w.exp.accept(this);
		w.s.accept(this);
		return null;
	}

	@Override
	public E visit(Print print) {
		print.exp.accept(this);
		return null;
	}

	@Override
	public E visit(Assign assign) {
		assign.name.accept(this);
		assign.value.accept(this);
		return null;
	}

	@Override
	public E visit(ArrayAssign arrayAssign) {
		arrayAssign.name.accept(this);
		arrayAssign.index.accept(this);
		arrayAssign.value.accept(this);
		return null;
	}

	@Override
	public E visit(And and) {
		and.left.accept(this);
		and.right.accept(this);
		return null;
	}

	@Override
	public E visit(LessThan lt) {
		lt.left.accept(this);
		lt.right.accept(this);
		return null;
	}

	@Override
	public E visit(Plus plus) {
		plus.left.accept(this);
		plus.right.accept(this);
		return null;
	}

	@Override
	public E visit(Minus minus) {
		minus.left.accept(this);
		minus.right.accept(this);
		return null;
	}

	@Override
	public E visit(Times times) {
		times.left.accept(this);
		times.right.accept(this);
		return null;
	}

	@Override
	public E visit(ArrayLookup arrayLookup) {
		arrayLookup.index.accept(this);
		arrayLookup.value.accept(this);
		return null;
	}

	@Override
	public E visit(ArrayLength arrayLength) {
		arrayLength.array.accept(this);
		return null;
	}

	@Override
	public E visit(Call call) {
		call.obj.accept(this);
		call.method.accept(this);
		for (int i = 0; i < call.args.size(); i++) {
			call.args.elementAt(i).accept(this);
		}
		return null;
	}

	@Override
	public E visit(IntegerLiteral intLiteral) {
		return null;
	}

	@Override
	public E visit(True t) {
		return null;
	}

	@Override
	public E visit(False f) {
		return null;
	}

	@Override
	public E visit(IdentifierExpression idExp) {
		return null;
	}

	@Override
	public E visit(This t) {
		return null;
	}

	@Override
	public E visit(NewArray newArray) {
		newArray.size.accept(this);
		return null;
	}

	@Override
	public E visit(NewObject newObject) {
		return null;
	}

	@Override
	public E visit(Not not) {
		not.exp.accept(this);
		return null;
	}

	@Override
	public E visit(Identifier id) {
		// TODO Auto-generated method stub
		return null;
	}
}

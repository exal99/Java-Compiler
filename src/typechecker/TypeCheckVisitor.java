package typechecker;

import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.DeapthFirstVisitor;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Times;
import syntaxtree.Type;
import syntaxtree.While;


public class TypeCheckVisitor extends DeapthFirstVisitor<Type> {
	
	private GlobalTable gTable;
	private ClassTable cTable;
	private MethodTable mTable;
	
	private ErrorMsg error;
	
	public TypeCheckVisitor(GlobalTable gTable) {
		this.gTable = gTable;
		error = new ErrorMsg();
	}
	
	@Override
	public Type visit(ClassDeclSimple classDecl) {
		cTable = gTable.getClassTable(classDecl.className.toString());
		super.visit(classDecl);
		cTable = null;
		return null;
	}
	
	@Override
	public Type visit(ClassDeclExtends classDecl) {
		cTable = gTable.getClassTable(classDecl.className.toString());
		super.visit(classDecl);
		cTable = null;
		return null;
	}
	
	@Override
	public Type visit(MethodDecl method) {
		mTable = cTable.getMethod(method.name.toString());
		super.visit(method);
		mTable = null;
		return null;
	}
	
	@Override
	public Type visit(IntArrayType array) {
		return array;
	}
	
	@Override
	public Type visit(BooleanType bool) {
		return bool;
	}
	
	@Override
	public Type visit(IntegerType i) {
		return i;
	}
	
	@Override
	public Type visit(IdentifierType idType) {
		return idType;
	}
	
	@Override
	public Type visit(If i) {
		Type t = i.exp.accept(this);
		if (! (t instanceof BooleanType)) {
			error.complain(error.formatTypeMissmatch(t.toString(), "boolean"));
		}
		
		i.s.accept(this);
		i.elseS.accept(this);
		
		return null;
	}
	
	@Override
	public Type visit(While w) {
		Type t = w.exp.accept(this);
		if (! (t instanceof BooleanType)) {
			error.complain(error.formatTypeMissmatch(t.toString(), "boolean"));
		}
		
		w.s.accept(this);
		
		return null;
	}
	
	@Override
	public Type visit(Print print) {
		Type t = print.exp.accept(this);
		if (!(t instanceof IntegerType)) {
			error.complain(error.formatTypeMissmatch(t.toString(), "int"));
		}
		
		return null;
	}
	
	@Override
	public Type visit(Assign assign) {
		Type lhs = varLookup(assign.name.toString());
		Type rhs = assign.value.accept(this);
		
		if (lhs == null) {
			error.complain("No variable named: '" + assign.name + "'");
			return null;
		}
		
		if (rhs == null)
			return null;
		
		if (lhs instanceof IdentifierType) {
			if (!(rhs instanceof IdentifierType)) {
				error.complain(error.formatTypeMissmatch(rhs.toString(), lhs.toString()));
				return null;
			}
			IdentifierType varType = (IdentifierType) lhs;
			IdentifierType typeToCheck = (IdentifierType) rhs;
			
			ClassTable cTable = gTable.getClassTable(typeToCheck.name);
			if (cTable != null) {
				if (!cTable.instanceOf(varType.name))
					error.complain(error.formatTypeMissmatch(rhs.toString(), lhs.toString()));
			} else
				error.complain("No class named '" + rhs + "'");
		} else {
			if (!lhs.equals(rhs))
				error.complain(error.formatTypeMissmatch(rhs.toString(), lhs.toString()));
		}
		
		return null;
	}
	
	@Override
	public Type visit(ArrayAssign assign) {
		Type array = varLookup(assign.name.toString());
		if (array == null) {
			error.complain("No variable named: '" + assign.name + "'");
		}
		if (!(array instanceof IntArrayType)) {
			error.complain(error.formatTypeMissmatch(array.toString(), "int[]"));
		}
		
		Type index = assign.index.accept(this);
		Type value = assign.value.accept(this);
		
		if (!(index instanceof IntegerType)) 
			error.complain(error.formatTypeMissmatch(index.toString(), "int"));
		if (!(value instanceof IntegerType)) 
			error.complain(error.formatTypeMissmatch(value.toString(), "int"));
		
		return null;
	}
	
	private void checkOperator(Type left, Type right, Class<? extends Type> type, String name) {		
		if (!type.isInstance(left)) 
			error.complain(error.formatTypeMissmatch(left.toString(), name));
		
		if (!type.isInstance(right))
			error.complain(error.formatTypeMissmatch(right.toString(), name));
	}
	
	@Override
	public Type visit(Plus p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int");
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(Minus p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int");
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(Times p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int");
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(LessThan p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int");
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(And p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, BooleanType.class, "boolean");
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(ArrayLookup arrayLookup) {
		Type arrayType = arrayLookup.value.accept(this);
		Type indexType = arrayLookup.value.accept(this);
		
		typeCheck(arrayType, IntArrayType.class, "int[]");
		typeCheck(indexType, IntegerType.class, "int");
		
		return new IntegerType(arrayLookup.getPos());
	}
	
	@Override
	public Type visit(ArrayLength arrayLength) {
		Type arrayType = arrayLength.array.accept(this);
		typeCheck(arrayType, IntArrayType.class, "int[]");
		
		return new IntegerType(arrayLength.getPos());
	}
	
	@Override
	public Type visit(Call call) {
		Type object = call.obj.accept(this);	
	}
	
	
	private void typeCheck(Type type, Class<? extends Type> expected, String name) {
		if (!expected.isInstance(type)) {
			error.complain(error.formatTypeMissmatch(type.toString(), name));
		}
	}
	
	private Type varLookup(String id) {
		if (mTable != null) {
			return mTable.getVariable(id);
		}
		return null;
	}
}

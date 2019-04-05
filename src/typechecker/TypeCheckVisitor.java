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
import syntaxtree.False;
import syntaxtree.IdentifierExpression;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
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
	public Type visit(MainClass mainClass) {
		cTable = gTable.getClassTable(mainClass.className.toString());
		mTable = cTable.getMethod("main");
		super.visit(mainClass);
		mTable = null;
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
			error.complain(error.formatTypeMissmatch(t.toString(), "boolean"), cTable.getName() + "." + mTable.getName(), i.getPos());
		}
		
		i.s.accept(this);
		i.elseS.accept(this);
		
		return null;
	}
	
	@Override
	public Type visit(While w) {
		Type t = w.exp.accept(this);
		if (! (t instanceof BooleanType)) {
			error.complain(error.formatTypeMissmatch(t.toString(), "boolean"), cTable.getName() + "." + mTable.getName(), w.getPos());
		}
		
		w.s.accept(this);
		
		return null;
	}
	
	@Override
	public Type visit(Print print) {
		Type t = print.exp.accept(this);
		if (!(t instanceof IntegerType)) {
			error.complain(error.formatTypeMissmatch(t.toString(), "int"), cTable.getName() + "." + mTable.getName(), print.getPos());
		}
		
		return null;
	}
	
	@Override
	public Type visit(Assign assign) {
		Type lhs = varLookup(assign.name.toString());
		Type rhs = assign.value.accept(this);
		
		if (lhs == null) {
			error.complain("No variable named: '" + assign.name + "'", cTable.getName() + "." + mTable.getName(), assign.getPos());
			return null;
		}
		
		if (rhs == null)
			return null;
		
		if (lhs instanceof IdentifierType) {
			if (!(rhs instanceof IdentifierType)) {
				error.complain(error.formatTypeMissmatch(rhs.toString(), lhs.toString()), cTable.getName() + "." + mTable.getName(), assign.getPos());
				return null;
			}
			IdentifierType varType = (IdentifierType) lhs;
			IdentifierType typeToCheck = (IdentifierType) rhs;
			
			ClassTable cTable = gTable.getClassTable(typeToCheck.name);
			if (cTable != null) {
				if (!cTable.instanceOf(varType.name))
					error.complain(error.formatTypeMissmatch(rhs.toString(), lhs.toString()), cTable.getName() + "." + mTable.getName(), assign.getPos());
			} else
				error.complain("No class named '" + rhs + "'", this.cTable.getName() + "." + mTable.getName(), assign.getPos());
		} else {
			if (!lhs.equals(rhs))
				error.complain(error.formatTypeMissmatch(rhs.toString(), lhs.toString()), cTable.getName() + "." + mTable.getName(), assign.getPos());
		}
		
		return null;
	}
	
	@Override
	public Type visit(ArrayAssign assign) {
		Type array = varLookup(assign.name.toString());
		if (array == null) {
			error.complain("No variable named: '" + assign.name + "'", cTable.getName() + "." + mTable.getName(), assign.getPos());
		}
		if (!(array instanceof IntArrayType)) {
			error.complain(error.formatTypeMissmatch(array.toString(), "int[]"), cTable.getName() + "." + mTable.getName(), assign.getPos());
		}
		
		Type index = assign.index.accept(this);
		Type value = assign.value.accept(this);
		
		if (!(index instanceof IntegerType)) 
			error.complain(error.formatTypeMissmatch(index.toString(), "int"), cTable.getName() + "." + mTable.getName(), assign.getPos());
		if (!(value instanceof IntegerType)) 
			error.complain(error.formatTypeMissmatch(value.toString(), "int"), cTable.getName() + "." + mTable.getName(), assign.getPos());
		
		return null;
	}
	
	private void checkOperator(Type left, Type right, Class<? extends Type> type, String name, String pos, int lineno) {		
		if (!type.isInstance(left)) 
			error.complain(error.formatTypeMissmatch(left.toString(), name), pos, lineno);
		
		if (!type.isInstance(right))
			error.complain(error.formatTypeMissmatch(right.toString(), name), pos, lineno);
	}
	
	@Override
	public Type visit(Plus p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int", cTable.getName() + "." + mTable.getName(), p.getPos());
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(Minus p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int", cTable.getName() + "." + mTable.getName(), p.getPos());
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(Times p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int", cTable.getName() + "." + mTable.getName(), p.getPos());
		return new IntegerType(p.getPos());
	}
	
	@Override
	public Type visit(LessThan p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, IntegerType.class, "int", cTable.getName() + "." + mTable.getName(), p.getPos());
		return new BooleanType(p.getPos());
	}
	
	@Override
	public Type visit(And p) {
		Type left = p.left.accept(this);
		Type right = p.right.accept(this);
		checkOperator(left, right, BooleanType.class, "boolean", cTable.getName() + "." + mTable.getName(), p.getPos());
		return new BooleanType(p.getPos());
	}
	
	@Override
	public Type visit(ArrayLookup arrayLookup) {
		Type arrayType = arrayLookup.value.accept(this);
		Type indexType = arrayLookup.value.accept(this);
		
		typeCheck(arrayType, IntArrayType.class, "int[]", arrayLookup.getPos());
		typeCheck(indexType, IntegerType.class, "int", arrayLookup.getPos());
		
		return new IntegerType(arrayLookup.getPos());
	}
	
	@Override
	public Type visit(ArrayLength arrayLength) {
		Type arrayType = arrayLength.array.accept(this);
		typeCheck(arrayType, IntArrayType.class, "int[]", arrayLength.getPos());
		
		return new IntegerType(arrayLength.getPos());
	}
	
	@Override
	public Type visit(Call call) {
		Type object = call.obj.accept(this);
		if (!(object instanceof IdentifierType)) {
			error.complain("Object of type: '" + object + "' has no method named: '" + call.method, cTable.getName() + "." + mTable.getName(), call.getPos());
			return null;
		}
		IdentifierType realObject = (IdentifierType) object;
		ClassTable cTable = gTable.getClassTable(realObject.name);
		
		if (cTable == null) {
			error.complain("No class named: '" + realObject + "'", this.cTable.getName() + "." + mTable.getName(), call.getPos());
			return null;
		}
		
		MethodTable mTable = cTable.getMethod(call.method.toString());
		
		if (mTable == null) {
			error.complain("Object of type '" + cTable.getName() + "' has no method named '" + call.method + "'", this.cTable.getName() + "." + this.mTable.getName(), call.getPos());
			return null;
		}
		
		Type[] argTypes = mTable.getFormalOrder();
		Type[] gotTypes = new Type[call.args.size()];
		boolean error = false;
		for (int i = 0; i < call.args.size(); i++) {
			if (i < argTypes.length) {
				Type expected = argTypes[i];
				Type gotType = call.args.elementAt(i).accept(this);
				gotTypes[i] = gotType;
				if (expected instanceof IdentifierType) {
					error |= !(gotType instanceof IdentifierType);
					if (!error) {
						ClassTable expectedClass = gTable.getClassTable(gotType.toString());
						error |= !expectedClass.instanceOf(expected.toString());
					}
				} else {
					error |= !argTypes[i].equals(gotType);
				}
			} else {
				error = true;
				gotTypes[i] = call.args.elementAt(i).accept(this);
			}
		}
		
		if (error)
			formatCallError(cTable.getName(), call.method.toString(), argTypes, gotTypes, call.getPos());
		
		return mTable.getType();
	}
	
	@Override
	public Type visit(IntegerLiteral integer) {
		return new IntegerType(integer.getPos());
	}
	
	@Override
	public Type visit(True t) {
		return new BooleanType(t.getPos());
	}
	
	@Override
	public Type visit(False f) {
		return new BooleanType(f.getPos());
	}
	
	@Override
	public Type visit(IdentifierExpression idExp) {
		Type res = mTable.getVariable(idExp.id);
		if (res == null) {
			error.complain("No variable named '" + idExp.id + "'", cTable.getName() + "." + mTable.getName(), idExp.getPos());
			return null;
		} else {
			return res;
		}
	}
	
	@Override
	public Type visit(This t) {
		return new IdentifierType(t.getPos(), cTable.getName());
	}
	
	@Override
	public Type visit(NewArray newArray) {
		Type length = newArray.size.accept(this);
		if (!(length instanceof IntegerType)) {
			error.complain(error.formatTypeMissmatch(length.toString(), "int"), cTable.getName() + "." + mTable.getName(), newArray.getPos());
		}
		return new IntArrayType(newArray.getPos());
	}
	
	@Override
	public Type visit(NewObject newObject) {
		if (gTable.getClassTable(newObject.objName.toString()) == null) {
			error.complain("No class named: '" + newObject.objName + "'", cTable.getName() + "." + mTable.getName(), newObject.getPos());
			return null;
		}
		return new IdentifierType(newObject.getPos(), newObject.objName.toString());
	}
	
	@Override
	public Type visit(Not n) {
		Type t = n.exp.accept(this);
		if (!(t instanceof BooleanType)) {
			error.complain(error.formatTypeMissmatch(t.toString(), "boolean"), cTable.getName() + "." + mTable.getName(), n.getPos());
		}
		return new BooleanType(n.getPos());
	}
	
	
	private void formatCallError(String className, String methodName, Type[] expectedTypes, Type[] gotTypes, int lineno) {
		error.complain("The method " + methodName + formatArgs(expectedTypes) + " in the type " + className+
				       " is not applicable for the arguments " + formatArgs(gotTypes), cTable.getName() + "." + mTable.getName(), lineno);
	}
	
	private String formatArgs(Type[] args) {
		StringBuilder sb = new StringBuilder("(");
		for (Type type : args) {
			sb.append(type + ", ");
		}
		
		sb.delete(sb.length() - 2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	
	private void typeCheck(Type type, Class<? extends Type> expected, String name, int lineno) {
		if (!expected.isInstance(type)) {
			error.complain(error.formatTypeMissmatch(type.toString(), name), cTable.getName() + "." + mTable.getName(), lineno);
		}
	}
	
	private Type varLookup(String id) {
		if (mTable != null) {
			return mTable.getVariable(id);
		}
		return null;
	}
}

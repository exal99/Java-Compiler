package typechecker;

import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.DeapthFirstVisitor;
import syntaxtree.Formal;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Program;
import syntaxtree.VarDecl;

public class MiniJavaSymbolVisitor extends DeapthFirstVisitor<Void>{
	
	private GlobalTable gTable;
	private ClassTable cTable;
	private MethodTable mTable;
	
	private ErrorMsg error;
	
	
	public MiniJavaSymbolVisitor() {
		gTable = new GlobalTable();
		error = new ErrorMsg();
		cTable = null;
		mTable = null;
	}

	@Override
	public Void visit(Program prog) {
		prog.main.accept(this);
		for (int i = 0; i < prog.classList.size(); i++) {
			prog.classList.elementAt(i).accept(this);
		}
		return null;
	}

	@Override
	public Void visit(MainClass mainClass) {
		return null;
	}

	@Override
	public Void visit(ClassDeclSimple classDecl) {
		ClassTable table = gTable.addClass(classDecl.className.toString());
		if (table == null) {
			error.complain(error.formatDuplicate("class", classDecl.className.toString(), gTable.getName(), classDecl.getPos()));
			error.complain("Multiple definitions of class: '" + classDecl.className + "'");
		} else {
			cTable = table;
			for (int i = 0; i < classDecl.vars.size(); i++) {
				classDecl.vars.elementAt(i).accept(this);
			}
			
			for (int i = 0; i < classDecl.methods.size(); i++) {
				classDecl.methods.elementAt(i).accept(this);
			}
			cTable = null;
		}
		return null;
	}

	@Override
	public Void visit(ClassDeclExtends classDecl) {
		ClassTable table = gTable.addClass(classDecl.className.toString(), classDecl.superName.toString());
		if (table == null) {
			if (gTable.contains(classDecl.className.toString()))
				error.complain(error.formatDuplicate("class", classDecl.className.toString(), gTable.getName(), classDecl.getPos()));
			else
				error.complain("No class named: '" + classDecl.superName + "'");
		} else {
			cTable = table;
			for (int i = 0; i < classDecl.vars.size(); i++) {
				classDecl.vars.elementAt(i).accept(this);
			}
			
			for (int i = 0; i < classDecl.methods.size(); i++) {
				classDecl.methods.elementAt(i).accept(this);
			}
			cTable = null;
		}
		return null;
	}

	@Override
	public Void visit(VarDecl varDecl) {
		if (mTable != null) {
			if (!mTable.addLocal(varDecl.name.toString(), varDecl.type))
				error.complain(error.formatDuplicate("variable", varDecl.name.toString(), cTable.getName() + "." + mTable.getName(), varDecl.getPos()));
		} else {
			if (!cTable.addField(varDecl.name.toString(), varDecl.type))
				error.complain(error.formatDuplicate("field", varDecl.name.toString(), cTable.getName(), varDecl.getPos()));
		}
		
		return null;
	}

	@Override
	public Void visit(MethodDecl methodDecl) {
		MethodTable newMethod = cTable.addMethod(methodDecl.name.toString(), methodDecl.type);
		if (newMethod == null) {
			error.complain(error.formatDuplicate("method", methodDecl.name.toString(), cTable.getName(), methodDecl.getPos()));
		} else {
			mTable = newMethod;
			
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
			
			mTable = null;
		}
		return null;
	}

	@Override
	public Void visit(Formal formal) {
		mTable.addFormal(formal.id.toString(), formal.type);
		return null;
	}

	

}

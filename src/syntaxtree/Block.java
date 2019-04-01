package syntaxtree;

public class Block extends Statement {
	
	private StatementList statements;
	
	public Block(int pos, StatementList statements) {
		super(pos);
		this.statements = statements;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}

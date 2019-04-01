package syntaxtree;

public class Program extends AbstractSyntax{
	
	private MainClass main;
	private ClassDeclList classList;
	
	public Program(int pos, MainClass main, ClassDeclList classList) {
		super(pos);
		this.main = main;
		this.classList = classList;
	}
	

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}

package syntaxtree;

public class Program extends AbstractSyntax{
	
	public MainClass main;
	public ClassDeclList classList;
	
	public Program(int pos, MainClass main, ClassDeclList classList) {
		super(pos);
		this.main = main;
		this.classList = classList;
	}
	

	@Override
	public <E> E accept(Visitor<E> v) {
		return v.visit(this);
	}
}

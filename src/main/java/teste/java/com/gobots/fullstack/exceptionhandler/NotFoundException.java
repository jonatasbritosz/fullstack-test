package teste.java.com.gobots.fullstack.exceptionhandler;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1016934173109372285L;

	public NotFoundException(String msg) {
        super(msg);
    }
}

public class Token {
    private String tipo;
    private String lexema;

    public Token(String tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLexema() {
        return lexema;
    }
}

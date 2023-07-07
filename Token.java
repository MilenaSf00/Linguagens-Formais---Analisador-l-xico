import Tokens.TokenNome;

public class Token {
    private TokenNome tipo;
    private String texto;
    private int linha;
    private int coluna;

    public Token(TokenNome tipo, String texto, int linha, int coluna) {
        this.tipo = tipo;
        this.texto = texto;
        this.linha = linha;
        this.coluna = coluna;
    }

    public TokenNome getTipo() {
        return tipo;
    }

    public String getTexto() {
        return texto;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setTipo(TokenNome tipo) {
        this.tipo = tipo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tipo=" + tipo +
                ", texto='" + texto  +
                ", linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }
}
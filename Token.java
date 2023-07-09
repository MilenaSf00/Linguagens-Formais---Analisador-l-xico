import Tokens.TokenNome;

public class Token {
    private TokenNome tipo;
    private String token;
    private int linha;
    private int coluna;

    public Token(TokenNome tipo, String token, int linha, int coluna) {
        this.tipo = tipo;
        this.token = token;
        this.linha = linha;
        this.coluna = coluna;
    }

    public TokenNome getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
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

    public void setToken(String token) {
        this.token = token;
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
                ", token='" + token  +
                ", linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }


 
}
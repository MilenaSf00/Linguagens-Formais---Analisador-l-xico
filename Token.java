import Tokens.TokenComentario;
import Tokens.TokenDelimitador;
import Tokens.TokenNome;
import Tokens.TokenOperador;

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
    

    public String getSimbolo() {
        switch (tipo) {
            case DELIMITADOR:
                return TokenDelimitador.valueOf(token).getSimbolo();
            case OP:
                return TokenOperador.valueOf(token).getSimbolo();
            case COMENTARIO:
                return TokenComentario.valueOf(token).getSimbolo();
            case BOOLEAN:
            case CONDICIONAL:
            case NSTRING:
            case PALAVRA_RESERVADA:
            case REPETICAO:
            case VARIAVEIS:
            case ESCRITA:
            case LEITURA:
            case NINT:
            case NREAL:
            case ID:
                return token;
            default:
                return "";
        }
    }

    
    @Override
    public String toString() {
        String tipoString = String.format("%-20s", tipo.toString());
        String tokenString = String.format("%-56s", token);
        String simboloString = String.format("%-56s", getSimbolo());
        String linhaString = String.format("%5s", linha);
        String colunaString = String.format("%6s", coluna);
        return String.format("│ %s │ %s │ %s │ %s │ %s │", tipoString, tokenString, simboloString, linhaString, colunaString);
    }
}
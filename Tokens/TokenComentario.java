package Tokens;

public enum TokenComentario {
     BARRA_DE_COMENTARIO("//"),
    INICIO_COMENTARIO_BLOCO("/*"),
    FIM_COMENTARIO_BLOCO("*/");

    private String simbolo;

    TokenComentario(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}

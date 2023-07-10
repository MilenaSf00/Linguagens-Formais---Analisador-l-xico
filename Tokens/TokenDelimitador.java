package Tokens;

public enum TokenDelimitador {
    ABRE_CHAVES("{"),
    FECHA_CHAVES("}"),
    ABRE_PARENTESES("("),
    FECHA_PARENTESES(")"), // )
    ABRE_COLCHETES("["), // [
    FECHA_COLCHETES("]"), // ]
    VIRGULA(","), // ,
    PONTO_E_VIRGULA(";"),  // ;
    PONTO("."), //.
    DOIS_PONTOS(":"); 

    private String simbolo;

    TokenDelimitador(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

}

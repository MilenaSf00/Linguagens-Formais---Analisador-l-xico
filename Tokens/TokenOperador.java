package Tokens;

public enum TokenOperador {
    ADICAO("+"),
    SUBTRACAO("-"),
    MULTIPLICACAO("*"),
    ATRIBUICAO("="),
    PORCENTAGEM("%"),
    E_LOGICO("&&"),
    OU_LOGICO("||"),
    NEGACAO_LOGICA("!"),
    MAIOR(">"),
    MENOR("<"),
    MAIOR_IGUAL(">="),
    MENOR_IGUAL("<="),
    IGUAL("=="),
    DIVISOR("/");

    private String simbolo;

    TokenOperador(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

}

package Tokens;

public enum TokenEscrita {
   PROMPT("prompt");
    private final String valor;

    TokenEscrita(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}

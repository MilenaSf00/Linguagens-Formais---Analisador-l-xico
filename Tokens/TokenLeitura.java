package Tokens;

public enum TokenLeitura {
   
    CONSOLE_LOG("console.log");
    private final String valor;

    TokenLeitura(String valor) { 
        this.valor = valor; 
    }
    public String getValor() {
        return valor;
    }
}

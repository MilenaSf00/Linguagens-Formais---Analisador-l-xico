package Tokens;

public enum TokenLeitura {
   
    CONSOLE_LOG("console.log");
    private final String valor;

    TokenLeitura(String valor) { //precisa adicionar um construtor ao enum TokenLeitura para receber o valor como parâmetro. 
        this.valor = valor;     //recebe o valor como parâmetro e atribui ao campo valor do enum. 
    }
    public String getValor() {
        return valor;
    }
}

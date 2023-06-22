# ESPECIFICAÇÃO LÉXICA DA LINGUAGEM JAVASCRIPT
##
  Token: ID ; Padrão de formação: letra(letra+dígito)*
  
  Token: Nreal ; Padrão de formação: dígito+.dígito∗
  
  Token: Nint ; Padrão de formação: dígito+
  
  Token: Nstring ; Padrão de formação: “(letra + d´ıgito + simbolo)∗”

  Token: op ; Padrão de formação: ‘+’ + ‘-’ + ‘*’ + ‘/’ + ‘=’
  
Obs.: espaços em branco e quebras de linha devem ser descartados durante o reconhecimento dos
tokens.

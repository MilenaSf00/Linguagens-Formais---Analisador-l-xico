import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lexer {

    private List<String> operators = Arrays.asList("+", "-", "*", "/", "=");
    private List<String> reservedWords = Arrays.asList("var", "let", "if", "else", "console.log");
    private List<String> delimiters = Arrays.asList(",", ";", "(", ")", "{", "}", "[", "]");

    private String currentToken = "";
    private String trail = "nada";

    public void analyzeCode(String code) {
        for (char c : code.toCharArray()) {
            if (!openQuotes()) {
                if (!isSpace(c)) {
                    if (!isOperator(Character.toString(c))) {
                        if (!isDelimiter(Character.toString(c))) {
                            if (!isNumber(Character.toString(c))) {
                                if (!isNumber(currentToken)) {
                                    currentToken += c;
                                    trail = "constante";
                                } else {
                                    if (!trail.equals("letra")) {
                                        checkToken(currentToken);
                                    }
                                    currentToken = Character.toString(c);
                                    trail = "letra";
                                }
                            } else if (isNumber(currentToken.charAt(0) + Character.toString(c))) {
                                currentToken += c;
                                trail = "constante";
                            }
                            else {
                                checkToken(currentToken);
                                currentToken = Character.toString(c);
                                trail = "constante";
                            }
                        } else {
                            if (c == '"' || c == '\'') {
                                currentToken = Character.toString(c);
                            } else {
                                checkToken(currentToken);
                                currentToken = Character.toString(c);
                                trail = "delimitador";
                                checkToken(currentToken);
                            }
                        }
                    } else if (operators.contains(Character.toString(c))) {
                        currentToken += c;
                        trail = "operador";
                        if (isCommentLine()) {
                            checkToken(currentToken);
                            trail = "comentario";
                        } else if (isCommentBlock()) {
                            checkToken(currentToken);
                            trail = "comentario";
                        }
                    } else {
                        checkToken(currentToken);
                        currentToken = Character.toString(c);
                        trail = "operador";
                    }
                } else {
                    checkToken(currentToken);
                    trail = "nada";
                }
            }
        }

        if (!currentToken.isEmpty()) {
            checkToken(currentToken);
        }
    }

    private boolean openQuotes() {
        return currentToken.length() > 0 && currentToken.charAt(0) == '"' && !currentToken.endsWith("\\");
    }

    private void checkToken(String currentToken) {
        if (!currentToken.isEmpty()) {
            switch (trail) {
                case "nada":
                    break;

                case "comentario":
                    System.out.println("Comentário: " + currentToken);
                    break;

                case "letra":
                    if (reservedWords.contains(currentToken)) {
                        System.out.println("Palavra Reservada: " + currentToken);
                    } else if (!currentToken.equals("\n")) {
                        System.out.println("Identificador: " + currentToken);
                    }
                    break;

                case "operador":
                    if (currentToken.equals("=")) {
                        System.out.println("Atribuição: " + currentToken);
                    } else {
                        System.out.println("Operador: " + currentToken);
                    }
                    break;

                case "constante":
                    System.out.println("Constante Numérica: " + currentToken);
                    break;

                case "delimitador":
                    if (currentToken.equals(",")) {
                        System.out.println("Separador: " + currentToken);
                    } else if (currentToken.equals(";")) {
                        System.out.println("Terminador: " + currentToken);
                    } else if (currentToken.equals("(") || currentToken.equals("{") || currentToken.equals("[")) {
                        System.out.println("Delimitador - Abertura: " + currentToken);
                    } else if (currentToken.equals(")") || currentToken.equals("}") || currentToken.equals("]")) {
                        System.out.println("Delimitador - Fechamento: " + currentToken);
                    } else {
                        System.out.println("Delimitador: " + currentToken);
                    }
                    break;
            }
        }
        currentToken = "";
    }

    private boolean isSpace(char c) {
        return c == ' ';
    }

    private boolean isCommentLine() {
        return currentToken.equals("//");
    }

    private boolean isCommentBlock() {
        return currentToken.equals("/*");
    }

    private boolean isOperator(String token) {
        return operators.contains(token);
    }

    private boolean isDelimiter(String token) {
        return delimiters.contains(token);
    }

   private boolean isNumber(String token) {
    try {
        Double.parseDouble(token);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    public static void main(String[] args) {
        File file = new File("arquivo.txt"); // Substitua "arquivo.txt" pelo caminho correto do arquivo

        try {
            Scanner scanner = new Scanner(file);
            StringBuilder codeBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                codeBuilder.append(scanner.nextLine()).append("\n");
            }

            scanner.close();

            String code = codeBuilder.toString();

            Lexer lexer = new Lexer();
            lexer.analyzeCode(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

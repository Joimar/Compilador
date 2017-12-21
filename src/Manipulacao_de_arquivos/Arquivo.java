/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manipulacao_de_arquivos;
import Lexico.Token;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joimar
 */
public class Arquivo 
{
    
    private String localFile;
    private LineNumberReader linha = null;
    private FileReader reader = null;
    private Scanner scan = null;
    /**
     * Busca e retorna todos os códigos fonte presentes na pasta <i>/src/testes/in/</i>.
     *
     * @return Lista com os nomes dos códigos fonte presentes na pasta <i>/src/testes/in/</i>
     */
    public ArrayList<String> lerCodigos() {
        
        File raiz = new File("src/testes/in/"); // Pasta com os códigos de entrada.
        ArrayList<String> codigos = new ArrayList<>(); // Nomes dos arquivos com os códigos de entrada.
        
        for (File f : raiz.listFiles()) { // Inserindo caminho dos códigos.
           if(f.exists()) codigos.add(f.getName());
           else System.out.println("acabou");
        }
        
        
       //System.out.println(codigos);
        
        return codigos;
    }
    
    /**
     * Retorna a lista de strings obtidas a partir de um arquivo com o código
     * fonte.
     *
     * @param localFile Nome do arquivo do código que será lido
     *
     * @return Lista com as strings contidas no arquivo com o código
     *
     * @throws FileNotFoundException Erro com a leitura do arquivo com o código
     */
    public ArrayList<String> lerCodigoFonte(String localFile) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader("src/testes/in/" + localFile)); // Lendo o arquivo do código.
        this.localFile = localFile; // Guarda o nome do arquivo de entrada para que o arquivo de saída tenha o "mesmo" nome.
        ArrayList<String> codigo = new ArrayList(); // Código obtido.
        while (scanner.hasNextLine()) { // Capturando as linhas do código.
            codigo.add(scanner.nextLine());
        }
        scanner.close();
        //System.out.println(codigo);
        return codigo;
    }
    
    /**
     * Gera o arquivo de saída após a análise do código fonte. Neste arquivo de
     * saída, conterá todos os tokens encontrados no código fonte e os erros
     * encontrados (se houver).
     *
     * @param tokens Lista de tokens obtidos após a análise do código fonte
     * @param erros Erros obtidos após a análise do código fonte
     * 
     * @throws IOException Arquivo de saida não foi gerado com sucesso
     */
    public void escreverSaidaLexico(ArrayList<Token> tokens, ArrayList<Token> erros) throws IOException {

        FileWriter arq = new FileWriter("src/testes/out/" + this.localFile + ".out", false); // Cria o arquivo de saída relacionado ao seu respectivo arquivo de entrada ("mesmo" nome). 

        PrintWriter gravar = new PrintWriter(arq);
        if(tokens.isEmpty())
        {
            System.out.println("\nao ha erros codigo\n");
        }
        else {
            
            for (Token token : tokens) { // Insere os tokens no arquivo de saída.
            gravar.println(token.getValor() + " " + token.getTipo() + " " + token.getLinha());
        }
        }
        
        
        if (erros.isEmpty()) { // Se não houver erros léxicos.
            gravar.printf("nao ha erros lexicos\n");
        } else { // Se houver erros léxicos, os insere no arquivo de saída.
            for (Token erro : erros) {
                gravar.println(erro.getValor() + " " + erro.getTipo() + " " + erro.getLinha());
            }
        }
        arq.close();
        
        
        
    }
    
}

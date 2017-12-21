/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import Manipulacao_de_arquivos.Arquivo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joimar
 */



public class Compilador {

    /**
     * @param args the command line arguments
     */
    
    private final Arquivo arquivo;
    
    //private AnalisadorLexico analisadorLexico = new AnalisadorLexico();
    
     public Compilador() {

        arquivo = new Arquivo(); // Criação do manipulador de entrada e saída.
    }
    
     
     public void compilar() throws FileNotFoundException, IOException {

        ArrayList<String> localFiles = arquivo.lerCodigos(); // Recebe a lista com todos os códigos da pasta.
        if (localFiles.isEmpty()) { // Pasta de códigos de entrada vazia.
            System.out.println("Sem Códigos para Compilar");
            System.exit(0);
        }
        for (String lF : localFiles) { // Para cada arquivo fonte, o analisador léxico gera as listas de tokens e erros (se houver).
           // Scanner scan = new Scanner(new FileReader(lF));
            //ArrayList<String> codigoFonte = arquivo.lerCodigoFonte(lF);
            ArrayList<String> codigoFonte = arquivo.lerCodigoFonte(lF);
            AnalisadorLexico analisadorLexico = new AnalisadorLexico();
            analisadorLexico.analise(codigoFonte);
            
            arquivo.escreverSaidaLexico(analisadorLexico.getTokens(), analisadorLexico.getErros());
            
          //  arquivo.escreverSaidaLexico(analisadorLexico.getTokens(), analisadorLexico.getErros());
            
          //  analisadorSintatico = new AnalisadorSintatico(analisadorLexico.getTokens());
        }
         
        // Arquivo arquivo = new Arquivo();
        // arquivo.escreverSaidaLexico(null, null);
    }
    
    public static void main(String[] args) throws FileNotFoundException 
    {
        // TODO code application logic here
        // Arquivo de entrada contendo o código a ser analisado
		//Scanner scan = new Scanner(new FileReader("code.txt"));
               // PrintStream writer = new PrintStream("result.txt");
                
                try 
                {
                    Compilador compilador = new Compilador(); // Cria o compilador.
                    compilador.compilar(); // Executa o compilador.
            
                } 
                catch (FileNotFoundException error1) 
                {
                    
                    System.out.println("Arquivo não encontrado");
                    System.exit(0);
                }
                catch (IOException ex) 
                {
                    System.out.println("Arquivo de saida não foi gerado com sucesso");
                    System.exit(0);
                }
                int i=0;
                for(i=0;i<100;i++) if(i==1) i=10;
                {
                
                }


		
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

import java.util.ArrayList;

/**
 *
 * @author Joimar
 */



class AnalisadorLexico {

    
   // private final EstruturaLexica estruturaLexica;
    /**
     * Tabela contendo os identificadores desta etapa.
     */
   // private TabelaSimbolos tabelaSimbolos;
    /**
     * Lista de tokens.
     */
    private final ArrayList<Token> tokens;
    /**
     * Lista com os erros.
     */
    private final ArrayList<Token> erros;
    /**
     * Representa a linha atual no código.
     */
    
    /**
     * Representa a coluna atual no código.
     */
    private int coluna;
    /**
     * Lista com todas as linhas do código.
     */
    
    /**
     * Identifica se uma linha está vazia ou não.
     */
    private boolean linhaVazia;
    /**
     * identifica se o operador '-' pode gerar um número (negativo).
     */
    private boolean podeSerNumero;

    /**
     * Construtor da classe.
     */
    
    /**
     * Atributo erpresentando fim de arquivo.
     */
    private static final char EOF = '\0';
    /**
     * Atributo representando quebra de linha.
     */
    private static final char QUEBRA_LINHA = ' ';
    /**
     * Atributo representando uma linha vazia.
     */
    private static final char LINHA_VAZIA = ' ';
    /**
     * Estrutura léxica que rege o compilador.
     */
    
    private int automato;
    private ArrayList<String> codigo;
   
    public AnalisadorLexico() 
    {
        automato = 0;
         this.tokens = new ArrayList<>();
         this.erros = new ArrayList<>();
    }
    
    public int retornaUltimo(ArrayList<Token> tokens)
    {
       return tokens.size() -1;
        
    }
    
    public void substituiUltimo(Token token)
    {
        tokens.remove(retornaUltimo(tokens));
        tokens.add(token);
        
    
    }
    
    public boolean verificaOperador(char caracter)
    {
    
        if(caracter == 33 || caracter == 38 || caracter == 124|| caracter == 60
           || caracter == 61 || caracter == 62 || caracter == 43 || caracter == 45
           || caracter == 42 || caracter == 47 || caracter == 37 || caracter == 124)
        {return true;}
        
        else return false;
        
    }
    
    public boolean verificaOperador(String caracter)
    {
    
        if(caracter.equals("!") || caracter.equals("&") || caracter.equals("|")|| caracter.equals("<")
           || caracter.equals("=") || caracter.equals(">") || caracter.equals("+") || caracter.equals("-")
           || caracter.equals("*") || caracter.equals("/") || caracter.equals("%") || caracter.equals("|"))
        {return true;}
        
        else return false;
        
    }
    
    
    
    public boolean verificaDelimitador(char caracter)
    {
         
    
        if(
                                caracter ==123 || caracter==125
                                || caracter ==91 || caracter== 93 
                                || caracter==40 || caracter== 41
                                || caracter==44 || caracter== 59
                                ||caracter == 58
                           )
        {return true;}
        else return false;
        
    }
    
    public boolean verificaDelimitador(String caracter)
    {
    
        if(
                                caracter.equals("{") || caracter.equals("}")
                                || caracter.equals("[") || caracter.equals("]")
                                || caracter.equals("(") || caracter.equals(")")
                                || caracter.equals(",") || caracter.equals(";")
                                ||caracter.equals(":")
                           )
        {return true;}
        else return false;
    
    }
    
    
    public boolean verificaLetra(char caractere)
    {
    
        if(caractere>=65 && caractere<=90) return true;
        else if(caractere>=97 && caractere<=122) return true;
        else return false;
    }
    
    public boolean verificaNumero(char caracter)
    {
    
         if(caracter >= 48 && caracter <= 57) return true;
         else return false;
        
    }
            
    public boolean verificaEspaco(char caracter)
    {
    
        if(caracter == 9 || caracter == 10 || caracter == 13 || caracter == 32 ) return true;
        else return false;
    
    }
    
    public void addIDToken(String valor, boolean erroID, int i)
    {
        
        if(erroID==false)
        {
            if(verificaPalavraReservada(valor))
            {
                Token token = new Token(valor, "palavra reservada", (i+1) );
                tokens.add(token);
            
            }
            else
            {
            
                Token token = new Token(valor, "Identificador", (i+1) );
                tokens.add(token);
            }
            
        }
        else 
        {
            Token erro = new Token(valor, "Identificador malformado", (i+1));
            erros.add(erro);
        }
    
    }
    
    public void addNumToken(String valor, boolean erronum, int linha)
    {
    
    
        if(erronum==false)
        {
        
            Token token = new Token(valor, "Numero", (linha+1) );
            tokens.add(token);
        
        }
        else 
        {
            Token erro = new Token(valor, "Numero malformado", (linha+1));
            erros.add(erro);
        }
    
    }
    
    public void addOpToken(char valor, boolean Opcomp,int i)
    {
        String aux = ""+ valor;
         int ultimo = retornaUltimo(tokens);
        if(Opcomp == false)
        {
            Token token = new Token(aux,"Operador", i );
            tokens.add(token);
        }
        
        else if(aux.equals("=") && (tokens.get(ultimo).getValor().equals(">") || tokens.get(ultimo).getValor().equals("<")))
        {
            String palavra;
           
            Token token1 = tokens.get(ultimo);
            Token token2;
            
            palavra = token1.getValor();
            palavra += aux;
            token2 = new Token(palavra, "Operador", i);
            tokens.remove(ultimo);
            tokens.add(token2);
            
        }
        
        else if(aux.equals("=") && tokens.get(ultimo).getValor().equals("!"))
        {
        
            String palavra;
           
            Token token1 = tokens.get(ultimo);
            Token token2;
            
            palavra = token1.getValor();
            palavra += aux;
            token2 = new Token(palavra, "Operador", i);
            tokens.remove(ultimo);
            tokens.add(token2);
        }
        
        else if(aux.equals("&") && tokens.get(ultimo).getValor().equals("&") )
        {
        
            String palavra;
           
            Token token1 = tokens.get(ultimo);
            Token token2;
            
            palavra = token1.getValor();
            palavra += aux;
            token2 = new Token(palavra, "Operador", i);
            tokens.remove(ultimo);
            tokens.add(token2);
        
        }
        
        else if(aux.equals("|") && tokens.get(ultimo).getValor().equals("|") )
        {
        
            String palavra;
           
            Token token1 = tokens.get(ultimo);
            Token token2;
            
            palavra = token1.getValor();
            palavra += aux;
            token2 = new Token(palavra, "Operador", i);
            tokens.remove(ultimo);
            tokens.add(token2);
        
        }
        
        else
        {
        
            Token erro = new Token(aux, "Operador malformado", i);
            erros.add(erro);
            
        }
        
    
    }
    
    public void addDelToken(char caracter, int linha)
    {
    
        String aux = ""+ caracter;
        Token token = new Token(aux, "Delimitador", linha);
        tokens.add(token);
    }
   
    public boolean verificaOpComp(char caractere)
    {
        
        
        if(caractere == 33) return true; // !
        else if(caractere == 60 || caractere == 62) return true; // < >
        else if(caractere == 38) return true; //&
        else if(caractere == 124) return true;  // |
        else return false;
        
    }
    
    public boolean verificaSimboloErro(char caracter)
    {
        
        if(verificaLetra(caracter)) return false;
        else if(verificaNumero(caracter)) return false;
        else if(verificaOperador(caracter)) return false;
        else if(verificaEspaco(caracter)) return false;
        else if(verificaDelimitador(caracter)) return false;
        else return true;
        
    }
    
    public void addDelimitador(char caracter, int linha)
    {
    
        if(verificaDelimitador(caracter))
        {
        
            String aux = ""+ caracter;
            Token token = new Token(aux, "Delimitador", linha+1);
            tokens.add(token);
        }
        else System.out.println(caracter+" Nao eh delimitador");
    
    }
    
    public void addCadToken(String valor, boolean erroCad, int linha)
    {// TESTAR CADEIA DE CARACTERES
    
        if(erroCad)
        {
        
            Token erro = new Token(valor, "Cadeia de Caracteres mal formada", linha+1);
            erros.add(erro);
        
        }
        else 
        {
            Token token = new Token(valor, "Cadeia de Caracteres", linha+1);
            tokens.add(token);
        }
    
    }
    
    public void addCadToken(String valor, boolean erroCad, boolean quebra, int linha)
    {
    
        if(erroCad)
        {
        
            Token erro = new Token(valor, "Cadeia de Caracteres mal formada", linha+1);
            erros.add(erro);
        
        }
        else if(quebra)
        {
            Token erro = new Token(valor, "Cadeia de Caracteres sem fechamento de aspas", linha+1);
            erros.add(erro);        
        }
        else 
        {
            Token token = new Token(valor, "Cadeia de Caracteres", linha+1);
            tokens.add(token);
        }
    
    
    }
    
    public boolean verificaPalavraReservada(String palavra)
    {
    
        if(palavra.equals("class") || palavra.equals("final") || palavra.equals("if") || palavra.equals("else") || 
                palavra.equals("for") || palavra.equals("scan") || palavra.equals("print") || palavra.equals("int") ||
                palavra.equals("float") || palavra.equals("bool") || palavra.equals("true") || palavra.equals("false")
                || palavra.equals("string"))
        {
        
            return true;
        }
        
        else return false;
    }
    
    public void analise(ArrayList<String> codigo)
    {
        
        String numero = "";
        String id = "";
        String operador = "";
        String cadeia = "";
        boolean Idnum = false;
        boolean erroNum = false;
        boolean idnum = false;
        boolean erroID = false;
        boolean IDflag = false;
        boolean commentFlag = false;
        boolean Opcomp = false;
        boolean ponto = false;
        boolean erroCad = false;
        boolean numNeg = false;
        boolean flagBarra = false;
        boolean flagBloco = false;
        for(int i=0; i< codigo.size(); i++)
        {   

            for(int s = 0; s < codigo.get(i).length(); s++)
            {
                //if(verificaNumero(codigo.get(i).charAt(s)) == false) numNeg = false;
                //System.out.println(codigo.get(i).charAt(s));
                
                //Operador
                if(verificaOperador(codigo.get(i).charAt(s))){ automato = 1; IDflag = false; }
                //numero
              else  if(codigo.get(i).charAt(s) >= 48 && codigo.get(i).charAt(s) <= 57 && IDflag == false) automato = 2;
                
                //ID
              else  if(verificaLetra(codigo.get(i).charAt(s))) automato = 4;
                
                //Delimitador
             else   if(verificaDelimitador(codigo.get(i).charAt(s)))
                {
                    IDflag = false;
                    addDelToken(codigo.get(i).charAt(i), i+1);
                    
                }
             
                //aspas
             else if(codigo.get(i).charAt(s) == 34)
             {
             
                 automato = 7;
                 
             
             }
                
                if(flagBarra == true || flagBloco == true || commentFlag == true) automato = 6;
                                
                switch(automato)
                {
            
                case 0:
                    
                    
                    break;
                case 1:
                    
                    //+
                    if(codigo.get(i).charAt(s) == 43) 
                    {
                        Opcomp = false;
                        addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                          
                    }
                    // -
                    else if(codigo.get(i).charAt(s) == 45 && tokens.isEmpty())
                    {
                        numNeg = true;
                        Token token = new Token("-", "operador", (i+1));
                         tokens.add(token);
          
                    }
                   else if(codigo.get(i).charAt(s) == 45)
                    {
                        Opcomp = false;
                         Token token = new Token("-", "operador", (i+1));
                         tokens.add(token);
                         int aux = retornaUltimo(tokens);
                         Token auxToken = tokens.get(aux);
                         if(verificaDelimitador(auxToken.getValor()) || verificaOperador(auxToken.getValor()) || auxToken.getTipo().equals("Cadeia de Caracteres") )
                         {                      
                            numNeg = true;
                         }
                         
                    }
                  
                    //*
                   else if(codigo.get(i).charAt(s) == 42) 
                   {
                        Opcomp = false;
                       
                       
                           if(commentFlag == false)
                           {
                                Token token = new Token("*", "operador", (i+1));
                                tokens.add(token);
                           }
                           else 
                           {
                               automato = 6;
                               commentFlag = false;
                               tokens.remove(retornaUltimo(tokens));
                               flagBloco = true;
                           }
                                           
                   }
                    //  / barra
                   else if(codigo.get(i).charAt(s) == 47)
                   {
                   
                        Opcomp = false;
                           if(commentFlag == false)
                           {
                           
                               Token token = new Token("/", "operador", (i+1));
                               tokens.add(token);
                               commentFlag = true;
                               
                           }
                           
                           else 
                           {
                               automato = 6;
                               commentFlag = false;
                               tokens.remove(retornaUltimo(tokens));
                               flagBarra = true;
                               
                           }
                      
                       
                   }
                   
                   else if(codigo.get(i).charAt(s) == 37)  // %
                   {
                   
                        Opcomp = false;
                        
                        addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                                        
                   }
                    
                   else if(codigo.get(i).charAt(s) == 60 || codigo.get(i).charAt(s) == 62) // < >
                   {
                   
                       addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                       Opcomp = true;    
                   }
                    
                   else if(codigo.get(i).charAt(s) == 33) // !
                   {
                                         
                       addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                       Opcomp = true;
                   }
                    
                   else if(codigo.get(i).charAt(s) == 61)// =
                   {
                       
                       if(Opcomp == false) addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                       else
                       {
                       
                           addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                           Opcomp = false;
                       }
                   
                   }
                   else if(codigo.get(i).charAt(s) == 38) // &
                   {
                       if(Opcomp == false) Opcomp = true;
                       
                       else
                       {
                           addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                           Opcomp = false;
                       }                 
                   }
                    
                   else if( codigo.get(i).charAt(s) == 124)// |
                   {
                   
                       if(Opcomp == false) Opcomp = true;
                       else
                       {                    
                           addOpToken(codigo.get(i).charAt(s), Opcomp, i+1);
                           Opcomp = false;
                       }
                   }
                   
                   else
                   {
                   
                       String aux = ""+ codigo.get(i).charAt(s);
                       Token erro = new Token(aux, "Simbolo estranho", i+1);
                       erros.add(erro);                  
                   }
                    
                    
                    automato = 0;
                    break;
                    
                case 2://numero
                    
                    System.out.println("numero");
                    
                   if(numNeg) numero+= "-"; 
                    for(int auxs = s; s<codigo.get(i).length(); s++)
                    {
                        char aux = codigo.get(i).charAt(s);
                        
                        if(verificaLetra(aux))
                        { 
                            numero += Character.toString(codigo.get(i).charAt(s));
                            erroNum = true;
                            if(s == (codigo.get(i).length() - 1)) // fim de linha
                            {
                            
                                addIDToken(numero, erroNum, i);  // apesar de numero, esses erros sao de id malformado
                                numero = "";
                                               
                                break;
                            }
                        }
                        else if(verificaNumero(aux) && numNeg == true && tokens.get(retornaUltimo(tokens)).getValor().equals("-"))//numero negativo
                        {
                            int posicao = retornaUltimo(tokens);
                            //numero += "-";
                            numero += Character.toString(codigo.get(i).charAt(s)); 
                            //numNeg = false;
                            numNeg = false;
                             if(s == (codigo.get(i).length() - 1)) // fim de linha
                            {
                                Token token = new Token(numero, "Numero", i+1);
                                substituiUltimo(token);
                                numero = "";
                                erroNum = false;
                                
                                break;
                            }
                            
                        }
                        else if(verificaNumero(aux))
                        { 
                            numero += Character.toString(codigo.get(i).charAt(s));
                            if(s == (codigo.get(i).length() - 1) && numNeg == true) // fim de linha
                            {
                            
                                Token token = new Token(numero, "Numero", i+1);
                                substituiUltimo(token);
                                numero = "";
                                erroNum = false;
                                numNeg=false;
                                break;
                            }
                            else if(s == (codigo.get(i).length() - 1))
                            {                              
                                addNumToken(numero, erroNum, i);
                                numero = "";
                                erroNum = false;
                                numNeg=false;
                                break;
                            }
                            
                            
                        }
                        else if(verificaSimboloErro(aux))
                        {
                            
                            erroNum=true;
                            numero += Character.toString(codigo.get(i).charAt(s));
                            if(s == (codigo.get(i).length() - 1)) // fim de linha
                            {
                            
                                addNumToken(numero, erroNum, i);
                                numero = "";
                                erroNum = false;
                
                                break;
                            }
                                
                        }
                        else if(verificaEspaco(aux))
                        {
                        
                            addNumToken(id, erroNum, i);                       
                            numero = "";
                            erroNum = false;
                            break;                       
                        }
                        else if(verificaOperador(codigo.get(i).charAt(s)))
                        {   
                            Opcomp = verificaOpComp(aux);
                            System.out.println("Saca "+ numero);
                            Token token = new Token(numero, "Numero", i+1);
                            substituiUltimo(token);
                            addOpToken(aux, Opcomp, i+1);
                            numero = "";
                           
                            erroNum = false;                         
                            automato = 0;
                           break; 
                           
                        }
                        //ponto
                        else if(codigo.get(i).charAt(s) == 46)
                        {
                            numero += Character.toString(codigo.get(i).charAt(s));
                            
                             if(ponto == false) ponto = true;
                            
                             else
                             {
                                 ponto = true;
                                 erroNum = true;
                             }
                          
                            if(s == (codigo.get(i).length() - 1)) // fim de linha
                            {
                            
                                addNumToken(numero, erroNum, i);
                                numero = "";
                                erroNum = false;
     
                                break;
                            }
                                                      
                        }
                        // fim de linha
                        else if(s == (codigo.get(i).length() - 1)) 
                        {
                            
                            addNumToken(numero, erroNum, i);
                            numero = "";
                            erroNum = false;
                            
                            break;
                        }
                        
                        
                        else
                        {
                        
                            erroNum = true;
                            numero += Character.toString(codigo.get(i).charAt(s));
                                                    
                        }
                        
                    }
                     
                    break;
                    
                case 3:
                    break;
                case 4://ID
                    
                    
                    IDflag = true;
                    int IDaux = s;
                    for( IDaux = s; IDaux < codigo.get(i).length(); IDaux++)
                    {
                        char aux = codigo.get(i).charAt(IDaux);
                        
                        if(verificaLetra(aux))
                        { 
                            id += Character.toString(codigo.get(i).charAt(IDaux));
                            if(IDaux == (codigo.get(i).length() - 1)) // fim de linha
                            {
                           
                                addIDToken(id, erroID, i);
                                id = "";
                                erroID = false;
                                IDflag = false;
                            
                                break;
                            }
                        }
                        else if(verificaNumero(aux))
                        { 
                            id += Character.toString(codigo.get(i).charAt(IDaux));
                            if(IDaux == (codigo.get(i).length() - 1)) // fim de linha
                            {
                            
                                addIDToken(id, erroID, i);
                                id = "";
                                erroID = false;
                                IDflag = false;
                            
                                break;
                            }
                        }
                        
                        else if(verificaSimboloErro(aux))
                        {
                            
                            erroID=true;
                            id += Character.toString(codigo.get(i).charAt(IDaux));
                            if(IDaux == (codigo.get(i).length() - 1)) // fim de linha
                            {
                            
                                addIDToken(id, erroID, i);
                                id = "";
                                erroID = false;
                                IDflag = false;
                                
                                break;
                            }
                                
                        }
                        else if(verificaEspaco(aux))
                        {
                        
                            addIDToken(id, erroID, i);                       
                            id = "";
                            erroID = false;
                            break;
                        
                        }
                        else if(verificaOperador(codigo.get(i).charAt(IDaux)))
                        {
                            Opcomp = verificaOpComp(aux);
                            System.out.println("Saca "+ id);
                            addIDToken(id, erroID, i); 
                            addOpToken(aux, Opcomp, i+1);
                            id = "";
                           
                            erroID = false;
                            IDflag = false;
                            automato = 0;
                           break; 
                           
                        }
                        
                        else if(verificaDelimitador(codigo.get(i).charAt(IDaux)))
                        {
                        
                            addIDToken(id, erroID, i);                        
                            id = "";
                            addIDToken(id, erroID, i);
                            erroID = false;
                            IDflag = false;
                            automato = 0;   
                            
                            break;
                        }
                        
                        else if(IDaux == (codigo.get(i).length() - 1)) // fim de linha
                        {
                            
                            addIDToken(id, erroID, i);
                            id = "";
                            erroID = false;
                            IDflag = false;
                            
                            break;
                        }
                        
                        
                        else
                        {
                        
                            erroID = true;
                            id += Character.toString(codigo.get(i).charAt(IDaux));
                                                    
                        }
                                             
                    }
                     s = IDaux;
              
                    //else if(){}
                    
                  //  System.out.println(tokens.get(i));
        //            System.out.println(erros);
                    
                    
                    break;
                    
                case 5: //comentario //
                    
                   s = codigo.get(i).length() -1;
                   commentFlag = false;
                    break;
                
                case 6: //comentario 
                    
                    
                    
                     
                    // coment //
                     if(flagBarra == true)
                     {
                        tokens.remove(retornaUltimo(tokens));
                         while(true)
                         {
                            if(s<codigo.get(i).length()) s++;
                            else break;
                         }
                         flagBarra = false;
                         
                     }
                     else if(flagBloco == true)
                     {
                         boolean asterisco = false;
                         boolean fim = false;
                         for( int counti =i; i < codigo.size(); i++)
                         {
                         
                             for(int counts = s; s < codigo.get(i).length();s++)
                             {
                                 if(codigo.get(i).charAt(s) == 42) asterisco = true;
                                 else if(codigo.get(i).charAt(s) == 47 && asterisco == true) {fim = true; break;}
                                 else asterisco = false;
                             }
                             
                             if(fim==true) break;
                         }
                         if(fim == false)
                         {
                         
                             Token erro = new Token("Comentario nao fechado", "Erro", 0);
                             erros.add(erro);
                         }
                     }
                    
                    break;
                    
                case 7://Cadeia de caracteres
                    
                    if(codigo.get(i).charAt(s) == 34)//"
                    {   
                        char aux = codigo.get(i).charAt(s);
                        
                        cadeia += Character.toString(aux);
                        s++;
                        for(int count = s; s< codigo.get(i).length(); s++ )
                        {
                         if(verificaSimboloErro(aux)) //erro
                         {
                         
                            erroCad = true;
                            cadeia += Character.toString(aux);
                         }
                         else if(aux == 34)  // "
                         {
                         
                            cadeia += Character.toString(aux);
                            addCadToken(cadeia, erroCad, i);
                            erroCad = false;
                            break;
                         }
                         else if(s == (codigo.get(i).length() - 1)) // fim de linha
                        {
                            erroCad = false;
                            boolean quebra = true;
                            addCadToken(cadeia, erroCad, quebra, i);
                            cadeia = "";
                          
                            break;
                        }
                         else cadeia += Character.toString(aux);
                         
                         
                         //falta as condicoes de saida
                         
                        }
                             
                    }
                    
                    break;
                    
                default:
                    
                    System.out.println("Default");
            
            }
           
            }
           // System.out.println(tokens.get(i).getValor());
              
            
        }
       // System.out.println(linha);
       
    //System.out.println(tokens);
    }
    
    public ArrayList<Token> getTokens() 
    {
        return this.tokens;
    }
    
    public ArrayList<Token> getErros()
    {
    
        return this.erros;
    }
    
}

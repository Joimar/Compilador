/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintatico;

import Lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author Joimar
 */
public class Parser {
    
    
    private ArrayList<Token> tokens;
    private ArrayList<String> erro;
    private int i=0;
    private int o=0;
    
    Parser(ArrayList<Token> tokens)
    {
    
        this.tokens = tokens;
       // teste.add("int");
       // teste.add("id");
       // teste.add("(");
       // teste.add("");
        
        
    }
    
    public void analiseConstanteGlobal()
    {
    
        int cont = i;
        
    
    }
    
    public boolean fim()
    {
        if(i<tokens.size()) return true;
        else return false;
        
        
        
    }
    
    public boolean constanteGlobal()
    {
        
        if(tokens.get(i).getValor().equals("final") && fim())
        {
        
            i++;
            if(varTipo(tokens.get(i)) && fim())
            {
            
               i++;
                if(tokens.get(i).getTipo().equals("Identificador") && fim())
                {
                
                    i++;
                    if(tokens.get(i).getValor().equals(";"))
                    {
                        //recurcao
                        i++;
                        if(!tokens.get(i).getValor().equals("final") && fim()) return true;
                        else { constanteGlobal();}
                        
                        
                    
                    }
                    else return false;
                
                }
                else if(tokens.get(i).getValor().equals("[") && fim()) 
                {
                    i--;
                    vetor();
                    
                }
                else return false;
            
            }
            else return false;
            
            
        
        }
        else return true; //producao fazia
        
        return true;
    }
    
    
    public boolean varTipo(Token token)
    {
    
        if(token.getValor().equals("int") || token.getValor().equals("float") || token.getValor().equals("bool") || token.getValor().equals("string"))
        {
            return true;
        }
        else return false;
    }
    
    public boolean analisaPalavraReservada(Token token)
    {
    
        if(token.getTipo().equals("palavra reservada"))
        {
        
            return true;
        
        }
        else return false;
    
    }
    
    public boolean Var()
    {
    
        if(varTipo(tokens.get(i)))
        {
        
            i++;
            if(tokens.get(i).getTipo().equals("Identificador"))
            {
                i++;
                if(tokens.get(i).getTipo().equals(";")) return true;
                else if(tokens.get(i).getTipo().equals(","))
                {
                
                    if(auxVar())
                    {
                    
                        i++;
                        if(tokens.get(i).getTipo().equals(";")) return true;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
            
        }
        else return false;// provisorio
        return false;
    }
    
    public boolean auxVar()
    {
    
        if(tokens.get(i).getValor().equals(","))
        {
            i++;
            if(tokens.get(i).getTipo().equals("Identificador"))
            {
               i++;
               if(tokens.get(i).getTipo().equals(","))
               {
               
                   if(auxVar()) return true;
                   else return false;
               }
               else return false;
                
            }
            else return false;
        }
        else return false;
    }
    
    public boolean vetor()
    {
    
        if(varTipo(tokens.get(i)))
        {
        
            i++;
            if(tokens.get(i).getValor().equals("["))
            {
            
                i++;
                if(tokens.get(i).getTipo().equals("Numero"))
                {
                    i++;
                    if(tokens.get(i).getValor().equals("]"))
                    {
                        //Possibilidade de producao nula
                        i++;
                        if(tokens.get(i).getValor().equals("["))
                        {
                        
                            if(auxVetor())
                            {
                                i++;
                                if(tokens.get(i).getTipo().equals("Identificador"))
                                {
                                
                                    i++;
                                    if(tokens.get(i).getValor().equals(";")) return true;
                                    else return false;
                                
                                }
                                else return false;
                            
                            }
                            
                            else return false;
                        }
                        else if(tokens.get(i).getTipo().equals("Identificador"))
                        {
                                
                                    i++;
                                    if(tokens.get(i).getValor().equals(";"))
                                    {
                                    
                                        return true;
                                    }
                                    else return false;
                         }
                        else return false;
                    
                    }
                    else return false;
                    //errado????
                   
                
                }
                else return false;
            
            }
            else return false;
        }
        else return false;
    
    }
    
    public boolean auxVetor()
    {
    
        if(tokens.get(i).getValor().equals("["))
        {
        
            i++;
            if(tokens.get(i).getTipo().equals("Numero"))
            {
            
                i++;
                if(tokens.get(i).getValor().equals("]"))
                {
                    i++;
                    if(tokens.get(i).getValor().equals("["))
                    {
                    
                        if(auxVetor())
                        {
                    
                            return true;
                        
                    
                        }
                        else return false; 
                    }
                    else return false;
                
                   
                
                }
                else return false;
            
            }
            else return false;
        
        }
        else return false;
    
    }
    
    public boolean retornoMain()
    {
    
        if(tokens.get(i).getTipo().equals("Identificador"))
        {
        
            i++;
            return true;
        }
        else if(tokens.get(i).getValor().equals("False"))
        {
        
            i++;
            return true;
        }
        else if(tokens.get(i).getValor().equals("True"))
        {
        
            i++;
            return true;
        }
        
        else return false;
    }
    
    public boolean operadorAritmetico()
    {
    
        if(tokens.get(i).getValor().equals("+") || tokens.get(i).equals("-") || 
                tokens.get(i).equals("*") || tokens.get(i).equals("/") || tokens.get(i).equals("%")){ i++; return true; }
        i++;
        return false;
        
    }
    
   public boolean operadorRelacional()
   {
   
       if(tokens.get(i).getValor().equals("!=") || tokens.get(i).getValor().equals("=")|| tokens.get(i).getValor().equals("<")
               || tokens.get(i).getValor().equals("<=") || tokens.get(i).getValor().equals(">") || tokens.get(i).getValor().equals(">="))
       {
       
           i++;
           return true;
       }
       i++;
       return false;
   }
   
   public boolean operadorLogico()
   {
   
       if(tokens.get(i).getValor().equals("&&") || tokens.get(i).getValor().equals("||") || tokens.get(i).getValor().equals("!"))
       {
       
           i++;
           return true;
       }
       
       i++;
       return false;
   
   }
   //Operacoes
   //<OperacaoAritmetica> ::= Identifier <AuxOpAritmetica> | Number <AuxOpAritmetica>
   public boolean operacaoAritmetica()
   {
   
       if(tokens.get(i).getTipo().equals("Identificador"))
       {
       
           i++;
           if(auxOpAritmetica()) {i++; return true;}
           else{i++; return false;}
         
       }
       else if(tokens.get(i).getTipo().equals("Numero")) 
       {
           i++;
           if(auxOpAritmetica()) {i++; return true;}
           else {i++; return false;}
       }
       else return false;
   }
   
   //<AuxOpAritmetica> ::= <OperadorAritmetico> Identifier <Aux2OpAritmetica> | <OperadorAritmetico> Number <Aux2OpAritmetica> 
   public boolean auxOpAritmetica()
   {
   
       if(operadorAritmetico())
       {
       
           i++;
           if(tokens.get(i).getTipo().equals("Identificador"))
           {
           
               i++;
               if(aux2OpAritmetica()) return true;
               else return false;
               
           }
           else if(tokens.get(i).getTipo().equals("Numero"))
           {
           
               i++;
               if(aux2OpAritmetica())
               {
                   i++;
                   return true;
                   
               }
               else return false;
           }
           else return false;
       }
       
       return false;
   }
   //<Aux2OpAritmetica> ::= <OperadorAritmetico> Identifier <Aux2OpAritmetica> | <OperadorAritmetico> Number <Aux2OpAritmetica> | ';'
   public boolean aux2OpAritmetica()
   {
   
       if(operadorAritmetico())
       {
       
           i++;
           if(tokens.get(i).getTipo().equals("Identificador") && fim())
           {
           
               i++;
               if(aux2OpAritmetica()){i++; return true;}
               else{ i++; return false;}
           }
           else if(tokens.get(i).getTipo().equals("Numero") && fim())
           {
           
               i++;
               if(aux2OpAritmetica())
               {
               
                   i++;
                   return true;
               }
               else{ i++; return false;}
           }
           else {i++; return false;}
       }
       else if(tokens.get(i).getValor().equals(";") && fim()){i++; return true;}
       
       return false;
       
   }
   //Laco
   //<Incremento> ::= Identifier '=' Identifier <OperadorAritmetico> Number
   public boolean incremento()
   {
   
       if(tokens.get(i).getTipo().equals("Identificador") && fim())
       {
       
           i++;
           if(tokens.get(i).getValor().equals("=") && fim())
           {
           
               i++;
               if(operadorAritmetico() && fim())
               {
               
                   i++;
                   if(tokens.get(i).getTipo().equals("Numero") && fim()) return true;
                   else return false;
               }
               else 
               {   
                   i++;
                   
                   return false;
               }
               
           }
           else return false;
       
       }
       else return false;
   }
    
    public void analise()
    {
    
      
        
        
        
   
    }
    
    
    
}

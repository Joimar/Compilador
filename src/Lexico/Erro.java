/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

/**
 *
 * @author Joimar
 */
public class Erro 
{
    private String valor;
    private int linha;
    private String tipo;
    
    public Erro(String valor, int linha, String tipo)
    {
    
        this.valor = valor;
        this.linha = linha;
        this.tipo = tipo;
    
    }
    
    
    public String getValor()
    {
    
        return valor;
    }
    
    public int getLinha()
    {
    
        return linha;
    }
    
    public String getTipo()
    {
    
        return tipo;
        
    }
    
}

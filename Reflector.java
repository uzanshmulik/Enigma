/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anigma;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Reflector extends Translator{
    
    public Reflector(String name, char[] table){
        this.name = name ;
        this.permutation = table.clone();
    }
    
    public char translator(char ch){
        return this.permutation[toIndex(ch)];
    }
}

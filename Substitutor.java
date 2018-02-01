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
public abstract class Substitutor {
    protected char[] permutation;
    protected String name;

    public char[] getLetter_index_conversion() {
        return permutation;
    }

    public String getName() {
        return name;
    }
    
    protected int toIndex(char c){
        return (int)(c - 'A');
    }
    
    protected char toLetter(int c){
        return (char)(c + 'A');
    }
    
    protected char module(char c, int i){
        if(i < 0) 
            i+= 26;
        return toLetter((toIndex(c)+i)%26);
    }
    
    protected abstract char translation(char ch);
    
    protected char[] reverseTranslation(){
        char rev[] = new char[26];
        for(int i=0; i< rev.length ; i++){
            rev[toIndex(this.permutation[i])] = toLetter(i);
        }
        return rev;
    }
    
    public void print(){
        System.out.println(this.name + "\t" + new String(this.permutation) );
    }
    
}

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
public class Translator extends Substitutor{

    @Override
    protected char translation(char ch) {
        return this.permutation[toIndex(ch)];
    }
    
    protected char forward(char ch){
        int fromCharToInt = super.toIndex(ch);
        return super.permutation[fromCharToInt];
    }
    
    protected  char reverse(char ch){
        int fromCharToInt = super.toIndex(ch);
        return super.permutation[fromCharToInt];
    }
}

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

enum DIRECTION{
    FORWARD, REVERS
};

public class Rotor extends Translator {
    
    private int offset;
    private int setting;
    private int turnOver;
    private Rotor nextRotor;
    private DIRECTION state;
    
    public Rotor(String name, char[] permutation, char turnOver){
        this.name = name;
        this.permutation = permutation.clone();
        this.turnOver = toIndex(turnOver);
        this.offset = 0;
        this.setting = 0;
        this.state = DIRECTION.FORWARD;
        this.nextRotor = null;
    }
    // set next rotor like a clock but is not clock . left ->Middle ->Right
    public void setNextRotor(Rotor rotor){
        this.nextRotor = rotor;
    }
    
    @Override
    public void print() {
        super.print();
	System.out.print("\t" + toLetter(this.turnOver));
    }
    
    public int getTurnOver(){
        return this.turnOver;
    }
    
    public int getSetting(){
        return this.setting;
    }
    
    public int getOffset(){
        return this.offset;
    }
    
    public void setOffset(int offset){
        this.offset = offset -1;
    }
    
    public void setSetting(int setting){
        this.setting = setting-1;
    }
    
    public void changeDirection(){
        if(state == DIRECTION.FORWARD)
            state = DIRECTION.REVERS;
        else
            state = DIRECTION.FORWARD;
    }
    //find reverse of char in arrary of permutation
    private char findReverse(char c) {
        for (int i = 0; i < permutation.length; i++) {
        	if(permutation[i] == c)
        		return toLetter(i);
	}
	return c;
    }
    
    @Override
    protected char translation(char ch) {
        char shift;
        char convert;
        shift = module(ch, offset - setting);
        if(this.state == DIRECTION.REVERS )
            convert = findReverse(shift);
        else
            convert = permutation[toIndex(shift)];
        
        shift = module(convert, (setting-1) - (offset-1));
        return shift;
    }
    
    public boolean turnOver(){
        return offset == turnOver;
    }
    //step of rotor
    public void step(){
        if(offset == 25)
            offset = 0;
        else
            offset++;
    }
    
    public Rotor getRotor(){
        return nextRotor;
    }
}

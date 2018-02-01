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
public class Enigma {
    
    private final int ROTERS_LENGHT = 5;
    private Rotor[] rotors;
    private Rotor left; //left rotor
    private Rotor middle; // middle rotor
    private Rotor right; // right rotor
    private Reflector reflector; 
    private Plugboard plugboard;

    // two constuctor one defualt and second is not.
    public Enigma(){
        init();
        this.plugboard = new Plugboard("Plug Board", "");
    }
    
    public Enigma(int left, int middle, int right, int loffset, int moffset, int roffset, int lsetting, int msetting , int rsetting, String plugboard){
        init();
        
        setRoters(left, middle, right);
        setOffsets(loffset, moffset, roffset);
        setSettings(lsetting, msetting, rsetting);
        this.plugboard = new Plugboard("Plug Board", plugboard);
    }
    
    //function that init all functional of all components Rotors, Reflector, Plugboard.
    public void init(){
        rotors = new Rotor[ROTERS_LENGHT];
        
        rotors[0] = new Rotor("I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray(), 'Q');
        rotors[1] = new Rotor("II", "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray(), 'E');
        rotors[2] = new Rotor("III", "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray(), 'V');
        rotors[3] = new Rotor("IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray(), 'J');
        rotors[4] = new Rotor("V", "VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray(), 'Z');

        reflector = new Reflector("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray());
        
    
    }
    //set and get
    public void setRoters(int left, int middle, int right){
        this.left = this.rotors[left];
        this.middle = this.rotors[middle];
        this.right = this.rotors[right];
        
        this.middle.setNextRotor(this.left);
        this.right.setNextRotor(this.middle);
        
    }
    
    public void setOffsets(int loffset, int moffset, int roffset){
        this.left.setOffset(loffset);
        this.middle.setOffset(moffset);
        this.right.setOffset(roffset);
    }
    
    public void setSettings(int lsetting, int msetting , int rsetting){
        this.left.setSetting(lsetting);
        this.middle.setSetting(msetting);
        this.right.setSetting(rsetting);
    }
    //encrypt message and used with function encryptLetter.
    public String encrypt(String message){
        StringBuilder sb = new StringBuilder();
        String temp = message.toUpperCase();
        for(char ch : temp.toCharArray()){
            
            if(Character.isAlphabetic(ch))
                sb.append(encryptLetter(ch));
            else if(ch == ' ')
                sb.append(ch);
            
        }
        return sb.toString();
    }
    
    private void changeDirection() {
        left.changeDirection();
	middle.changeDirection();
        right.changeDirection();
    }
   
    private char encryptLetter(char ch) {
        char c = ch;
        if (this.right.turnOver() || this.middle.turnOver()){
            if (this.middle.turnOver())
                this.left.step();
            this.middle.step();
        }
        
	this.right.step();
        
	c = plugboard.translation(c);
	c = right.translation(c);
	c = middle.translation(c);
	c = left.translation(c);
	c = reflector.translation(c);
        
	changeDirection();
        
	c = left.translation(c);
        c = middle.translation(c);
	c = right.translation(c);
        c = plugboard.translation(c);
        
	changeDirection();

        return c; 
    }
    
    public int[] getOffset(){
        int[] offset = new int[3];
        offset[0] = left.getOffset();
        offset[1] = middle.getOffset();
        offset[2] = right.getOffset();
        return offset;
    }
    
    public int[] getSetting(){
        int[] offset = new int[3];
        offset[0] = left.getSetting();
        offset[1] = middle.getSetting();
        offset[2] = right.getSetting();
        return offset;
    }

    public void setPlagboard(String plugs){
        this.plugboard.clearPlugs();
        this.plugboard.addPlugs(plugs);
    }

 
}

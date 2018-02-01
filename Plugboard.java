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
public class Plugboard extends Translator{
    
    private static final String REGEX ="([A-Z][A-Z][' '])*([A-Z][A-Z])";
    private int connected_count;
    private String[] configurations;
    
    /**
     * 
     * @param plugs
     * @param name 
     */
    public Plugboard(String plugs, String name ){
        this.configurations = new String[10];
        this.name = name;
        this.connected_count = 0;
        this.permutation = new char[26];
        
        for(int i=0; i< this.permutation.length ; i++ ){
            this.permutation[i] = (char)('A' + i);
        }
        //if the expression like AB CD ... 
        if (plugs.matches(REGEX))
            addPlugs(plugs); 
    }

    public boolean addPlugs(String plugs) {
        char[] other_plugs = plugs.toCharArray();
        
        for(int i=0; i< other_plugs.length-1 ; i++){
            if(Character.isAlphabetic(other_plugs[i]) && Character.isAlphabetic(other_plugs[i+1])){
                if (addPlug(Character.toUpperCase(other_plugs[i]),Character.toUpperCase(other_plugs[i + 1])) == false)
                    return false;
            }
        }
          return true;   
    }

    public boolean addPlug(char leftLetter, char rightLetter) {
        
        if(!(this.permutation[toIndex(leftLetter)] == leftLetter && permutation[toIndex(rightLetter)] == rightLetter)){
            return false;
        }
        if(this.connected_count == 10){
            System.out.println("Plugbord.addPlug: Max plugs already in use!");
            return false;
        }
        
        this.permutation[toIndex(leftLetter)] = rightLetter;
        this.permutation[toIndex(rightLetter)] = leftLetter;
        
        this.configurations[this.connected_count++] = leftLetter+ rightLetter + "";
        
        
        return true;
    }
    
    public String getConfigurations(){
        StringBuilder sb = new StringBuilder();
        
        for(String conf : this.configurations)
            sb.append(conf + " ");
        
        return sb.toString().trim();
        
    }
    
    @Override
    protected char translation(char ch) {
        return this.permutation[toIndex(ch)];
    }
    
    public boolean removePlug(char leftLetter, char rightLetter) {
        
        boolean flag_found = false;
        
        if (this.connected_count == 0) {
            System.out.println("Plugboard.removePlug: No Plugs Connected");
            return false;
	}
        if(!(this.permutation[toIndex(leftLetter)] == leftLetter && permutation[toIndex(rightLetter)] == rightLetter)){
            System.out.println("Plugboard.removePlug: Given Plug Not Connected");
            return false;
	}

	this.permutation[toIndex(leftLetter)] = leftLetter;
        this.permutation[toIndex(rightLetter)] = rightLetter;
	String plug = "" + leftLetter + rightLetter;

        for (int i = 0; i < configurations.length; i++) {
            if (configurations[i].equals(plug)){
                configurations[i] = "";
                flag_found= true;
            }
        }
        
        if(flag_found){
            connected_count--;
            return true;
        }
        else{
            return false;
        }
    }
    
    public void clearPlugs() {
        for (int i = 0; i < this.permutation.length; i++) {
            this.permutation[i] = (char) ('A' + i);
	}
        for (int i = 0; i < this.configurations.length; i++) {
            configurations[i] = "";
        }
        connected_count= 0;
        
            
    }
    

}

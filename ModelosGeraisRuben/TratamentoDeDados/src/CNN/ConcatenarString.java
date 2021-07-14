/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CNN;

import java.util.List;

/**
 *
 * @author afons
 */
public class ConcatenarString extends Thread{
    
    public List<String> array;
    public String texto;
    public int min,max;
    
    public ConcatenarString(int min, int max, List<String> array){
        this.texto = "";
        this.min = min;
        this.max = max;
        this.array = array;
        this.texto = "";
    }
    
    @Override
    public void run(){
        for (int i = min; i < max; i++) {
            this.texto += this.array.get(i);
        }
    }
}

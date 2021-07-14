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
public class RemoverTimeStamp extends Thread{
    
    public List<String> array;
    public int min, max;
    
    public RemoverTimeStamp(int min, int max, List<String> array){
        super();
        this.min = min;
        this.max = max;
        this.array = array;
    }
    
    @Override
    public void run(){
        for (int i = min; i < max; i++) {
            this.array.set(i,this.array.get(i).substring(0, this.array.get(i).length() - 20));
        }
    }
}

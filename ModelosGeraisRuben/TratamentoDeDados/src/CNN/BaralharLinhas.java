/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CNN;

import java.util.List;
import java.util.Random;

/**
 *
 * @author afons
 */
public class BaralharLinhas extends Thread{
    
    public List<String> array;
    public int itera;
    
    public BaralharLinhas(int itera, List<String> array){
        this.array = array;
        this.itera = itera;
    }
    
    @Override
    public void run(){
        Random rand = new Random();
        for (int i = 0; i < this.itera; i++) {
            int a = rand.nextInt(array.size());
            int b = rand.nextInt(array.size());
            String aux = this.array.get(a);
            this.array.set(a, this.array.get(b));
            this.array.set(b, aux);
        }
    }
    
}

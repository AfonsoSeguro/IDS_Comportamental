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
public class TransformarStringDouble extends Thread{
    
    public int min, max;
    double [][] array;
    List<String> linhas;
    
    public TransformarStringDouble(int min, int max, double [][] array, List<String> linhas){
        this.min = min;
        this.max = max;
        this.array = array;
        this.linhas = linhas;
    }
    
    @Override
    public void run(){
        for (int i = min; i < max; i++) {
            String [] valores = linhas.get(i).split(",");
            this.array[i] = new double[valores.length - 1];
            for (int j = 0; j < valores.length - 1; j++) {
                this.array[i][j] = Double.parseDouble(valores[j]);
            }
        }
    }
}

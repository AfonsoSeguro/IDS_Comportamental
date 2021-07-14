/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CNN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author afons
 */
public class TratamentoDeDados {
    
    //https://www.codeproject.com/Articles/86551/Part-1-Programming-your-Graphics-Card-GPU-with-Jav
    
    public static List<String> CarregarFicheiro() throws Exception{
        File fich = new File("..\\DatasetTotal.csv");
        BufferedReader fileInp = new BufferedReader(new FileReader(fich));
        List<String> result = Collections.synchronizedList(new ArrayList());
        while(fileInp.ready()){result.add(fileInp.readLine());}
        fileInp.close();
        return result;
    }
    
    public static void GuardarFicheiro(String texto)throws Exception{
        File fich = new File("..\\DataSetCNN.csv");
        fich.createNewFile();
        BufferedWriter fileOut = new BufferedWriter(new FileWriter(fich));
        fileOut.write(texto);
        fileOut.close();
    }
    
    public double [] Extremos(double [] array){
        double min = array[0];
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(min > array[i])min = array[i];
            if(max < array[i])max = array[i];
        }
        return new double[]{min,max};
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        List<String> linhas = CarregarFicheiro();
        String result = linhas.remove(0);
        linhas.remove(0);
        linhas.remove(0);
        
        int intervalo = linhas.size() / 8;
        
        System.out.println("TimeStamp");
        //Remover TimeStamp
        RemoverTimeStamp [] thrsR = new RemoverTimeStamp[8];
        for (int i = 0; i < 8; i++) {
            thrsR[i] = new RemoverTimeStamp(i * intervalo, i * intervalo + intervalo, linhas);
            thrsR[i].start();
        }
        for (int i = 0; i < 8; i++) {
            thrsR[i].join();
        }
        System.out.println("Baralhar");
        //Baralhar Linhas
        BaralharLinhas [] thrsB = new BaralharLinhas[8];
        for (int i = 0; i < 8; i++) {
            thrsB[i] = new BaralharLinhas(intervalo, linhas);
            thrsB[i].start();
        }
        for (int i = 0; i < 8; i++) {
            thrsB[i].join();
        }
        System.out.println("Transformar");
        //Transformar
        double [][] dados = new double[linhas.size()][];
        
        TransformarStringDouble [] thrsT = new TransformarStringDouble[8];
        for (int i = 0; i < 8; i++) {
            thrsT[i] = new TransformarStringDouble(i * intervalo, i * intervalo + intervalo, dados, linhas);
            thrsT[i].start();
        }
        for (int i = 0; i < 8; i++) {
            thrsT[i].join();
        }
        System.out.println("Normalizar");
        //Transformar
        
        System.out.println("Concatenar");
        //Concatenar
        ConcatenarString [] thrsC = new ConcatenarString[8];
        for (int i = 0; i < 8; i++) {
            thrsC[i] = new ConcatenarString(i * intervalo, i * intervalo + intervalo, linhas);
            thrsC[i].start();
        }
        for (int i = 0; i < 8; i++) {
            thrsC[i].join();
            result += thrsC[i].texto;
        }
        
        //Guargar
        //GuardarFicheiro(result.toString());
        //System.out.println(result);
    }
    
}

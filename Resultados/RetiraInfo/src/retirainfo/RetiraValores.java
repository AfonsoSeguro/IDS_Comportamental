/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retirainfo;

/**
 *
 * @author afons
 */
public class RetiraValores {
    public static void main(String[] args) {
        
        int [][] matrixMedia = new int[][]{{208590,0},{0,0}};
        
        double TP = matrixMedia[1][1];
        double FN = matrixMedia[1][0];
        double FP = matrixMedia[0][1];
        double TN = matrixMedia[0][0];
        
        String result = "";
        result += "Precision: " + (TP/(TP + FP)) + "\n";
        result += "Recall: " + (TP/(TP + FN)) + "\n";
        result += "False Alarm Rate: " + (FP/(FP + TN)) + "\n";
        result += "True Negative Rate: " + (TN/(TN + FP)) + "\n";
        result += "Accuracy: " + ((TP+TN)/(TP+TN+FP+FN)) + "\n";
        result += "Confusion Matrix: [[" + matrixMedia[0][0] + ", " + matrixMedia[0][1] + "],[" + matrixMedia[1][0] + ", " + matrixMedia[1][1] + "]]";
        
        System.out.println(result);
    }
}

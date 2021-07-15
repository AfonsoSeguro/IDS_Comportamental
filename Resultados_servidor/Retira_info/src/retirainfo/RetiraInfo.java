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
public class RetiraInfo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text =
        "Modelo 0\n" +
"[array([0.00000000e+00, 7.89750595e-04, 1.00000000e+00]), array([0.        , 0.99343566, 1.        ]), 0.9963229552789498]\n" +
"[[449154    355]\n" +
" [   473  71583]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 1\n" +
"[array([0.00000000e+00, 8.56490081e-04, 1.00000000e+00]), array([0.        , 0.99096536, 1.        ]), 0.9950544350969708]\n" +
"[[449124    385]\n" +
" [   651  71405]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 2\n" +
"[array([0.00000000e+00, 7.25235757e-04, 1.00000000e+00]), array([0.        , 0.99375486, 1.        ]), 0.996514810787974]\n" +
"[[449183    326]\n" +
" [   450  71606]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 3\n" +
"[array([0.        , 0.00110343, 1.        ]), array([0.        , 0.99361608, 1.        ]), 0.9962563251011216]\n" +
"[[449013    496]\n" +
" [   460  71596]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 4\n" +
"[array([0.        , 0.00186648, 1.        ]), array([0.        , 0.99657211, 1.        ]), 0.9973528147990381]\n" +
"[[448670    839]\n" +
" [   247  71809]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 5\n" +
"[array([0.00000000e+00, 8.29796133e-04, 1.00000000e+00]), array([0.        , 0.99548962, 1.        ]), 0.997329911526209]\n" +
"[[449135    373]\n" +
" [   325  71731]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 6\n" +
"[array([0.00000000e+00, 8.45368714e-04, 1.00000000e+00]), array([0.        , 0.99453203, 1.        ]), 0.9968433309642973]\n" +
"[[449128    380]\n" +
" [   394  71662]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 7\n" +
"[array([0.00000000e+00, 7.87527697e-04, 1.00000000e+00]), array([0.        , 0.98630232, 1.        ]), 0.9927573963602523]\n" +
"[[449154    354]\n" +
" [   987  71069]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 8\n" +
"[array([0.00000000e+00, 7.60831843e-04, 1.00000000e+00]), array([0.        , 0.99211724, 1.        ]), 0.9956782051510916]\n" +
"[[449166    342]\n" +
" [   568  71488]]\n" +
"------------------------------------\n" +
"------------------------------------\n" +
"Modelo 9\n" +
"[array([0.00000000e+00, 6.74070317e-04, 1.00000000e+00]), array([0.        , 0.99487898, 1.        ]), 0.9971024563481661]\n" +
"[[449205    303]\n" +
" [   369  71687]]";
        
        text = text.replaceAll("------------------------------------\n", "");
        String [] modelos = text.split("Modelo ");
        double mediaAuc = 0;
        int [][] matrixMedia = new int[][]{{0,0},{0,0}};
        for (int i = 1; i < modelos.length; i++) {
            String modelo = modelos[i];
            String linha = modelo.split("\n")[1];
            mediaAuc += Double.parseDouble(linha.split(",")[linha.split(",").length - 1].replace("]", ""));
            
            linha = modelo.split("\n")[2].replace("[", "").replace("]", "").trim();
            while(linha.contains("  ")){
                linha = linha.replace("  ", " ");
            }
            matrixMedia[0][0] += Integer.parseInt(linha.split(" ")[0]);
            matrixMedia[0][1] += Integer.parseInt(linha.split(" ")[1]);
            
            linha = modelo.split("\n")[3].replace("[", "").replace("]", "").trim();
            while(linha.contains("  ")){
                linha = linha.replace("  ", " ");
            }
            matrixMedia[1][0] += Integer.parseInt(linha.split(" ")[0]);
            matrixMedia[1][1] += Integer.parseInt(linha.split(" ")[1]);
        }
        mediaAuc /= 10;
        for (int i = 0; i < matrixMedia.length; i++) {
            for (int j = 0; j < matrixMedia[i].length; j++) {
                matrixMedia[i][j] /= 10;
            }
        }
        double TP = matrixMedia[1][1];
        double FN = matrixMedia[1][0];
        double FP = matrixMedia[0][1];
        double TN = matrixMedia[0][0];
        
        String result = "";
        result += "Auc: " + mediaAuc + "\n";
        result += "Precision: " + (TP/(TP + FP)) + "\n";
        result += "Recall: " + (TP/(TP + FN)) + "\n";
        result += "False Alarm Rate: " + (FP/(FP + TN)) + "\n";
        result += "True Negative Rate: " + (TN/(TN + FP)) + "\n";
        result += "Accuracy: " + ((TP+TN)/(TP+TN+FP+FN)) + "\n";
        result += "Confusion Matrix: [[" + matrixMedia[0][0] + ", " + matrixMedia[0][1] + "],[" + matrixMedia[1][0] + ", " + matrixMedia[1][1] + "]]";
        
        System.out.println(result);
        
    }
    
}

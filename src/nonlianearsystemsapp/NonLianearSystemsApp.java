package nonlianearsystemsapp;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class NonLianearSystemsApp {

    public static void main(String[] args) {
        double eps = 0.001;
        double a = 0.5;
        double b = 5.0;
        
        System.out.println("{sin(x) - y = 1,2");
        System.out.println("{0,15 + cos(y) - x = 0");
        System.out.println("\n[" + a + "][" + b + "]");
        System.out.println("eps = " + eps);
        
        System.out.println("Метод простой итерации");
        simpleIter(a, b, eps);
        System.out.println("\nМетод Ньютона");
        newtone(a, b, eps);
    }

    private static void simpleIter(double a, double b, double eps) {
        double x = a;
        double y = a;
        
        int n = 0;
        while(abs(y - Math.pow(1.-Math.pow(Math.pow(y, 1/3.),2.), 1./2.)) > eps  && abs(y - Math.pow(Math.pow(1.-Math.pow(x,2.), 1./2.), 1./3.)) > eps   ) {
            x = Math.pow(y, 1/3.);
            y = Math.pow(1-Math.pow(x,2.), 1./2.);
            n++;
        }
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("n = " + n);
    } 

    private static void newtone(double a, double b, double eps) {
        double []x = {0.0, 5.0};
        double [][]w = new double[2][2];
        double []f = new double[2];
        double []deltaX = new double[2];
        double max;
        int n = 0;
        
        do{
            n++;
            
            w[0][0] = cos(x[0]);
            w[0][1] = -1.0;
            w[1][0] = -1.0;
            w[1][1] = -sin(x[1]);

            f[0] = -sin(x[0]) + x[1] + 1.2;
            f[1] = -0.15 - cos(x[1]) + x[0];

            triangulation(w, f);
            backStroke(w, f, deltaX);
            x[0] += deltaX[0];
            x[1] += deltaX[1];
            
            if(abs(deltaX[0]) > abs(deltaX[1]))
                max = abs(deltaX[0]);
            else max = abs(deltaX[1]);
        }while(max > eps);
        
        System.out.println("x = " + x[0]);
        System.out.println("y = " + x[1]);
        System.out.println("n = " + n);
        
    }
    
    
    private static void triangulation(double [][]a, double[] b){
        double first;
        for(int k = 0; k < 2; k++){    
            first = a[k][k];
                if(first != 0){                   
                    b[k] /= first;
                for(int j = k; j < 2; j++)
                    a[k][j] /= first;       
                }

                for(int i = k + 1; i < 2; i++){
                    first = a[i][k];
                    b[i] -= b[k] * first;
                    for(int j = k; j < 2; j++)
                        a[i][j] -= a[k][j] * first;
                }
        }
    }
    
    private static void backStroke(double [][]a, double []b, double []c){
        c[1] = b[1];
        double sum;
        for(int i = 0; i >= 0; i--){
            sum = 0;
            c[i] = b[i];
            for(int j = i+1; j < 2; j++)
                sum += a[i][j] * c[j];
            c[i] -= sum;
        }
    }
    
    static void print_m(double[][] a){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++)
                System.out.print(a[i][j] + "\t");
            System.out.println("");
        }
        System.out.println("");
    }
    
    static void print_v(double[] v){
        for(int i = 0; i < 2; i++)
                System.out.print(v[i] + "\t");
        System.out.println("");
    }
    
}

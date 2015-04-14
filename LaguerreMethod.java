package JNumericalAnalysis;

import biz.source_code.dsp.math.Complex;
import biz.source_code.dsp.math.PolynomialRootFinderLaguerre;

public class LaguerreMethod {

    public static void main(String[] args) {
        Complex[] podpunktA = {new Complex(243,0),new Complex(-
                486,0),new Complex(783,0),new Complex(-990,0),new Complex(558,0)
                ,new Complex(-28,0),new Complex(-72,0),new
                Complex(16,0)};
        Complex[] podpunktB = {new Complex(1,0),new Complex(1,0),new
                Complex(3,0),new Complex(2,0),new Complex(-1,0)
                ,new Complex(-3,0),new Complex(-11,0),new Complex(-
                8,0),new Complex(-12,0),new Complex(-4,0),new Complex(-4,0)};
        Complex[] podpunktC = {new Complex(1,0),new Complex(0,1),new
                Complex(-1,0),new Complex(0,-1),new Complex(1,0)};
        Complex[] resultA =
                PolynomialRootFinderLaguerre.findRoots(podpunktA);
        Complex[] resultB =
                PolynomialRootFinderLaguerre.findRoots(podpunktB);
        Complex[] resultC =
                PolynomialRootFinderLaguerre.findRoots(podpunktC);
        System.out.println("Miejsca zerowe pierwszego wielomianu:");
        for (Complex complex : resultA) {
            System.out.println(complex);
        }
        System.out.println("\nMiejsca zerowe drugiego wielomianu:");
        for (Complex complex : resultB) {
            System.out.println(complex);
        }
        System.out.println("\nMiejsca zerowe trzeciego wielomianu:");
        for (Complex complex : resultC) {
            System.out.println(complex);
        }
    }

}

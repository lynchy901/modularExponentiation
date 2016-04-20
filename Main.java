import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] arr = getInput();
        int base = arr[0];
        int exponent = arr[1];
        int mod = arr[2];

        int answer = modularExponentiation(base, exponent, mod);
        System.out.println("Answer: " + answer);

    }

    public static int[] getInput() {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[3];

        System.out.println("Enter the base number: ");
        arr[0] = in.nextInt();
        System.out.println("Enter the exponent: ");
        arr[1] = in.nextInt();
        System.out.println("Enter the mod: ");
        arr[2] = in.nextInt();

        return arr;
    }


    public static int modularExponentiation(int base, int exponent, int mod) {
        int answer = 0;
        int cExp = 1;
        int counter = 0;
        int currAns;

        Map<Integer, Integer> pAns = new HashMap<Integer, Integer>();
        System.out.println("Calculating " + base + "^" + exponent + " mod " + mod);
        while (cExp < exponent) {

            if (counter == 0) {
                currAns = (int)(Math.pow((double)base,(double)cExp) % mod);
                System.out.println(base + "^" + cExp + " mod " + mod);
            } else {
                currAns = (int)Math.pow((double)pAns.get(cExp/2), 2.0) % mod;
                System.out.println(base + "^" + cExp + " mod " + mod + " = " + pAns.get(cExp/2) + "^2 mod " + mod + " = " + currAns);
            }

            pAns.put(cExp, currAns);
            counter++;

            cExp = cExp * 2;
        }
        if (cExp > exponent) {
            cExp = cExp/2;
        }

        ArrayList<Integer> neededExponents = getNeededExponents(exponent - cExp, pAns);

        answer = pAns.get(neededExponents.get(0));

        for (int i = 0; i < neededExponents.size() - 1; i++) {
            System.out.println(answer + " * " +  pAns.get(neededExponents.get(i+1)) + " mod " + mod);
            answer = (answer * pAns.get(neededExponents.get(i+1))%mod);
            System.out.println(" = " + answer);
            System.out.println();
        }
        System.out.println("Answer: " + answer + " * " + pAns.get(cExp) + " mod " + mod);
        return answer * pAns.get(cExp) % mod;
    }

    public static ArrayList<Integer> getNeededExponents(int exp, Map<Integer, Integer> pAns) {
        ArrayList<Integer> exponents = new ArrayList();

        int total = 0;

        while (total+2 <= exp) {
            total += 2;
            exponents.add(2);
        }

        if (total % exp != 0) {
            total += 1;
            exponents.add(1);
        }

        return exponents;
    }
}

/*Algoritmul urmareste urmatoare idee:
 *  Suma curenta incercam sa o impartim intr-un divizor al sumei si diferenta dintre suma si acel divior.
 *  Diferenta trebuie sa fie la randul ei divizibila cu divizorul generat.
 *  Cand se gaseste divizorul potrivit se face apel recursiv pe diferenta si se aplica acelasi algoritm.
 *  Acesta se opreste cand nu mai poate gasi un divizor pentru o suma care sa fie mai mic decat ea si mai mare decat ultimul divizor gasit.
 *
 *
 * In cazul in care pentru o suma sunt mai multe variante (de exemplu 120 are ca si parte atat 6 cat si 11) partea cu valoarea cea mai mare a fost
 * gasita.
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> listOfDivisors = new ArrayList<>();
        listOfDivisors.add(0);
        for (int i = 1; i < 20; i++) {

            int rez = getPart(listOfDivisors, i * 10);
            if(rez!= -1){
                System.out.println("Cost: " +  i*10 + " Part: " + rez / 10);
            }else{
                System.out.println("Cost: " +  i*10 + " Part: " + -1);
            }
            listOfDivisors = new ArrayList<>();
            listOfDivisors.add(0);
        }
    }

    public static int getPart(ArrayList<Integer> listOfDivisors, int current) {
        int newDivisor;
        //calculate the potential divisor given the list with divisors and current element
        int divisor = calculateNewDivisor(listOfDivisors.get(listOfDivisors.size() - 1), current);

        if (divisor != 0) {
            listOfDivisors.add(divisor);
            boolean ok = true;

            while (ok) {
                int newCurrent = current - divisor;
                //check if the new element is divisible with all elements from current list
                if (checkElementDivisibleWithList(listOfDivisors, newCurrent)) {
                    
                    //recursive call
                    int result = getPart(listOfDivisors, newCurrent);
                    
                    //if the return value from recursive call is -1 => no solution, try to find anther divisor
                    if (result == -1) {
                        newDivisor = calculateNewDivisor(listOfDivisors.get(listOfDivisors.size() - 1), current);
                        //if there are no others divisors stop the "while"
                        if (newDivisor != 0) {
                            //we make sure that we not exclude worker 1 from list(all parts should have it)
                            if (listOfDivisors.size() - 1 == 1) {
                                return -1;
                            }
                            listOfDivisors.remove(listOfDivisors.size() - 1);
                            listOfDivisors.add(newDivisor);
                            divisor = newDivisor;
                        } else {
                            ok = false;
                        }
                    } else {

                        return result;
                    }

                } else {

                    newDivisor = calculateNewDivisor(listOfDivisors.get(listOfDivisors.size() - 1), current);
                    if (listOfDivisors.size() - 1 == 1) return -1;
                    listOfDivisors.remove(listOfDivisors.size() - 1);
                    if (newDivisor != 0) {
                        listOfDivisors.add(newDivisor);
                        divisor = newDivisor;
                    } else {
                        return -1;
                    }
                }
            }
            //verify if we got a solution by checking if there are all divisors in final the list
        } else {
            if (checkFinalElementDivisorNumber(listOfDivisors, current)) {
                return current;
            } else {
                return -1;
            }
        }

        return -1;
    }

    public static boolean checkElementDivisibleWithList(ArrayList<Integer> list, int elem) {
        for (Integer item : list) {
            if (item == elem) return false;
            if (item != 0 && elem % item != 0) {
                return false;
            }
        }
        return true;
    }

    public static int calculateNewDivisor(int lastElemFromList, int current) {

        for (int i = lastElemFromList + 10; i <= current / 2; i += 10) {
            if (current % i == 0) {
                return i;
            }
        }
        return 0;
    }

    public static boolean checkFinalElementDivisorNumber(ArrayList<Integer> list, int elem) {

        int counter = 1;
        for (int i = 10; i <= elem / 2; i+=10) {
            if (elem % i == 0) {
                counter++;
            }
        }

        return counter == list.size();
    }


}

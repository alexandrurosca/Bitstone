import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        //System.out.print(checkElementDivizibilWithList(list,10 ));

    }

    public  static boolean checkElementDivizibilWithList(ArrayList<Integer> list, int elem) {
        for (Integer item : list) {
            if (elem % item != 0) {
                return false;
            }
        }
        return true;
    }

    public static int calculateNewDivizor(int lastElemFromList, int current) {

        for (int i = lastElemFromList + 1; i < Math.sqrt(current); i++) {
            if (current % i == 0) {
                return i;
            }
        }
        return 0;
    }


}

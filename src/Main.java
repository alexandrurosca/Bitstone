import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

    }


    boolean checkElementDivizibilWithList(ArrayList<Integer> list, int  elem){
        for(Integer item: list){
            if(elem % item != 0){
                return false;
            }
        }
        return true;
    }



}

package com.mygdx.game;

import com.mygdx.game.Towers.FireTower;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Requirements {
    FireTower[][] f = new FireTower[2][3];
    FireTower requirementTower = new FireTower();
    int[][]num = {{1,2,3,4,5},{6,7,8,9,10}};
    int[] numbers = {1,3,2,4,5,657,2,1,123,14,3};
    ArrayList<Integer> number = new ArrayList<>();
    public ArrayList<Integer> selectionsort(){
        number.add(2);
        number.add(1);
        number.add(4);
        number.add(3);
        for (int i = 0; i < number.size()-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < number.size(); j++) {
                if (number.get(j) < number.get(minIndex)) {
                    minIndex = j;
                }
            }
            int temp = number.get(minIndex);
            number.set(minIndex,number.get(i));
            number.set(i,temp);
        }
        return number;
    }
    public ArrayList<Integer> insertionSort(){
        for(int i =0;i<number.size();i++){
            int index  = i;
            while(index>0 && number.get(index).compareTo(number.get(index-1))<0){
                int temp = number.get(index);
                number.set(index,number.get(index-1));
                number.set(index-1,temp);
                index--;
            }
        }
        try {
            System.out.println(f[0][6]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("nothing to see here");
        }
        try {
            number.add(1);
            number.add(2);
            number.add(2);
            number.add(1,3);
            for (int i : number) {
                number.remove(i);
            }
        }
        catch(ConcurrentModificationException e){
            for (int i = number.size()-1; i >=0; i--){
                if (number.get(i)==1){
                    number.remove(i);
                }
            }
            System.out.println(number.add(1));
            System.out.println(number.size());
            System.out.println(number.get(1));
            System.out.println(number.set(2,3));
            System.out.println(number.remove(1));
        }
        return number;
    }

    public void rowMajor(){
        for (int[] r : num){
            for (int c : r){
                System.out.println(c);
            }
        }
    }
    
    public void columnMajor() {
        for (int col = 0; col < num[0].length; col++) {
            for (int row = 0; row < num.length; row++) {
                // Process element here
                System.out.print(num[row][col] + " ");
            }
        }
    }
    


}

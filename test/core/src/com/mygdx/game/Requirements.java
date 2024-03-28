package com.mygdx.game;

import com.mygdx.game.Towers.AlphaTower;
import com.mygdx.game.Towers.ArrowTower;
import com.mygdx.game.Towers.FireTower;
import com.mygdx.game.Towers.TowerHitBox;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Requirements{
    FireTower[][] f = new FireTower[2][3];
    FireTower requirementTower = new FireTower();
    int[][]num = {{1,2,3,4,5},{6,7,8,9,10}};
    ArrayList<Integer> number = new ArrayList<>();
    TowerHitBox alpha = new AlphaTower();
    TowerHitBox[] towers = new TowerHitBox[1];
    ArrayList<TowerHitBox> towers1= new ArrayList<TowerHitBox>();

    public Requirements(){
        towers[0] = new ArrowTower();
        towers1.add(new ArrowTower());
        towers1.add(alpha);
        towers1.add(new TowerHitBox());
        towers1.get(1).equals(requirementTower);
        number.add(0, 1);
        number.add(2);
        number.add(2);
        number.add(1,3);
    }
    public ArrayList<Integer> ArrayListIntegerSelectionSort(ArrayList<Integer> ints) {
        for (int i = 0; i < ints.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ints.size(); j++) {
                if (ints.get(j) < ints.get(minIndex)) {
                    minIndex = j;
                }
            }
            int temp = ints.get(minIndex);
            ints.set(minIndex, ints.get(i));
            ints.set(i, temp);
        }
        return ints;
    }

    public ArrayList<Integer> ArrayListIntegerInsertionSort(ArrayList<Integer> ints){
        for (int i=1; i < ints.size(); i++){
            int current = ints.get(i);
            int j = i - 1;
            while (j >= 0 && ints.get(j) > current){
                ints.set(j+1, ints.get(j));
                j--;
            }
            ints.set(j+1, current);
        }
        return ints;
    }

    public void rowMajor(){
        for (int[] r : num){
            for (int c : r){
                System.out.println(c);
            }
        }
        try {
            System.out.println(f[0][6]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("nothing to see here");
        }
        try {
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
    }
    
    public void columnMajor() {
        for (int col = 0; col < num[0].length; col++) {
            for (int row = 0; row < num.length; row++) {
                // Process element here
                System.out.print(num[row][col] + " ");
            }
        }
    }
    public String toString(){
        return "OVERRIDEN";
    }
    public boolean equals(Object obj){
        return true;
    }
    


}

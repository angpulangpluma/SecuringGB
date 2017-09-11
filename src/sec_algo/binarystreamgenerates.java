/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sec_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Marienne Lopez
 */
public class binarystreamgenerates {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // TODO code application logic here
//        random = new SecureRandom();
        
        //prelims
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input string: ");
        String input = sc.nextLine();
//        String key = new BigInteger(130, random).toString(32);
        System.out.println("input: " + input);
//        System.out.println("key: " + key);
        
        byte[] bytstring = input.getBytes();
        ArrayList<String> bitstring = new ArrayList<String>();
        for(int i=0; i<bytstring.length; i++)
            bitstring.add(String.format("%8s", Integer.toBinaryString(bytstring[i] & 0xFF)).replace(' ', '0'));
        
        //how to keep this???
        Integer[] key = new Integer[8];
        for(int i=0; i<key.length; i++){
            key[i] = i;
        }
        Collections.shuffle(Arrays.asList(key));
        
        System.out.println("size of bit input: " + bitstring.size());
        System.out.print("bit input: [/");
        for (int i=0; i<bitstring.size(); i++){
            System.out.print(bitstring.get(i) + " ");
        }
        System.out.println("]/");
        System.out.println("key: " + Arrays.toString(key));
        
        //bit shuffling
        ArrayList<Integer> s0 = new ArrayList<Integer>();
        ArrayList<Integer> s1 = new ArrayList<Integer>();
        ArrayList<String> shuffledstring = new ArrayList<String>();
        
        for(int i=0; i<key.length; i++){
            int selected = (key[i]-7)*-1;
//            int selected = (0-7)*-1;
            System.out.println("selected: " + selected);
            for(int j=0; j<bitstring.size(); j++){
//                System.out.println("current binary string: " + bitstring.get(0));
//                System.out.println("character at index " + selected + ": " + bitstring.get(0).charAt(selected));
                if(bitstring.get(j).charAt(selected)=='0')
//                    s0.add(Integer.parseInt("0"));
                    s0.add(j);
                else s1.add(j);
//                    s1.add(Integer.parseInt("0"));
            }
            System.out.println("done shuffling");
            
            //combine s0 and s1
            for (int m=0; m<s0.size(); m++){
//                System.out.print(s0.get(m) + " ");
                shuffledstring.add(bitstring.get(s0.get(m)));
            }
            for (int n=0; n<s1.size(); n++){
//                System.out.print(s1.get(n) + " ");
                shuffledstring.add(bitstring.get(s1.get(n)));
            }
            
            bitstring = (ArrayList<String>)shuffledstring.clone();
            shuffledstring.clear();
            s0.clear();
            s1.clear();
        }

        System.out.println("size of shuffled bit input: " + bitstring.size());
        System.out.print("shuffled binary string: [/");
        for (int n=0; n<bitstring.size(); n++){
            System.out.print(bitstring.get(n) + " ");
        }
        System.out.println("]/");  
    }

}

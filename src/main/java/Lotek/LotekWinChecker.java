package Lotek;

import java.util.HashSet;
import java.util.Set;

class LotekWinChecker {

    static void checkResults(int[] userNumbers, int[] winningNumbers){
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<userNumbers.length;i++) {
            for(int j=0;j<userNumbers.length;j++) {
                if(userNumbers[i]==winningNumbers[j]) {
                    set.add(winningNumbers[j]);
                }
            }
        }
        System.out.println("\n\nIlość trafionych liczb: " + set.size());
        if(set.size() == winningNumbers.length) {
            System.out.println("\nGratulacje! Wygrałeś");
        }
        else {
            System.out.println("\nTym razem się nie udało, spróbuj ponownie");
        }
    }
}

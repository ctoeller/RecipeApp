package application;

import java.util.ArrayList;

public class QuickSort {
	 QuickSort (ArrayList<Recipe> recipeList, int start, int end){
		 quickSort(recipeList, start, end);
	 }
    public static void quickSort(ArrayList<Recipe> recipeList, int start, int end){
 
        int partition = partition(recipeList, start, end);
 
        if(partition-1>start) {
            quickSort(recipeList, start, partition - 1);
        }
        if(partition+1<end) {
            quickSort(recipeList, partition + 1, end);
        }
    }
 
    public static int partition(ArrayList<Recipe> recipeList, int start, int end){
        Recipe pivot = recipeList.get(end);
 
        for(int i=start; i<end; i++){
            if(recipeList.get(i).getMatches()<pivot.getMatches()){
                Recipe temp= recipeList.get(start);
                recipeList.set(start, recipeList.get(i));
                recipeList.set(i, temp);
                start++;
            }
        }
 
        Recipe temp = recipeList.get(start);
        recipeList.set(start, pivot);
        recipeList.set(end, temp);
 
        return start;
    }
}
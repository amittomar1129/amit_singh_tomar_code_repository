package com;

public class QuickSort 
{
	int array[] = {2, 6, 9, 8, 1, 3, 5, 7, 4};
	
	public void swapArray(int[] array , int start, int end)
	{
		int temp = array[start];
		for (int i = 0; i < array.length; i++) 
		{
			if(i == end)
			{
				array[i] = temp;
				return;
			}
			if(i >= start)
			{
				array[i] = array[i+1];
			}
		}
		
	}
	
	public int[] quickSort(int []array, int start, int end)
	{
		int pivot = end;
		
		for (int i = start; i <= end; i++) 
		{
			if(array[i] > array[pivot] && i < pivot)
			{
				swapArray(array, i, end);
				i--;
				pivot--;
				
			}
		}
		
		if(end - pivot > 1)
		{
			quickSort(array, pivot, end);
		}
		
		if(pivot != 0)
		{
			quickSort(array, 0, pivot - 1);
		}
		
		return array;
	}
	
	public static void main(String[] args) 
	{
		int array[] = {3,2,1,9,8,7,5};
		int[] sorted = new QuickSort().quickSort(array, 0, array.length - 1);

		for (int i = 0; i < sorted.length; i++) 
		{
			System.out.print(sorted[i] + "  ");
		}
		
		
	}

}

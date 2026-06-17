package com;

public class MergeSort {

	private int[] sort(int[] array, int start, int end) 
	{
		int middle = (end - start ) / 2;
		middle = start + (int)middle;
		
		if(end % 2 != 0) 
		{
			middle = middle + 1;
		}
		
		int[] array1 = new int[middle - start];
		int[] array2 = new int[end - middle];
		
		int j = 0, k = 0;

		for (int i = 0; i < array.length; i++) 
		{
			if(i >= start && i < middle)
			{
				array1[j] = array[i];
				j++;
			}
			if(i >= middle && i < end) 
			{
				array2[k] = array[i];
				k++;
			}
		}
		
		if(array1.length > 2 )
		{
			array1 = sort(array1, 0, array1.length);
		}
		checkForSwap(array1);

		if(array2.length > 2 )
		{
			array2 = sort(array2, 0, array2.length);
		}
		checkForSwap(array2);
		
		array = merge(array1, array2);
		
		return array;
	}
	
	public void checkForSwap(int[] array)
	{
		if(array.length == 2)
		{
			if(array[0] > array[1])
			{
				int temp = 0;
				temp = array[0];
				array[0] = array[1];
				array[1] = temp;
			}
		}
	}


	public int[] merge(int[] array1, int[] array2)
	{
		int[] out =new int[ array1.length + array2.length ];
		int i = 0, j = 0, k = 0;
		while(k < out.length)
		{
			if(i < array1.length && (j >= array2.length || array1[i] < array2[j]))
			{
				out[k] = array1[i];
				i++;	
			}
			else if (j < array2.length && (i >= array1.length || array1[i] >= array2[j]))
			{
				out[k] = array2[j];
				j++;
			}
			k++;
		}
		return out;
	}
	
	public static void main(String[] args) 
	{	
		int array[] = {3,2,1,9,8,7,5,6,21,43,545,656,654,7567,67867,3424,324324,324,46353453,0,98,54,34,123,1111111111};
		int[] sorted = new MergeSort().sort(array, 0, array.length);
		for (int i = 0; i < sorted.length; i++) 
		{
			System.out.print(sorted[i] + "  ");
		}

	}

}

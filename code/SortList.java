package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortList {


	private static List<Integer> solution(List<List<Integer>> rawData){
		
		List<Integer> temp = new ArrayList<Integer>();
		
		for (int i = 0; i < rawData.size(); i++) {
			
			for(Integer val : rawData.get(i))
			{
				if(!temp.contains(val))
					temp.add(val);
			}
		}
		
		//System.out.println(temp);
		
		return temp.stream().sorted( (l1, l2) ->  l1 > l2 ? 1 : l1 < l2 ? -1 : 0).collect(Collectors.toList());
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<List<Integer>> rawData = new ArrayList<>();
		List<Integer> first =       Arrays.asList(1,2,4,5,7);
		List<Integer> second = Arrays.asList(2,4,6,8,10);
		List<Integer> third =     Arrays.asList(10,9,8,3);

		rawData.add(first);
		rawData.add(second);
		rawData.add(third);

		final List<Integer> solution = solution(rawData);        

		System.out.println(solution);

		//OUTPUTs: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

	}

}

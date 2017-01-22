# 用到了自定义的comparator。heap和sort都曾经用到过， 另一题是Merge Ksorted Lineked List
Arrays/Collections.sort(list, new Comparator<Type>() {
  public int compare(Type a, Type b) {
    // return the compared result;
  }
});


给个input list of floating points, 要求output list of integers, 满足以下两个constraint， 
就是和跟Round(x1+x2+… +xn)的结果一样，但是minimize output 和input的绝对值差之和 
#Input: A = # Sum T = Round(x1+x2+… +xn) ; 
Output: B = # Constraint #1: y1+y2+…+yn = T
# Constraint #2: minimize sum(abs(diff(xi - yi))) 
举例 # A = # Round(1.2 + 2.3 + 3.4) = 6.9 => 7 
# 1 + 2 + 3 => 6 # 1 + 3 + 3 => 7 
# 0.2 + 0.7 + 0.4 = 1.3 

# 1 + 2 + 4 => 7 
# 0.2 + 0.3 + 0.6 = 1.1 所以比要好 

链接: https://instant.1point3acres.com/thread/139420 
来源: 一亩三分地


import java.util.Comparator;
import java.util.PriorityQueue;

//(int) auto get floor
//math.floor/ceil/round return double 

class numDiff{ //stor the floored number and the diff bt orginal num and it's ceil 
	double diffCeil;
	int floorNum; //the num after floor
	public numDiff(int floorNum, double diffCeil){
		this.diffCeil = diffCeil;
		this.floorNum = floorNum;
	}
}

public class RoundNumber {
	public static int[] getNearlyArrayWithSameSum(double[] input) {
		double sum = 0.0; 
		int seprtSum = 0; //the sum added individually
		//create a heap to store diff bt num and ceil in ascending order
		PriorityQueue<numDiff> numWithDiff = new PriorityQueue<numDiff>(input.length, new Comparator<numDiff>(){
			public int compare(numDiff n1, numDiff n2){
				if ( n1.diffCeil >=n2.diffCeil){
					return 1;
				}
				else{
					return -1;
				}
			}
		});
		for ( int i = 0; i < input.length ; i ++){
			sum += input[i];
			int floorSum = (int) Math.floor(input[i]); 
			seprtSum += floorSum; 
			double diff = Math.ceil(input[i]) - input[i];
			//check number like 4.0, which ceil and floor are the same 
			if (diff == 0){
				diff ++; 
			}
				numWithDiff.offer(new numDiff(floorSum , diff)); 
		}
		
		int roundSum = (int) Math.round(sum);
		int totalDiff = roundSum - seprtSum; //know how much num to get ceil
		int[] output = new int[input.length]; 
		for (int i = 0; i < input.length; i ++ ){
			int numToAdd = 0; 
			//check how many num to use ceil 
			if ( totalDiff > 0 ){
				numToAdd = numWithDiff.poll().floorNum + 1; // poll out num with smallest diff from ceil
				totalDiff --; 
			}
			else{
				numToAdd = numWithDiff.poll().floorNum;
			}
			output[i] = numToAdd;
		}
		
		return output;
	}
	



public static void main(String args[]) {
	//double[] d = {1.2, 2.3, 3.4};
	//test with negtive numbee
	double[] d = {-0.4,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3}; 
	//result : {0, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1}
	//together round 13: seperate round 10



	
	int s = getNearlyArrayWithSameSum(d).length;
	while ( s >0 ){
		System.out.println(getNearlyArrayWithSameSum(d)[s-1]);
		s--;
	}
	
	//System.out.println(	Math.floor(-0.4) );

	
    
    
    
 }
}

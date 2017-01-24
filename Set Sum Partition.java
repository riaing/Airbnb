Minimum Sum Partition

Given an array, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.

Input:
The first line contains an integer ‘T’ denoting the total number of test cases. In each test cases, the first line contains an integer ‘N’ denoting the size of array. The second line contains N space-separated integers A1, A2, …, AN denoting the elements of the array.

Output:
In each seperate line print minimum absolute difference.

Constraints:
1<=T<=30
1<=N<=50
1<=A[I]<=50

Example:

Input:
2
4
1 6 5 11
4
36 7 46 40
Output :
1
23
Explaination :
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11


// Function to find the minimum sum
int findMinRec(int arr[], int i, int sumCalculated, int sumTotal)
{
    // If we have reached last element.  Sum of one
    // subset is sumCalculated, sum of other subset is
    // sumTotal-sumCalculated.  Return absolute difference
    // of two sums.
    if (i==0)
        return abs((sumTotal-sumCalculated) - sumCalculated);
 
 
    // For every item arr[i], we have two choices
    // (1) We do not include it first set
    // (2) We include it in first set
    // We return minimum of two choices
    return min(findMinRec(arr, i-1, sumCalculated+arr[i-1], sumTotal),
               findMinRec(arr, i-1, sumCalculated, sumTotal));
}
 
// Returns minimum possible difference between sums
// of two subsets
int findMin(int arr[], int n)
{
    // Compute total sum of elements
    int sumTotal = 0;
    for (int i=0; i<n; i++)
        sumTotal += arr[i];
 
    // Compute result using recursive function
    return findMinRec(arr, n, 0, sumTotal);
}

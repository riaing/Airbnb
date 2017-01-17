Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
For example, given the following matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
时间 O(MN) 空间 O(MN)
思路
当我们判断以某个点为正方形右下角时最大的正方形时，那它的上方，左方和左上方三个点也一定是某个正方形的右下角，否则该点为右下角的正方形最大就是它自己了。这是定性的判断，那具体的最大正方形边长呢？我们知道，该点为右下角的正方形的最大边长，最多比它的上方，左方和左上方为右下角的正方形的边长多1，最好的情况是是它的上方，左方和左上方为右下角的正方形的大小都一样的，这样加上该点就可以构成一个更大的正方形。但如果它的上方，左方和左上方为右下角的正方形的大小不一样，合起来就会缺了某个角落，这时候只能取那三个正方形中最小的正方形的边长加1了。假设dpi表示以i,j为右下角的正方形的最大边长，则有
dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
当然，如果这个点在原矩阵中本身就是0的话，那dpi肯定就是0了。
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int max = 0; 
        if ( matrix == null || matrix.length== 0 || matrix[0].length == 0){
            return max; 
        }
        int row = matrix.length ;
        int col = matrix[0].length; 
        int[][] length = new int[row][col];
        // first col 赋值
        for ( int j = 0; j < row; j ++ ){
            length[j][0] = matrix[j][0] - '0';
            max = Math.max( max, length[j][0]);
        }
        //first row 赋值
        for ( int i = 0; i < col; i ++ ){
            length[0][i] = matrix[0][i] -'0';
            max = Math.max( max, length[0][i]);
        }
        // 递推
        for ( int i = 1; i < row ; i ++ ){
            for ( int j = 1; j < col ; j ++ ){
                length[i][j] = matrix[i][j] == '1'? Math.min(length[i-1][j] , 
                Math.min(length[i][j-1], length[i-1][j-1]))+1 : 0; 
                max = Math.max ( max, length[i][j]);
            }
        }
        return max * max ; 
        
    }
}
Contact GitHub API Training Shop Blog About

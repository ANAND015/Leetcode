class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, int[]> lookup = new HashMap<>();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                lookup.put(mat[i][j], new int[]{i,j});
            }
        }
        int[] rowCount = new int [m];
        int[] colCount = new int [n];

        for(int i=0;i<arr.length;i++)
        {
            int[] coor = lookup.get(arr[i]);
            int x = coor[0];
            int y = coor[1];
            
            rowCount[x]++;
            colCount[y]++;

            if(rowCount[x] == n || colCount[y] == m)
            {
                return i;
            }
        }
        return 0;
    }
}
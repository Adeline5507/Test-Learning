package leetcode;

import java.util.HashSet;

public class PrintMatrix {
 
    public static void main(String[] args){
        int[][] matrix ={{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        HashSet<String> visited = new HashSet<String>();
        System.out.println(String.format("rowNum:%s,colNum:%s", rowNum,colNum));
        printMatrix(matrix, rowNum, colNum, new int[]{0,0}, visited); 
    }
    
    public static void printMatrix(int[][] matrix,int rowNum, int colNum,int[] start,HashSet<String> visited){
        int[] righttop={0,0};
        int[] down={0,0};
        int[] leftbottom={0,0};
        
        if(visited.contains(start[0]+""+start[1])){
            return;
        }
        for(int i=start[1];i<colNum;i++){
            if(visited.contains(start[0]+""+i)){
                break;
            }
            System.out.println(matrix[start[0]][i]);
            visited.add(start[0]+""+i);
            righttop[0] = start[0];
            righttop[1] = i;
        }
        
        for(int i=righttop[0]+1,j=righttop[1]-1;i<rowNum && j>=0;i++ ,j--){
            if(visited.contains(i+""+j)){
                break;
            }
            System.out.println(matrix[i][j]);
            visited.add(i+""+j);
            down[0] = i;
            down[1] = j;
        }
        
        for(int i=down[1]-1;i>=0;i--){
            if(visited.contains(down[0]+""+i)){
                break;
            }
            System.out.println(matrix[down[0]][i]);
            visited.add(down[0]+""+i);
            leftbottom[0] = down[0];
            leftbottom[1] = i;
        }
        
        for(int i=leftbottom[0]-1;i>=0;i--){
            if(visited.contains(i+""+leftbottom[1])){
                break;
            }
            System.out.println(matrix[i][leftbottom[1]]);
            visited.add(i+""+leftbottom[1]);
            start[0] = i;
            start[1] = leftbottom[1];
        }
        start[1] = start[1]+1;
        printMatrix(matrix, rowNum, colNum, start, visited);
    }
}

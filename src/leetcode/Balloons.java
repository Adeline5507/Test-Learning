package leetcode;

//思路是通过最后一个爆炸的气球把数组分作两半，进行递归
public class Balloons {

    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] myNums = new int[n];
        int index = 1;
        for (int nn : nums) {
            myNums[index++] = nn;
        }
        myNums[0] = 1;
        myNums[n - 1] = 1;
        int[][] values = new int[n][n];

        return burstBalloons(values,myNums,0,n-1);

    }

    private int burstBalloons(int[][] values,int[] nums,int left,int right){
        if(left+1==right) return 0;
        if(values[left][right]>0) return values[left][right];
        
        for(int i=left+1;i<right;i++){
            values[left][right] = Math.max(values[left][right], 
                    nums[left]*nums[i]*nums[right]+burstBalloons(values,nums,left,i)+burstBalloons(values,nums,i,right));
        }
        return values[left][right];
    }

    public static void main(String[] args) {

    }

}

//nums[left]*nums[i]*nums[right]+values[left][i]+values[i][right]
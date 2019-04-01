/**
 * Glass Falling
 */
public class GlassFalling {
    
    static int [][] glassDrops;
    // Do not change the parameters!
    public int glassFallingRecur(int floors, int sheets) {
        // Fill in here and change the return
        // This is the base value where if the floors is 0 or 1 the attempt can either be zero or one
        if(floors==1 || floors==0){
            return floors;
        }
        // This is the base case where there is only one sheet you can only start from the lowest to highest
        if(sheets==1){
            return floors;
        }
        
        int minDrops = Integer.MAX_VALUE;
        int result;
        for(int x=1; x<= floors; x++ ){
            result = Math.max(glassFallingRecur(x-1, sheets-1), glassFallingRecur(floors-x,sheets));
            minDrops = Math.min(result,minDrops);
        }
        return minDrops+1;
    }
    
    // Optional:
    // Pick whatever parameters you want to, just make sure to return an int.
    public int glassFallingMemoized(int floors, int sheets, int glassDrops[][]) {
        // Fill in here and change the return
        // This is the base value where if the floors is 0 or 1 the attempt can either be zero or one
        if(floors==1 || floors==0){
            return floors;
        }
        // This is the base case where there is only one sheet you can only start from the lowest to highest
        if(sheets==1){
            return floors;
        }
        if(glassDrops[sheets][floors]!=0) {
            return glassDrops[sheets][floors];
        }else {
            
            int minDrops = Integer.MAX_VALUE;
            int result;
            for(int x=1; x<= floors; x++ ){
                result = Math.max(glassFallingMemoized(x-1, sheets-1, glassDrops), glassFallingMemoized(floors-x,sheets,glassDrops));
                minDrops = Math.min(result,minDrops);
                glassDrops[sheets][floors]=minDrops+1;
                
            }
            return minDrops+1;
        }
        
    }
    
    
    // Do not change the parameters!
    public int glassFallingBottomUp(int floors, int sheets) {
        // Fill in here and change the return
        int [][] glassDrops = new int [sheets+1][floors+1];
        //This is the base case for if the floor is either 0 or 1 then the attempts will only be 0 and 1
        for (int i = 1; i <=sheets ; i++) {
            glassDrops[i][0] = 0;
            glassDrops[i][1] = 1;
        }
        //This is the base case for if there is only one sheet which will lead to dropping from lowest to highest
        for (int i = 1; i <=floors ; i++) {
            glassDrops[1][i] = i;
        }
        
        for (int i = 2; i <=sheets ; i++) {
            for (int j = 2; j <=floors ; j++) {
                glassDrops[i][j] = Integer.MAX_VALUE;
                int result;
                for (int k = 1; k <=j ; k++) {
                    result = 1 + Math.max(glassDrops[i-1][k-1], glassDrops[i][j-k]);
                    glassDrops[i][j] = Math.min(result,glassDrops[i][j]);
                }
            }
        }
        // glassDrops[sheets][floors] will have the result : minimum number of drops required in worst case
        return glassDrops[sheets][floors];
    }
    public static void main(String args[]){
        GlassFalling gf = new GlassFalling();
        glassDrops= new int [10][101];
        // Do not touch the below lines of code, and make sure
        // in your final turned-in copy, these are the only things printed
        int minTrials1Recur = gf.glassFallingRecur(27, 2);
        int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
        int minTrials2Recur = gf.glassFallingMemoized(100, 3,glassDrops);
        int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
        System.out.println(minTrials1Recur + " " + minTrials1Bottom);
        System.out.println(minTrials2Recur + " " + minTrials2Bottom);
    }
}

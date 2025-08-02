import java.math.BigInteger;
import java.util.Random;

public class MyRandom extends Random {
    /* Your code here */
    //private final long a= 10002;
   // private long b= 0;
    private final long m= 1073741827;
    private long seed;
    //private BigInteger seed;

    public MyRandom(long seed){
        setSeed(seed);
    }


    @Override
    public int next(int bits) {
//        long oldS; long newS;
//        oldS=seed;
//        newS=(a*oldS + b)%m;
//        seed=newS;
//        long pos=1;
//        long num=pos<<bits;
//        num-=1;
//
//        return (int) ((int) newS & num);

        BigInteger originalKey=new BigInteger(String.valueOf(seed));
        byte[] key=originalKey.toByteArray();

        int[] S = new int[256];
        for (int i=0;i<256;i++){
            S[i]=i;
        }
        int j=0;
        for(int i=0; i<256; i++){
            j = (j + S[i] + (key[i % key.length] & 0xFF)) % 256;
            swap(S,i, j);
        }

        int i=0;
        j=0;

            i=(i+1)% 256;
            j=(j+S[i])% 256;
            swap(S,i, j);
            int K=S[(S[i]+S[j])% 256];
            return K;

    }

    @Override
    public void setSeed(long seed){
        this.seed=seed%m;
    }

//    public static void swap(int a, int b){
//        int temp;
//        temp=a;
//        a=b;
//        b=temp;
//    }
public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}


}


import java.util.Random;

public class MyRandom extends Random {
	/* Your code here */
    private final long a= 2;
    private long b= 0;
    private final long m= 1073741827;
    private long seed;

    public MyRandom(long seed){
        setSeed(seed);
    }


    @Override
    public int next(int bits) {
        long oldS; long newS;
        oldS=seed;
        newS=(a*oldS + b)%m;
        seed=newS;
        long pos=1;
        long num=pos<<bits;
        num-=1;

        return (int) ((int) newS & num);

    }

    @Override
    public void setSeed(long seed){
        this.seed=seed % m;
    }


}

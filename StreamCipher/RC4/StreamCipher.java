import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.*;
import java.lang.System;

public class StreamCipher {
    /* Your code here */

    public static void encryptDecrypt(String key, byte[] inputBytes, File outputFile) throws IOException {
        // XOR key with input and write to output file
        BigInteger seed = new BigInteger(key);

        FileOutputStream o= new FileOutputStream(outputFile);
        // turn the input into bytes
        byte[] outputBytes = new byte[inputBytes.length];

        MyRandom prng = new MyRandom(seed.longValue());



        for(int i=0;i<inputBytes.length;i++) {
            //do XOR
            outputBytes[i]=(byte) (inputBytes[i]^prng.next(8));
        }
        o.write(outputBytes);
        o.close();

    }


    public static void main(String[] args) throws IOException {
        if(args.length!=6){
            System.out.println("Incorrect input, try with all 6 parameters");
            System.exit(1);
        }

        String key =args[1];

        if(key==null){
            System.out.println("Invalid key");
            System.exit(1);
        }

        File inputTest=new File(args[3]);
        if(!inputTest.exists()){
            System.err.println("Input file can't be found");
            System.exit(1);
        }

        FileInputStream input=new FileInputStream(args[3]);
        byte[] inputBytes= input.readAllBytes();
        input.close();


        String outputFile=args[5];
        File outputTest=new File(outputFile);
        File output=new File(outputFile);

        if(!output.exists()){
            output.createNewFile();
        }

        if(!outputTest.exists()){
            System.err.println("Output file can't be created");
            System.exit(1);
        }


        encryptDecrypt(key, inputBytes,output);
        System.exit(0);
    }


}


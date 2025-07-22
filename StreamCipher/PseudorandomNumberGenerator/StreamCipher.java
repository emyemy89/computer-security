
import java.io.*;
import java.util.Random;
import java.util.*;
import java.lang.System;

public class StreamCipher {
    /* Your code here */

    public static void encryptDecrypt(String key, byte[] inputBytes, File outputFile) throws IOException {
        // XOR key with input and write to output file
        long seed=Long.parseLong(key);
        MyRandom prng=new MyRandom(seed);
        FileOutputStream o= new FileOutputStream(outputFile);

        // turn the input into bytes
        byte[] outputBytes = new byte[inputBytes.length];

        for(int i=0;i<inputBytes.length;i++) {
            //do XOR
            outputBytes[i]=(byte) (inputBytes[i]^prng.nextInt(256));
        }
        o.write(outputBytes);
        o.close();

    }


    public static void main(String[] args) throws IOException {
        if(args.length!=6){
            System.out.println("Incorrect input, try with all 6 parameters");
            System.exit(1);
        }

        if (!args[0].equals("--key") || !args[2].equals("--in") || !args[4].equals("--out")) {
            System.err.println("May you use the correct order please");
            System.exit(1);
        }

        String key =args[1];

        if(key==null){
            System.out.println("Invalid key");
            System.exit(1);
        }

        //check correct format for key
        try {
            long keyValue = Long.parseUnsignedLong(key);
        } catch (NumberFormatException e) {
            System.err.println("Please provide a correct key format");
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

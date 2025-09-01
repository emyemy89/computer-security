import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class PasswordCrack {
  

  /** 
   * Method for mangling words
   * mangleWord() takes a 'currentWord' parameter and computes uncomplicated mangles
   * The results are used in the beginning of the program, given the short time to compute
  */ 
  public static List<String> mangleWord(String currentWord){
    List<String> mangledWords=new ArrayList<>();
    if (currentWord == null) {
      return mangledWords;
    }
    //delete first/ last char
    if (currentWord.length()>1) {
      mangledWords.add(currentWord.substring(1));
      mangledWords.add(currentWord.substring(0,currentWord.length()-1));
    } 
    //reverse the word
    mangledWords.add(new StringBuilder(currentWord).reverse().toString());
    //duplicate the word
    mangledWords.add(currentWord+currentWord);
    //reflected duplicate
    mangledWords.add((new StringBuilder(currentWord).reverse().toString())+ currentWord);
    //uppercase
    mangledWords.add(currentWord.toUpperCase());
    //lowercase
    mangledWords.add(currentWord.toLowerCase());
    // capitalize first letter
    mangledWords.add(currentWord.substring(0,1).toUpperCase()+ currentWord.substring(1));
    // nCAPITALIZE first letter
    mangledWords.add(currentWord.substring(0,1).toLowerCase()+ currentWord.substring(1).toUpperCase());

    return mangledWords;
  }

  static String cleverMangle;
  /** 
   * Method for mangling words
   * cleverMangles() takes a 'currentWord' parameter and computes the complicated/ time exhaustive mangles
   * The results of this method are used later in the program, given the longer time to compute
  */ 
  public static List<String> cleverMangles(String currentWord){
    List<String> mangledWords=new ArrayList<>();
    if (currentWord == null) {
      return mangledWords;
    }
    //prepend & append digits
    for(int i=0;i<10;i++){
      mangledWords.add(i+currentWord);
      mangledWords.add(currentWord+i);
    }
    //append and prepend letters
    String letters="qwertyuiopasdfghjklzxcvbnm";
    for(char c: letters.toCharArray()){
      mangledWords.add(currentWord+c);
      mangledWords.add(c+currentWord);
    }
    //toggle case
    StringBuilder toggledWord1=new StringBuilder(currentWord.length());
    StringBuilder toggledWord2=new StringBuilder(currentWord.length());
    for(int i=0;i<currentWord.length();i++){
      char letter= currentWord.charAt(i);
      if(i%2 == 0){
        toggledWord1.append(Character.toLowerCase(letter));
        toggledWord2.append(Character.toUpperCase(letter));
      }else{
        toggledWord1.append(Character.toUpperCase(letter));
        toggledWord2.append(Character.toLowerCase(letter));
      }
    }
    mangledWords.add(toggledWord1.toString());
    mangledWords.add(toggledWord2.toString());


    return mangledWords;
  }



  /** 
   * Method for finding colliding hashes
   * getOriginalDict() takes a 'currentWord' and 'setOfHashes' parameters and tries to find collisions
   * For the given word, a hash is computed and checked for matching collision
  */ 
  public static void getOriginalDict(String currentWord, HashSet<String> setOfHashes){
    for(String j : setOfHashes){   
      //take salt of password
      String salt=j.substring(0,2);
      String hash = jcrypt.crypt(salt,currentWord);
      if(hash.equals(j)){
        System.out.println(currentWord);
        // stop searcing for this user
        setOfHashes.remove(j);
        break;
      }
    }
  }


  /** 
   * Method for finding colliding hashes
   * getMangledOnce() takes a 'currentWord' and 'setOfHashes' parameters and tries to find collisions
   * For the given word, we first find its first-level mangles using mangleWord()
   * Then a hash is computed and checked for matching collision
  */ 
  public static void getMangledOnce(String currentWord, HashSet<String> setOfHashes){
        for(String words: mangleWord(currentWord)){
          for(String j : setOfHashes){     
            //take salt of password
            String salt=j.substring(0,2);
            String hash = jcrypt.crypt(salt,words);
            if(hash.equals(j)){
              System.out.println(words);
              setOfHashes.remove(j);
              break;
            }
          }
        }
  }


  /** 
   * Method for finding colliding hashes
   * getMangledOnce() takes a 'currentWord' and 'setOfHashes' parameters and tries to find collisions
   * For the given word, we first find its first-level mangles by using cleverMangles()
   * Then a hash is computed and checked for matching collision
  */ 
  public static void getMangledOnceCLEVER(String currentWord, HashSet<String> setOfHashes){
        for(String words: cleverMangles(currentWord)){
          for(String j : setOfHashes){
            
            //take salt of password
            String salt=j.substring(0,2);
            String hash = jcrypt.crypt(salt,words);
  
            if(hash.equals(j)){
              System.out.println(words);
              setOfHashes.remove(j);
              break;
            }
          }
        }    
  }

  

  /** 
   * Method for finding colliding hashes
   * getMangledTwice() takes a 'mangleWords' and 'setOfHashes' parameters and tries to find collisions
   * For the given list of words, we first find their second-level mangles by using mangleWord()
   * Then a hash is computed and checked for matching collision
  */ 
  public static void getMangledTwice(List<String> mangledWords, HashSet<String> setOfHashes){
    for(String word: mangledWords){
      List<String> moreMangledWords=mangleWord(word);    
      for (String doubleMangles : moreMangledWords){
        for(String j: setOfHashes){       
          String salt=j.substring(0,2);
          String hash = jcrypt.crypt(salt,doubleMangles);
          if(hash.equals(j)){
            System.out.println(doubleMangles);
            // stop searcing for this user
            setOfHashes.remove(j);
            break;
          }
        }
      }
    }
  }


  /** 
   * Method for finding colliding hashes
   * getMangledTwice() takes a 'mangleWords' and 'setOfHashes' parameters and tries to find collisions
   * For the given list of words, we first find their second-level mangles by using cleverMangles()
   * Then a hash is computed and checked for matching collision
  */ 
  public static void getMangledTwiceCLEVER(List<String> mangledWords, HashSet<String> setOfHashes){
    for(String word: mangledWords){
      List<String> moreMangledWords=cleverMangles(word);   
      for (String doubleMangles : moreMangledWords){
        for(String j: setOfHashes){        
          String salt=j.substring(0,2);         
          String hash = jcrypt.crypt(salt,doubleMangles);
          if(hash.equals(j)){           
            System.out.println(doubleMangles);
            // stop searcing for this user
            setOfHashes.remove(j);
            break;
          }
        }
      }
    }
  }


  /** 
   * Method for mangling words
   * Created for reducing code complexity in 3rd level mangling
   * Represents a merging of mangleWord() and cleverMangle() methods
   * Even though this method seems redundant, it actaully saves creating other 8 while loops in main()
  */ 
  public static List<String> mangleWordCOMPLETE(String currentWord){
    List<String> mangledWords=new ArrayList<>();
    if (currentWord == null) {
      return mangledWords;
    }
    mangledWords.addAll(mangleWord(currentWord));
    mangledWords.addAll(cleverMangles(currentWord));
    return mangledWords;
  }


  /** 
   * Method for finding colliding hashes
   * getMangled3() takes a 'mangleWords' and 'setOfHashes' parameters and tries to find collisions
   * For the given list of words, we first find their third-level mangles by using mangleWordCOMPLETE()
   * Then a hash is computed and checked for matching collision
  */ 
  public static void getMangled3(List<String> mangledWords, HashSet<String> setOfHashes){
    for(String word: mangledWords){
      List<String> doublyMangled=mangleWordCOMPLETE(word);
      for(String word1: doublyMangled){
        List<String> triplyMangled=mangleWordCOMPLETE(word1);
        for(String tripleWord: triplyMangled){
          for(String j: setOfHashes){      
            String salt=j.substring(0,2);         
            String hash = jcrypt.crypt(salt,tripleWord);
            if(hash.equals(j)){         
              System.out.println(tripleWord);
              // stop searcing for this user
              setOfHashes.remove(j);
              break;
            }
          }
        }
      }
    }
  }


  

  public static void main(String[] args) {


    if(args.length !=2){
      System.err.println("Please provide the correct arguments needed.");
      System.exit(1);
    }

    File dict=new File(args[0]);
    if(!dict.exists() || !dict.canRead()){
        System.err.println("There is no dictionary, or it can't be read");
        System.exit(1);
    }

    File passwordFile=new File(args[1]);
    if(!passwordFile.exists()|| !passwordFile.canRead()){
        System.err.println("There is no password file or it can't be read");
        System.exit(1);
    }

    try {
      BufferedReader dictionary= new BufferedReader(new FileReader(dict));
      BufferedReader password= new BufferedReader(new FileReader(passwordFile));
      String currentPassword;
      String currentWord;



      //store all hashes to search for duplicates found later
      HashSet<String> setOfHashes=new HashSet<>();
      HashSet<String> nameEntries=new HashSet<>();

      while((currentPassword=password.readLine() )!= null){
        String[] fields=currentPassword.split(":");
        setOfHashes.add(fields[1]);
        nameEntries.add(fields[4]);
      }
      password.close();

      
      
      /** 
      * Easy Guesses 
      */ 
      List<String> commonWords=new ArrayList<>();
      commonWords.add("123456");
      commonWords.add("12345678");
      commonWords.add("1234");
      commonWords.add("00000000");
      commonWords.add("111111");
      commonWords.add("12121212");
      commonWords.add("55555555");
      commonWords.add("77777777");
      commonWords.add("88888888");
      commonWords.add("123456a");
      commonWords.add("11223344");
      commonWords.add("147258369");
      commonWords.add("102030");

      commonWords.add("qwerty");
      commonWords.add("abc123");
      commonWords.add("1qaz2wsx");
      commonWords.add("letmein");
      commonWords.add("login");
      commonWords.add("qwertyuiop");
      commonWords.add("passw0rd");
      commonWords.add("starwars");
      commonWords.add("iloveyou");
      commonWords.add("abc123");
      commonWords.add("1q2w3e4r");
      commonWords.add("admin");
      commonWords.add("qazwsx");
      commonWords.add("lovely");
      commonWords.add("secret"); 
      commonWords.add("target123"); 
      commonWords.add("asdfghjk");
      commonWords.add("zxcvbnm");
      commonWords.add("abcd1234");
      commonWords.add("azerty");
      commonWords.add("dearbook");
      commonWords.add("asdfgh");
      commonWords.add("unknown");
      commonWords.add("pokemon");
      commonWords.add("asdasd");
      commonWords.add("tinkle");
      commonWords.add("zag12wsx");
      commonWords.add("guerty");
      commonWords.add("Querty");
      commonWords.add("azerty");
      commonWords.add("que123");
      commonWords.add("1234qwer");
      

      commonWords.add("!@#$%^&*");
      commonWords.add("p@ssw0rd");
      commonWords.add("querty1!");


      // Name scraping from passwd file
      for(String i: nameEntries){
        //remove spaces between names
        String[] names = i.split("\\s+");
        for(String j: names){
          String name= j.toLowerCase().replace(".","");
          if (name.length()>1) {
            commonWords.add(name);
          }
        }
      }


      // I:
      // easy guesses
      int i=0;
      while (i < commonWords.size()) {
        currentWord = commonWords.get(i);
       
        getOriginalDict(currentWord, setOfHashes);
        getMangledOnce(currentWord, setOfHashes);
        List<String> mangledWords = mangleWord(currentWord);
        List<String> cleverMangledWords = cleverMangles(currentWord);

        getMangledTwice(mangledWords, setOfHashes);

        getMangledOnceCLEVER(currentWord,setOfHashes);
        getMangledTwiceCLEVER(mangledWords,setOfHashes);
        getMangledTwiceCLEVER(cleverMangledWords,setOfHashes);
        //getMangled3(mangledWords,setOfHashes);

        i++;
      }

      
      // II:
      // simple dict + Simple Mangles
      while((currentWord =dictionary.readLine()) !=null){
        getOriginalDict(currentWord, setOfHashes);
        getMangledOnce(currentWord, setOfHashes);
        
       password.close();
      }
      dictionary.close();


      // III:
      // complicated Single Mangles
      dictionary= new BufferedReader(new FileReader(dict));
      while((currentWord =dictionary.readLine()) !=null){
        getMangledOnceCLEVER(currentWord,setOfHashes);
      }
      dictionary.close();


      // IV:
      // double simple mangles  from single simple mangles
      dictionary= new BufferedReader(new FileReader(dict));
      while((currentWord =dictionary.readLine()) !=null){
        List<String> mangledWords = mangleWord(currentWord);
        getMangledTwice(mangledWords, setOfHashes);
      }
      dictionary.close();

      
      // V:
      // double simple mangles cleverMangles
      dictionary= new BufferedReader(new FileReader(dict));
      while((currentWord =dictionary.readLine()) !=null){
        List<String> cleverMangledWords = cleverMangles(currentWord);
        getMangledTwice(cleverMangledWords, setOfHashes);
      }
      dictionary.close();


      // VI:
      // double clever mangles from simple single mangles
      dictionary= new BufferedReader(new FileReader(dict));
      while((currentWord =dictionary.readLine()) !=null){
        List<String> mangledWords = mangleWord(currentWord);
        getMangledTwiceCLEVER(mangledWords,setOfHashes);
      }
      dictionary.close();


      // VII:
      // double clever mangles from clever single mangles
      dictionary= new BufferedReader(new FileReader(dict));
      while((currentWord =dictionary.readLine()) !=null){
        List<String> cleverMangledWords = cleverMangles(currentWord);
        getMangledTwiceCLEVER(cleverMangledWords,setOfHashes);
      }
      dictionary.close();


      // VIII:
      // triple mangles
      dictionary= new BufferedReader(new FileReader(dict));
      while((currentWord =dictionary.readLine()) !=null){
        List<String> mangledWords = mangleWordCOMPLETE(currentWord);
        getMangled3(mangledWords,setOfHashes);
      }
      dictionary.close(); 

    } catch (IOException e) {
      System.err.println("Problems with reading the dictionary");
      System.exit(1);
    }


  }
}

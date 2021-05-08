
package project_5wc_hill;
import java.util.Scanner;

public class Project_5WC_HILL {

 static int letter = 0; 
    private static Scanner IS;

    // MADE BY IT-PNU ... NFM-IT
    
    public static void main(String[] args){
 
        
         IS = new Scanner(System.in);

    
     System.out.println("  -----------------------------------  "
                      + "\n | Welcome to our HCX CIPHER Program |"
                      + "\n  -----------------------------------  "
                      + "\n\n  HCX cipher ruls:"
                      + "\n\n - Use letters in Plaintext"
                      + "\n - Use letters in XOR key "
                      + "\n - Use numbers in Shift Key (1 to 26)"
                      + "\n"
                     + "\n\n\b* this program made by IT-PNU student");
   
     
    
    
     
     
    
                        boolean quit=false; //to make switch                  
do{
     

     System.out.print("\n\n------------------"
                        + "\n   Our Services:"
                        +"\n\n 1.  Encryption "
                        +"\n 2.  Decryption "
                        +"\n 3.  Exit"
                        + "\n------------------");
           System.out.print("\nEnter Your choice: ");
           int menu=IS.nextInt();
             IS.nextLine();          
          
      
        switch (menu) {
      case 1:     
          System.out.print("\nEnter the PlainText: ");
          String PT=IS.nextLine();
            PT = PT.replaceAll("\\s" , "");//remove space
            PT = PT.toUpperCase();
            
          System.out.print("\nEnter XOR key: ");
          String Pkeyxor = IS.nextLine();
        
         
          System.out.print("\nEnter shift Key : ");
          int Pshift = IS.nextInt();
          
          
        String PKey="HILL";
          

        cipherEncryption(PT,Pshift,PKey,Pkeyxor);
        break;

        
      case 2:
          System.out.print("\nEnter the CipherText: ");
          String CT=IS.nextLine();
          CT = CT.replaceAll("\\s" , "");//remove space
          CT = CT.toUpperCase();
          
          System.out.print("\nEnter XOR key: ");
          String Ckeyxor = IS.nextLine();
          
          System.out.print("\nEnter shift Key: ");
          int Cshift = IS.nextInt();
          
          String CKey="HILL";
          
          
          cipherDecryption(CT,Cshift, CKey,Ckeyxor);
       
        break; //to stop

        
      case 3:
              quit = true;
        break; 
        
      default: //if user select another number
          System.out.println("Error no such a service"); }
} while(!quit);  }
    
        
    
  
    
 static void cipherEncryption(String PlainText, int Pshift, String PKey, String keyxor) {
     

        // if irregular length, then perform padding
        int lenChk = 0;
        if (PlainText.length() % 2 != 0){// not even
            PlainText += '@'; }
           
        
   
        //Caesar Method
         String ciphertext = "";
        char alphabet;
        for(int i=0; i < PlainText.length();i++) 
        {
             // Shift one character at a time
            alphabet = PlainText.charAt(i);
         
            // if alphabet lies between 'A'and 'Z'
             if(alphabet >= 'A' && alphabet <= 'Z') {
             // shift alphabet
             alphabet = (char) (alphabet + Pshift);    
                
             // if shift alphabet greater than 'Z'
             if(alphabet > 'Z') {
                 //reshift to starting position 
                 alphabet = (char) (alphabet+'A'-'Z'-1);
             }
             ciphertext = ciphertext + alphabet;
            }
            else {
             ciphertext = ciphertext + alphabet;  }} 
            
        
    
        
    
          //HILL Method
        // message to matrices
        int[][] PlainText2D = new int[2][ciphertext.length()];
        int CFR1 = 0; //coulm for row 1
        int CFR2 = 0; //coulm for row 2
        for (int i = 0; i < ciphertext.length(); i++){
            if (i%2 == 0){
                PlainText2D[0][CFR1] = ((int)ciphertext.charAt(i)) - 65;    //[N][U][A]   | [13][20][0]
                                                                            //[O][R][H]   | [14][17][7]
                CFR1++;
            } else {
                PlainText2D[1][CFR2] = ((int)ciphertext.charAt(i)) - 65;
                CFR2++;
            } // if-else
        } // for

        
        
        
        

        // key to 2x2 matrix
        int[][] PKey2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                PKey2D[i][j] = (int)PKey.charAt(itr3)-65;
                itr3++; }}
           
        
        
        
        String encrypText = "";
        int itrCount = ciphertext.length() / 2;
        if (lenChk == 0){
            // if msg length % 2 = 0
            for (int i = 0; i < itrCount; i++) {
                int temp1 = PlainText2D[0][i] * PKey2D[0][0] + PlainText2D[1][i] * PKey2D[0][1];
                encrypText += (char)((temp1 % 26) + 65);
                int temp2 = PlainText2D[0][i] * PKey2D[1][0] + PlainText2D[1][i] * PKey2D[1][1];
                encrypText += (char)((temp2 % 26) + 65);
            }}
        
        //XOR Method
        String encrypHexa = "";
        int keyItr = 0;
        for (int i = 0; i < encrypText.length(); i++) {
            // XOR Operation
            int temp = encrypText.charAt(i) ^ keyxor.charAt(keyItr);

            encrypHexa += String.format("%02x", (byte)temp);
            keyItr++;
            if(keyItr >= keyxor.length()){
                // once all of key's letters are used, repeat the key
                keyItr = 0; }}
         
        
        System.out.println("\nEncrypted Text: " + encrypHexa); }
  

        
 
 
 
 
 
 
    
    private static void cipherDecryption(String CipherText, int Cshift, String CKey,String Ckeyxor ) {
       
        
       int lenChk = 0;
      
        String hexToDeci = "";
            //49204c split into two characters 49, 20, 4c...
        for (int i = 0; i < CipherText.length()-1; i+=2) {
            // splitting hex into a pair of two
            String output = CipherText.substring(i, (i+2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            hexToDeci += (char)decimal;
        }

        
            //XOR Method
        String decrypTextXor = ""; //NOURA  PROPR
        int keyItr = 0;
        for (int i = 0; i < hexToDeci.length(); i++) {
            // XOR Operation
            int temp = hexToDeci.charAt(i) ^ Ckeyxor.charAt(keyItr);

            decrypTextXor += (char)temp;
            keyItr++;
            if(keyItr >= Ckeyxor.length()){
                // once all of key's letters are used, repeat the key
                keyItr = 0; }}
            
        
        //HILL Method
        // message to matrices
        
        int[][] msg2D = new int[2][decrypTextXor.length()];
        int itr1 = 0;
        int itr2 = 0; //NOURAH 
        for (int i = 0; i < decrypTextXor.length(); i++){
            if (i%2 == 0){                                                 //[N][U][A]
                                                                           //[O][R][H]
                msg2D[0][itr1] = ((int)decrypTextXor.charAt(i)) - 65;
                itr1++;
            } else {
                msg2D[1][itr2] = ((int)decrypTextXor.charAt(i)) - 65;
                itr2++;
            } // if-else
        } // for

        
        
        // key to 2x2 matrix
        int[][] CKey2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                CKey2D[i][j] = (int)CKey.charAt(itr3)-65;
                itr3++;
            }
        }

        // validating the key
        // finding determinant of key matrix
        int deter = CKey2D[0][0] * CKey2D[1][1] - CKey2D[0][1] * CKey2D[1][0];
        deter = moduloFunc(deter, 26);
        
        
        
            /*                  CK
                        [H][I]  |   [11][-8]
                        [L][L]  |  [-11][7]   
            */
        
        /*int deter = CKey2D[0][0] * CKey2D[1][1] - CKey2D[0][1] * CKey2D[1][0];
                    =7*11 - 8*11 = -11
            deter = moduloFunc(deter, 26); -11%26 = 15
            
            */

        // multiplicative inverse of key matrix
        int mulInverse = -1;
        for (int i = 0; i < 26; i++) {
            int tempInv = deter * i;
            if (moduloFunc(tempInv, 26) == 1){
                mulInverse = i;
                break;
            } else {
                continue;
            } // if-else
        } // for

        // adjugate matrix [][]
        // swapping        [][] 
        int swapTemp = CKey2D[0][0];
        CKey2D[0][0] = CKey2D[1][1];
        CKey2D[1][1] = swapTemp;

        // changing signs
        CKey2D[0][1] *= -1;
        CKey2D[1][0] *= -1;

        CKey2D[0][1] = moduloFunc(CKey2D[0][1], 26);
        CKey2D[1][0] = moduloFunc(CKey2D[1][0], 26);

        // multiplying multiplicative inverse with adjugate matrix
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                CKey2D[i][j] *= mulInverse;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                CKey2D[i][j] = moduloFunc(CKey2D[i][j], 26); 
            }
        }

        String decrypText = "";
        int itrCount = decrypTextXor.length() / 2;
        if (lenChk == 0){
            // if msg length % 2 = 0
            for (int i = 0; i < itrCount; i++) {
                int temp1 = msg2D[0][i] * CKey2D[0][0] + msg2D[1][i] * CKey2D[0][1];
                decrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * CKey2D[1][0] + msg2D[1][i] * CKey2D[1][1];
                decrypText += (char)((temp2 % 26) + 65);
            }}
        
        
                   
            /*             PT                              PK
                 [N][U][A]  | [13][20][0]  |       [H][I]  |   [7][8]
                 [O][R][H]  | [14][17][7]  |       [L][L]  |  [11][11]   
            
            13*7 + 14*8
            temp1 =203%26 > 21+65= 86 > V
            C1 PT * R1 Key
            
 i=0:           
            13*11 + 14*11
            temp2 = 297%26 > 11+65=76 > L
             C1 PT * R2 key
            
            ----------
            
            20*7 + 17*8
            temp1 =276%26 > 16+65= 81 > Q
            C2 PT * R1 Key
            
i=2:            
            
            20*11 + 17*11
            temp2 = 407%26 > 17+65=82 > R
            C2 PT * R2 key 
            
            
            
   ----------
            
            0*7 + 7*8
            temp1 =56%26 > 4+65= 69 > E
            C3 PT * R1 Key
            
i=3:            
            
            0*11 + 7*11
            temp2 = 77%26 > 25+65=90 > Z
            C3 PT * R2 key 
                         
            
            */
    
       
       //Caesar Method
        String decryptMessage = "";
        for(int i=0; i < decrypText.length();i++)  

        {
            // Shift one character at a time
            char alphabet = decrypText.charAt(i);
           
                // if alphabet lies between A and Z
             if(alphabet >= 'A' && alphabet <= 'Z')
            {
             // shift alphabet
                alphabet = (char) (alphabet - Cshift);
                
                //shift alphabet lesser than 'A'
                if (alphabet < 'A') {
                    // reshift to starting position 
                    alphabet = (char) (alphabet-'A'+'Z'+1);
                }
                decryptMessage = decryptMessage + alphabet;            
            }
            else 
            {
             decryptMessage = decryptMessage + alphabet; }}            
           
             
        char replace;
        int New =90-Cshift;

             if (Cshift > 0 && Cshift < 11){
              replace=(char)((New % 10) + 80); 
              
              
        char[] c = decryptMessage.toCharArray();
        String new_decryptMessage = "";
    for (int i=c.length-1; i < c.length; i++) {
        if (c[i]==replace) {
            
             for (int J=0; J < c.length-1; J++) 
               new_decryptMessage += c[J];
   System.out.println("\nDecrypted Text: " + new_decryptMessage); }
    
        else  System.out.println("\nDecrypted Text: " + decryptMessage); 

    }}

              
             
              
              else if (Cshift >10 && Cshift <21){
              replace=(char)((New % 10) + 70); 
              
    
              
        char[] c = decryptMessage.toCharArray();
        String new_decryptMessage = "";
    for (int i=c.length-1; i < c.length; i++) {
        if (c[i]==replace) {
            
             for (int J=0; J < c.length-1; J++) 
               new_decryptMessage += c[J];
   System.out.println("\nDecrypted Text: " + new_decryptMessage); }
   else System.out.println("\nDecrypted Text: " + decryptMessage); 

    }}

              
              
              
              
              else if(Cshift >20 && Cshift <27){
              replace=(char)((New % 10) + 60); 

              
        char[] c = decryptMessage.toCharArray();
        String new_decryptMessage = "";
    for (int i=c.length-1; i < c.length; i++) {
        if (c[i]==replace) {
            
             for (int J=0; J < c.length-1; J++) 
               new_decryptMessage += c[J];
System.out.println("\nDecrypted Text: " + new_decryptMessage); }
       else  System.out.println("\nDecrypted Text: " + decryptMessage); 
}}

    
    else
        System.out.println("\nDecrypted Text: " + decryptMessage); }



    // modulo function
    public static int moduloFunc(int a, int b){
        int result = a % b;
        if (result < 0){
            result += b;
        }
        return result; }}
package helpers;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//cette classe n'est pas fonctionnelle, prendre HMAC256.java
/*
public class markle {

    public static String encode_sha256(byte[] data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return byteArrayToHex(hash);
    }

    public static String encode(String key, byte[] data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        System.out.println(data + " " + Hex.encodeHexString(sha256_HMAC.doFinal(data)));
        return Hex.encodeHexString(sha256_HMAC.doFinal(data));
    }

    public static String f(byte[] data) throws Exception {

        return encode("\000", data); //String.valueOf(sb)
    }

    public static String n(byte[] h1, byte[] h2) throws Exception {
        byte[] concatenated = concatarray("".getBytes(),concatarray(h1,h2));
        List<String> linessecret = Collections.emptyList();
        String fileSecret = "C:\\Users\\selen\\Downloads\\tests_v3\\tests\\test_crypto\\merkle\\node_1\\secret";
        linessecret = Files.readAllLines(Paths.get(fileSecret), StandardCharsets.UTF_8);


        StringBuilder sb = new StringBuilder();
        for (byte b : linessecret.get(0).getBytes()) {
            sb.append(String.format("%02X ", b));
        }
        var x = Hex.encodeHexString(concatarray(h1,h2));
        var x2 = byteArrayToHex(concatarray(h1,h2));

        return encode("\001", concatarray(h1,h2));
        //e528c4d99e6177f5841f712a143b90843299a4aa181a06501422d9ca862bd2a5
    }


    public static byte[] concatarray(byte[] a, byte[] b){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        try {
            outputStream.write( a );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.write( b );
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte c[] = outputStream.toByteArray( );

        return c;
    }

    public static Boolean isData (byte[] data, String[] F, String H_sommet_reel, int index, int n) throws Exception {
        String f_frere = "";
        String h_oncle = "";
        String h_sommet = "";
        String h = "";
        // F[n] F[n-1]

        if(index+1 != n) {
            if (index % 2 == 0) {
                f_frere = F[index + 1];
                h = n(hexStringToByteArray(f(data)), hexStringToByteArray(f_frere));

            } else {
                f_frere = F[index - 1];
                h = n(hexStringToByteArray(f_frere), hexStringToByteArray(f(data)));

            }

            if (index >= n / 2) {
                if (index % 2 == 0) {
                    h_oncle = n(hexStringToByteArray(F[index - 2]), hexStringToByteArray(F[index - 1]));
                } else {
                    h_oncle = n(hexStringToByteArray(F[index - 3]), hexStringToByteArray(F[index - 2]));
                }
                h_sommet = n(hexStringToByteArray(h_oncle), hexStringToByteArray(h));

            } else {
                if (index % 2 == 0) {
                    h_oncle = n(hexStringToByteArray(F[index + 2]), hexStringToByteArray(F[index + 3]));
                    h_sommet = n(hexStringToByteArray(h), hexStringToByteArray(h_oncle));

                } else {
                    h_oncle = n(hexStringToByteArray(F[index + 1]), hexStringToByteArray(F[index + 2]));
                    h_sommet = n(hexStringToByteArray(h), hexStringToByteArray(h_oncle));
                }
            }

            if (n % 2 != 0) {
                h_sommet = n(hexStringToByteArray(h_sommet), hexStringToByteArray(F[n - 1]));
            }

            if (h_sommet.equals(H_sommet_reel)) {
                return true;
            } else {
                return false;
            }
        }
        else{
            String F_dern = F[n-1];
            String first_sommet = calculHsommet(n-1,Arrays.copyOfRange(F, 0, n-1));
            h_sommet = n(hexStringToByteArray(first_sommet),hexStringToByteArray(F[index]));
            if (h_sommet.equals(H_sommet_reel)) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    public static String[] calculH(int n, String[] F) {
        String[] H = new String[n-1];
        if(n%2==0){
            for(int j = 1; 2*j<=n;j++){
                try {



                    H[j-1] = n(hexStringToByteArray(F[2*j-2]), hexStringToByteArray(F[2*j-1]));
                    System.out.println(("H "+ String.valueOf(j-1) + " = "  + H[j-1]));

                } catch (Exception e) {
                    break;
                }
            }
        }
        else{
            for(int j = 1; 2*j<n;j++){
                try {
                    H[j-1] = n(hexStringToByteArray(F[2*j-2]), hexStringToByteArray(F[2*j-1]));
                } catch (Exception e) {
                    break;
                }
                System.out.println(("H "+ String.valueOf(j-1) + " = "  + H[j-1]));
                if(2*j+1==n){
                    H[j] = F[n-1];
                    System.out.println("F dernier: " + F[n-1]);
                }
            }
        }
        //System.out.println(("H xx"+ String.valueOf(H.length) + " = "  + H[0]));

        return H;
    }

    public static  String calculHsommet(int n, String[] F) throws Exception {

        String H_sommet = "";
        if(n%2==0){
            for(int k= 0; k<(n/2);k++){
                String[] T = calculH(n, F);
                F = T;
                H_sommet = T[0];
            }
            //calculH(n, F);
        }
        else{
            String F_dern ="";
            for(int k= 0; k<(n+1/2)-1;k++){
                String[] T = calculH(n, F);
                //System.out.println("T "+ String.valueOf(T.length) + " = " + T[0] + " " + T[1]+ " " + T[2] );
                if(k == 0){
                    F_dern = T[T.length-2];
                    F = Arrays.copyOf(T, T.length-2);
                    //System.out.println("F first"+ F.length + " " + F[0] + " " + F[1] );
                    n = n-1;
                    F = T;
                }
                if( k + 1 == (n+1)/2){
                    T[1] = F_dern;
                    System.out.println("H last"+  " " + T[0] + " " + T[1]);
                    String[] Tmp = new String[2];
                    Tmp[0] = T[0];
                    Tmp[1] = T[1];
                    F = Tmp;
                }
                else{
                    F = T;
                }
                if(T[0] != null){
                    H_sommet = T[0];
                }
            }
        }
        return H_sommet;
    }


    public static void main(String [] args) throws Exception {
        // data => type byte[]
        //for d in data:
        // encode(n(d , d+1), d+2)
        // n =  nombre datasets
        int nbr = 15;
        int n = nbr;
        byte[][] D = new byte[n][2056];


        String[] fileName =new String[15];
        List<String> lines = Collections.emptyList();
        for(int i=0;i<15;i++){
            if(i<10){
                fileName[i] = "C:\\Users\\selen\\Downloads\\tests_v3\\tests\\test_crypto\\merkle\\node_5\\data_0" + String.valueOf(i);
                //            fileName[i] = "C:\\Users\\selen\\Downloads\\tests_v3\\tests\\test_crypto\\merkle\\proof_2\\data_01";
            }
            else
                fileName[i] = "C:\\Users\\selen\\Downloads\\tests_v3\\tests\\test_crypto\\merkle\\node_5\\data_" + String.valueOf(i);

        }



        lines = Files.readAllLines(Paths.get(fileName[0]), StandardCharsets.UTF_8);
        List<String> lines1 = Files.readAllLines(Paths.get(fileName[1]), StandardCharsets.UTF_8);
        List<String> lines2 = Files.readAllLines(Paths.get(fileName[2]), StandardCharsets.UTF_8);
        List<String>  lines3 = Files.readAllLines(Paths.get(fileName[3]), StandardCharsets.UTF_8);
        List<String>  lines4 = Files.readAllLines(Paths.get(fileName[4]), StandardCharsets.UTF_8);
        List<String>  lines5 = Files.readAllLines(Paths.get(fileName[5]), StandardCharsets.UTF_8);
        List<String>  lines6 = Files.readAllLines(Paths.get(fileName[6]), StandardCharsets.UTF_8);
        List<String>  lines7 = Files.readAllLines(Paths.get(fileName[7]), StandardCharsets.UTF_8);
        List<String>  lines8 = Files.readAllLines(Paths.get(fileName[8]), StandardCharsets.UTF_8);
        List<String>  lines9 = Files.readAllLines(Paths.get(fileName[9]), StandardCharsets.UTF_8);
        List<String>  lines10 = Files.readAllLines(Paths.get(fileName[10]), StandardCharsets.UTF_8);
        List<String>  lines11 = Files.readAllLines(Paths.get(fileName[11]), StandardCharsets.UTF_8);
        List<String> lines12 = Files.readAllLines(Paths.get(fileName[12]), StandardCharsets.UTF_8);
        List<String> lines13 = Files.readAllLines(Paths.get(fileName[13]), StandardCharsets.UTF_8);
        List<String> lines14 = Files.readAllLines(Paths.get(fileName[14]), StandardCharsets.UTF_8);





        D[0]=(lines.get(0)).getBytes();
        D[1]=(lines1.get(0)).getBytes();
        D[2]=(lines2.get(0)).getBytes();
        D[3]=(lines3.get(0)).getBytes();
        D[4]=(lines4.get(0)).getBytes();
        D[5]=(lines5.get(0)).getBytes();
        D[6]=(lines6.get(0)).getBytes();
        D[7]=(lines7.get(0)).getBytes();
        D[8]=(lines8.get(0)).getBytes();
        D[9]=(lines9.get(0)).getBytes();
        D[10]=(lines10.get(0)).getBytes();
        D[11]=(lines11.get(0)).getBytes();
        D[12]=(lines12.get(0)).getBytes();
        D[13]=(lines13.get(0)).getBytes();
        D[14]=(lines14.get(0)).getBytes();


        String H_sommet = "";
        String[] F = new String[n];
        String[] F_first = new String[n];
        int j = 0;
        for(byte[] data : D){
            System.out.println(data);
            F[j] = f(data);
            F_first[j] = f(data);
            System.out.println("F "+ String.valueOf(j) + " = " + F[j]);
            j++;
        }




        H_sommet = calculHsommet( n, F );
        System.out.println("H_sommet: " + H_sommet);


        String H_sommet2 = "";
        String[] F2 = new String[n];
        String[] F_first2 = new String[n];
        int t = 0;
        for(byte[] data : D){
            F2[t] = f(data);
            F_first2[t] = f(data);
            t++;
        }
        H_sommet2 = calculHsommet( n, F2 );

        if(H_sommet.equals(H_sommet2)){
            System.out.println("TRUE");
        }
        else{
            System.out.println("false'" + H_sommet);
            System.out.println(H_sommet2);

        }






    }
}
}

*/

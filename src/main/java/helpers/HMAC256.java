package helpers;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import static org.apache.commons.codec.binary.Hex.*;




public class HMAC256 {

    //===========SHA256==============================
    public static String encode_sha256(byte[] data) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return byteArrayToHex(hash);
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

    public static String encode(String key, byte[] data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        //we have a problem here !!! TODO
        //return encodeHexString(sha256_HMAC.doFinal(data));
        return(null);
    }

    /*public static byte[] concatarray(byte[] a, byte[] b){
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }*/

    public static byte[] concatarray(byte[] a, byte[] b){
        /*byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);*/
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


    public static String n(byte[] h1, byte[] h2) throws Exception {
        byte[] concatenated = concatarray(h1,h2);
        //return encode("key", concatenated);
        //return encode("\001", concatenated);
        /*if (data == null )
            return encode("\002", concatarray(h1,h2));
        */
        return encode("\001", concatarray(h1,h2));
    }

    public static String f(byte[] data) throws Exception {
        //return encode("key", data);
        return encode("\000", data);
    }

    /*public static String[] calculH(int n, String[] F) {
        String[] H = new String[n-1];
        if(n%2==0){
            for(int j = 1; 2*j<=n;j++){
                try {
                    H[j-1] = n(F[2*j-2].getBytes(), F[2*j-1].getBytes());
                    System.out.println(("H "+ String.valueOf(j-1) + " = "  + H[j-1]));

                } catch (Exception e) {
                    break;
                }
            }
        }
        else{
            for(int j = 1; 2*j<n;j++){
                try {
                    H[j-1] = n(F[2*j-2].getBytes(), F[2*j-1].getBytes());
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
                H_sommet = T[0];
            }
        }
        return H_sommet;
    }*/

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
                if(T[0] != null){   // on update meme si c'est T[0]
                    H_sommet = T[0];
                }
            }
        }
        return H_sommet;
    }



}

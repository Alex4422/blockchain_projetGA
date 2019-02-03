package helpers;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MerkleTest {

    /**
     *
     * @param path
     * @return
     */
    public static byte[] readFile(String path) {
        byte[] content = null;
        try{
            File file = new File(path);
            FileInputStream insputStream = new FileInputStream(file);
            content = new byte[(int) file.length()];
            insputStream.read(content);
            insputStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Fichier introuvable");
        }
        catch(IOException e){
            System.out.println("Probleme d'entree sortie : "+e.getMessage());
        }
        return content;
    }


    /**
     *
     * @throws IOException
     */
    @Test
    public void testMerkle_empty() throws IOException {

        //String hex = SHA256.toHex(readFile("tests/test_crypto/merkle/empty/root"));


        String hex = null;
        try {
            hex = HMAC256.encode("\002",readFile("tests/test_crypto/merkle/empty/root"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/merkle/empty/root_hex");


        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    //dir leaf_0
    /**
     *
     * @throws IOException
     */
    @Test
    public void testMerkle_leaf0() throws IOException {

        String hex = null;
        try {
            hex = HMAC256.encode("\000",readFile("tests/test_crypto/merkle/leaf_0/data_00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/merkle/leaf_0/root_hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    @Test
    public void testMerkle_node5() throws IOException {

        String hex = null;
        String hex2 = null;
        String calculHsommet = null;

        try {

            String[] F = new String[15];
            //byte[] F = HMAC256.concatarray(hex.getBytes(),hex2.getBytes());
            //val F = HMAC256.concatarray(String.valueOf(hex),String.valueOf(hex2));

            for (int i=0;i<15;i++){
                if(i<10){
                    hex = HMAC256.f((readFile("tests/test_crypto/merkle/node_5/data_0"+String.valueOf(i))));
                    F[i] = hex;
                }else{

                    hex = HMAC256.f((readFile("tests/test_crypto/merkle/node_5/data_"+String.valueOf(i))));
                    F[i] = hex;
                }

            }


            /*F[0] = hex;
            F[1] = hex2; */

            calculHsommet = HMAC256.calculHsommet(15,F);

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/merkle/node_5/root_hex");

        assertEquals(calculHsommet,new String(HexaFile, StandardCharsets.UTF_8));
    }

    //dir. proof_5

    @Test
    public void testProof_2() throws IOException {

        String hex = null;
        String hex2, member = null;
        String calculHsommet = null;
        String calculHsommet2 = null;


        try {
            hex = HMAC256.f((readFile("tests/test_crypto/merkle/proof_2/data_00")));
            hex2 = HMAC256.f((readFile("tests/test_crypto/merkle/proof_2/data_01")));
            member = HMAC256.f((readFile("tests/test_crypto/merkle/proof_2/member")));

            String[] F = new String[2];
            F[0] = hex;
            F[1] = hex2;

            calculHsommet = HMAC256.calculHsommet(2,F);

            F[0] = member;
            calculHsommet2 = HMAC256.calculHsommet(2,F);

        } catch (Exception e) {
            e.printStackTrace();
        }


        assertEquals(calculHsommet,calculHsommet2);

    }


    //dir SECP256R1

    @Test
    public void SECP256R1() throws IOException {

        String hex = null;

        try {

            String fileName = "tests/test_crypto/sha256/data";
            hex = HMAC256.encode_sha256((readFile(fileName)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] HexaFile = readFile("tests/test_crypto/sha256/sha256_hex");
        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));

    }

}

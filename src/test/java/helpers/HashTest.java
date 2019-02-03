package helpers;

import org.junit.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;


public class HashTest {

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
     * dir hex
     * @throws IOException
     */
    @Test
    public void testHex_0() throws IOException {

        String hex = SHA256.toHex(readFile("tests/test_crypto/hex/test_0/data"));
        byte[] HexaFile = readFile("tests/test_crypto/hex/test_0/hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    @Test
    public void testHex_1() throws IOException {

        String hex = SHA256.toHex(readFile("tests/test_crypto/hex/test_1/data"));
        byte[] HexaFile = readFile("tests/test_crypto/hex/test_1/hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    @Test
    public void testHex_2() throws IOException {

        String hex = SHA256.toHex(readFile("tests/test_crypto/hex/test_2/data"));
        byte[] HexaFile = readFile("tests/test_crypto/hex/test_2/hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }


    /**
     * dir sha_256
     */
    @Test
    public void testSHA256_data() throws IOException {

        String hex = SHA256.toHex(readFile("tests/test_crypto/SHA256/data"));
        byte[] HexaFile = readFile("tests/test_crypto/SHA256/data_hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    @Test
    public void testSHA256_sha256() throws IOException {

        String hex = SHA256.toHex(readFile("tests/test_crypto/SHA256/sha256"));
        byte[] HexaFile = readFile("tests/test_crypto/SHA256/sha256_hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    /**
     * dir hmac
     */

    //dir invalid
    @Test
    public void testHmac_invalid() throws IOException {

        String hex = null;
        try {
            hex = HMAC256.encode(new String(readFile("tests/test_crypto/hmac/invalid/secret"), StandardCharsets.UTF_8),readFile("tests/test_crypto/hmac/invalid/data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/hmac/invalid/hmac_hex");

        System.out.println("hex :" + hex);

        assertNotEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));

    }

    //dir valid
    @Test
    public void testHmac_valid() throws IOException {

        String hex = null;
        try {
            hex = HMAC256.encode(new String(readFile("tests/test_crypto/hmac/valid/secret"), StandardCharsets.UTF_8),readFile("tests/test_crypto/hmac/valid/data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/hmac/valid/hmac_hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    //dir valid_2
    @Test
    public void testHmac_valid2() throws IOException {

        String hex = null;
        try {
            hex = HMAC256.encode(new String(readFile("tests/test_crypto/hmac/valid_2/secret"), StandardCharsets.UTF_8),readFile("tests/test_crypto/hmac/valid_2/data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/hmac/valid_2/hmac_hex");

        assertEquals(hex,new String(HexaFile, StandardCharsets.UTF_8));
    }

    //dir node
    //question : one test to do ?
    // ok !
    @Test
    public void testHmac_node() throws IOException {

        String hex = null;
        String hex2 = null;
        String calculHsommet = null;

        try {
            hex = HMAC256.f((readFile("tests/test_crypto/hmac/node/data_1")));
            hex2 = HMAC256.f((readFile("tests/test_crypto/hmac/node/data_2")));

            String[] F = new String[2];
            //byte[] F = HMAC256.concatarray(hex.getBytes(),hex2.getBytes());
            //val F = HMAC256.concatarray(String.valueOf(hex),String.valueOf(hex2));


            F[0] = hex;
            F[1] = hex2;

            calculHsommet = HMAC256.calculHsommet(2,F);

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/hmac/node/hmac_node_hex");

        assertEquals(calculHsommet,new String(HexaFile, StandardCharsets.UTF_8));
    }

    @Test
    public void testHmac_node2() throws IOException {

        String hex = null;
        String hex2 = null;
        String calculHsommet = null;

        try {
            hex = HMAC256.f((readFile("tests/test_crypto/hmac/node/leaf_hex_1")));
            hex2 = HMAC256.f((readFile("tests/test_crypto/hmac/node/leaf_hex_2")));

            String[] F = new String[2];
            F[0] = hex;
            F[1] = hex2;

            calculHsommet = HMAC256.calculHsommet(2,F);

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/hmac/node/hmac_node_hex");

        //assertNotEquals(calculHsommet,new String(HexaFile, StandardCharsets.UTF_8));
        assertEquals(calculHsommet,new String(HexaFile, StandardCharsets.UTF_8));
    }

    @Test
    public void testHmac_node3() throws IOException {

        String hex = null;
        String hex2 = null;
        String calculHsommet = null;

        try {
            hex = HMAC256.f((readFile("tests/test_crypto/hmac/node/leaf_hex_1")));
            hex2 = HMAC256.f((readFile("tests/test_crypto/hmac/node/leaf_hex_2")));

            String[] F = new String[2];
            F[0] = hex;
            F[1] = hex2;

            calculHsommet = HMAC256.calculHsommet(2,F);

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] HexaFile = readFile("tests/test_crypto/hmac/node/secret_hex");

        assertNotEquals(calculHsommet,new String(HexaFile, StandardCharsets.UTF_8));
    }
}

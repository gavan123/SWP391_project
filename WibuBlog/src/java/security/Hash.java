package security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

public class Hash {

    private static byte[] getSHA(String input, String hashAlgorithm) throws NoSuchAlgorithmException {
        String[] supportedAlgorithms = {"SHA-256", "SHA-1", "MD5"};
        String defaultAlgorithm = "SHA-256";
        boolean valid = false;
        for (String supportedAlgorithm : supportedAlgorithms) {
            if (hashAlgorithm.equals(supportedAlgorithm)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            hashAlgorithm = defaultAlgorithm;
        }

        MessageDigest md = MessageDigest.getInstance(hashAlgorithm);

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, " 0 ");
        }

        return hexString.toString();
    }

    public static String getHash(String input, String hashAlgorithm) {
        String output = "";
        try {
            output = toHexString(getSHA(input, hashAlgorithm));
        } catch (NoSuchAlgorithmException nsae) {
            System.err.println("Unsupported hash algorithm!");
        }

        return output;
    }
    
    public static String getHash(String input) {
        String output = "";
        try {
            output = toHexString(getSHA(input, "SHA-256"));
        } catch (NoSuchAlgorithmException nsae) {
            System.err.println("Unsupported hash algorithm!");
        }

        return output;
    }
    
}

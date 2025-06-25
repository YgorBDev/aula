import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class GeradorHash {

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String ygor01(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String reversed = new StringBuilder(input).reverse().toString();
        int result = reversed.length() * 31;
        result += (int) reversed.charAt(0);
        result -= (int) reversed.charAt(reversed.length() - 1);
        String resultStr = Integer.toString(result);
        return bytesToHex(resultStr.getBytes(StandardCharsets.UTF_8));
    }

    private static String bytesToHex(byte[] digest) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("\nMenu de Hash Generator:");
            System.out.println("1 - Gerar hash MD5");
            System.out.println("2 - Gerar hash SHA-1");
            System.out.println("3 - Gerar hash SHA-256");
            System.out.println("4 - Gerar hash YGOR-01");
            System.out.println("5 - Fechar o programa");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4) {
                System.out.print("Digite o texto para gerar o hash: ");
                input = scanner.nextLine();

            } else if (opcao == 5) {
                System.out.println("Fechando o programa...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Hash MD5: " + md5(input));
                    break;
                case 2:
                    System.out.println("Hash SHA-1: " + sha1(input));
                    break;
                case 3:
                    System.out.println("Hash SHA-256: " + sha256(input));
                    break;
                case 4:
                    System.out.println("Hash YGOR-01: " + ygor01(input));
                    break;
            }
        }
        scanner.close();
    }
}

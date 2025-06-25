import java.io.*;
import java.security.NoSuchAlgorithmException;

public class AtaqueDicionarioComSalt {

    public static void main(String[] args) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader("banco_hashes.csv"));
        String entrada;
        int linhaNum = 0;

        while ((entrada = csvReader.readLine()) != null) {
            linhaNum++;

            if (linhaNum == 1 && entrada.toLowerCase().contains("usuario")) {
                continue;
            }

            String[] partes = entrada.split(",");
            if (partes.length != 3) {
                System.out.println("Linha inválida no CSV: " + entrada);
                continue;
            }

            String usuario = partes[0].trim();
            String salt = partes[1].trim();
            String hashAlvo = partes[2].trim();

            BufferedReader br = new BufferedReader(new FileReader("dicionario.txt"));
            String linha;
            boolean encontrada = false;

            while ((linha = br.readLine()) != null) {
                String tentativa = linha.trim();
                String hashTentativa = HashUtils.md5(salt + tentativa);

                if (hashTentativa.equalsIgnoreCase(hashAlvo)) {
                    System.out.println("Senha do usuário [" + usuario + "] encontrada: " + tentativa);
                    encontrada = true;
                    break;
                }
            }

            br.close();

            if (!encontrada) {
                System.out.println("Senha do usuário [" + usuario + "]   não encontrada no dicionário.");
            }
        }

        csvReader.close();
    }
}

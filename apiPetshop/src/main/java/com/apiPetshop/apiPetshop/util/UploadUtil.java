package com.apiPetshop.apiPetshop.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    public static boolean fazerUploadImagem(MultipartFile imagem) {

        boolean sucessoUpload = false;
        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();
            try {

                // criação de diretorio de armazemaneto de arquivo
                String pastaUploadImagem = "C:\\Users\\Mauricio Stocker\\Desktop\\Project-Amic-o-testeMAur1\\apiPetshop\\src\\main\\resources\\static\\img\\img-uploads";

                File dir = new File(pastaUploadImagem);

                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // criando o arquivo diretorio

                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();
                System.out.println("armazenado com sucesso" + serverFile.getAbsolutePath());

                System.out.println("foi feito o upload do arquivo" + nomeArquivo + "com sucesso");
            } catch (Exception e) {
                System.out.println("Voce falhou" + nomeArquivo + "=>" + e.getMessage());
            }
        } else {
            System.out.println("voce falhou no arquivo pq esta vazio");

        }
        return sucessoUpload;

    }
}

package org.arthur;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static void main() throws IOException {
        IO.println(String.format("Hello and welcome!"));

        Path path = Paths.get("src", "main", "arquivos", "registros.json");
        File file = path.toFile();

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            // Lê o arquivo JSON e converte para uma árvore de nós (JsonNode)
            JsonNode rootNode = objectMapper.readTree(file);

            // Obtem o corpo util do Json (Vendas)
            JsonNode vendasArray = rootNode.get("vendas");

            if (vendasArray != null && vendasArray.isArray()) {

                // 2. Iterar sobre todos os elementos do array
                System.out.println("--- Lista de Vendas ---");
                for (JsonNode item : vendasArray) {

                    // Lendo os campos de cada objeto
                    String vendedor = item.get("vendedor").asText();
                    double valor = item.get("valor").asDouble();

                    // Aplicando as Regras de Negocio
                    if(valor <= 100){
                        System.out.println("Vendedor: " + vendedor + " | " + "Sem Comissão" + " | " + "Valor da venda R$" + valor);
                    } else if (valor <= 500) {
                        System.out.println("Vendedor: " + vendedor + " | " + "Comissão R$" + String.format("%.2f", valor * 0.01) + " | " + "Valor da venda R$" + valor);
                    }else{
                        System.out.println("Vendedor: " + vendedor + " | " + "Comissão R$" + String.format("%.2f", valor * 0.05) + " | " + "Valor da venda R$" + valor);

                    }
                }
            }


        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        }

    }
}

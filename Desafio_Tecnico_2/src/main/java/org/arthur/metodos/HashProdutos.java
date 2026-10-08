package org.arthur.metodos;

import com.fasterxml.jackson.databind.JsonNode;
import org.arthur.model.Produto;

import java.util.HashMap;

public class HashProdutos {


    private JsonNode Array;
    private HashMap<String, Produto> mapperEstoque = new HashMap<String, Produto>();



    public HashMap<String, Produto> iniciar(JsonNode Array) {
        if (Array != null && Array.isArray()) {

            // Iterar sobre todos os elementos do array
            System.out.println("--- Lista de Produtos em Estoque ---");
            for (JsonNode item : Array) {

                //Adiciona os Produtos do Json em um HashMap usando o codigo unico do produto como chave para identifica-lo
                mapperEstoque.put(item.get("codigoProduto").asText(), new Produto(item.get("codigoProduto").asInt(), item.get("descricaoProduto").asText(), item.get("estoque").asInt()));

                Produto prod = mapperEstoque.get(item.get("codigoProduto").asText());
                System.out.println(prod.getCodigo() + " - " + prod.getDescricao() + " - " + prod.getQuantidade());
                System.out.println("--------------------------------");


            }
        }
        return mapperEstoque;
    }
}

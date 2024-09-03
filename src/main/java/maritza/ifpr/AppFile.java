package maritza.ifpr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppFile{
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        String pergunta = "";
        String respostaCorreta = "";
        String respostaErrada1 = "";
        String respostaErrada2 = "";
        String respostaErrada3 = "";
        String respostaErrada4 = "";
        int qtdePerguntas;
        ArrayList<Questao> questoes = new ArrayList<Questao>();

        File arq = new File("Perguntas.txt");

        System.out.println("Quantas perguntas voce quer fazer?");
        qtdePerguntas = ler.nextInt();
        ler.nextLine(); 

        while (qtdePerguntas > 0) {

            System.out.println("Digite sua pergunta");
            pergunta = ler.next();

            System.out.println("Digite a resposta correta");
            respostaCorreta = ler.next();
            System.out.println("Agora digite as respostas erradas");
            respostaErrada1 = ler.next();
            System.out.println("A segunda resposta");
            respostaErrada2 = ler.next();
            System.out.println("A terceira resposta");
            respostaErrada3 = ler.next();
            System.out.println("E a quarta resposta");
            respostaErrada4 = ler.next();
            String[] respostasErradas = { respostaErrada1, respostaErrada2, respostaErrada3, respostaErrada4 };
            Questao questao = new Questao(pergunta, respostaCorreta, respostasErradas);
            questoes.add(questao);
            qtdePerguntas--;
            System.out.println("passou aqui");
        }

        // System.out.println("Essas sao as respostas: ");
        // System.out.printf("%s ",respostaCorreta);
        // System.out.printf("\n%s ",respostaErrada1);
        // System.out.printf("\n%s ",respostaErrada2);
        // System.out.printf("\n%s ",respostaErrada3);
        // System.out.printf("\n%s ",respostaErrada4);

        try {
            if (arq.createNewFile()) {
                System.out.println("Arquivo criado com sucesso.");
            }

            try (FileWriter questoesFile = new FileWriter(arq, StandardCharsets.UTF_8)) {
                for (Questao questao : questoes) {
                    questoesFile.write(questao.getEnunciado() + "|" +
                            questao.getRespostaCorreta() + "|" +
                            String.join("|", questao.getOutrasAlternativas()) +
                            System.lineSeparator());
                }
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
        ArrayList<Questao> lista = new ArrayList<>();
        File file = new File("Perguntas.txt");
        List<String> listaTexto;
        try {
            listaTexto = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            for (String linha : listaTexto) {
                String[] partes = linha.split("\\|");
                if (partes.length >= 3) {
                    String enunciado = partes[0];
                    String respostaCorretaRead = partes[1];
                    String[] alternativas = new String[partes.length - 2];
                    System.arraycopy(partes, 2, alternativas, 0, alternativas.length);
    
                    Questao questao = new Questao(enunciado, respostaCorretaRead, alternativas);
                    lista.add(questao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Digite a pergunta
        // Digite a resposta correta
        // Digite 4 respostas erradas
        // salve essas informaçoes em arquivos.
        // Bonus: Pergunte ao usuário quantas perguntas ele quer cadastrar,
        // e coloque a opção de digitar 0 para encerrar o cadastro
    }

}

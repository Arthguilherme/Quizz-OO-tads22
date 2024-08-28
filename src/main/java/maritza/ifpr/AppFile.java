package maritza.ifpr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppFile {
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

        try (FileWriter writer = new FileWriter("Perguntas.txt", true)) { // 'true' para adicionar ao final do arquivo,
                                                                          // não sobrescrever
            writer.write("Pergunta: " + pergunta + "\n");
            writer.write(" " + respostaCorreta + "\n");
            writer.write(" " + respostaErrada1 + "\n");
            writer.write(" " + respostaErrada2 + "\n");
            writer.write(" " + respostaErrada3 + "\n");
            writer.write(" " + respostaErrada4 + "\n");

            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ler.close();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Perguntas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
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

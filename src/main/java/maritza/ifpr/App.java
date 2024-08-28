package maritza.ifpr;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application {

    private ControladorQuiz controladorQuiz;

    private VBox root;
    private Scene cena;
    private Text enunciado;
    private Button alternativa1;
    private Button alternativa2;
    private Button alternativa3;
    private Button alternativa4;
    private Button alternativa5;
    private Text resultado;
    private Button reiniciar;
    private Button proxima;

    @Override
    public void init() throws Exception {
        super.init();

        ArrayList<Questao> lista = new ArrayList<>();

        lista.add(new Questao("Qual é o nome do poder da zanpakutou de Barragan Luisenbarn, que manipula a idade? ", "Resupira",
                new String[] { "Toque da Morte", "Envelheça", "Fogo da Morte", "年をとる" }));
        lista.add(new Questao("Qual é o nome da mãe do Ichigo?", "Masaki",
                new String[] { "Kanae", "Tatsuki", "Rangiku", "Mashiro" }));
        lista.add(new Questao("De quem pertence a Zampakutou Ryumon Hakaue? ", "Kensei Muguruma",
                new String[] { "Mashiro Kuma", "Lisa Yadomaru", "Rjuro Otoribashi", "Sentaro Kotsubaki" }));
        lista.add(new Questao("Qual é o nome do grupo de elite entre os Quincy, liderado por Yhwach, que serve como sua guarda pessoal??", "Sternritter",
                new String[] { "Schwertritter", "Eisenritter", "Heilritter", "Ritterstern" }));
        lista.add(new Questao("Qual é o nome do artefato que divide a Soul Society da Terra?", "Oken",
                new String[] { "Korin", "Hogyoku", "Ginto", "Kinki no Sho" }));

        controladorQuiz = new ControladorQuiz(lista);

    }

    @Override
    public void start(Stage stage) throws Exception {
   
        // FXMLLoader FXMLLoader= new FXMLLoader(getClass().getResource("layout.fxml"));
       
        // Parent root = FXMLLoader.load();
       // Scene scene = new Scene(root,400,600);

        inicializaComponentes();
        atualizaComponentes();

        cena = new Scene(root, 700, 500);
        cena.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

         stage.setScene(cena);
         stage.show();

    }

    public void inicializaComponentes() {
        enunciado = new Text("Enunciado");
        alternativa1 = new Button("Questão 1");
        alternativa2 = new Button("Questão 2");
        alternativa3 = new Button("Questão 3");
        alternativa4 = new Button("Questão 4");
        alternativa5 = new Button("Questão 5");

        resultado = new Text("Resultado");
        proxima = new Button("Próxima");
        reiniciar = new Button("Reiniciar Quiz");

        root = new VBox();
        root.getChildren().add(enunciado);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10.0);
        
        root.getChildren().add(alternativa1);
        root.getChildren().add(alternativa2);
        root.getChildren().add(alternativa3);
        root.getChildren().add(alternativa4);
        root.getChildren().add(alternativa5);
        root.getChildren().add(resultado);
        root.getChildren().add(proxima);
        root.getChildren().add(reiniciar);

        alternativa1.setOnAction(respondeQuestao());
        alternativa2.setOnAction(respondeQuestao());
        alternativa3.setOnAction(respondeQuestao());
        alternativa4.setOnAction(respondeQuestao());
        alternativa5.setOnAction(respondeQuestao());
        proxima.setOnAction(proximaQuestao());
        reiniciar.setOnAction(reiniciaQuiz());

        enunciado.getStyleClass().add("titulo");

        alternativa1.getStyleClass().add("botao");
        alternativa2.getStyleClass().add("botao");
        alternativa3.getStyleClass().add("botao");
        alternativa4.getStyleClass().add("botao");
        alternativa5.getStyleClass().add("botao");

        resultado.setVisible(false);
        resultado.getStyleClass().add("titulo");
        proxima.setVisible(false);
        proxima.getStyleClass().add("botao");
        reiniciar.setVisible(false);
        reiniciar.getStyleClass().add("botao");

    }

    public void atualizaComponentes() {

        Questao objQuestao = controladorQuiz.getQuestao();
        ArrayList<String> questoes = objQuestao.getTodasAlternativas();

        enunciado.setText(objQuestao.getEnunciado());

        alternativa1.setText(questoes.get(0));
        alternativa2.setText(questoes.get(1));
        alternativa3.setText(questoes.get(2));
        alternativa4.setText(questoes.get(3));
        alternativa5.setText(questoes.get(4));

    }

    
    private EventHandler respondeQuestao() {
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Button clicado = (Button) event.getSource();
                String alternativa = clicado.getText();

                boolean result = controladorQuiz.respondeQuestao(alternativa);

                if (result) {
                    resultado.setText("uhull!! Acertou");

                }else{
                    resultado.setText("Que pena!! você errou");
                }
                resultado.setVisible(true);
                proxima.setVisible(true);
                alternativa1.setDisable(true);
                alternativa2.setDisable(true);
                alternativa3.setDisable(true);
                alternativa4.setDisable(true);
                alternativa5.setDisable(true);

            }
        };
    }

    private EventHandler proximaQuestao() {
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
               
                alternativa1.setDisable(false);
                alternativa2.setDisable(false);
                alternativa3.setDisable(false);
                alternativa4.setDisable(false);
                alternativa5.setDisable(false);

                if (controladorQuiz.temProximaQuestao()) {
                    controladorQuiz.proximaQuestao();
                    atualizaComponentes();
                }
                else{
                    enunciado.setVisible(false);
                    alternativa1.setVisible(false);
                    alternativa2.setVisible(false);
                    alternativa3.setVisible(false);
                    alternativa4.setVisible(false);
                    alternativa5.setVisible(false);
                    reiniciar.setVisible(true);
                    proxima.setVisible(false);
                    resultado.setText("Acertos: "+controladorQuiz.getAcertos()+"\nErros: "+controladorQuiz.getErros());
                    return;
                }

                resultado.setVisible(false);
                proxima.setVisible(false);

            }
        };
    }

    private EventHandler reiniciaQuiz(){
        return new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                enunciado.setVisible(true);
                alternativa1.setVisible(true);
                alternativa2.setVisible(true);
                alternativa3.setVisible(true);
                alternativa4.setVisible(true);
                alternativa5.setVisible(true);
                controladorQuiz.reiniciar();
                atualizaComponentes();
                reiniciar.setVisible(false);
                resultado.setVisible(false);
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }

}
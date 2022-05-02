package vesala;

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VesalaArena extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private LjudskiIgrac igrac1;
    private KompjuterskiIgrac igrac2;
    private List<Rec> recnik;
    private int naRedu = 0;
    private Rec trazenaRec;

    private void ispisiRecIsprobanaSlova(TextArea taIgra){
        taIgra.clear();
        taIgra.appendText("Trazena rec:\n");
        taIgra.appendText(trazenaRec.pogodjenaSlovaReci());
        taIgra.appendText("\n\n");

        if(!trazenaRec.getIsprobanaSlova().isEmpty()){
            taIgra.appendText("Isprobana slova:\n");
            for (int i=0; i<trazenaRec.getIsprobanaSlova().size(); i++){
                taIgra.appendText(trazenaRec.getIsprobanaSlova().get(i).toString());
                if(i<trazenaRec.getIsprobanaSlova().size()-1)
                    taIgra.appendText(", ");
            }
            taIgra.appendText("\n\n");
        }
    }

    private void ispisiImeIgracaNaRedu(TextArea taIgra){
        taIgra.appendText("Na redu je igrac: ");

        if(naRedu==0)
            taIgra.appendText(igrac1.getIme());
        else
            taIgra.appendText(igrac2.getIme());
    }

    @Override
    public void start(Stage primaryStage) {

        igrac1 = new LjudskiIgrac("Maja", 0, 0, 0);
        igrac2 = new KompjuterskiIgrac("Robot", 9, 24, 36);
        recnik = new ArrayList<>();

        VBox vbRoot = new VBox(10);
        vbRoot.setPadding(new Insets(10,10,10,10));

        Button btReci = new Button("Unesite reci");
        TextArea taReci = new TextArea("");

        HBox hbIgra = new HBox(10);
        Button btIgra =  new Button("Zapocnite igru");
        RadioButton rbLako = new RadioButton("Lako");
        RadioButton rbSrednje = new RadioButton("Srednje");
        RadioButton rbTesko = new RadioButton("Tesko");
        ToggleGroup tg = new ToggleGroup();
        rbLako.setToggleGroup(tg);
        rbSrednje.setToggleGroup(tg);
        rbTesko.setToggleGroup(tg);
        rbTesko.setSelected(true);
        hbIgra.getChildren().addAll(btIgra,rbLako,rbSrednje,rbTesko);

        HBox hbSlovo = new HBox(10);
        Button btSlovo = new Button("Unesi slovo");
        TextField tfSlovo = new TextField("");
        hbSlovo.getChildren().addAll(btSlovo,tfSlovo);

        Button btPotez = new Button("Odigraj potez");
        TextArea taIgra = new TextArea("");

        vbRoot.getChildren().addAll(btReci,taReci,hbIgra,hbSlovo,btPotez,taIgra);

        //---------------------------------------------------------------------------------

        btReci.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                taReci.clear();
                Path path = Paths.get("src/vesala/recnik.txt");
                try {
                    List<String> linije = Files.readAllLines(path);
                    for(String linija: linije){
                        String[] reci = linija.trim().split(",");
                        recnik.add(new Rec(reci[0].trim(), reci[1].trim()));
                    }
                }
                catch (IOException e){
                    System.out.println("Nije uspelo citanje iz datoteke!");
                }

                recnik.sort((rec1,rec2)->{
                    if(rec1.getTezina()!=rec2.getTezina())
                        return Integer.compare(rec1.getTezina(), rec2.getTezina());
                    else
                        return rec1.getRec().compareTo(rec2.getRec());
                });

                for(Rec rec: recnik)
                    taReci.appendText(rec + "\n");
            }
        });


        btIgra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int tezina = 0;
                if(rbLako.isSelected())
                    tezina = 1;
                else if(rbSrednje.isSelected())
                    tezina = 2;
                else
                    tezina = 3;

                int minIndRecnik=0;
                for(int i=0; i<recnik.size(); i++){
                    if(recnik.get(i).getTezina() == tezina){
                        minIndRecnik=i;
                        break;
                    }
                }

                int maxIndRecnik=0;
                for(int i=recnik.size()-1; i>=0; i--){
                    if(recnik.get(i).getTezina() == tezina){
                        maxIndRecnik=i;
                        break;
                    }
                }

                Random random = new Random();
                int randRecInd = minIndRecnik + random.nextInt(maxIndRecnik - minIndRecnik + 1);

                trazenaRec = recnik.get(randRecInd);
                ispisiRecIsprobanaSlova(taIgra);
                ispisiImeIgracaNaRedu(taIgra);
            }
        });

        btSlovo.setOnAction(event -> {
            igrac1.setIzabranoSlovo(tfSlovo.getCharacters().toString().trim().charAt(0));
        });

        btPotez.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Igrac trenutniIgrac;

                if(naRedu == 0){
                    trenutniIgrac = igrac1;
                    naRedu = 1;
                }
                else {
                    trenutniIgrac = igrac2;
                    naRedu = 0;
                }

                char odabranoSlovo = trenutniIgrac.odaberiSlovo(trazenaRec);
                trazenaRec.dodajIsprobanoSlovo(odabranoSlovo);
                ispisiRecIsprobanaSlova(taIgra);

                if(trazenaRec.getRec().indexOf(odabranoSlovo) != -1){
                    taIgra.appendText("Uspesno pronadjeno slovo " + odabranoSlovo + "\n");
                    naRedu = (naRedu+1) %2;
                }

                if(trazenaRec.getRec().equals(trazenaRec.pogodjenaSlovaReci())){
                    taIgra.appendText("Pobedio je " + trenutniIgrac.getIme());
                    return;
                }

                ispisiImeIgracaNaRedu(taIgra);

                if(trazenaRec.getIsprobanaSlova().size() == 6)
                    taIgra.appendText("\n\n" + trazenaRec.getNagovestaj());
            }
        });
        //----------------------------------------------------------------------------------

        Scene sc = new Scene(vbRoot, 570, 450);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Vesala Arena");
        primaryStage.show();

    }
}

package snd;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentsAndDragons extends Application {

    private static List<Heroj> heroji = new ArrayList<>();
    private static boolean ucitaniHeroji = false;

    private static void ucitajLikove(){
        Path path = Paths.get("src/snd/heroji.txt");
        List<String> linije = null;
        try {
            linije = Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        catch (IOException e){
            System.err.println("Neuspelo ucitavanje datoteke heroji.txt");
            return;
        }

        for(String linija: linije){
            String[] reci = linija.split(",");

            String ime = reci[1];
            int iskustvo = Integer.parseInt(reci[2]);
            Nivo nivo = new Nivo(iskustvo);
            Rasa rasa = Rasa.napraviRasu(reci[3]);
            int snaga = Integer.parseInt(reci[4]);
            int spretnost = Integer.parseInt(reci[5]);
            int konstitucija = Integer.parseInt(reci[6]);
            int inteligencija = Integer.parseInt(reci[7]);
            int mudrost = Integer.parseInt(reci[8]);
            int harizma = Integer.parseInt(reci[9]);

            AtributiHeroja atributiHeroja = new AtributiHeroja(snaga, spretnost, konstitucija, inteligencija, mudrost, harizma);

            if(reci[0].equals("r"))
                heroji.add(new HerojRatnik(ime, nivo, rasa, atributiHeroja));
            else if(reci[0].equals("m"))
                heroji.add(new HerojMag(ime, nivo, rasa, atributiHeroja));
            else {
                System.err.println("Nepoznata klasa");
                return;
            }
        }
        ucitaniHeroji = true;
    }

    private static void ispisiLikove(TextArea taIspis, String linija){
        Collections.sort(heroji);
        StringBuilder sb = new StringBuilder();
        for (Heroj h: heroji)
            sb.append(linija).append("\n").append(h.toString()).append("\n").append(linija).append("\n");

        taIspis.setText(sb.toString());
    }

    private static void ispisiRatnike(TextArea taIspis, String linija){
        Collections.sort(heroji);
        StringBuilder sb = new StringBuilder();
        for (Heroj h: heroji) {
            if(!(h instanceof HerojRatnik))
                continue;
            sb.append(linija).append("\n").append(h.toString()).append("\n").append(linija).append("\n");
        }
        taIspis.setText(sb.toString());
    }

    private static void ispisiVilenjake(TextArea taIspis, String linija){
        Collections.sort(heroji);
        StringBuilder sb = new StringBuilder();
        for (Heroj h: heroji) {
            if(h.getRasa() != Rasa.Vilenjak)
                continue;
            sb.append(linija).append("\n").append(h.toString()).append("\n").append(linija).append("\n");
        }
        taIspis.setText(sb.toString());
    }

    private static void ispisiNajjace(TextArea taIspis, String linija){
        Collections.sort(heroji, (h1, h2) -> h1.getAtributiHeroja().getSnaga() - h2.getAtributiHeroja().getSnaga());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<3 && i < heroji.size(); i++) {
            Heroj h = heroji.get(i);
            sb.append(linija).append("\n").append(h.toString()).append("\n").append(linija).append("\n");
        }
        taIspis.setText(sb.toString());
    }


    private static Heroj napraviLika(String ime, char klasa, Rasa rasa){
        AtributiHeroja atributiHeroja = null;
        Nivo nivo = Nivo.napraviNivo1();

        if(klasa=='r'){
            atributiHeroja = AtributiHeroja.pocetniAtributRatnik();
            return new HerojRatnik(ime, nivo, rasa, atributiHeroja);
        }
        else if(klasa=='m'){
            atributiHeroja = AtributiHeroja.pocetniAtributiMag();
            return new HerojMag(ime, nivo, rasa, atributiHeroja);
        }
        else {
            System.err.println("Neocekivani karakter");
            atributiHeroja = AtributiHeroja.pocetniAtributRatnik();
            return new HerojRatnik(ime, nivo, rasa, atributiHeroja);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox hBoxroot = new HBox(10);
        hBoxroot.setPadding(new Insets(10, 10, 10, 10));
        VBox vBoxLevi = new VBox(10);
        VBox vBoxDesni = new VBox(10);
        hBoxroot.getChildren().addAll(vBoxLevi, vBoxDesni);

        //---------------------------------------------------------------

        Label lbIme = new Label("Ime");
        TextField tfIme = new TextField("");

        Label lbKlasa = new Label("Klasa");
        RadioButton rbRatnik = new RadioButton("Ratnik");
        RadioButton rbMag = new RadioButton("Mag");
        ToggleGroup tgKlasa = new ToggleGroup();
        rbRatnik.setToggleGroup(tgKlasa);
        rbMag.setToggleGroup(tgKlasa);
        rbRatnik.setSelected(true);

        Label lbRasa = new Label("Rasa");
        RadioButton rbCovek = new RadioButton("Covek");
        RadioButton rbVilenjak = new RadioButton("Vilenjak");
        RadioButton rbPatuljak = new RadioButton("Patuljak");
        ToggleGroup tgRasa = new ToggleGroup();
        rbCovek.setToggleGroup(tgRasa);
        rbVilenjak.setToggleGroup(tgRasa);
        rbPatuljak.setToggleGroup(tgRasa);
        rbCovek.setSelected(true);

        Button btNapraviLika = new Button("Napravi lika");

        vBoxLevi.getChildren().addAll(lbIme, tfIme, lbKlasa, rbRatnik, rbMag, lbRasa, rbCovek, rbVilenjak, rbPatuljak, btNapraviLika);

        //---------------------------------------------------------------------------

        Button btUcitajLikove = new Button("Ucitaj likove");
        Button btPrikaziNajjace = new Button("Prikazi 3 najjacih");
        Button btPrikaziVilenjake = new Button("Prikazi samo vilenjake");
        Button btPrikaziRatnike = new Button("Prikazi samo ratnike");

        TextArea taIzvestaj = new TextArea("");

        vBoxDesni.getChildren().addAll(btUcitajLikove, btPrikaziNajjace, btPrikaziVilenjake, btPrikaziRatnike, taIzvestaj);

        //---------------------------------------------------------------------------

        String zvezdice = "*******************************************************************";

        btUcitajLikove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!ucitaniHeroji){
                    ucitajLikove();
                    ispisiLikove(taIzvestaj, zvezdice);
                }
            }
        });

        btPrikaziNajjace.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ucitaniHeroji)
                    ispisiNajjace(taIzvestaj,zvezdice);
                else
                    taIzvestaj.setText("Nisu ucitani likovi");
            }
        });

        btPrikaziVilenjake.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ucitaniHeroji)
                    ispisiVilenjake(taIzvestaj,zvezdice);
                else
                    taIzvestaj.setText("Nisu ucitani likovi");
            }
        });

        btPrikaziRatnike.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ucitaniHeroji)
                    ispisiRatnike(taIzvestaj,zvezdice);
                taIzvestaj.setText("Nisu ucitani likovi");
            }
        });

        btNapraviLika.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                char klasa = ' ';
                if(rbRatnik.isSelected())
                    klasa = 'r';
                else if(rbMag.isSelected())
                    klasa = 'm';
                else {
                    System.err.println("Nije selektovana klasa");
                    return;
                }
                Rasa rasa = null;
                if(rbCovek.isSelected())
                    rasa = Rasa.Covek;
                else if(rbVilenjak.isSelected())
                    rasa = Rasa.Vilenjak;
                else if(rbPatuljak.isSelected())
                    rasa = Rasa.Patuljak;
                else{
                    System.err.println("Nije selektovana klasa");
                    return;
                }
                Heroj heroj = napraviLika(tfIme.getText(),klasa, rasa);
                heroji.add(heroj);
                ispisiLikove(taIzvestaj, zvezdice);
            }
        });


        //----------------------------------------------------------------------------

        Scene sc = new Scene(hBoxroot,570, 450);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Students and Dragons - S'n'D");
        primaryStage.show();
    }

}

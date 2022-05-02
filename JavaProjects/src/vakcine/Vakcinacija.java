package vakcine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vakcinacija extends Application {
    private List<Vakcina> vakcine = new ArrayList<>();
    private List<Grad> gradovi = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBoxRoot = new VBox(10);
        vBoxRoot.setPadding(new Insets(10,10,10,10));
        vBoxRoot.setAlignment(Pos.CENTER);

        HBox hBoxTop = new HBox(10);
        TextArea taVakcine = new TextArea("");
        HBox hBoxMiddle = new HBox(10);
        TextArea taIzvestaj = new TextArea("");

        vBoxRoot.getChildren().addAll(hBoxTop, taVakcine, hBoxMiddle, taIzvestaj);

        //--------------------------------------------------------------------------

        Button btUcitaj = new Button("Ucitaj");

        RadioButton rbSortirano = new RadioButton("sortirano");
        RadioButton rbOriginalno =  new RadioButton("originalno");
        ToggleGroup tg = new ToggleGroup();
        rbSortirano.setToggleGroup(tg);
        rbOriginalno.setToggleGroup(tg);
        rbSortirano.setSelected(true);

        Button btIzvestaj = new Button("Izvestaj");

        hBoxTop.setAlignment(Pos.CENTER);
        hBoxTop.getChildren().addAll(btUcitaj, rbSortirano, rbOriginalno, btIzvestaj);

        //------------------------------------------------------------------------------

        Label lbGrad = new Label("Grad");
        TextField tfGrad = new TextField("");
        Label lbJmbg = new Label("JMBG");
        TextField tfJmbg = new TextField("");
        Button btVakcinisi = new Button("Vakcinisi");

        hBoxMiddle.setAlignment(Pos.CENTER);
        hBoxMiddle.getChildren().addAll(lbGrad, tfGrad, lbJmbg, tfJmbg, btVakcinisi);

        //-------------------------------------------------------------------------------

        btUcitaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                taVakcine.clear();
                Path path = Paths.get("src/vakcine/vakcine.txt");

                try {
                    List<String> linije = Files.readAllLines(path);
                    vakcine = new ArrayList<>();

                    for (String l : linije){
                        String[] reci = l.split(",");

                        if(reci.length == 2){
                            Fajzer fajzer = new Fajzer(reci[0], Jacina.izBroja(Integer.parseInt(reci[1].trim())));
                            vakcine.add(fajzer);
                        }
                        else if(reci.length == 3) {
                            SputnjikV sputnjikV = new SputnjikV(reci[0].trim(), reci[1].trim(), Integer.parseInt(reci[2].trim()));
                            vakcine.add(sputnjikV);
                        }
                        else
                            throw new RuntimeException("Datoteka nije u dobrom formatu.");
                    }

                    if(rbSortirano.isSelected()){
                        KomparatorVakcina komparatorVakcina = new KomparatorVakcina();
                        Collections.sort(vakcine, komparatorVakcina);
                    }

                    for(Vakcina v: vakcine)
                        taVakcine.appendText(v.toString() + "\n");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btVakcinisi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String imeGrada = tfGrad.getText();
                String jmbg = tfJmbg.getText();

                if(vakcine.isEmpty())
                {
                    taIzvestaj.appendText("Nema vise vakcina!\n");
                    return;
                }

                Vakcina v = vakcine.remove(0);
                Boolean uspehVakcinacije = v.vakcinisi();

                Grad grad = null;
                for(Grad g: gradovi)
                    if(g.getIme().compareTo(imeGrada)==0)
                        grad = g;
                if(grad == null){
                    grad = new Grad(imeGrada);
                    gradovi.add(grad);
                }

                grad.dodajOsobu(jmbg,uspehVakcinacije);

                taIzvestaj.appendText(imeGrada + ": " + jmbg + " je vakcinisan vakcinom " + v.getIdentifikator() + ".");
                if(uspehVakcinacije)
                    taIzvestaj.appendText("Vakcinacija je uspesna.\n");
                else
                    taIzvestaj.appendText("Vakcinacija nije uspesna.\n");

            }
        });

        btIzvestaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                taIzvestaj.clear();

                for(Grad g: gradovi)
                    taIzvestaj.appendText(g.toString());

                taIzvestaj.appendText("\n" + "Ukupno vakcinisanih: " + Vakcina.broj);
            }
        });

        //--------------------------------------------------------------------------------

        Scene sc = new Scene(vBoxRoot,570, 450);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Vakcinacija");
        primaryStage.show();


    }
}

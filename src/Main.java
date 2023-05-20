/**
 * GUI SET 03 "Sieć GSM"
 *
 * @author Oskar Kalbarczyk s27773 37c
 *
 * @version 0.0
 */

import Graphics.Visualisations.LayerVisual;
import Graphics.Visualisations.VDBvisual;
import Graphics.Visualisations.VRDvisual;
import Graphics.Window;
import Logic.StationType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

//Zakaz używania Timer oraz Executor!!!
//Do użycia: Wyjątki,kolekcje,wątki,wejścia/wyjście itp...
//Tylko standardowa biblioteka Javy!!!



public class Main {

   /* private ArrayList<VDBvisual> vdBvisuals;
    private ArrayList<VRDvisual> vrDvisuals;
    private LinkedList<LayerVisual> layerVisuals;*/
    public static void main(String[] args) {



        SwingUtilities.invokeLater(
                ()-> new Main()
        );



    }

    public Main() {
    /*    vdBvisuals = new ArrayList<>();
        vrDvisuals = new ArrayList<>();
        layerVisuals = new LinkedList<>();

        layerVisuals.add(new LayerVisual(StationType.BTSin));
        layerVisuals.add(new LayerVisual(StationType.BTSout));
        layerVisuals.add(new LayerVisual(StationType.BSC));*/


        Window window = new Window();

    }
}
package IntersectionProject;

import Components.*;
import DataObjects.DataInteger;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Controller_2 {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Controller2";
        pn.SetName("Controller2");
        pn.NetworkPort = 1081;

        DataString ini = new DataString();
        //ini.Printable = false;
        ini.SetName("ini");
        ini.SetValue("red");
        pn.ConstantPlaceList.add(ini);

        DataString red = new DataString();
        //red.Printable = false;
        red.SetName("red");
        red.SetValue("red");
        pn.ConstantPlaceList.add(red);

        DataString green = new DataString();
        //green.Printable = false;
        green.SetName("green");
        green.SetValue("green");
        pn.ConstantPlaceList.add(green);

        DataString yellow = new DataString();
        //yellow.Printable = false;
        yellow.SetName("yellow");
        yellow.SetValue("yellow");
        pn.ConstantPlaceList.add(yellow);

        DataString p1 = new DataString();
        p1.SetName("r0r1");
        p1.SetValue("signal");
        pn.PlaceList.add(p1);

        DataString p2 = new DataString();
        p2.SetName("g0r1");
        pn.PlaceList.add(p2);

        DataString p3 = new DataString();
        p3.SetName("y0r1");
        pn.PlaceList.add(p3);

        DataString p4 = new DataString();
        p4.SetName("r0g1");
        pn.PlaceList.add(p4);

        DataString p5 = new DataString();
        p5.SetName("r0y1");
        pn.PlaceList.add(p5);

        DataTransfer p6 = new DataTransfer();
        p6.SetName("OP0");
        p6.Value = new TransferOperation("localhost", "1080" , "P_I2_TL1");
        pn.PlaceList.add(p6);

        DataTransfer p7 = new DataTransfer();
        p7.SetName("OP1");
        p7.Value = new TransferOperation("localhost", "1080" , "P_I2_TL2");
        pn.PlaceList.add(p7);

        DataString p8 = new DataString();
        p8.SetName("i1");
        pn.PlaceList.add(p8);

        DataString p9 = new DataString();
        p9.SetName("i2");
        pn.PlaceList.add(p9);

        DataInteger p10 = new DataInteger();
        p10.SetName("Ten");
        p10.SetValue(10);
        pn.ConstantPlaceList.add(p10);

        DataInteger p11 = new DataInteger();
        p11.SetName("Five");
        p11.SetValue(5);
        pn.ConstantPlaceList.add(p11);

        // ?@$#$%$%^^%^$#$@#@#!#$%^

        // -------------------------------------------------------------------------------------
        // ----------------------------PN Start-------------------------------------------------
        // -------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}

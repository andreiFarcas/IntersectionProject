package IntersectionProject;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Lanes_Intersection1 {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Lanes Intersection1";

        pn.NetworkPort = 1083;

        DataString green = new DataString();
        green.Printable = false;
        green.SetName("green");
        green.SetValue("green");
        pn.ConstantPlaceList.add(green);

        DataString full = new DataString();
        full.SetName("full");
        full.SetValue("full");
        pn.ConstantPlaceList.add(full);

        // --------------------------------------------------------------------
        // -------------------------------Lane1--------------------------------
        // --------------------------------------------------------------------

        DataCar p1 = new DataCar();
        p1.SetName("P_I1_a1");
        pn.PlaceList.add(p1);

        DataCarQueue p2 = new DataCarQueue();
        p2.Value.Size = 3;
        p2.SetName("P_I1_x1");
        pn.PlaceList.add(p2);

        DataString p3 = new DataString();
        p3.SetName("P_I1_TL1");
        pn.PlaceList.add(p3);

        DataCar p4 = new DataCar();
        p4.SetName("P_I1_b1");
        pn.PlaceList.add(p4);

        // -------------------------------------------------------------------------------------
        // --------------------------------Lane2------------------------------------------------
        // -------------------------------------------------------------------------------------
        DataCar p9 = new DataCar();
        p9.SetName("P_I1_a2");
        pn.PlaceList.add(p9);

        DataCarQueue p10 = new DataCarQueue();
        p10.Value.Size = 3;
        p10.SetName("P_I1_x2");
        pn.PlaceList.add(p10);

        DataString p11 = new DataString();
        p11.SetName("P_I1_TL2");
        pn.PlaceList.add(p11);

        DataCar p12 = new DataCar();
        p12.SetName("P_I1_b2");
        pn.PlaceList.add(p12);

        // the lane from the right
        // -------------------------------------------------------------------------------------
        // --------------------------------Lane3------------------------------------------------
        // -------------------------------------------------------------------------------------
        DataCar p5 = new DataCar();
        p5.SetName("P_I1_a3");
        pn.PlaceList.add(p5);

        DataCarQueue p6 = new DataCarQueue();
        p6.Value.Size = 3;
        p6.SetName("P_I1_x3");
        pn.PlaceList.add(p6);

        DataString p7 = new DataString();
        p7.SetName("P_I1_TL3");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("P_I1_b3");
        pn.PlaceList.add(p8);


        // ----------------------------------------------------------------------------
        // ----------------------------Exit lane 1-------------------------------------
        // ----------------------------------------------------------------------------
        DataCarQueue p13 = new DataCarQueue();
        p13.Value.Size = 3;
        p13.SetName("P_I1_o1");
        pn.PlaceList.add(p13);

        DataCar p14 = new DataCar();
        p14.SetName("P_o1Exit");
        pn.PlaceList.add(p14);

        // ----------------------------------------------------------------------------
        // ----------------------------Exit lane 2-------------------------------------
        // ----------------------------------------------------------------------------

        DataCarQueue p15 = new DataCarQueue();
        p15.Value.Size = 3;
        p15.SetName("P_I1_o2");
        pn.PlaceList.add(p15);

        DataCar p16 = new DataCar();
        p16.SetName("P_o2Exit");
        pn.PlaceList.add(p16);

        DataCar p17 = new DataCar(); //p17.Printable = false;
        p17.SetName("P_I1_o2Next");
        pn.PlaceList.add(p17);

        // ---------------------------------OP-part----------------------------------------

        //Implementing OP1_I1 as an output channel connected to the controller
        DataTransfer OP1_I1 = new DataTransfer();
        OP1_I1.SetName("OP1_I1");
        OP1_I1.Value = new TransferOperation("localhost", "1082", "i2");
        pn.PlaceList.add(OP1_I1);

        //Implementing OP2_I1 as an output channel connected to the controller
        DataTransfer OP2_I1 = new DataTransfer();
        OP2_I1.SetName("OP2_I1");
        OP2_I1.Value = new TransferOperation("localhost", "1082", "i2");
        pn.PlaceList.add(OP2_I1);


        //Implementing OP3_I1 as an output channel connected to the controller
        DataTransfer OP3_I1 = new DataTransfer();
        OP3_I1.SetName("OP3_I1");
        OP3_I1.Value = new TransferOperation("localhost", "1082", "i1");
        pn.PlaceList.add(OP3_I1);


        // -------------------------------------------------------------------------------------------
        // --------------------------------Intersection-----------------------------------------------
        // -------------------------------------------------------------------------------------------

        DataCarQueue p18 = new DataCarQueue();
        p18.Value.Size = 3;
        p18.SetName("P_I1");
        pn.PlaceList.add(p18);

        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T_I1_u1";
        t1.InputPlaceName.add("P_I1_a1");
        t1.InputPlaceName.add("P_I1_x1");

        Condition T1Ct1 = new Condition(t1, "P_I1_a1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P_I1_x1", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "P_I1_a1", TransitionOperation.AddElement, "P_I1_x1"));
        t1.GuardMappingList.add(grdT1);

        // OP part
        Condition T1Ct3 = new Condition(t1, "P_I1_a1", TransitionCondition.NotNull);
        Condition T1Ct4 = new Condition(t1, "P_I1_x1", TransitionCondition.CanNotAddCars);
        T1Ct3.SetNextCondition(LogicConnector.AND, T1Ct4);

        GuardMapping grdT111 = new GuardMapping(); // we named it in that way because we used grdT11 for the "T_I1_i2" transition
        grdT111.condition= T1Ct3;
        grdT111.Activations.add(new Activation(t1, "full", TransitionOperation.Copy, "OP1_I1"));
        grdT111.Activations.add(new Activation(t1, "P_I1_a1", TransitionOperation.Copy, "P_I1_a1"));
        t1.GuardMappingList.add(grdT111);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T_I1_e1";
        t2.InputPlaceName.add("P_I1_x1");
        t2.InputPlaceName.add("P_I1_TL1");

        Condition T2Ct1 = new Condition(t2, "P_I1_TL1", TransitionCondition.Equal, "green");
        Condition T2Ct2 = new Condition(t2, "P_I1_x1", TransitionCondition.HaveCar);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P_I1_x1", TransitionOperation.PopElementWithoutTarget, "P_I1_b1"));
        grdT2.Activations.add(new Activation(t2, "P_I1_TL1", TransitionOperation.Move, "P_I1_TL1"));

        t2.GuardMappingList.add(grdT2);

//		t2.Delay = 3;
        pn.Transitions.add(t2);

        // buttom part
        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T_I1_u2";
        t3.InputPlaceName.add("P_I1_a2");
        t3.InputPlaceName.add("P_I1_x2");

        Condition T3Ct1 = new Condition(t3, "P_I1_a2", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "P_I1_x2", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "P_I1_a2", TransitionOperation.AddElement, "P_I1_x2"));
        t3.GuardMappingList.add(grdT3);

        // OP part
        Condition T3Ct3 = new Condition(t3, "P_I1_a2", TransitionCondition.NotNull);
        Condition T3Ct4 = new Condition(t3, "P_I1_x2", TransitionCondition.CanNotAddCars);
        T3Ct3.SetNextCondition(LogicConnector.AND, T3Ct4);

        GuardMapping grdT113 = new GuardMapping(); // we named it in that way because we used grdT13 for the another transition
        grdT113.condition= T3Ct3;
        grdT113.Activations.add(new Activation(t3, "full", TransitionOperation.Copy, "OP2_I1"));
        grdT113.Activations.add(new Activation(t3, "P_I1_a2", TransitionOperation.Copy, "P_I1_a2"));
        t3.GuardMappingList.add(grdT113);

        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4 ------------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T_I1_e2";
        t4.InputPlaceName.add("P_I1_x2");
        t4.InputPlaceName.add("P_I1_TL2");

        Condition T4Ct1 = new Condition(t4, "P_I1_TL2", TransitionCondition.Equal, "green");
        Condition T4Ct2 = new Condition(t4, "P_I1_x2", TransitionCondition.HaveCar);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "P_I1_x2", TransitionOperation.PopElementWithoutTarget, "P_I1_b2"));
        grdT4.Activations.add(new Activation(t4, "P_I1_TL2", TransitionOperation.Move, "P_I1_TL2"));
        t4.GuardMappingList.add(grdT2);

        t4.Delay = 0;
        pn.Transitions.add(t4);

        // right part
        // T5 ------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "T_I1_u3";
        t5.InputPlaceName.add("P_I1_a3");
        t5.InputPlaceName.add("P_I1_x3");

        Condition T5Ct1 = new Condition(t5, "P_I1_a3", TransitionCondition.NotNull);
        Condition T5Ct2 = new Condition(t5, "P_I1_x3", TransitionCondition.CanAddCars);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "P_I1_a3", TransitionOperation.AddElement, "P_I1_x3"));
        t5.GuardMappingList.add(grdT5);

        // OP part
        Condition T5Ct3 = new Condition(t5, "P_I1_a3", TransitionCondition.NotNull);
        Condition T5Ct4 = new Condition(t5, "P_I1_x3", TransitionCondition.CanNotAddCars);
        T5Ct3.SetNextCondition(LogicConnector.AND, T5Ct4);

        GuardMapping grdT15 = new GuardMapping(); // we named it in that way because we used grdT13 for the another transition
        grdT15.condition= T5Ct3;
        grdT15.Activations.add(new Activation(t5, "full", TransitionOperation.Copy, "OP3_I1"));
        grdT15.Activations.add(new Activation(t5, "P_I1_a3", TransitionOperation.Copy, "P_I1_a3"));
        t5.GuardMappingList.add(grdT15);

        t5.Delay = 0;
        pn.Transitions.add(t5);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "T_I1_e3";
        t6.InputPlaceName.add("P_I1_x3");
        t6.InputPlaceName.add("P_I1_TL3");

        Condition T6Ct1 = new Condition(t6, "P_I1_TL3", TransitionCondition.Equal, "green");
        Condition T6Ct2 = new Condition(t6, "P_I1_x3", TransitionCondition.HaveCar);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "P_I1_x3", TransitionOperation.PopElementWithoutTarget, "P_I1_b3"));
        grdT6.Activations.add(new Activation(t6, "P_I1_TL3", TransitionOperation.Move, "P_I1_TL3"));
        t6.GuardMappingList.add(grdT6);

        t6.Delay = 0;
        pn.Transitions.add(t6);

        // exit lanes
        // T7 ------------------------------------------------
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "T_I1_g1Exit";
        t7.InputPlaceName.add("P_I1_o1");

        Condition T7Ct1 = new Condition(t7, "P_I1_o1", TransitionCondition.HaveCar);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT7.Activations.add(new Activation(t7, "P_I1_o1", TransitionOperation.PopElementWithoutTarget, "P_I1_o1Exit"));
        t7.GuardMappingList.add(grdT7);

        t7.Delay = 0;
        pn.Transitions.add(t7);

        // T8 ------------------------------------------------
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "T_I1_g2Exit";
        t8.InputPlaceName.add("P_I1_o2");

        Condition T8Ct1 = new Condition(t8, "P_I1_o2", TransitionCondition.HaveCar);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;
        grdT8.Activations.add(new Activation(t8, "P_I1_o2", TransitionOperation.PopElementWithoutTarget, "P_I1_o2Exit"));
        t8.GuardMappingList.add(grdT8);

        t8.Delay = 0;
        pn.Transitions.add(t8);

        // --------------------------------------FirstPart-------------------------------------------

        // T9 ------------------------------------------------
        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "T_I1_i1";
        t9.InputPlaceName.add("P_I1_b1");
        t9.InputPlaceName.add("P_I1");

        Condition T9Ct1 = new Condition(t9, "P_I1_b1", TransitionCondition.NotNull);
        Condition T9Ct2 = new Condition(t9, "P_I1", TransitionCondition.CanAddCars);
        T9Ct1.SetNextCondition(LogicConnector.AND, T9Ct2);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;
        grdT9.Activations.add(new Activation(t9, "P_I1_b1", TransitionOperation.AddElement, "P_I1"));
        t9.GuardMappingList.add(grdT9);

        t9.Delay = 0;
        pn.Transitions.add(t9);

        // T10-----------------------------------------------------------
        PetriTransition t10 = new PetriTransition(pn);
        t10.TransitionName = "T_I1_g1";
        t10.InputPlaceName.add("P_I1");
        t10.InputPlaceName.add("P_I1_o1");

        Condition T10Ct1 = new Condition(t10, "P_I1", TransitionCondition.HaveCarForMe);
        Condition T10Ct2 = new Condition(t10, "P_I1_o1", TransitionCondition.CanAddCars);
        T10Ct1.SetNextCondition(LogicConnector.AND, T10Ct2);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct1;
        grdT10.Activations.add(new Activation(t10, "P_I1", TransitionOperation.PopElementWithTargetToQueue, "P_I1_o1"));
        t10.GuardMappingList.add(grdT10);

        t10.Delay = 0;
        pn.Transitions.add(t10);

        // --------------------------------------SecondPart-------------------------------------------

        // T11 ------------------------------------------------

        PetriTransition t11 = new PetriTransition(pn);
        t11.TransitionName = "T_I1_i2";
        t11.InputPlaceName.add("P_I1_b2");
        t11.InputPlaceName.add("P_I1");

        Condition T11Ct1 = new Condition(t11, "P_I1_b2", TransitionCondition.NotNull);
        Condition T11Ct2 = new Condition(t11, "P_I1", TransitionCondition.CanAddCars);
        T11Ct1.SetNextCondition(LogicConnector.AND, T11Ct2);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition = T11Ct1;
        grdT11.Activations.add(new Activation(t11, "P_I1_b2", TransitionOperation.PopElementWithoutTarget, "P_I1"));
        t11.GuardMappingList.add(grdT11);

        t11.Delay = 0;
        pn.Transitions.add(t11);

        // T12---------------------------------------------------------

        PetriTransition t12 = new PetriTransition(pn);
        t12.TransitionName = "T_I1_g2";
        t12.InputPlaceName.add("P_I1");
        t12.InputPlaceName.add("P_I1_o2");

        Condition T12Ct1 = new Condition(t12, "P_I1", TransitionCondition.HaveCarForMe);
        Condition T12Ct2 = new Condition(t12, "P_I1_o2", TransitionCondition.CanAddCars);
        T12Ct1.SetNextCondition(LogicConnector.AND, T12Ct2);

        GuardMapping grdT12 = new GuardMapping();
        grdT12.condition = T12Ct1;
        grdT12.Activations.add(new Activation(t12, "P_I1", TransitionOperation.PopElementWithTargetToQueue, "P_I1_o2"));
        t12.GuardMappingList.add(grdT12);

        t12.Delay = 0;
        pn.Transitions.add(t12);

        // ----------------------ThirdPart----------------------------------------------------------------

        // T13 ------------------------------------------------
        PetriTransition t13 = new PetriTransition(pn);
        t13.TransitionName = "T_I1_i3";
        t13.InputPlaceName.add("P_I1_b3");
        t13.InputPlaceName.add("P_I1");

        Condition T13Ct1 = new Condition(t13, "P_I1_b3", TransitionCondition.NotNull);
        Condition T13Ct2 = new Condition(t13, "P_I1", TransitionCondition.CanAddCars);
        T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);

        GuardMapping grdT13 = new GuardMapping();
        grdT13.condition = T13Ct1;
        grdT13.Activations.add(new Activation(t13, "P_I1_b3", TransitionOperation.AddElement, "P_I1"));
        t13.GuardMappingList.add(grdT13);

        t13.Delay = 0;
        pn.Transitions.add(t13);


        // T14 ------------------------------------------------  (the transition that comunicates with the 2nd intersection)
        PetriTransition t14 = new PetriTransition(pn);
        t14.TransitionName = "T_I1_g2Next";
        t14.InputPlaceName.add("P_I1_o2Exit");
        //t14.InputPlaceName.add("P_I1_o2Next");  should i add this transition?

        Condition T14Ct1 = new Condition(t14, "P_I1_o2Exit", TransitionCondition.HaveCarForMe);
        Condition T14Ct2 = new Condition(t14, "P_I1_o2Next", TransitionCondition.CanAddCars);
        T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);

        GuardMapping grdT14 = new GuardMapping();
        grdT14.condition = T14Ct1;
        grdT14.Activations.add(new Activation(t14, "P_I1_o2Exit", TransitionOperation.PopElementWithTargetToQueue, "P_I1_o2Next"));
        t14.GuardMappingList.add(grdT12);

        t14.Delay = 0;
        pn.Transitions.add(t14);

        // -------------------------------------------------------------------------------------
        // ----------------------------PNStart-------------------------------------------------
        // -------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }

}

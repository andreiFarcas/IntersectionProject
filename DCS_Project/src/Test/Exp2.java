package Test;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Exp2 {
	public static void main(String[] args) {

		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Main Petri";
		pn.NetworkPort = 1081;
		
		DataCar p4 = new DataCar();
		p4.SetName("P4");
		pn.PlaceList.add(p4);

		DataCar p5 = new DataCar();
		p5.SetName("p5");
		pn.PlaceList.add(p5);

		DataCar p6 = new DataCar();
		p6.SetName("p6");
		pn.PlaceList.add(p6);

		DataCar p7 = new DataCar();
		p7.SetName("p7");
		pn.PlaceList.add(p7);

		DataCar p8 = new DataCar();
		p8.SetName("p8");
		pn.PlaceList.add(p8);

		DataCar p9 = new DataCar();
		p9.SetName("p9");
		pn.PlaceList.add(p9);

		DataCarQueue P1 = new DataCarQueue();
		P1.SetName("P1");
		P1.Value.Size = 3;
		pn.PlaceList.add(P1);

		DataCarQueue P2 = new DataCarQueue();
		P2.SetName("P2");
		P2.Value.Size = 3;
		pn.PlaceList.add(P2);

		DataCarQueue P3 = new DataCarQueue();
		P3.SetName("P3");
		P3.Value.Size = 3;
		pn.PlaceList.add(P3);

		// T5 ------------------------------------------------
		PetriTransition t5 = new PetriTransition(pn);
		t5.TransitionName = "T5";
		t5.InputPlaceName.add("p5");

		Condition t5Ct1 = new Condition(t5, "p5", TransitionCondition.NotNull);
		Condition t5Ct2 = new Condition(t5, "P1", TransitionCondition.CanAddCars);
		t5Ct1.SetNextCondition(LogicConnector.AND, t5Ct2);

		GuardMapping grdT5 = new GuardMapping();
		grdT5.condition= t5Ct1;
		grdT5.Activations.add(new Activation(t5, "p5", TransitionOperation.AddElement, "P1"));
		t5.GuardMappingList.add(grdT5);
		
		t5.Delay = 0;
		pn.Transitions.add(t5);

		// T6 ------------------------------------------------
		PetriTransition t6 = new PetriTransition(pn);
		t6.TransitionName = "T6";
		t6.InputPlaceName.add("p6");

		Condition t6Ct1 = new Condition(t6, "p6", TransitionCondition.NotNull);
		Condition t6Ct2 = new Condition(t6, "P2", TransitionCondition.CanAddCars);
		t6Ct1.SetNextCondition(LogicConnector.AND, t6Ct2);

		GuardMapping grdT6 = new GuardMapping();
		grdT6.condition= t6Ct1;
		grdT6.Activations.add(new Activation(t6, "p6", TransitionOperation.AddElement, "P2"));
		t6.GuardMappingList.add(grdT6);

		t6.Delay = 0;
		pn.Transitions.add(t6);

		// T5 ------------------------------------------------
		PetriTransition t8 = new PetriTransition(pn);
		t8.TransitionName = "T8";
		t8.InputPlaceName.add("p8");

		Condition t8Ct1 = new Condition(t8, "p8", TransitionCondition.NotNull);
		Condition t8Ct2 = new Condition(t8, "P3", TransitionCondition.CanAddCars);
		t8Ct1.SetNextCondition(LogicConnector.AND, t8Ct2);

		GuardMapping grdT8 = new GuardMapping();
		grdT8.condition= t8Ct1;
		grdT8.Activations.add(new Activation(t8, "p8", TransitionOperation.AddElement, "P3"));
		t8.GuardMappingList.add(grdT8);

		t8.Delay = 0;
		pn.Transitions.add(t8);

		// T1 ------------------------------------------------
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "T1";
		t1.InputPlaceName.add("P1");

		Condition T1Ct1 = new Condition(t1, "P1", TransitionCondition.HaveCarForMe);
		Condition T1Ct2 = new Condition(t1, "P2", TransitionCondition.CanAddCars);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition= T1Ct1;
		grdT1.Activations.add(new Activation(t1, "P1", TransitionOperation.PopElementWithTarget, "P2"));
		t1.GuardMappingList.add(grdT1);

		t1.Delay = 0;
		pn.Transitions.add(t1);

		// T2 ------------------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "T2";
		t2.InputPlaceName.add("P2");

		Condition T2Ct1 = new Condition(t2, "P2", TransitionCondition.HaveCarForMe);
		Condition T2Ct2 = new Condition(t2, "P3", TransitionCondition.CanAddCars);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition= T2Ct1;
		grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.PopElementWithTarget, "P3"));
		t2.GuardMappingList.add(grdT2);

		t2.Delay = 0;
		pn.Transitions.add(t2);

		// T3 ------------------------------------------------
		PetriTransition t3 = new PetriTransition(pn);
		t3.TransitionName = "T3";
		t3.InputPlaceName.add("P3");

		Condition T3Ct1 = new Condition(t3, "P3", TransitionCondition.HaveCarForMe);
		Condition T3Ct2 = new Condition(t3, "P1", TransitionCondition.CanAddCars);
		T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

		GuardMapping grdT3 = new GuardMapping();
		grdT3.condition= T3Ct1;
		grdT3.Activations.add(new Activation(t3, "P3", TransitionOperation.PopElementWithTarget, "P1"));
		t3.GuardMappingList.add(grdT3);

		t3.Delay = 0;
		pn.Transitions.add(t3);


		// T7 ------------------------------------------------
		PetriTransition t7 = new PetriTransition(pn);
		t7.TransitionName = "T7";
		t7.InputPlaceName.add("P2");

		Condition T7Ct1 = new Condition(t7, "P2", TransitionCondition.HaveCarForMe);

		GuardMapping grdT7 = new GuardMapping();
		grdT7.condition= T7Ct1;
		grdT7.Activations.add(new Activation(t7, "P2", TransitionOperation.PopElementWithTarget, "P7"));
		t7.GuardMappingList.add(grdT7);

		t7.Delay = 0;
		pn.Transitions.add(t7);

		// T9 ------------------------------------------------
		PetriTransition t9 = new PetriTransition(pn);
		t9.TransitionName = "T9";
		t9.InputPlaceName.add("P3");

		Condition T9Ct1 = new Condition(t9, "P3", TransitionCondition.HaveCarForMe);

		GuardMapping grdT9 = new GuardMapping();
		grdT9.condition= T9Ct1;
		grdT9.Activations.add(new Activation(t9, "P3", TransitionOperation.PopElementWithTarget, "P9"));
		t9.GuardMappingList.add(grdT9);

		t9.Delay = 0;
		pn.Transitions.add(t9);

		// T4 ------------------------------------------------
		PetriTransition t4 = new PetriTransition(pn);
		t4.TransitionName = "T4";
		t4.InputPlaceName.add("P1");

		Condition T4Ct1 = new Condition(t7, "P1", TransitionCondition.HaveCarForMe);

		GuardMapping grdT4 = new GuardMapping();
		grdT4.condition= T4Ct1;
		grdT4.Activations.add(new Activation(t4, "P1", TransitionOperation.PopElementWithTarget, "P4"));
		t4.GuardMappingList.add(grdT4);

		t4.Delay = 0;
		pn.Transitions.add(t4);

		// ASTA NUSH CE E
		System.out.println("Exp1 started \n ------------------------------");
		pn.Delay = 2000;
		//pn.Start();
		
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);

	}
}

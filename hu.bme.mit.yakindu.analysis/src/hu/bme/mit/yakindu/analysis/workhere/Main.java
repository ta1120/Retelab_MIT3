package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		int stateCounter = 0;
		//System.out.println("public static void print(IExampleStatemachines) {");
		
		System.out.println("public class RunStatechart {");
		System.out.println("	public static void main(String[] args) throws IOException {");
		System.out.println("		ExampleStatemachine s = new ExampleStatemachine();");
		System.out.println("		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));\r\n" + 
				"		String inputLine = reader.readLine();");
		System.out.println("		while(!inputLine.equals(\"exit\"))\r\n" + 
				"		{\r\n" + 
				"			switch(inputLine)\r\n" + 
				"			{");
		
		while (iterator.hasNext()) {
			
			EObject content = iterator.next();
			
			if(content instanceof EventDefinition)
			{
				System.out.println("			case \"" + ((EventDefinition)content).getName() + "\":\r\n" + 
						"				s.getSCInterface().raise" + ((EventDefinition)content).getName() + "();\r\n" + 
						"				break;");
			}
			
			
			/*
			if(content instanceof VariableDefinition)
			{
				System.out.println("System.out.println(\"" + ((VariableDefinition)content).getName() + " = \" + s.getSCInterface().get"+ ((VariableDefinition)content).getName() + "());");
			}
			*/
			
			/*
			if(content instanceof VariableDefinition)
			{
				System.out.println(((VariableDefinition)content).getName());
			}
			
			if(content instanceof EventDefinition)
			{
				System.out.println(((EventDefinition)content).getName());
			}
			*/
			
			/*
			if(content instanceof State) {
				stateCounter++;
				State state = (State) content;
				EList<Transition> transitions = state.getOutgoingTransitions();
				
				System.out.println(state.getName());
				
				for(int i = 0; i < transitions.size();i++)
				{
					System.out.println(transitions.get(i).getSource().getName() + " -> " + transitions.get(i).getTarget().getName());
				}
				
				if(transitions.size() == 0) System.out.println("Csapda??llapot: " + state.getName());
				
				if(state.getName().equals("")) System.out.println("N??vtelen ??llapot, aj??nlott n??v: ??llapot" + stateCounter);
				
			}
			*/
		}
		System.out.println("		}");
		System.out.println("		print(s);\r\n" + 
				"			\r\n" + 
				"		inputLine = reader.readLine();");
		System.out.println("	}");
		System.out.println("}");
		
		//System.out.println("}");
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}

package hu.bme.mit.yakindu.analysis.workhere;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;

import hu.bme.mit.model2gml.Model2GML;
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
		while (iterator.hasNext()) {
			
			EObject content = iterator.next();
			if(content instanceof State) {
				stateCounter++;
				State state = (State) content;
				EList<Transition> transitions = state.getOutgoingTransitions();
				
				System.out.println(state.getName());
				
				for(int i = 0; i < transitions.size();i++)
				{
					System.out.println(transitions.get(i).getSource().getName() + " -> " + transitions.get(i).getTarget().getName());
				}
				
				if(transitions.size() == 0) System.out.println("Csapdaállapot: " + state.getName());
				
				if(state.getName().equals("")) System.out.println("Névtelen állapot, ajánlott név: Állapot" + stateCounter);
				
			}
		}
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}

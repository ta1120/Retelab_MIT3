package hu.bme.mit.yakindu.analysis.workhere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;



public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		s.runCycle();
		print(s);
		s.raiseStart();
		s.runCycle();
		System.in.read();
		//s.raiseWhite();
		s.runCycle();
		
		/*
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = reader.readLine();
		
		while(!inputLine.equals("exit"))
		{
			switch(inputLine)
			{
			case "white":
				s.getSCInterface().raiseWhite();
				break;
			case "black":
				s.getSCInterface().raiseBlack();
				break;
			case "start":
				s.getSCInterface().raiseStart();
				break;
			}
			
			print(s);
			
			inputLine = reader.readLine();
		}
		*/
		
	}

	public static void print(IExampleStatemachine s) {
		/*
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
		*/
	}
}

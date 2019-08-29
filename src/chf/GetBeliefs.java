package chf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import norsys.netica.*;

public class GetBeliefs {
	public static List<String> inputtedBeliefs = new ArrayList<String>();
	
	public static void main(String args[]) throws NeticaException{
		double prob = 0.0;
                
//=========================This is for Congestive Heart Failure=========================
//-------------------------SD1 status------------------------- 
                inputtedBeliefs.add("SD1p");
//                inputtedBeliefs.add("SD1n");
                
//-------------------------SDANN status-------------------------  
                inputtedBeliefs.add("SDANNp");
//                inputtedBeliefs.add("SDANNn");
                
//-------------------------rMSSD status-------------------------                
                inputtedBeliefs.add("rMSSDp");
//                inputtedBeliefs.add("rMSSDn");
                
//-------------------------pNN50 status-------------------------  
                inputtedBeliefs.add("pNN50p");
//                inputtedBeliefs.add("pNN50n");
                
//-------------------------DFAalpha1 status-------------------------                  
                inputtedBeliefs.add("alpha1p");  
//                inputtedBeliefs.add("alpha1n");
                
//-------------------------Alcoholic status-------------------------              
                inputtedBeliefs.add("Alcoholic");
//                inputtedBeliefs.add("nonAlcoholic");
                
//-------------------------Diabetic status-------------------------                  
                inputtedBeliefs.add("Diabetic");
//                inputtedBeliefs.add("NonDiabetic");
                
//-------------------------SDNN status-------------------------                 
                inputtedBeliefs.add("SDNNp");
//                inputtedBeliefs.add("SDNNn");
                
//-------------------------meanNN status------------------------- 
                inputtedBeliefs.add("meanNNp");
//                inputtedBeliefs.add("meanNNn");
                
//-------------------------LF status-------------------------                 
                inputtedBeliefs.add("LFp");
//                inputtedBeliefs.add("LFn");
                
//-------------------------LFHF status-------------------------   
                inputtedBeliefs.add("LFHFp");
//                inputtedBeliefs.add("LFHFn");

//-------------------------Age status-------------------------                  
//                inputtedBeliefs.add("LT55");
//                inputtedBeliefs.add("age55to60");
//                inputtedBeliefs.add("age60to65");
//                inputtedBeliefs.add("age65to70");
//                inputtedBeliefs.add("age70to75");
//                inputtedBeliefs.add("age75to80");
                inputtedBeliefs.add("GT80");

//-------------------------HF status------------------------- 
                inputtedBeliefs.add("HFp");
//                inputtedBeliefs.add("HFn");

//-------------------------Smoker status-------------------------                  
                inputtedBeliefs.add("Smoker");
//                inputtedBeliefs.add("nonSmoker");
                
//		This is for Anthrax
		
		
//		inputtedBeliefs.add("Equatorial");	//Hemisphere	
//		inputtedBeliefs.add("Forest");		//Natural
//		inputtedBeliefs.add("Sunny");		//Weather
//		inputtedBeliefs.add("Jobless");	//Occupation
//		inputtedBeliefs.add("Influenza");	//PreExistingIllness
//		inputtedBeliefs.add("Farms");		//ManMade
//		inputtedBeliefs.add("Rain");		//Season
//		inputtedBeliefs.add("Rash");		//Symptom
		
//		inputtedBeliefs.add("Pregnant");	//DevelopmentStage
//		inputtedBeliefs.add("Vegetarian");		//Eating
//		inputtedBeliefs.add("Anthrax");		//Eating
//		inputtedBeliefs.add("US");	//Country
//		inputtedBeliefs.add("Female");		//Gender
//		inputtedBeliefs.add("Smoker");		//Smoking
		
			
//		This is for Crohn's Disease
		
//		inputtedBeliefs.add("NotSpecified");	//Weather
//		inputtedBeliefs.add("Parents");	//Relationship
//		inputtedBeliefs.add("One");		//Genetic
//		inputtedBeliefs.add("UK");		//Country
//		inputtedBeliefs.add("NotSpecified");		//Season
//		inputtedBeliefs.add("Unknown");	//Smoking
		
//		This is for Tuberculosis	
		
//		inputtedBeliefs.add("HIV");	//PreExistingIllness
//		inputtedBeliefs.add("Elementary");	//Education
//		inputtedBeliefs.add("BCG");		//Vaccination
//		inputtedBeliefs.add("Private");		//Living Habit
//		inputtedBeliefs.add("Dry");	//Weather
//		inputtedBeliefs.add("Drinker");	//Alcohol
//		inputtedBeliefs.add("Indonesia");		//Country
//		inputtedBeliefs.add("Female");	//Gender
//		inputtedBeliefs.add("a15to24");	//Age
//		inputtedBeliefs.add("NonSmoker");	//Smoking
		
//		This is for Dengue
		
		
		
		prob = calculate();
		
//		System.out.println(prob*100);
                System.out.println ("\nGiven the patient status,\n" +
                "the probability of Congestive Heart Failure is " + prob*100);
	}

	public static double calculate() throws NeticaException{
		Environ env = new Environ ("+MahanantoF/InsTechSepNop/310-7-A/8400");
		
		Net net = new Net (new Streamer ("CongestiveHeartFailure.dne"));
		
		Node disease	= net.getNode("CongestiveHeartFailure");
		
		//Read in the net
		NodeList nodes = net.getNodes();
		int numNodes = nodes.size();

		for(int i=0; i<(numNodes-1); i++){
			Node temporary = nodes.getNode(i);			
			temporary.finding().clear();
			temporary.finding().enterState(inputtedBeliefs.get(i));
		}
		
		net.compile();
		
		return disease.getBelief("AtRisk");
	}
}

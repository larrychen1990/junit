package org.junit.custom.theory.suppliedby;

import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

public class NameSupplier extends ParameterSupplier {
	 
	 @Override
	 public List getValueSources(
	   ParameterSignature signature) {
	 
	  AllNames annotation = signature.getAnnotation(AllNames.class);
	  System.out.println("just wanted to show that I can access it "
	    + annotation);
	 
	  ArrayList result = new ArrayList();
	 
	  result.add(PotentialAssignment.forValue("Alf", "Alf"));
	  result.add(PotentialAssignment.forValue("Willie", "Willie"));
	  result.add(PotentialAssignment.forValue("Tanner", "Tanner"));
	  result.add(PotentialAssignment.forValue("Cat", "Cat"));
	 
	  return result;
	 }
	}

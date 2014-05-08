package org.junit.custom.theory.suppliedby;

import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

public class CreditCardSupplier extends ParameterSupplier {
	 
	 @Override
	 public List getValueSources(
	   ParameterSignature signature) {
	 
	  ArrayList result = new ArrayList();
	 
	  result.add(PotentialAssignment.forValue("Amex", "Amex"));
	  result.add(PotentialAssignment.forValue("Master", "Master"));
	  result.add(PotentialAssignment.forValue("Visa", "Visa"));
	 
	  return result;
	 }
	}

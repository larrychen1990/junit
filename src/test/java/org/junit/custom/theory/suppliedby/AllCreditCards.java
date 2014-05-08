package org.junit.custom.theory.suppliedby;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.experimental.theories.ParametersSuppliedBy;


@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(CreditCardSupplier.class)
public @interface AllCreditCards {
}

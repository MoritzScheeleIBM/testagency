package org.acme.travels.P8A;

import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;
import org.acme.travels.*;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaConsequence8ABF2CC0EA2CC0D4E1F1AABCB5CDCAE3 implements org.drools.model.functions.Block1<org.acme.travels.Trip>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "003B4F1F54C15773BB8120D11EE9F2D4";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public void execute(org.acme.travels.Trip $trip) throws java.lang.Exception {
        $trip.setVisaRequired(false);
    }
}

package org.acme.travels.P74;

import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;
import org.acme.travels.*;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaConsequence74035364E7998D0AAE75FC4F29C57E54 implements org.drools.model.functions.Block1<org.acme.travels.Trip>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "DCFBC4906F5F537E67F6A7CB41F890F4";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public void execute(org.acme.travels.Trip $trip) throws java.lang.Exception {
        $trip.setVisaRequired(true);
    }
}

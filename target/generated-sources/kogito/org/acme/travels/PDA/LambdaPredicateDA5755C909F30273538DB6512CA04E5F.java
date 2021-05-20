package org.acme.travels.PDA;

import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;
import org.acme.travels.*;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaPredicateDA5755C909F30273538DB6512CA04E5F implements org.drools.model.functions.Predicate1<org.acme.travels.Trip>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "1A1158053192D10FD74A3825B92D44D0";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public boolean test(org.acme.travels.Trip _this) throws java.lang.Exception {
        return org.drools.modelcompiler.util.EvaluationUtil.areNullSafeEquals(_this.getCountry(), "UK");
    }

    @Override()
    public org.drools.model.functions.PredicateInformation predicateInformation() {
        return new org.drools.model.functions.PredicateInformation("$trip.country == \"UK\"", "Polish citizens do not require visa to UK", "/Users/moritzscheele/Desktop/IBM/kogito-examples/kogito-travel-agency/basic/src/main/resources/org/acme/travels/visa-rules.xls");
    }
}

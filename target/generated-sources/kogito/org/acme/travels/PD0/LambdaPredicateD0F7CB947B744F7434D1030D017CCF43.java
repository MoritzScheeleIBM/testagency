package org.acme.travels.PD0;

import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;
import org.acme.travels.*;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaPredicateD0F7CB947B744F7434D1030D017CCF43 implements org.drools.model.functions.Predicate1<org.acme.travels.Traveller>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "C96177F6A6AD31C4CC687A192D4E10BE";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public boolean test(org.acme.travels.Traveller _this) throws java.lang.Exception {
        return org.drools.modelcompiler.util.EvaluationUtil.areNullSafeEquals(_this.getNationality(), "Polish");
    }

    @Override()
    public org.drools.model.functions.PredicateInformation predicateInformation() {
        return new org.drools.model.functions.PredicateInformation("$traveller.nationality == \"Polish\"", "Polish citizens require visa to Australia", "/Users/moritzscheele/Desktop/IBM/kogito-examples/kogito-travel-agency/basic/src/main/resources/org/acme/travels/visa-rules.xls");
    }
}

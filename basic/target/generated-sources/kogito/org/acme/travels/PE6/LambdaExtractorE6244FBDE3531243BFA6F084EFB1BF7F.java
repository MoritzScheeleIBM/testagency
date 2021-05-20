package org.acme.travels.PE6;

import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;
import org.acme.travels.*;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaExtractorE6244FBDE3531243BFA6F084EFB1BF7F implements org.drools.model.functions.Function1<org.acme.travels.Traveller, java.lang.String>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "41EF90148DBEC62D76CCDFB69BF38C59";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public java.lang.String apply(org.acme.travels.Traveller _this) {
        return _this.getNationality();
    }
}

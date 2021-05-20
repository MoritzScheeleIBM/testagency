package org.acme.travels.P6B;

import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;
import org.acme.travels.*;
import org.drools.modelcompiler.dsl.pattern.D;

@org.drools.compiler.kie.builder.MaterializedLambda()
public enum LambdaExtractor6B227A962D2A5906597BC2330C04C14A implements org.drools.model.functions.Function1<org.acme.travels.Trip, java.lang.String>, org.drools.model.functions.HashedExpression {

    INSTANCE;

    public static final String EXPRESSION_HASH = "E1921335BDAA114A0008B3D9577B37E6";

    public java.lang.String getExpressionHash() {
        return EXPRESSION_HASH;
    }

    @Override()
    public java.lang.String apply(org.acme.travels.Trip _this) {
        return _this.getCountry();
    }
}

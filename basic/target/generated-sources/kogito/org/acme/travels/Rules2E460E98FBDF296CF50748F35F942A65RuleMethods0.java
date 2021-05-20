package org.acme.travels;

import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.model.Index.ConstraintType;
import static org.acme.travels.Rules2E460E98FBDF296CF50748F35F942A65.*;

public class Rules2E460E98FBDF296CF50748F35F942A65RuleMethods0 {

    /**
     * Rule name: Polish citizens require visa to US
     */
    public static org.drools.model.Rule rule_Polish_32citizens_32require_32visa_32to_32US() {
        final org.drools.model.Variable<org.acme.travels.Trip> var_$trip = D.declarationOf(org.acme.travels.Trip.class,
                                                                                           DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65.org_acme_travels_Trip_Metadata_INSTANCE,
                                                                                           "$trip");
        final org.drools.model.Variable<org.acme.travels.Traveller> var_$traveller = D.declarationOf(org.acme.travels.Traveller.class,
                                                                                                     DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65.org_acme_travels_Traveller_Metadata_INSTANCE,
                                                                                                     "$traveller");
        org.drools.model.Rule rule = D.rule("org.acme.travels",
                                            "Polish citizens require visa to US").attribute(org.drools.model.Rule.Attribute.RULEFLOW_GROUP,
                                                                                            "visas")
                                                                                 .build(D.pattern(var_$trip).expr("GENERATED_A7B76908B81A3DA78E57328A257F3950",
                                                                                                                  org.acme.travels.P66.LambdaPredicate66B73A8B50F1BC3EB29F842404602CB4.INSTANCE,
                                                                                                                  D.alphaIndexedBy(java.lang.String.class,
                                                                                                                                   org.drools.model.Index.ConstraintType.EQUAL,
                                                                                                                                   -1,
                                                                                                                                   org.acme.travels.P6B.LambdaExtractor6B227A962D2A5906597BC2330C04C14A.INSTANCE,
                                                                                                                                   "US"),
                                                                                                                  D.reactOn("country")),
                                                                                        D.pattern(var_$traveller).expr("GENERATED_35B94D5F500233D6B8577C6DCDD7CCB1",
                                                                                                                       org.acme.travels.PD0.LambdaPredicateD0F7CB947B744F7434D1030D017CCF43.INSTANCE,
                                                                                                                       D.alphaIndexedBy(java.lang.String.class,
                                                                                                                                        org.drools.model.Index.ConstraintType.EQUAL,
                                                                                                                                        -1,
                                                                                                                                        org.acme.travels.PE6.LambdaExtractorE6244FBDE3531243BFA6F084EFB1BF7F.INSTANCE,
                                                                                                                                        "Polish"),
                                                                                                                       D.reactOn("nationality")),
                                                                                        D.on(var_$trip).execute(org.acme.travels.P74.LambdaConsequence74035364E7998D0AAE75FC4F29C57E54.INSTANCE));
        return rule;
    }

    /**
     * Rule name: Polish citizens do not require visa to UK
     */
    public static org.drools.model.Rule rule_Polish_32citizens_32do_32not_32require_32visa_32to_32UK() {
        final org.drools.model.Variable<org.acme.travels.Trip> var_$trip = D.declarationOf(org.acme.travels.Trip.class,
                                                                                           DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65.org_acme_travels_Trip_Metadata_INSTANCE,
                                                                                           "$trip");
        final org.drools.model.Variable<org.acme.travels.Traveller> var_$traveller = D.declarationOf(org.acme.travels.Traveller.class,
                                                                                                     DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65.org_acme_travels_Traveller_Metadata_INSTANCE,
                                                                                                     "$traveller");
        org.drools.model.Rule rule = D.rule("org.acme.travels",
                                            "Polish citizens do not require visa to UK").attribute(org.drools.model.Rule.Attribute.RULEFLOW_GROUP,
                                                                                                   "visas")
                                                                                        .build(D.pattern(var_$trip).expr("GENERATED_870FAA254DD7AE2732F6F23EA83A9634",
                                                                                                                         org.acme.travels.PDA.LambdaPredicateDA5755C909F30273538DB6512CA04E5F.INSTANCE,
                                                                                                                         D.alphaIndexedBy(java.lang.String.class,
                                                                                                                                          org.drools.model.Index.ConstraintType.EQUAL,
                                                                                                                                          -1,
                                                                                                                                          org.acme.travels.P6B.LambdaExtractor6B227A962D2A5906597BC2330C04C14A.INSTANCE,
                                                                                                                                          "UK"),
                                                                                                                         D.reactOn("country")),
                                                                                               D.pattern(var_$traveller).expr("GENERATED_35B94D5F500233D6B8577C6DCDD7CCB1",
                                                                                                                              org.acme.travels.PD0.LambdaPredicateD0F7CB947B744F7434D1030D017CCF43.INSTANCE,
                                                                                                                              D.alphaIndexedBy(java.lang.String.class,
                                                                                                                                               org.drools.model.Index.ConstraintType.EQUAL,
                                                                                                                                               -1,
                                                                                                                                               org.acme.travels.PE6.LambdaExtractorE6244FBDE3531243BFA6F084EFB1BF7F.INSTANCE,
                                                                                                                                               "Polish"),
                                                                                                                              D.reactOn("nationality")),
                                                                                               D.on(var_$trip).execute(org.acme.travels.P8A.LambdaConsequence8ABF2CC0EA2CC0D4E1F1AABCB5CDCAE3.INSTANCE));
        return rule;
    }

    /**
     * Rule name: Polish citizens require visa to Australia
     */
    public static org.drools.model.Rule rule_Polish_32citizens_32require_32visa_32to_32Australia() {
        final org.drools.model.Variable<org.acme.travels.Trip> var_$trip = D.declarationOf(org.acme.travels.Trip.class,
                                                                                           DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65.org_acme_travels_Trip_Metadata_INSTANCE,
                                                                                           "$trip");
        final org.drools.model.Variable<org.acme.travels.Traveller> var_$traveller = D.declarationOf(org.acme.travels.Traveller.class,
                                                                                                     DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65.org_acme_travels_Traveller_Metadata_INSTANCE,
                                                                                                     "$traveller");
        org.drools.model.Rule rule = D.rule("org.acme.travels",
                                            "Polish citizens require visa to Australia").attribute(org.drools.model.Rule.Attribute.RULEFLOW_GROUP,
                                                                                                   "visas")
                                                                                        .build(D.pattern(var_$trip).expr("GENERATED_9EACDF2377C5EC838B1F7D00D9C84657",
                                                                                                                         org.acme.travels.PEA.LambdaPredicateEA45AB0870EE25BD4658E16286FC4F7D.INSTANCE,
                                                                                                                         D.alphaIndexedBy(java.lang.String.class,
                                                                                                                                          org.drools.model.Index.ConstraintType.EQUAL,
                                                                                                                                          -1,
                                                                                                                                          org.acme.travels.P6B.LambdaExtractor6B227A962D2A5906597BC2330C04C14A.INSTANCE,
                                                                                                                                          "Australia"),
                                                                                                                         D.reactOn("country")),
                                                                                               D.pattern(var_$traveller).expr("GENERATED_35B94D5F500233D6B8577C6DCDD7CCB1",
                                                                                                                              org.acme.travels.PD0.LambdaPredicateD0F7CB947B744F7434D1030D017CCF43.INSTANCE,
                                                                                                                              D.alphaIndexedBy(java.lang.String.class,
                                                                                                                                               org.drools.model.Index.ConstraintType.EQUAL,
                                                                                                                                               -1,
                                                                                                                                               org.acme.travels.PE6.LambdaExtractorE6244FBDE3531243BFA6F084EFB1BF7F.INSTANCE,
                                                                                                                                               "Polish"),
                                                                                                                              D.reactOn("nationality")),
                                                                                               D.on(var_$trip).execute(org.acme.travels.P74.LambdaConsequence74035364E7998D0AAE75FC4F29C57E54.INSTANCE));
        return rule;
    }
}

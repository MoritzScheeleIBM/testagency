package org.acme.travels;
public class DomainClassesMetadata2E460E98FBDF296CF50748F35F942A65 {

    public static final org.drools.model.DomainClassMetadata org_acme_travels_Traveller_Metadata_INSTANCE = new org_acme_travels_Traveller_Metadata();
    private static class org_acme_travels_Traveller_Metadata implements org.drools.model.DomainClassMetadata {

        @Override
        public Class<?> getDomainClass() {
            return org.acme.travels.Traveller.class;
        }

        @Override
        public int getPropertiesSize() {
            return 6;
        }

        @Override
        public int getPropertyIndex( String name ) {
            switch(name) {
                case "address": return 0;
                case "email": return 1;
                case "firstName": return 2;
                case "lastName": return 3;
                case "nationality": return 4;
                case "visaApplication": return 5;
             }
             throw new RuntimeException("Unknown property '" + name + "' for class class class org.acme.travels.Traveller");
        }
    }

    public static final org.drools.model.DomainClassMetadata org_acme_travels_Trip_Metadata_INSTANCE = new org_acme_travels_Trip_Metadata();
    private static class org_acme_travels_Trip_Metadata implements org.drools.model.DomainClassMetadata {

        @Override
        public Class<?> getDomainClass() {
            return org.acme.travels.Trip.class;
        }

        @Override
        public int getPropertiesSize() {
            return 5;
        }

        @Override
        public int getPropertyIndex( String name ) {
            switch(name) {
                case "begin": return 0;
                case "city": return 1;
                case "country": return 2;
                case "end": return 3;
                case "visaRequired": return 4;
             }
             throw new RuntimeException("Unknown property '" + name + "' for class class class org.acme.travels.Trip");
        }
    }

}
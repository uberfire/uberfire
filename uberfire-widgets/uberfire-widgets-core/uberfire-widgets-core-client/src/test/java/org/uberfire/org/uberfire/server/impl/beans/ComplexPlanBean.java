package org.uberfire.org.uberfire.server.impl.beans;


public class ComplexPlanBean {

    private String text;
    private boolean bool;
    private Boolean bool2;
    private Integer integ;
    private int inti;
    private Long lon;
    private long plong;
    private SampleEnum enumSample;

    public ComplexPlanBean(){};

    public ComplexPlanBean( String text,
                            boolean bool,
                            Boolean bool2,
                            Integer integ,
                            int inti,
                            Long lon,
                            long plong,
                            SampleEnum enumSample ) {
        this.text = text;
        this.bool = bool;
        this.bool2 = bool2;
        this.integ = integ;
        this.inti = inti;
        this.lon = lon;
        this.plong = plong;
        this.enumSample = enumSample;
    }
}

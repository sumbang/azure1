package tv.wouri.azure.models;

import org.springframework.beans.factory.annotation.Value;

public interface TCotisation {
    @Value(value = "#{target.MUNUMERO}")
    Integer getMUNUMERO();
    @Value(value = "#{target.RETRAITE}")
    Double getRETRAITE();
    @Value(value = "#{target.SECOURS}")
    Double getSECOURS();
    @Value(value = "#{target.TOTALCOTISE}")
    Double getTOTALCOTISE();
}

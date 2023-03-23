package tv.wouri.azure.models;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface THistoriqueMutualiste {
    @Value(value = "#{target.TMNUMERO}")
    Integer getTMNUMERO();
    @Value(value = "#{target.TMDATE}")
    Date getTMDATE();
    @Value(value = "#{target.TMLIBELLE}")
    String getTMLIBELLE();
    @Value(value = "#{target.TMDEBIT}")
    Double getTMDEBIT();
    @Value(value = "#{target.TMCREDIT}")
    Double getTMCREDIT();
    @Value(value = "#{target.SOLDE}")
    Double getSOLDE();
}

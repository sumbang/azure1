package tv.wouri.azure.models;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface TTransactionmutualiste {
    @Value(value = "#{target.TMID}")
    Integer getTMID();
    @Value(value = "#{target.TMTYPE}")
    String getTMTYPE();
    @Value(value = "#{target.TMNUMERO}")
    Integer getTMNUMERO();
    @Value(value = "#{target.MUNUMERO}")
    Integer getMUNUMERO();
    @Value(value = "#{target.TMDATE}")
    Date getTMDATE();
    @Value(value = "#{target.TMLIBELLE}")
    String getTMLIBELLE();
    @Value(value = "#{target.TMDEBIT}")
    Double getTMDEBIT();
    @Value(value = "#{target.TMCREDIT}")
    Double getTMCREDIT();
    @Value(value = "#{target.TMSOLDE}")
    Double getTMSOLDE();

}

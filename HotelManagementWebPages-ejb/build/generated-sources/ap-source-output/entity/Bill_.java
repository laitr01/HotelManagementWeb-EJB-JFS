package entity;

import entity.reservation.Reservation;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(Bill.class)
public class Bill_ { 

    public static volatile SingularAttribute<Bill, BigDecimal> amount;
    public static volatile SingularAttribute<Bill, Reservation> reservationID;
    public static volatile SingularAttribute<Bill, BigDecimal> service;
    public static volatile SingularAttribute<Bill, Integer> billID;
    public static volatile SingularAttribute<Bill, String> fullname;
    public static volatile SingularAttribute<Bill, Date> paymentDate;
    public static volatile SingularAttribute<Bill, BigDecimal> rent;

}
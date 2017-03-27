package entity;

import entity.Services;
import entity.reservation.Reservation;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(ServiceDetail.class)
public class ServiceDetail_ { 

    public static volatile SingularAttribute<ServiceDetail, Date> dateOfUse;
    public static volatile SingularAttribute<ServiceDetail, Reservation> reservationID;
    public static volatile SingularAttribute<ServiceDetail, BigDecimal> price;
    public static volatile SingularAttribute<ServiceDetail, Services> serviceName;
    public static volatile SingularAttribute<ServiceDetail, Integer> serviceDetailID;

}
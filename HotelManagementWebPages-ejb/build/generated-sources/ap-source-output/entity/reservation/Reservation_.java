package entity.reservation;

import entity.Bill;
import entity.Customer;
import entity.ServiceDetail;
import entity.reservation.ReservationDetail;
import entity.room.Rooms;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile CollectionAttribute<Reservation, ServiceDetail> serviceDetailCollection;
    public static volatile SingularAttribute<Reservation, Integer> reservationID;
    public static volatile SingularAttribute<Reservation, Date> endDate;
    public static volatile SingularAttribute<Reservation, BigDecimal> paid;
    public static volatile SingularAttribute<Reservation, Customer> customerID;
    public static volatile CollectionAttribute<Reservation, ReservationDetail> reservationDetailCollection;
    public static volatile CollectionAttribute<Reservation, Bill> billCollection;
    public static volatile SingularAttribute<Reservation, Date> startDate;
    public static volatile SingularAttribute<Reservation, Integer> numberOfPeople;
    public static volatile SingularAttribute<Reservation, Rooms> roomID;
    public static volatile SingularAttribute<Reservation, String> status;

}
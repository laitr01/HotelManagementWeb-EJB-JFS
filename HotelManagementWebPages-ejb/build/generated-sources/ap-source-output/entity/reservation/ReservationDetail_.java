package entity.reservation;

import entity.reservation.Reservation;
import entity.reservation.ReservationDetailPK;
import entity.room.Rooms;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(ReservationDetail.class)
public class ReservationDetail_ { 

    public static volatile SingularAttribute<ReservationDetail, Date> date;
    public static volatile SingularAttribute<ReservationDetail, ReservationDetailPK> reservationDetailPK;
    public static volatile SingularAttribute<ReservationDetail, Rooms> rooms;
    public static volatile SingularAttribute<ReservationDetail, BigDecimal> rate;
    public static volatile SingularAttribute<ReservationDetail, Reservation> reservation;

}
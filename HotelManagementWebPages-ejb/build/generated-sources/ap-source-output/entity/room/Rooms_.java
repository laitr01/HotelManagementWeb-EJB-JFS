package entity.room;

import entity.RoomType;
import entity.reservation.Reservation;
import entity.reservation.ReservationDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(Rooms.class)
public class Rooms_ { 

    public static volatile SingularAttribute<Rooms, String> roomStatus;
    public static volatile SingularAttribute<Rooms, String> image3;
    public static volatile CollectionAttribute<Rooms, Reservation> reservationCollection;
    public static volatile CollectionAttribute<Rooms, ReservationDetail> reservationDetailCollection;
    public static volatile SingularAttribute<Rooms, String> image1;
    public static volatile SingularAttribute<Rooms, String> image2;
    public static volatile SingularAttribute<Rooms, Integer> roomID;
    public static volatile SingularAttribute<Rooms, String> roomName;
    public static volatile SingularAttribute<Rooms, RoomType> roomTypeID;

}
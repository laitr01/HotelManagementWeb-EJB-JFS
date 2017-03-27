package entity;

import entity.room.Rooms;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(RoomType.class)
public class RoomType_ { 

    public static volatile CollectionAttribute<RoomType, Rooms> roomsCollection;
    public static volatile SingularAttribute<RoomType, BigDecimal> roomRate;
    public static volatile SingularAttribute<RoomType, String> note;
    public static volatile SingularAttribute<RoomType, Integer> numOfPeople;
    public static volatile SingularAttribute<RoomType, String> roomTypeName;
    public static volatile SingularAttribute<RoomType, Integer> roomTypeID;

}
package entity.room;

import entity.room.Rooms;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T00:35:37")
@StaticMetamodel(RoomType.class)
public class RoomType_ { 

    public static volatile CollectionAttribute<RoomType, Rooms> roomsCollection;
    public static volatile SingularAttribute<RoomType, Double> roomRate;
    public static volatile SingularAttribute<RoomType, String> note;
    public static volatile SingularAttribute<RoomType, String> roomTypeName;
    public static volatile SingularAttribute<RoomType, Integer> roomTypeID;
    public static volatile SingularAttribute<RoomType, Integer> numOfChild;
    public static volatile SingularAttribute<RoomType, Integer> numOfAdult;

}
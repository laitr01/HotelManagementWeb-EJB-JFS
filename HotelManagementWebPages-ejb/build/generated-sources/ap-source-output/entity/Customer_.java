package entity;

import entity.Feedback;
import entity.reservation.Reservation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> address;
    public static volatile SingularAttribute<Customer, String> gender;
    public static volatile SingularAttribute<Customer, String> phone;
    public static volatile SingularAttribute<Customer, Date> dob;
    public static volatile CollectionAttribute<Customer, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Customer, Integer> customerID;
    public static volatile SingularAttribute<Customer, String> fullName;
    public static volatile CollectionAttribute<Customer, Reservation> reservationCollection;
    public static volatile SingularAttribute<Customer, String> personIdentifier;
    public static volatile SingularAttribute<Customer, String> company;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> status;

}
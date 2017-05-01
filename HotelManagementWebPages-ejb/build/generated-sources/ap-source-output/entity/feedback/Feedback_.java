package entity.feedback;

import entity.customer.Customer;
import entity.employee.Employee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T00:35:37")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Date> dateFeedback;
    public static volatile SingularAttribute<Feedback, String> feedback;
    public static volatile SingularAttribute<Feedback, String> question;
    public static volatile SingularAttribute<Feedback, Date> dateReceive;
    public static volatile SingularAttribute<Feedback, Customer> customerID;
    public static volatile SingularAttribute<Feedback, Integer> feedbackID;
    public static volatile SingularAttribute<Feedback, Employee> employeeID;

}
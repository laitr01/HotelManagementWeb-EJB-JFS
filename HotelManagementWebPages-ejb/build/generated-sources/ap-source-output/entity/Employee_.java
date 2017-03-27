package entity;

import entity.Feedback;
import entity.News;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> passWord;
    public static volatile SingularAttribute<Employee, Character> role;
    public static volatile SingularAttribute<Employee, String> address;
    public static volatile SingularAttribute<Employee, String> gender;
    public static volatile CollectionAttribute<Employee, News> newsCollection;
    public static volatile CollectionAttribute<Employee, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Employee, String> fullName;
    public static volatile SingularAttribute<Employee, Integer> employeeID;
    public static volatile SingularAttribute<Employee, String> userName;
    public static volatile SingularAttribute<Employee, String> phone;
    public static volatile SingularAttribute<Employee, Date> dob;
    public static volatile SingularAttribute<Employee, String> position;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile SingularAttribute<Employee, String> status;

}
package entity.employee;

import entity.feedback.Feedback;
import entity.news.News;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T00:35:37")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> passWord;
    public static volatile SingularAttribute<Employee, String> role;
    public static volatile CollectionAttribute<Employee, News> newsCollection;
    public static volatile SingularAttribute<Employee, String> gender;
    public static volatile SingularAttribute<Employee, Date> dob;
    public static volatile CollectionAttribute<Employee, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Employee, Integer> employeeID;
    public static volatile SingularAttribute<Employee, String> userName;
    public static volatile SingularAttribute<Employee, String> email;

}
package entity;

import entity.Employee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Integer> newsID;
    public static volatile SingularAttribute<News, String> imageUri;
    public static volatile SingularAttribute<News, String> newsTitle;
    public static volatile SingularAttribute<News, Date> datePost;
    public static volatile SingularAttribute<News, Employee> employeeID;

}
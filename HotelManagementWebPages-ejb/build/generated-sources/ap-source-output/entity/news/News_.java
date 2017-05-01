package entity.news;

import entity.employee.Employee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T00:35:37")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Integer> newsID;
    public static volatile SingularAttribute<News, String> imageUri;
    public static volatile SingularAttribute<News, String> newsTitle;
    public static volatile SingularAttribute<News, Date> datePost;
    public static volatile SingularAttribute<News, Employee> employeeID;

}
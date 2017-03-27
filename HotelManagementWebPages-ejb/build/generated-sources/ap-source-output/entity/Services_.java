package entity;

import entity.ServiceDetail;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-27T19:11:50")
@StaticMetamodel(Services.class)
public class Services_ { 

    public static volatile CollectionAttribute<Services, ServiceDetail> serviceDetailCollection;
    public static volatile SingularAttribute<Services, BigDecimal> price;
    public static volatile SingularAttribute<Services, Integer> serviceID;
    public static volatile SingularAttribute<Services, String> serviceName;

}
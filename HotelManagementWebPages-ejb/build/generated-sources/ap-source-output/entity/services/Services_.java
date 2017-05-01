package entity.services;

import entity.services.ServiceDetail;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T00:35:37")
@StaticMetamodel(Services.class)
public class Services_ { 

    public static volatile CollectionAttribute<Services, ServiceDetail> serviceDetailCollection;
    public static volatile SingularAttribute<Services, BigDecimal> price;
    public static volatile SingularAttribute<Services, Integer> serviceID;
    public static volatile SingularAttribute<Services, String> serviceName;

}
package com.example.products.repository;

import com.example.products.constants.Constants;
import com.example.products.domain.Product;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;

/**
 * Created by a036862 on 7/21/16.
 */
@Repository
public class ProductRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /*Get product price details from the DB for a product*/

    public Product getProductDetails(Integer id) {

       Query query = new Query(Criteria.where(Constants.PRODUCTID).is(id));
       return mongoTemplate.findOne(query, Product.class);

    }

    /*Save product price details to the DB for a product*/
    public void saveProductPrice(double value, String currencyCode, Integer id){

        Update update = new Update();
        update.set(Constants.VALUE, value);
        update.set(Constants.CURRENCY_CODE, currencyCode);
        Query query = new Query(Criteria.where(Constants.PRODUCTID).is(id));
        mongoTemplate.updateFirst(query, update, Product.class);

    }
}

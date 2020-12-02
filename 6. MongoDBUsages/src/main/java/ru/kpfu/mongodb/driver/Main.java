package ru.kpfu.mongodb.driver;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("education");
        MongoCollection<Document> collection = database.getCollection("courses");
        //INSERT document in collection

        Document course = new Document("_id", new ObjectId());
        course.append("active", false)
                .append("description", "lorem")
                .append("title", "lorem");
        InsertOneResult insertOneResult = collection.insertOne(course);
        System.out.println("Inserted " + insertOneResult.getInsertedId());

        //UPDATE document in collection

        UpdateResult updateResult = collection.updateOne(eq("title", "Hello1"), set("title", "Hello"));
        System.out.println("Updated " + updateResult.getModifiedCount() + " values");
        //DELETE document from collection

        DeleteResult result = collection.deleteOne(eq("title", "Hello"));
        System.out.println("Deleted " + result.getDeletedCount() + " docs");

    }
}

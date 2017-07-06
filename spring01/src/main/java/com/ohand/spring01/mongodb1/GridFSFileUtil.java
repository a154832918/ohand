package com.ohand.spring01.mongodb1;

import java.io.InputStream;
import org.springframework.data.mongodb.MongoDbFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class GridFSFileUtil {
    /** 
     * 保存文件到MongoDB GridFS 
     *  
     * @param in            - 需要保存文件的输入流 
     * @param id            - 需要保存文件的唯一ID 
     * @param fileName      - 需要保存文件的文件名 
     * @param contentType  - 需要保存文件的文件类型 
     * @param downloadName - 需要保存文件被下载时的文件名 
     */   
    public static GridFSInputFile  saveMongoDBFile(MongoDbFactory mongodbfactory,String tableName,InputStream in, String id, String fileName, String contentType, String downloadName){  
	       GridFS fs = new GridFS(mongodbfactory.getDb(), tableName);  
	       GridFSInputFile fsFile = fs.createFile(in);  
	         fsFile.setId(id);  
	         fsFile.setFilename(fileName);  
	         fsFile.setContentType(contentType);  
	         fsFile.put("downloadName", downloadName);  
	         fsFile.save();  
	       return fsFile; 
    }  
    /** 
     * 从MongoDB GridFS文件系统中删除指定ID的文件 
     *  
     * @param id 
     */  
    public void remove(MongoDbFactory mongodbfactory , String id,String tableName) {  
        GridFS fs = new GridFS(mongodbfactory.getDb(), tableName);  
        BasicDBObject query = new BasicDBObject("_id", id);  
        fs.remove(query);  
    }  
    
    /** 
     * 从MongoDB GridFS文件系统中读取指定ID的文件 
     *  
     * @param id 
     */ 
    public static GridFSDBFile readMongoDBFileById(MongoDbFactory mongodbfactory ,Object id,String tableName){  
    	BasicDBObject query  = new BasicDBObject("_id", id);  
        GridFS gridFS = new GridFS(mongodbfactory.getDb(),tableName);  
        GridFSDBFile gridFSDBFile = gridFS.findOne(query); 
        return gridFSDBFile;  
    }
      
    /** 
     * 从MongoDB GridFS文件系统中批量删除指定ID的文件 
     * @param ids 
     */  
    public void batchRemove(MongoDbFactory mongodbfactory,String tableName,String... ids) {  
        GridFS fs = new GridFS(mongodbfactory.getDb(), tableName);  
        for(String id : ids){  
            BasicDBObject query = new BasicDBObject("_id", id);  
            fs.remove(query);  
        }  
    } 
}

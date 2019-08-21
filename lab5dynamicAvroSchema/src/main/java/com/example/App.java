package com.example;


import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        String schemaFile="src\\main\\resources\\user.avsc";
        String inRecFile="src\\main\\resources\\user.csv";
        String serializedRecFile="src\\main\\resources\\user_out.avro";
        //Read Schema
        Schema schema = new Schema.Parser().parse(new File(schemaFile));

        /*   To know the fields
        for (Schema.Field fld : schema.getFields()) {
            Schema fieldSchema=fld.schema();
            if(Schema.Type.UNION.equals(fieldSchema.getType()))
                System.out.println(fieldSchema.getTypes().get(0).getType());
            else
                System.out.println(fieldSchema.getType());
        }*/
        serializer(schema, inRecFile ,serializedRecFile);
        deserializer(schema, serializedRecFile);


    }

    public static void serializer(Schema schema, String inFile, String outFile) throws  IOException{
        BufferedReader br;
        //Serialization
        File file = new File(outFile);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        //Read flat file or CSV record
        br= new BufferedReader(new FileReader(inFile));
        String line = br.readLine();
        while(line != null) {
            GenericRecord user1 = new GenericData.Record(schema);
            String lineToken[]=line.split(",");
            int i=0;
            for (Schema.Field fld : schema.getFields()) {
                if(fld.schema().isUnion()) {
                    if (fld.schema().getTypes().get(0).getType().equals(Schema.Type.INT))
                        user1.put(fld.name(), Integer.parseInt(lineToken[i++]));
                }
                else
                    user1.put(fld.name(), lineToken[i++]);
            }
            //Writing Serialized data to file
            dataFileWriter.append(user1);
            line=br.readLine();
        }
        br.close();
        //Close file
        dataFileWriter.close();
        return;
    }

    public static void deserializer(Schema schema, String inFile) throws IOException {
        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File(inFile), datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }
        return;
    }
}

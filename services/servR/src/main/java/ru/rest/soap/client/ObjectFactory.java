package ru.rest.soap.client;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.serv_r.soap_client package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.serv_r.soap_client
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Students }
     * 
     * @return
     *     the new instance of {@link Students }
     */
    public Students createStudents() {
        return new Students();
    }

    /**
     * Create an instance of {@link StudentsArray }
     * 
     * @return
     *     the new instance of {@link StudentsArray }
     */
    public StudentsArray createStudentsArray() {
        return new StudentsArray();
    }

}

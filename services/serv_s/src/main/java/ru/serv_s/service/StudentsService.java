package ru.serv_s.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import ru.serv_s.domain.Student;
import java.util.List;

@WebService(serviceName = "Students")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface StudentsService {

    @WebMethod(operationName = "All")
    List<Student> findAll();

    @WebMethod(operationName = "OneToCard")
    Student findStudentsCard(@WebParam int cardNumber);
}

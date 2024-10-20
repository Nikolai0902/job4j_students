package ru.serv_s.configuration;

import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.serv_s.service.StudentsService;

/**
 * Конфигурация StudentsService для реализации SOAP интерфейса.
 * Точка входа.
 */
@Configuration
@AllArgsConstructor
public class SoapConfig {

    public Bus bus;

    @Bean
    public Endpoint endpoint(StudentsService studentsService) {
        EndpointImpl endpoint = new EndpointImpl(bus, studentsService);
        endpoint.publish("/service");
        return endpoint;
    }
}

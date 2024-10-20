package ru.serv_r.sevice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;
import ru.serv_r.dto.StudentDTO;
import ru.serv_r.exception.ServiceException;
import ru.serv_r.dto.StudentReplyDTO;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class KafkaProducerService {

    @Value("${topic.name.requests.all}")
    public String topicAllRequests;

    @Value("${topic.name.requests.one}")
    public String topicOneRequests;

    private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    public KafkaProducerService(@Qualifier("replyKafkaTemplate") ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate) {
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    public List<StudentReplyDTO> allStudents() throws ServiceException {
        String msgId = UUID.randomUUID().toString();
        ProducerRecord<String, String> record = new ProducerRecord<>(topicAllRequests,1, msgId, "");
        log.info("Веб-сервис R помещает запрос в брокер очередей сообщений");
        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
        List<StudentReplyDTO> list;
        try {
            ConsumerRecord<String, String> consumerRecord = replyFuture.get();
            TypeReference<List<StudentDTO>> typeReference = new TypeReference<List<StudentDTO>>() {
            };
            List<StudentDTO> dtoList = new ObjectMapper().readValue(consumerRecord.value(), typeReference);
            log.info("Веб-сервис R трансформирует ответ из XML  в JSON");
            list = dtoList.stream().map(this::mapperStudent).toList();
        } catch (InterruptedException | ExecutionException e) {
            throw new ServiceException("Error receiving the message: ", e);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Deserialization XML error: ", e);
        }
        return list;
    }

    public StudentReplyDTO oneStudents(int cardNumber) throws ServiceException {
        String msgId = UUID.randomUUID().toString();
        ProducerRecord<String, String> record = new ProducerRecord<>(topicOneRequests,2, msgId, String.valueOf(cardNumber));
        log.info("Веб-сервис R помещает запрос в брокер очередей сообщений");
        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
        StudentReplyDTO student;
        try {
            ConsumerRecord<String, String> consumerRecord = replyFuture.get();
            StudentDTO studentDTO = new ObjectMapper().readValue(consumerRecord.value(), StudentDTO.class);
            log.info("Веб-сервис R трансформирует ответ из XML  в JSON");
            student = mapperStudent(studentDTO);
        } catch (InterruptedException | ExecutionException e) {
            throw new ServiceException("Error receiving the message: ", e);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Deserialization XML error: ", e);
        }
        return student;
    }

    private StudentReplyDTO mapperStudent(StudentDTO dto) {
        return StudentReplyDTO.builder()
                .cardNumber(dto.getCardNumber())
                .faculty(dto.getFaculty())
                .surname(dto.getSurname())
                .name(dto.getName())
                .patronymic(dto.getPatronymic())
                .build();
    }
}

package Episante.back.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class DiagnosticSessionDTO {
    private String sessionId;
    private Date createdAt;
}

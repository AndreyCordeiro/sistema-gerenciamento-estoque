package com.sge.service.email;

import java.util.Map;

public interface EmailService {
    String enviarEmailTexto(String destinatario, String titulo, String mensagem);

    void enviarEmailTemplate(String destinatario, String titulo, Map<String, Object> propriedades);
}

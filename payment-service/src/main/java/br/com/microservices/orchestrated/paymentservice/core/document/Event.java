package br.com.microservices.orchestrated.paymentservice.core.document;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.ESagaStatus;
import br.com.microservices.orchestrated.orderservice.core.document.History;
import br.com.microservices.orchestrated.orderservice.core.document.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String id;
    private String transactionId;
    private String orderId;
    private Order payload;
    private String source;
    private ESagaStatus status;
    private List<History> histories;
    private LocalDateTime created;
}

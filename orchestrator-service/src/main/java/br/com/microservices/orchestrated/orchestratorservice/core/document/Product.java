package br.com.microservices.orchestrated.orchestratorservice.core.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String code;
    private double unitValue;
}

package bank.service.bankaccount.service;

import bank.service.bankaccount.event.TransactionEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

        public static void main(String[] args) {
            String json = "{\"accountId\":\"300156789\",\"customerId\":\"CUST001\",\"amount\":35.0,\"content\":\"string\",\"ict\":\"0\",\"ofsAccount\":\"301548623\",\"ofsCustomer\":\"CUST002\",\"category\":\"\",\"transactionStatus\":\"PENDING\"}";

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                TransactionEvent event = objectMapper.readValue(json, TransactionEvent.class);
                System.out.println("Conversion successful:");
                System.out.println(event.getAccountId());
            } catch (Exception e) {
                System.err.println("Conversion failed:");
                e.printStackTrace();
            }
        }

}

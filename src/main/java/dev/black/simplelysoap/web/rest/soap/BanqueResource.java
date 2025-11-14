package dev.black.simplelysoap.web.rest.soap;

import dev.black.simplelysoap.service.ClientService;
import dev.black.simplelysoap.service.PaymentService;
import dev.black.simplelysoap.service.TransferService;
import dev.black.simplelysoap.service.dto.ClientDTO;
import dev.black.simplelysoap.service.dto.PaymentDTO;
import dev.black.simplelysoap.service.dto.TransferDTO;
import dev.black.simplelysoap.soap.gen.*;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BanqueResource {

    private static final String NAMESPACE_URI = "http://www.black.dev/banque";
    private static final Logger log = LoggerFactory.getLogger(BanqueResource.class);

    @Autowired
    ClientService clientService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private PaymentService paymentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addClientRequest")
    @ResponsePayload
    public ClientResponse addClient(@RequestPayload AddClientRequest request) {
        ClientResponse response = new ClientResponse();
        ServiceStatus status = new ServiceStatus();
        ClientInfo soapClient = request.getClient();

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setNumeroTel(soapClient.getNumeroTel());
        clientDTO.setSolde(soapClient.getSolde());

        try {
            ClientDTO savedClient = clientService.save(clientDTO);
            status.setMessage("Youpi you get you account. Enjoy you it !");
            status.setStatus("SUCCESS");

            soapClient.setSolde(savedClient.getSolde());
        } catch (Exception e) {
            log.error("Error adding client", e);
            status.setMessage("Client registration failed. Please retry later or contact support");
            status.setStatus("ERROR");
        }

        response.setStatus(status);
        response.setClient(soapClient);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSoldeRequest")
    @ResponsePayload
    public SoldeResponse getSolde(@RequestPayload GetSoldeRequest request) {
        SoldeResponse response = new SoldeResponse();
        ClientDTO clientDTO = clientService.findOneByNumeroTel(request.getTel()).orElse(null);
        if (clientDTO == null) {
            response.setSolde(0L);
        } else {
            response.setSolde(clientDTO.getSolde());
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTransferRequest")
    @ResponsePayload
    public TransferResponse addTransfer(@RequestPayload AddTransferRequest request) {
        TransferResponse response = new TransferResponse();
        ServiceStatus status = new ServiceStatus();
        try {
            ClientDTO receiver = clientService.findOneByNumeroTel(request.getTransfer().getNumberReceive()).orElse(null);
            if (receiver == null) {
                status.setMessage("Receiver not found");
                status.setStatus("ERROR");
                return response;
            }

            ClientDTO sender = clientService.findOneByNumeroTel(request.getTransfer().getNumberSender()).orElse(null);
            if (sender == null) {
                status.setMessage("Sender not found");
                status.setStatus("ERROR");
            }
            TransferDTO transferDTO = new TransferDTO();
            transferDTO.setAmountSent(request.getTransfer().getAmountSent());
            transferDTO.setDateTransaction(Instant.now());
            transferDTO.setReceiver(receiver);
            transferDTO.setSender(sender);

            TransferDTO savedTransaction = transferService.save(transferDTO);

            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setAmountSent(savedTransaction.getAmountSent());
            transferInfo.setNumberReceive(savedTransaction.getReceiver().getNumeroTel());
            transferInfo.setNumberSender(savedTransaction.getSender().getNumeroTel());
            response.setTransfer(transferInfo);

            status.setMessage("Transfer added successfully");
            status.setStatus("SUCCESS");
            response.setStatus(status);

            return response;
        } catch (Exception e) {
            log.error("Error adding transfer", e);
            status.setMessage("Transfer failed. Please retry later or contact support");
            status.setStatus("ERROR");
            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaymentRequest")
    @ResponsePayload
    public PaymentResponse addPayment(@RequestPayload AddPaymentRequest request) {
        PaymentResponse response = new PaymentResponse();
        ServiceStatus status = new ServiceStatus();

        try {
            ClientDTO receiver = clientService.findOneByNumeroTel(request.getPayment().getNumberReceive()).orElse(null);
            if (receiver == null) {
                status.setMessage("Receiver not found");
                status.setStatus("ERROR");
                return response;
            }

            ClientDTO initiator = clientService
                .findOneByNumeroTel(773887568L)
                .orElse(clientService.save(new ClientDTO(773887568L, 1_000_000_000L)));

            PaymentDTO newPayment = new PaymentDTO();
            newPayment.setAmountSent(request.getPayment().getAmountSent());
            newPayment.setNumberReceive(receiver.getNumeroTel());
            newPayment.setInitiator(initiator);
            newPayment.setDateTransaction(Instant.now());
            paymentService.save(newPayment);

            status.setMessage("Payment added successfully");
            status.setStatus("SUCCESS");

            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setAmountSent(request.getPayment().getAmountSent());
            paymentInfo.setNumberReceive(receiver.getNumeroTel());

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

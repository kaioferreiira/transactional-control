package com.transaction.application.controller.v1;

import com.transaction.application.controller.v1.request.TransactionRequestV1;
import com.transaction.domain.transaction.TransactionServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/transactions")
@Tag(name = "transactions-v1", description = "Group of APIs for handling financial transactions")
@Slf4j
public class TransactionControllerV1 {

    private final TransactionServiceV1 transactionServiceV1;

    @Operation(description = "Create a financial transaction")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Transaction created successfully"),
        @ApiResponse(responseCode = "404", description = "resource not found"),
        @ApiResponse(responseCode = "500", description = "Internal error processing transaction")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postLibraryEvent(@RequestBody @Valid TransactionRequestV1 transactionRequest) {

        log.info("Starting transaction submission . . . ");

        transactionServiceV1.sendTransaction(transactionRequest);

        log.info("transaction sent successfully . . .");

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionRequest);
    }

}



package io.github.neharoshni.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.neharoshni.demo.entities.PortfolioInstrument;
import io.github.neharoshni.demo.models.http.*;
import io.github.neharoshni.demo.services.TradeService;
import io.github.neharoshni.demo.services.PortfolioService;
import io.github.neharoshni.demo.util.AuthTokenValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Tag(name = "Trade", description = "Trading APIs")
@RestController
@RequestMapping("/api/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @Autowired
    PortfolioService portfolioService;

    @Autowired
    AuthTokenValidator authTokenValidator;

    @Operation(summary = "Place an order")

    @ApiResponses({@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")}), @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public boolean placeOrder(@RequestHeader(value = "access-token") String accessToken, @RequestBody final PlaceOrder order) {
        try {
            Order newOrder = new Order(order.getInstrument(), new Date(System.currentTimeMillis()), order.getQuantity(), order.getUserId(), UUID.randomUUID().toString(), order.getType());
            return tradeService.placeOrder(newOrder);
            // if (authTokenValidator.validateAuthToken(accessToken)) {
            // } else {
            //     throw new IllegalArgumentException("Invalid token!");
            // }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Operation(summary = "List orders of a user")
//    @ApiResponses({@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")}), @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
//    @GetMapping("/orders/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<UserOrder> listOrders(@RequestHeader(value = "access-token") String accessToken, @PathVariable String userId) {
//        try {
//            if (authTokenValidator.validateAuthToken(accessToken)) {
//                return tradeService.listOrdersOfUser(userId);
//            } else {
//                throw new IllegalArgumentException("Invalid token!");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Operation(summary = "Get portfolio of a user")

    @ApiResponses({@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")}), @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/portfolio/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioItem> getPortfolioOfUser(@RequestHeader(value = "access-token") String accessToken, @PathVariable String userId) throws JsonProcessingException {
        try {
            return tradeService.getPortfolioOfUser(userId);
            // if (authTokenValidator.validateAuthToken(accessToken)) {
            // } else {
            //     throw new IllegalArgumentException("Invalid token!");
            // }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get portfolio of a user with instrument price averages")

    @ApiResponses({@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")}), @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/portfolio/averages/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PortfolioItemAverage> getPortfolioWithInstrumentPriceAveragesOfUser(@RequestHeader(value = "access-token") String accessToken, @PathVariable String userId) throws JsonProcessingException {
        try {
            return tradeService.getPortfolioWithInstrumentPriceAveragesOfUser(userId);
            // if (authTokenValidator.validateAuthToken(accessToken)) {
            // } else {
            //     throw new IllegalArgumentException("Invalid token!");
            // }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get user's holdings")

    @ApiResponses({@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")}), @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/holdings/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserHolding> holdings(@RequestHeader(value = "access-token") String accessToken, @PathVariable Long userId) {
        try {
            List<PortfolioInstrument> portfolio = portfolioService.getAllPortfolioInstrumentsByUserId(userId);
            return new ArrayList<>();
            // if (authTokenValidator.validateAuthToken(accessToken)) {
            // } else {
            //     throw new IllegalArgumentException("Invalid token!");
            // }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
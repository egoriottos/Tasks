package org.example.crud;

import org.example.crud.commands.orderItem.CreateOrderItemCommand;
import org.example.crud.commands.orderItem.OrderItemRequest;
import org.example.crud.commands.orderItem.OrderItemResponse;
import org.example.crud.commands.orderItem.UpdateOrderItemCommand;
import org.example.crud.controller.OrderItemController;
import org.example.crud.entity.OrderItem;
import org.example.crud.services.interfaces.OrderItemServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class OrderItemControllerTest {
    @InjectMocks
    private OrderItemController orderItemController;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private OrderItemServiceInterface orderItemService;
    private OrderItem orderItem;
    private OrderItemRequest orderItemRequest;
    private OrderItemResponse orderItemResponse;
    private CreateOrderItemCommand createOrderItemCommand;
    private UpdateOrderItemCommand updateOrderItemCommand;

    @Test
    public void testCreateOrderItem() {
        MockitoAnnotations.openMocks(this);
        orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemDescription("description");
        orderItemRequest.setItemName("name");
        orderItemRequest.setQuantity(3);
        orderItemRequest.setPrice(new BigDecimal(30));

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand();
        createOrderItemCommand.setItemDescription("description");
        createOrderItemCommand.setItemName("name");
        createOrderItemCommand.setQuantity(3);
        createOrderItemCommand.setPrice(new BigDecimal(30));

        orderItem = OrderItem.builder()
                .itemName(createOrderItemCommand.getItemName())
                .itemDescription(createOrderItemCommand.getItemDescription())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        when(modelMapper.map(any(OrderItemRequest.class), eq(CreateOrderItemCommand.class))).thenReturn(createOrderItemCommand);
        ResponseEntity<String> response = orderItemController.create(orderItemRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order item created", response.getBody());
    }

    @Test
    public void testGetAll() {
        MockitoAnnotations.openMocks(this);
        orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemDescription("description");
        orderItemRequest.setItemName("name");
        orderItemRequest.setQuantity(3);
        orderItemRequest.setPrice(new BigDecimal(30));

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand();
        createOrderItemCommand.setItemDescription("description");
        createOrderItemCommand.setItemName("name");
        createOrderItemCommand.setQuantity(3);
        createOrderItemCommand.setPrice(new BigDecimal(30));

        orderItem = OrderItem.builder()
                .itemName(createOrderItemCommand.getItemName())
                .itemDescription(createOrderItemCommand.getItemDescription())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        orderItemResponse = new OrderItemResponse();
        orderItemResponse.setItemDescription(orderItem.getItemDescription());
        orderItemResponse.setItemName(orderItem.getItemName());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setCreatedAt(orderItem.getCreatedAt());

        when(orderItemService.findAll()).thenReturn(List.of(orderItem));
        when(modelMapper.map(any(OrderItem.class), eq(OrderItemResponse.class))).thenReturn(orderItemResponse);

        ResponseEntity<List<OrderItemResponse>> response = orderItemController.getAll();

        verify(orderItemService).findAll();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testFindById() {
        MockitoAnnotations.openMocks(this);
        orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemDescription("description");
        orderItemRequest.setItemName("name");
        orderItemRequest.setQuantity(3);
        orderItemRequest.setPrice(new BigDecimal(30));

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand();
        createOrderItemCommand.setItemDescription("description");
        createOrderItemCommand.setItemName("name");
        createOrderItemCommand.setQuantity(3);
        createOrderItemCommand.setPrice(new BigDecimal(30));

        orderItem = OrderItem.builder()
                .itemName(createOrderItemCommand.getItemName())
                .itemDescription(createOrderItemCommand.getItemDescription())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        orderItemResponse = new OrderItemResponse();
        orderItemResponse.setItemDescription(orderItem.getItemDescription());
        orderItemResponse.setItemName(orderItem.getItemName());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setCreatedAt(orderItem.getCreatedAt());

        when(modelMapper.map(orderItem, OrderItemResponse.class)).thenReturn(orderItemResponse);
        when(orderItemService.findById(orderItem.getId())).thenReturn(orderItem);
        ResponseEntity<OrderItemResponse> response = orderItemController.getById(orderItem.getId());
        verify(orderItemService).findById(orderItem.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderItemResponse, response.getBody());
    }

    @Test
    public void testDeleteById() {
        MockitoAnnotations.openMocks(this);
        orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemDescription("description");
        orderItemRequest.setItemName("name");
        orderItemRequest.setQuantity(3);
        orderItemRequest.setPrice(new BigDecimal(30));

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand();
        createOrderItemCommand.setItemDescription("description");
        createOrderItemCommand.setItemName("name");
        createOrderItemCommand.setQuantity(3);
        createOrderItemCommand.setPrice(new BigDecimal(30));

        orderItem = OrderItem.builder()
                .itemName(createOrderItemCommand.getItemName())
                .itemDescription(createOrderItemCommand.getItemDescription())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        orderItemResponse = new OrderItemResponse();
        orderItemResponse.setItemDescription(orderItem.getItemDescription());
        orderItemResponse.setItemName(orderItem.getItemName());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setCreatedAt(orderItem.getCreatedAt());

        ResponseEntity<String> response = orderItemController.delete(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order item deleted", response.getBody());
    }

    @Test
    public void testUpdate() {
        MockitoAnnotations.openMocks(this);
        orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemDescription("description");
        orderItemRequest.setItemName("name");
        orderItemRequest.setQuantity(3);
        orderItemRequest.setPrice(new BigDecimal(30));

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand();
        createOrderItemCommand.setItemDescription("description");
        createOrderItemCommand.setItemName("name");
        createOrderItemCommand.setQuantity(3);
        createOrderItemCommand.setPrice(new BigDecimal(30));

        orderItem = OrderItem.builder()
                .itemName(createOrderItemCommand.getItemName())
                .itemDescription(createOrderItemCommand.getItemDescription())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        orderItemResponse = new OrderItemResponse();
        orderItemResponse.setItemDescription(orderItem.getItemDescription());
        orderItemResponse.setItemName(orderItem.getItemName());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setCreatedAt(orderItem.getCreatedAt());

        updateOrderItemCommand = new UpdateOrderItemCommand();
        updateOrderItemCommand.setItemDescription("description update");
        updateOrderItemCommand.setItemName("name update");
        updateOrderItemCommand.setQuantity(3);
        updateOrderItemCommand.setPrice(new BigDecimal(50));
        orderItem.setUpdatedAt(LocalDateTime.now());

        when(modelMapper.map(orderItemRequest, UpdateOrderItemCommand.class)).thenReturn(updateOrderItemCommand);
        ResponseEntity<String> response = orderItemController.update(1L, orderItemRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order item updated", response.getBody());
    }

    @Test
    public void testGetByItemName() {
        MockitoAnnotations.openMocks(this);
        orderItemRequest = new OrderItemRequest();
        orderItemRequest.setItemDescription("description");
        orderItemRequest.setItemName("name");
        orderItemRequest.setQuantity(3);
        orderItemRequest.setPrice(new BigDecimal(30));

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        CreateOrderItemCommand createOrderItemCommand = new CreateOrderItemCommand();
        createOrderItemCommand.setItemDescription("description");
        createOrderItemCommand.setItemName("name");
        createOrderItemCommand.setQuantity(3);
        createOrderItemCommand.setPrice(new BigDecimal(30));

        orderItem = OrderItem.builder()
                .itemName(createOrderItemCommand.getItemName())
                .itemDescription(createOrderItemCommand.getItemDescription())
                .quantity(createOrderItemCommand.getQuantity())
                .price(createOrderItemCommand.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        orderItemResponse = new OrderItemResponse();
        orderItemResponse.setItemDescription(orderItem.getItemDescription());
        orderItemResponse.setItemName(orderItem.getItemName());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setCreatedAt(orderItem.getCreatedAt());

        when(orderItemService.findByItemName("name")).thenReturn(orderItem);
        when(modelMapper.map(orderItem, OrderItemResponse.class)).thenReturn(orderItemResponse);
        ResponseEntity<OrderItemResponse> response = orderItemController.getByItemName("name");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderItemResponse, response.getBody());
    }
}

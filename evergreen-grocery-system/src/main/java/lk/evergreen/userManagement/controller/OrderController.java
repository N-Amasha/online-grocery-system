package lk.evergreen.grocery.controller;

import lk.evergreen.grocery.dto.OrderRequest;
import lk.evergreen.grocery.entity.Order;
import lk.evergreen.grocery.service.OrderService;
import lk.evergreen.grocery.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@
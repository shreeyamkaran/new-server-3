package com.beehyv.server.controller;

import com.beehyv.server.dto.NotificationDto;
import com.beehyv.server.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications/")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/connect/{token}")
    public SseEmitter connect(@PathVariable("token") String token, Authentication authentication) {
        return notificationService.connect(token, authentication);
    }

    @GetMapping("/")
    public List<NotificationDto> fetchAllNotifications(Authentication authentication) {
        return notificationService.fetchAllNotifications(authentication);
    }

    @PostMapping("/send/projects/{project-id}/tasks/{task-id}")
    public NotificationDto sendNotificationToEmployee(@PathVariable("project-id") Long projectId, @PathVariable("task-id") Long taskId, @RequestBody NotificationDto notification) {
        return notificationService.sendNotificationToEmployee(projectId, taskId, notification);
    }

}

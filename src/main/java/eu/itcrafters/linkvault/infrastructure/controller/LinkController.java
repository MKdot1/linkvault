package eu.itcrafters.linkvault.infrastructure.controller;

import eu.itcrafters.linkvault.infrastructure.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/link/all")
    public List<LinkInfo> getAllLinks() {
        return linkService.getAllLinks();
    }
}


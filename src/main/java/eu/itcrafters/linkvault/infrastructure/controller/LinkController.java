package eu.itcrafters.linkvault.infrastructure.controller;

import eu.itcrafters.linkvault.infrastructure.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/link/all")
    public List<LinkInfo> getAllLinks() {
        return linkService.getAllLinks();
    }

    @GetMapping("/link/{id}")
    public LinkInfo getLinkById(@PathVariable Integer id) {
        return linkService.getLinkById(id);
    }

    @PostMapping("/link")
    public LinkInfo addLink(@RequestBody LinkCreateRequest request) {
        return linkService.addLink(request);
    }

    @PutMapping("/link/{id}")
    public LinkInfo updateLink(@PathVariable Integer id, @RequestBody LinkUpdateRequest request) {
        return linkService.updateLink(id, request);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Integer id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}
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
    public List<LinkInfoDTO> getAllLinks() {
        return linkService.getAllLinks();
    }

    @GetMapping("/link/{id}")
    public LinkInfoDTO getLinkById(@PathVariable Integer id) {
        return linkService.getLinkById(id);
    }

    @PostMapping("/link")
    public LinkInfoDTO addLink(@RequestBody LinkCreateRequestDTO request) {
        return linkService.addLink(request);
    }

    @PutMapping("/link/{id}")
    public LinkInfoDTO updateLink(@PathVariable Integer id, @RequestBody LinkUpdateRequestDTO request) {
        return linkService.updateLink(id, request);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Integer id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}
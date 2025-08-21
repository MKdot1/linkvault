package eu.itcrafters.linkvault.infrastructure.service;

import eu.itcrafters.linkvault.infrastructure.controller.LinkInfo;
import eu.itcrafters.linkvault.infrastructure.persistence.link.Link;
import eu.itcrafters.linkvault.infrastructure.persistence.link.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

public List<LinkInfo> getAllLinks() {
        List<Link> links = linkRepository.findAll();

        return linkMapper.toLinkInfoList(links);
    }


}

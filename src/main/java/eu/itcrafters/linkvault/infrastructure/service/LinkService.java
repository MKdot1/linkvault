package eu.itcrafters.linkvault.infrastructure.service;

import eu.itcrafters.linkvault.infrastructure.controller.LinkCreateRequest;
import eu.itcrafters.linkvault.infrastructure.controller.LinkInfo;
import eu.itcrafters.linkvault.infrastructure.controller.LinkUpdateRequest;
import eu.itcrafters.linkvault.infrastructure.errorhandling.exception.DataNotFoundException;
import eu.itcrafters.linkvault.infrastructure.persistence.category.Category;
import eu.itcrafters.linkvault.infrastructure.persistence.link.Link;
import eu.itcrafters.linkvault.infrastructure.persistence.link.LinkMapper;
import eu.itcrafters.linkvault.infrastructure.persistence.link.LinkRepository;
import eu.itcrafters.linkvault.infrastructure.persistence.linktag.LinkTag;
import eu.itcrafters.linkvault.infrastructure.persistence.linktag.LinkTagRepository;
import eu.itcrafters.linkvault.infrastructure.persistence.tag.Tag;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;
    private final LinkTagRepository linkTagRepository;

    public List<LinkInfo> getAllLinks() {
        return getListOfAllLinks();
    }

    public LinkInfo getLinkById(Integer id) {
        return getTheLinkByID(id);
    }

    public LinkInfo addLink(LinkCreateRequest request) {
        return saveTheLink(request);
    }

    public LinkInfo updateLink(Integer id, LinkUpdateRequest request) {
        return updateTheLink(id, request);
    }

    @Transactional
    public void deleteLink(Integer id) {
        deleteTheLink(id);
    }

    private List<LinkInfo> getListOfAllLinks() {
        List<Link> links = linkRepository.findAll();

        return linkMapper.toLinkInfoList(links);
    }

    private LinkInfo getTheLinkByID(Integer id) {
        Link link = linkRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Link not found with id: " + id));

        return linkMapper.toLinkInfo(link);
    }

    private LinkInfo saveTheLink(LinkCreateRequest request) {
        // Build Link
        Link link = new Link();
        link.setAddress(request.getLinkAddress());
        link.setDescription(request.getDescription());
        link.setWorkflowStatus(request.getWorkflowStatus() != null ? request.getWorkflowStatus() : "waiting");
        link.setVisibility(request.getVisibility() != null ? request.getVisibility() : "private");
        link.setCreatedAt(Instant.now());

        // Attach category by ID
        Category cat = new Category();
        cat.setId(request.getCategoryId());
        link.setCategory(cat);

        // Save the link first (so it has an ID)
        link = linkRepository.save(link);

        // Attach multiple tags if provided
        if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
            for (Integer tagId : request.getTagIds()) {
                Tag tag = new Tag();
                tag.setId(tagId);

                LinkTag lt = new LinkTag();
                lt.setLink(link);
                lt.setTag(tag);
                linkTagRepository.save(lt);
            }
        }
        return linkMapper.toLinkInfo(link);
    }

    private LinkInfo updateTheLink(Integer id, LinkUpdateRequest request) {
        // Load existing the link or 404
        Link link = linkRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Link not found with id: " + id));

        // Update fields
        link.setAddress(request.getLinkAddress());
        link.setDescription(request.getDescription());

        if (request.getWorkflowStatus() != null) {
            link.setWorkflowStatus(request.getWorkflowStatus());
        }
        if (request.getVisibility() != null) {
            link.setVisibility(request.getVisibility());
        }

        // Switch category by ID
        Category cat = new Category();
        cat.setId(request.getCategoryId());
        link.setCategory(cat);

        // Save and return DTO
        Link saved = linkRepository.save(link);
        return linkMapper.toLinkInfo(saved);
    }

    private void deleteTheLink(Integer id) {
        if (!linkRepository.existsById(id)) {
            throw new DataNotFoundException("Link not found with id: " + id);
        }
        // remove junction rows first (to avoid FK constraint errors)
        linkTagRepository.deleteByLink_Id(id);
        linkRepository.deleteById(id);
    }
}

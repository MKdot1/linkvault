package eu.itcrafters.linkvault.infrastructure.persistence.link;

import eu.itcrafters.linkvault.infrastructure.controller.LinkInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LinkMapper {
    @Mapping(source = "id", target = "linkId")
    @Mapping(source = "address", target = "linkAddress")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "workflowStatus", target = "workflowStatus")
    @Mapping(source = "visibility", target = "visibility")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(target = "tagNames", expression = "java(extractTagNames(link))")
    LinkInfo toLinkInfo(Link link);

    List<LinkInfo> toLinkInfoList(List<Link> links);

    // takes all tags if any and makes a list
    default List<String> extractTagNames(Link link) {
        if (link.getLinkTags() == null) {
            return java.util.Collections.emptyList();
        }
        return link.getLinkTags().stream().map(lt -> lt.getTag().getTagName()).toList();
    }
}
package eu.itcrafters.linkvault.infrastructure.service;

import eu.itcrafters.linkvault.infrastructure.controller.LinkInfo;
import eu.itcrafters.linkvault.infrastructure.persistence.link.Link;
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
    LinkInfo toLinkInfo(Link link);

    List<LinkInfo> toLinkInfoList(List<Link> links);

}
package eu.itcrafters.linkvault.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LinkUpdateRequest {
    private String linkAddress;
    private String description;
    private String workflowStatus;
    private String visibility;
    private Integer categoryId;
}
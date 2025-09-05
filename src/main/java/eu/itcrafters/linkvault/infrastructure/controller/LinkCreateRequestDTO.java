package eu.itcrafters.linkvault.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkCreateRequestDTO {
    private String linkAddress;
    private String description;
    private String workflowStatus;
    private String visibility;
    private Integer categoryId;
    private List<Integer> tagIds;

}
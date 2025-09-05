package eu.itcrafters.linkvault.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkInfoDTO implements Serializable {
    private Integer linkId;
    private String linkAddress;
    private String description;
    private String workflowStatus;
    private String visibility;
    private String categoryName;
    private List<String> tagNames;
}
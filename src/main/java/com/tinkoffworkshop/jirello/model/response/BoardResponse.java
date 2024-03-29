package com.tinkoffworkshop.jirello.model.response;

import com.tinkoffworkshop.jirello.model.dto.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Long id;

    private String title;

    private List<UserRoleDTO> users;
}

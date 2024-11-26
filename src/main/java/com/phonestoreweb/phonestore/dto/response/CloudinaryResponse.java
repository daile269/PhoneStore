package com.phonestoreweb.phonestore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CloudinaryResponse {
    private String publicId;
    private String url;
}

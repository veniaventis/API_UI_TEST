package api.models;

import lombok.Data;

@Data
public class UploadModel {
    private String server;
    private String photo;
    private String hash;
}

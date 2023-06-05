package com.GrooveSpring.outils;

public class AuthResponse extends ApiResponse {
    private Long musicienId;

    public AuthResponse(String message, Long musicienId) {
        super(message);
        this.musicienId = musicienId;
    }

    public Long getMusicienId() {
        return musicienId;
    }

    public void setMusicienId(Long musicienId) {
        this.musicienId = musicienId;
    }
}

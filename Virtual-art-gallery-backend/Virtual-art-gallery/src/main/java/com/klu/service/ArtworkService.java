package com.klu.service;

import com.klu.model.Artwork;
import java.util.List;

public interface ArtworkService {
    Artwork addArtwork(Artwork artwork);
    List<Artwork> getAllArtworks();
    Artwork getArtworkById(Long id);
    void deleteArtwork(Long id);
    
    Artwork approveArtwork(Long id);
}
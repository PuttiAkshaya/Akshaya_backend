package com.klu.service.impl;

import com.klu.model.Artwork;
import com.klu.repository.ArtworkRepository;
import com.klu.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Override
    public Artwork addArtwork(Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    @Override
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @Override
    public Artwork getArtworkById(Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteArtwork(Long id) {
        artworkRepository.deleteById(id);
    }

    @Override
    public Artwork approveArtwork(Long id) {
        Artwork art = artworkRepository.findById(id).orElse(null);
        if (art != null) {
            art.setApproved(true);
            return artworkRepository.save(art);
        }
        return null;
    }
}
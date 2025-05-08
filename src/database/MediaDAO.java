package database;

import models.Book;
import models.DVD;
import models.Magazine;
import models.Media;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;

public class MediaDAO {
    public void addMedia(Media media) throws SQLException {
        String sql = "INSERT INTO media (id, title, publication_year, is_available, type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, media.getId());
            stmt.setString(2, media.getTitle());
            stmt.setInt(3, media.getPublicationYear().getValue());
            stmt.setBoolean(4, media.isAvailable());
            stmt.setString(5, media.getClass().getSimpleName());

            stmt.executeUpdate();
        }
    }
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM media WHERE id = ?";
        Media media = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Créer le bon type de média selon la colonne 'type'
                // (implémentation simplifiée pour l'exemple)
                String type = rs.getString("type");
                if ("Book".equals(type)) {
                    media = new Book();
                } else if ("DVD".equals(type)) {
                    media = new DVD();
                } else if ("Magazine".equals(type)) {
                    media = new Magazine();
                }

                if (media != null) {
                    media.setId(rs.getInt("id"));
                    media.setTitle(rs.getString("title"));
                    media.setPublicationYear(Year.of(rs.getInt("publication_year")));
                    media.setAvailable(rs.getBoolean("is_available"));
                }
            }
        }
        return media;
    }

    public List<Media> getAllMedia() throws SQLException {
        List<Media> mediaList = new ArrayList<>();
        String sql = "SELECT * FROM media";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Media media = null;
                String type = rs.getString("type");

                if ("Book".equals(type)) {
                    media = new Book();
                } else if ("DVD".equals(type)) {
                    media = new DVD();
                } else if ("Magazine".equals(type)) {
                    media = new Magazine();
                }

                if (media != null) {
                    media.setId(rs.getInt("id"));
                    media.setTitle(rs.getString("title"));
                    media.setPublicationYear(Year.of(rs.getInt("publication_year")));
                    media.setAvailable(rs.getBoolean("is_available"));
                    mediaList.add(media);
                }
            }
        }
        return mediaList;
    }

    public void updateMedia(Media media) throws SQLException {
        String sql = "UPDATE media SET title = ?, publication_year = ?, is_available = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, media.getTitle());
            stmt.setInt(2, media.getPublicationYear().getValue());
            stmt.setBoolean(3, media.isAvailable());
            stmt.setInt(4, media.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteMedia(int id) throws SQLException {
        String sql = "DELETE FROM media WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
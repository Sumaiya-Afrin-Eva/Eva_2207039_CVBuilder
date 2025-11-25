package com.example.cv_builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Optional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CVDao {
    private final Database db = Database.getInstance();

    public CVData insert(CVData cv) throws SQLException {
        String sql = "INSERT INTO cv(fullName, jobTitle, email, phone, address, projects, skills, languages, experience, education, imageUrl) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cv.getFullName());
            ps.setString(2, cv.getJobTitle());
            ps.setString(3, cv.getEmail());
            ps.setString(4, cv.getPhone());
            ps.setString(5, cv.getAddress());
            ps.setString(6, cv.getProjects());
            ps.setString(7, cv.getSkills());
            ps.setString(8, cv.getLanguages());
            ps.setString(9, cv.getExperience());
            ps.setString(10, cv.getEducation());
            ps.setString(11, cv.getImageUrl());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cv.setId(rs.getInt(1));
                }
            }
        }
        return cv;
    }

    public boolean update(CVData cv) throws SQLException {
        if (cv.getId() <= 0) return false;
        String sql = "UPDATE cv SET fullName=?, jobTitle=?, email=?, phone=?, address=?, projects=?, skills=?, languages=?, experience=?, education=?, imageUrl=? WHERE id=?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cv.getFullName());
            ps.setString(2, cv.getJobTitle());
            ps.setString(3, cv.getEmail());
            ps.setString(4, cv.getPhone());
            ps.setString(5, cv.getAddress());
            ps.setString(6, cv.getProjects());
            ps.setString(7, cv.getSkills());
            ps.setString(8, cv.getLanguages());
            ps.setString(9, cv.getExperience());
            ps.setString(10, cv.getEducation());
            ps.setString(11, cv.getImageUrl());
            ps.setInt(12, cv.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM cv WHERE id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Optional<CVData> getById(int id) throws SQLException {
        String sql = "SELECT * FROM cv WHERE id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(rowToCV(rs));
            }
        }
        return Optional.empty();
    }

    public ObservableList<CVData> getAll() throws SQLException {
        ObservableList<CVData> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM cv ORDER BY created_at DESC";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rowToCV(rs));
        }
        return list;
    }

    private CVData rowToCV(ResultSet rs) throws SQLException {
        CVData cv = new CVData();
        cv.setId(rs.getInt("id"));
        cv.setFullName(rs.getString("fullName"));
        cv.setJobTitle(rs.getString("jobTitle"));
        cv.setEmail(rs.getString("email"));
        cv.setPhone(rs.getString("phone"));
        cv.setAddress(rs.getString("address"));
        cv.setProjects(rs.getString("projects"));
        cv.setSkills(rs.getString("skills"));
        cv.setLanguages(rs.getString("languages"));
        cv.setExperience(rs.getString("experience"));
        cv.setEducation(rs.getString("education"));
        cv.setImageUrl(rs.getString("imageUrl"));
        return cv;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM cv_data WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cv_database.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
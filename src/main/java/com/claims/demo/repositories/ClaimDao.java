package com.claims.demo.repositories;

import com.claims.demo.domain.Claim;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClaimDao {

    private final JdbcTemplate jdbcTemplate;

    public ClaimDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertClaim(Claim claim) {
        jdbcTemplate.update(
                "INSERT INTO claims (claim_id, policy_number, loss_type, date_of_loss, reported_at, state, county, zip_code, description, source_channel, source_reported_by, actor_type, actor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                claim.getId(),
                claim.getPolicyNumber(),
                claim.getLossType(),
                java.sql.Timestamp.from(claim.getDateOfLoss()),
                java.sql.Timestamp.from(claim.getReportedAtDate()),
                claim.getState(),
                claim.getCounty(),
                claim.getZipCode(),
                claim.getDescription(),
                claim.getSourceChannel(),
                claim.getSourceReportedBy(),
                claim.getActorType(),
                claim.getActorId()
        );
    }

    public List<Claim> getAllClaims() {
        return jdbcTemplate.query(
                "SELECT claim_id, policy_number, loss_type, date_of_loss, reported_at, state, county, zip_code, description, source_channel, source_reported_by, actor_type, actor_id FROM claims ORDER BY created_at DESC",
                (rs, rowNum) -> mapRowToClaim(rs)
        );
    }

    private Claim mapRowToClaim(ResultSet rs) throws SQLException {
        Claim claim = new Claim();

        claim.setId(rs.getString("claim_id"));
        claim.setPolicyNumber(rs.getString("policy_number"));
        claim.setLossType(rs.getString("loss_type"));

        java.sql.Timestamp tsDateOfLoss = rs.getTimestamp("date_of_loss");
        if (tsDateOfLoss != null) claim.setDateOfLoss(tsDateOfLoss.toInstant());

        java.sql.Timestamp tsReportedAt = rs.getTimestamp("reported_at");
        if (tsReportedAt != null) claim.setReportedAtDate(tsReportedAt.toInstant());

        claim.setState(rs.getString("state"));
        claim.setCounty(rs.getString("county"));
        claim.setZipCode(rs.getString("zip_code"));
        claim.setDescription(rs.getString("description"));
        claim.setSourceChannel(rs.getString("source_channel"));
        claim.setSourceReportedBy(rs.getString("source_reported_by"));
        claim.setActorType(rs.getString("actor_type"));
        claim.setActorId(rs.getString("actor_id"));

        return claim;
    }
}

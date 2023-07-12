package com.xmut303.pojo;

import java.math.BigDecimal;

public class FaceItem {
    String EntityId;
    String FaceId;
    BigDecimal Score;
    BigDecimal Confidence;
    String DbName;

    @Override
    public String toString() {
        return "FaceItem{" +
                "EntityId='" + EntityId + '\'' +
                ", FaceId='" + FaceId + '\'' +
                ", Score='" + Score + '\'' +
                ", Confidence='" + Confidence + '\'' +
                ", DbName='" + DbName + '\'' +
                '}';
    }

    public String getEntityId() {
        return EntityId;
    }

    public void setEntityId(String entityId) {
        EntityId = entityId;
    }

    public String getFaceId() {
        return FaceId;
    }

    public void setFaceId(String faceId) {
        FaceId = faceId;
    }

    public BigDecimal getScore() {
        return Score;
    }

    public void setScore(BigDecimal score) {
        Score = score;
    }

    public BigDecimal getConfidence() {
        return Confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        Confidence = confidence;
    }

    public String getDbName() {
        return DbName;
    }

    public void setDbName(String dbName) {
        DbName = dbName;
    }
}

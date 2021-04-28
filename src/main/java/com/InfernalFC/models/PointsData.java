package com.InfernalFC.models;

import lombok.Getter;
import lombok.Setter;

public class PointsData {
        @Getter @Setter
        public double sum;
        @Getter @Setter
        public String count;
        @Getter @Setter
        public PlayerData member;

        public PointsData() {
            member = new PlayerData();
        }
}

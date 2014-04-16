package net.onlineconsultations.domain;

public enum UserRole {
    ROLE_ADMIN {
        public String toString() {
            return "Administrator";
        }
    },
    ROLE_CONSULTANT {
        public String toString() {
            return "Consultant";
        }
    }
}

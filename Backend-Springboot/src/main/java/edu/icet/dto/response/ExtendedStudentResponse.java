package edu.icet.dto.response;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
public record ExtendedStudentResponse(Object responseBody) {
    public static class ExtendedStudentResponseBuilder {
        private Map<String, Object> responseBodyMap = new HashMap<>();

        public ExtendedStudentResponseBuilder responseBody(String key, Object value) {
            this.responseBodyMap.put(key, value);
            return this;
        }
    }
}
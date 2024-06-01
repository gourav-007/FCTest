package com.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("website")
    private String website;

    @JsonProperty("company")
    private Company company;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {

        @JsonProperty("street")
        private String street;

        @JsonProperty("suite")
        private String suite;

        @JsonProperty("city")
        private String city;

        @JsonProperty("zipcode")
        private String zipcode;

        @JsonProperty("geo")
        private Geo geo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geo {

        @JsonProperty("lat")
        private String lat;

        @JsonProperty("lng")
        private String lng;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Company {

        @JsonProperty("name")
        private String name;

        @JsonProperty("catchPhrase")
        private String catchPhrase;

        @JsonProperty("bs")
        private String bs;
    }
}

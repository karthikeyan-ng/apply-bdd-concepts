package com.techstack.stepdefinitions;

public enum ResourcePath {

    ADD_A_PLACE("/maps/api/place/add/json"),
    GET_A_PLACE("/maps/api/place/get/json");

    private String resource;

    ResourcePath(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}

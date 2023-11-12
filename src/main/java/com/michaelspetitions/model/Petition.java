package com.michaelspetitions.model;
import java.util.List;
import java.util.ArrayList;

public class Petition {

    private String id;
    private String name;
    private String description;
    private String scope;
    private List<Signature> signatures = new ArrayList<>();

    public Petition() {}

    public Petition(String id, String name, String description, String scope, List<Signature> signatures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.scope = scope;
        this.signatures = signatures;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void addSignature(Signature signature) {
        signatures.add(signature);
    }

}

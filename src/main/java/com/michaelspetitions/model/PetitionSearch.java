package com.michaelspetitions.model;

public class PetitionSearch {

        private String query;

        public PetitionSearch() {}

        public PetitionSearch(String query) {
            this.query = query;
        }
        public String getQuery() {
            return query;
        }
        public void setQuery(String query) {
            this.query = query;
        }

}

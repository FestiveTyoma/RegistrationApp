package com.myprojects.registrationapp.pojo;


public class Result {

    @com.squareup.moshi.Json(name = "wrapperType")
    private String wrapperType;
    @com.squareup.moshi.Json(name = "collectionType")
    private String collectionType;
    @com.squareup.moshi.Json(name = "artistId")
    private Integer artistId;
    @com.squareup.moshi.Json(name = "collectionId")
    private Integer collectionId;
    @com.squareup.moshi.Json(name = "amgArtistId")
    private Integer amgArtistId;
    @com.squareup.moshi.Json(name = "artistName")
    private String artistName;
    @com.squareup.moshi.Json(name = "collectionName")
    private String collectionName;
    @com.squareup.moshi.Json(name = "collectionCensoredName")
    private String collectionCensoredName;
    @com.squareup.moshi.Json(name = "artistViewUrl")
    private String artistViewUrl;
    @com.squareup.moshi.Json(name = "collectionViewUrl")
    private String collectionViewUrl;
    @com.squareup.moshi.Json(name = "artworkUrl60")
    private String artworkUrl60;
    @com.squareup.moshi.Json(name = "artworkUrl100")
    private String artworkUrl100;
    @com.squareup.moshi.Json(name = "collectionPrice")
    private Float collectionPrice;
    @com.squareup.moshi.Json(name = "collectionExplicitness")
    private String collectionExplicitness;
    @com.squareup.moshi.Json(name = "trackCount")
    private Integer trackCount;
    @com.squareup.moshi.Json(name = "copyright")
    private String copyright;
    @com.squareup.moshi.Json(name = "country")
    private String country;
    @com.squareup.moshi.Json(name = "currency")
    private String currency;
    @com.squareup.moshi.Json(name = "releaseDate")
    private String releaseDate;
    @com.squareup.moshi.Json(name = "primaryGenreName")
    private String primaryGenreName;

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(Integer amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Float getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Float collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

}
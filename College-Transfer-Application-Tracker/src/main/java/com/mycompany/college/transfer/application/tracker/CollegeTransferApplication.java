
package com.mycompany.college.transfer.application.tracker;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CollegeTransferApplication implements Serializable {
    private static final long serialVersionUID = 1L;
    private String collegeName;
    private String address;
    private Date applicationDate;
    private double cost;
    private String applicationPlatform;
    private List<String> recommenderNames;
    private String emailAddress;
    private Date recommendationRequestedDate;
    private Date recommendationRequiredDate;
    private Date expectedAcceptanceLetterDate;
    private boolean isEssayWritten;
    private boolean areTranscriptsSubmitted;

    public CollegeTransferApplication() {
    }

    public CollegeTransferApplication(String collegeName, String address, Date applicationDate, double cost, String applicationPlatform, List<String> recommenderNames, String emailAddress, Date recommendationRequestedDate, Date recommendationRequiredDate, Date expectedAcceptanceLetterDate, boolean isEssayWritten, boolean areTranscriptsSubmitted) {
        this.collegeName = collegeName;
        this.address = address;
        this.applicationDate = applicationDate;
        this.cost = cost;
        this.applicationPlatform = applicationPlatform;
        this.recommenderNames = recommenderNames;
        this.emailAddress = emailAddress;
        this.recommendationRequestedDate = recommendationRequestedDate;
        this.recommendationRequiredDate = recommendationRequiredDate;
        this.expectedAcceptanceLetterDate = expectedAcceptanceLetterDate;
        this.isEssayWritten = isEssayWritten;
        this.areTranscriptsSubmitted = areTranscriptsSubmitted;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getApplicationPlatform() {
        return applicationPlatform;
    }

    public void setApplicationPlatform(String applicationPlatform) {
        this.applicationPlatform = applicationPlatform;
    }

    public List<String> getRecommenderNames() {
        return recommenderNames;
    }

    public void setRecommenderNames(List<String> recommenderNames) {
        this.recommenderNames = recommenderNames;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getRecommendationRequestedDate() {
        return recommendationRequestedDate;
    }

    public void setRecommendationRequestedDate(Date recommendationRequestedDate) {
        this.recommendationRequestedDate = recommendationRequestedDate;
    }

    public Date getRecommendationRequiredDate() {
        return recommendationRequiredDate;
    }

    public void setRecommendationRequiredDate(Date recommendationRequiredDate) {
        this.recommendationRequiredDate = recommendationRequiredDate;
    }

    public Date getExpectedAcceptanceLetterDate() {
        return expectedAcceptanceLetterDate;
    }

    public void setExpectedAcceptanceLetterDate(Date expectedAcceptanceLetterDate) {
        this.expectedAcceptanceLetterDate = expectedAcceptanceLetterDate;
    }

    public boolean isIsEssayWritten() {
            return isEssayWritten;
    }

    public void setIsEssayWritten(boolean isEssayWritten) {
        this.isEssayWritten = isEssayWritten;
    }

    public boolean isAreTranscriptsSubmitted() {
        return areTranscriptsSubmitted;
    }

    public void setAreTranscriptsSubmitted(boolean areTranscriptsSubmitted) {
        this.areTranscriptsSubmitted = areTranscriptsSubmitted;
    }

    @Override
    public String toString() {
        return "CollegeTransferApplication{" + "collegeName=" + collegeName + ", address=" + address + ", applicationDate=" + applicationDate + ", cost=" + cost + ", applicationPlatform=" + applicationPlatform + ", recommenderNames=" + recommenderNames + ", emailAddress=" + emailAddress + ", recommendationRequestedDate=" + recommendationRequestedDate + ", recommendationRequiredDate=" + recommendationRequiredDate + ", expectedAcceptanceLetterDate=" + expectedAcceptanceLetterDate + ", isEssayWritten=" + isEssayWritten + ", areTranscriptsSubmitted=" + areTranscriptsSubmitted + '}';
    }

}

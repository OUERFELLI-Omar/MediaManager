package models;

import java.time.Year;

public class Magazine extends Media {
    private String publisher;
    private int issueNumber;

    public Magazine() {}

    public Magazine(int id, String title, Year publicationYear, String publisher, int issueNumber) {
        super(id, title, publicationYear, false); // Les magazines ne sont pas empruntables
        this.publisher = publisher;
        this.issueNumber = issueNumber;
    }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getIssueNumber() { return issueNumber; }
    public void setIssueNumber(int issueNumber) { this.issueNumber = issueNumber; }

    @Override
    public void displayInfo() {
        System.out.println("Magazine: " + getTitle() + " - Issue #" + issueNumber + " by " + publisher + " (" + getPublicationYear() + ")");
    }
}
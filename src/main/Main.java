package main;

import interfaces.emprunterInterface;
import models.*;
import database.MediaDAO;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MediaDAO mediaDAO = new MediaDAO();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Gestion de Bibliothèque ===");
            System.out.println("1. Ajouter un média");
            System.out.println("2. Lister tous les médias");
            System.out.println("3. Rechercher un média par ID");
            System.out.println("4. Mettre à jour un média");
            System.out.println("5. Supprimer un média");
            System.out.println("6. Emprunter un média");
            System.out.println("7. Retourner un média");
            System.out.println("8. Quitter");
            System.out.print("Choix : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consommer la nouvelle ligne

            try {
                switch (choice) {
                    case 1:
                        addMedia();
                        break;
                    case 2:
                        listAllMedia();
                        break;
                    case 3:
                        findMediaById();
                        break;
                    case 4:
                        updateMedia();
                        break;
                    case 5:
                        deleteMedia();
                        break;
                    case 6:
                        borrowMedia();
                        break;
                    case 7:
                        returnMedia();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
        }

        System.out.println("Programme terminé.");
    }

    private static void addMedia() throws Exception {
        System.out.println("\nType de média :");
        System.out.println("1. Livre");
        System.out.println("2. DVD");
        System.out.println("3. Magazine");
        System.out.print("Choix : ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Titre : ");
        String title = scanner.nextLine();

        System.out.print("Année de publication : ");
        int year = scanner.nextInt();
        scanner.nextLine();

        switch (typeChoice) {
            case 1: // Livre
                System.out.print("Auteur : ");
                String author = scanner.nextLine();

                System.out.print("Nombre de pages : ");
                int pageCount = scanner.nextInt();
                scanner.nextLine();

                System.out.print("ISBN : ");
                String isbn = scanner.nextLine();

                Book book = new Book(id, title, Year.of(year), author, pageCount, isbn);
                mediaDAO.addMedia(book);
                System.out.println("Livre ajouté avec succès.");
                break;

            case 2: // DVD
                System.out.print("Réalisateur : ");
                String director = scanner.nextLine();

                System.out.print("Durée (minutes) : ");
                int duration = scanner.nextInt();
                scanner.nextLine();

                DVD dvd = new DVD(id, title, Year.of(year), director, duration);
                mediaDAO.addMedia(dvd);
                System.out.println("DVD ajouté avec succès.");
                break;

            case 3: // Magazine
                System.out.print("Éditeur : ");
                String publisher = scanner.nextLine();

                System.out.print("Numéro : ");
                int issueNumber = scanner.nextInt();
                scanner.nextLine();

                Magazine magazine = new Magazine(id, title, Year.of(year), publisher, issueNumber);
                mediaDAO.addMedia(magazine);
                System.out.println("Magazine ajouté avec succès.");
                break;

            default:
                System.out.println("Type invalide.");
        }
    }

    private static void listAllMedia() throws Exception {
        List<Media> mediaList = mediaDAO.getAllMedia();

        if (mediaList.isEmpty()) {
            System.out.println("Aucun média trouvé.");
        } else {
            System.out.println("\nListe des médias :");
            for (Media media : mediaList) {
                media.displayInfo();
                System.out.println("Disponible : " + (media.isAvailable() ? "Oui" : "Non"));
                System.out.println("---------------");
            }
        }
    }

    private static void findMediaById() throws Exception {
        System.out.print("ID du média à rechercher : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Media media = mediaDAO.getMediaById(id);

        if (media != null) {
            System.out.println("\nMédia trouvé :");
            media.displayInfo();
            System.out.println("Disponible : " + (media.isAvailable() ? "Oui" : "Non"));
        } else {
            System.out.println("Aucun média trouvé avec l'ID " + id);
        }
    }

    private static void updateMedia() throws Exception {
        System.out.print("ID du média à mettre à jour : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Media media = mediaDAO.getMediaById(id);

        if (media != null) {
            System.out.println("\nAnciennes informations :");
            media.displayInfo();

            System.out.print("Nouveau titre : ");
            String newTitle = scanner.nextLine();

            System.out.print("Nouvelle année de publication : ");
            int newYear = scanner.nextInt();
            scanner.nextLine();

            media.setTitle(newTitle);
            media.setPublicationYear(Year.of(newYear));

            mediaDAO.updateMedia(media);
            System.out.println("Média mis à jour avec succès.");
        } else {
            System.out.println("Aucun média trouvé avec l'ID " + id);
        }
    }

    private static void deleteMedia() throws Exception {
        System.out.print("ID du média à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        mediaDAO.deleteMedia(id);
        System.out.println("Média supprimé avec succès.");
    }

    private static void borrowMedia() throws Exception {
        System.out.print("ID du média à emprunter : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Media media = mediaDAO.getMediaById(id);

        if (media instanceof emprunterInterface) {
            ((emprunterInterface) media).borrow();
            mediaDAO.updateMedia(media);
        } else {
            System.out.println("Ce type de média n'est pas empruntable.");
        }
    }

    private static void returnMedia() throws Exception {
        System.out.print("ID du média à retourner : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Media media = mediaDAO.getMediaById(id);

        if (media instanceof emprunterInterface) {
            ((emprunterInterface) media).returnItem();
            mediaDAO.updateMedia(media);
        } else {
            System.out.println("Ce type de média n'est pas empruntable.");
        }
    }
}
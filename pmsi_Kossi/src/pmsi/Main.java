package pmsi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import config.DbConfig;
import dao.DiagnosticDao;
import dao.HospitalisationDao;
import dao.PatientDao;
import model.Acte;
import model.Diagnostic;
import model.Hospitalisation;
import model.Patient;

public class Main {

	public static void main(String[] args) throws SQLException, ParseException, IOException {

		Connection connection = DriverManager
				.getConnection("" + DbConfig.url + "?user=" + DbConfig.user + "&password=" + DbConfig.password);
		PatientDao daoPatient = new PatientDao(connection);
		DiagnosticDao daoDiagnostic = new DiagnosticDao(connection);
		HospitalisationDao daoHospitalisation = new HospitalisationDao(connection);
		Scanner sc = new Scanner(System.in);
		int k = 0;
		int k2 = 0;
		int choix_menu_principal = 0;
		int choix_menu_statistiques = 0;
		String reponse_hospitalisation;

		
			System.out.println(" §-§-$-§-§-§-$-§-§-§-§-$-§-§-§-§-§-$-§-§-§-§-§-$");
			System.out.println(" *                                             *");
			System.out.println(" *           GESTION DES DONNEES               *");
			System.out.println(" *                   PMSI                      *");
			System.out.println(" *                                             *");
			System.out.println(" §-§-$-§-§-§-$-§-§-§-§-$-§-§-§-§-§-$-§-§-§-§-§-$\n");
			
			do {
			System.out.println(".........     MENU PRINCIPAL PMSI     ...........\n");

			System.out.println("1- ENREGISTRER UN NOUVEAU PATIENT ");
			System.out.println("2- RECHERCHER LES SEJOURS D'UN PATIENT ");
			System.out.println("3- NOMBRE ACTES D'UN PATIENT AUCOURS D'UNE PERIODE ");
			System.out.println("4- STATISTIQUES ");
			System.out.println("5- EXIT ");

			System.out.print("VEUILLEZ SELECTIONNER UN NUMERO: ");

			choix_menu_principal = sc.nextInt();

			switch (choix_menu_principal) {

			case 1: {

				System.out.println("CREATION D'UN NOUVEAU PATIENT");
				System.out.print("\tId patient: ");
				int idPatient = sc.nextInt();
				System.out.print("\tnom: ");
				sc.nextLine();
				String nom = sc.nextLine();
				System.out.print("\tPrenom: ");
				String prenom = sc.nextLine();
				System.out.print("\tSexe (M/F): ");
				int sexe = sc.nextLine().equals("M") ? 1 : 2;
				System.out.print("\tDate de naissance (yyyy-mm-dd): ");
				LocalDate date_Naissance = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);
				daoPatient.addPatient(new Patient(idPatient, nom, prenom, sexe, date_Naissance));
				System.out.print("Voulez-vous enregistrer une hospitalisation ?(Yes/No):");
				reponse_hospitalisation = sc.nextLine();

				if (reponse_hospitalisation.equals("Yes") || reponse_hospitalisation.equals("Y")) {

					System.out.println("ENREGISTREMENT D'UNE HOSPITALISATION");
					System.out.print("\tId Hospitalisation: ");
					int idHospitalisation = sc.nextInt();
					System.out.print("\tDate d'entree(yyyy-mm-dd): ");
					sc.nextLine();
					LocalDate dateEntree = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);
					System.out.print("\tDate de sortie(yyyy-mm-dd): ");
					LocalDate dateSortie = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);
					daoHospitalisation.addHospitalisation(
							new Hospitalisation(idHospitalisation, idPatient, dateEntree, dateSortie));

				}

				else {
					break;
				}

			}
				break;

			case 2: {
				System.out.println("\tVEUILLEZ SAISIR LES INFORMATIONS DU PATIENT");
				System.out.print("\tnom: ");
				sc.nextLine();
				String nom = sc.nextLine();
				System.out.print("\tprenom: ");
				String prenom = sc.nextLine();
				System.out.print("\tdate de naissance(yyyy-mm-dd): ");
				LocalDate date_Naissance = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);

				System.out.println();
				Patient mon_patient = daoPatient.getPatient(nom, prenom, date_Naissance);
				if (mon_patient.getNom() != null) {
					System.out.println("INFORMATIONS PATIENT");
					System.out.println("###########################################");
					System.out.println("Nom: " + mon_patient.getNom());
					System.out.println("Prenom: " + mon_patient.getPrenom());
					String sexe = mon_patient.getSexe() == 1 ? "Masculin" : "Féminin";
					System.out.println("Sexe: " + sexe);
					System.out.println("Date de Naissance : " + mon_patient.getDateNaissance());
					System.out.println("###########################################");
					System.out.println("SEJOURS");
					List<Hospitalisation> patient_hospitalisations = daoPatient.getPatientHospitalisations(mon_patient);
					if (patient_hospitalisations.size() == 0) {
						System.out.println("AUCUN SEJOUR POUR CE PATIENT");
						System.out.println("###########################################");

					} else {
						for (int i = 0; i < patient_hospitalisations.size(); i++) {

							System.out.println("SEJOUR " + (i + 1) + ": Date entrée: "
									+ patient_hospitalisations.get(i).getDateEntree() + " -> Date sortie: "
									+ patient_hospitalisations.get(i).getDateSortie());
						}
						System.out.println("###########################################");

					}

				}
			}
				break;

			case 3: {
				System.out.println("\tVEUILLEZ SAISIR LES INFORMATIONS DU PATIENT");
				System.out.print("\tnom: ");
				sc.nextLine();
				String nom = sc.nextLine();
				System.out.print("\tprenom: ");
				String prenom = sc.nextLine();
				System.out.print("\tdate de naissance(yyyy-mm-dd): ");
				LocalDate date_Naissance = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);

				System.out.println();
				System.out.println("\tVEUILLEZ SAISIR UNE PERIODE UNE PERIODE");
				System.out.print("\tDate debut(yyyy-mm-dd): ");
				LocalDate date_debut = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);

				System.out.print("\tDate fin(yyyy-mm-dd): ");
				LocalDate date_fin = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_DATE);
				Patient mon_patient = daoPatient.getPatient(nom, prenom, date_Naissance);
				if (mon_patient.getNom() != null) {
					System.out.println("INFORMATIONS PATIENT");
					System.out.println("###########################################");
					System.out.println("Nom: " + mon_patient.getNom());
					System.out.println("Prenom: " + mon_patient.getPrenom());
					String sexe = mon_patient.getSexe() == 1 ? "Masculin" : "Féminin";
					System.out.println("Sexe: " + sexe);
					System.out.println("Date de Naissance : " + mon_patient.getDateNaissance());
					System.out.println("###########################################");

					List<Acte> patient_actes = daoPatient.getActesBetweenTwoDate(mon_patient, date_debut, date_fin);
					System.out.println("ACTES (" + patient_actes.size() + " actes )");
					if (patient_actes.size() == 0) {
						System.out.println("AUCUN ACTE POUR CE PATIENT");
						System.out.println("###########################################");

					} else {
						for (int i = 0; i < patient_actes.size(); i++) {

							System.out.println("Date Acte: " + patient_actes.get(i).getDateActe());
							System.out.println("Libelle Acte: " + patient_actes.get(i).getLibelleActe());
							System.out.println();
						}
						System.out.println("###########################################");

					}
				}

			}
				break;
			case 4: {
				do {
					System.out.println("1- LES DIAGNOSTICS LES PLUS FREQUENTS CHEZ LES FEMMES");
					System.out.println("2- LES DIAGNOSTICS LES PLUS FREQUENTS CHEZ LES HOMMES");
					System.out.println("3- LES DIAGNOSTICS LES PLUS FREQUENTS CHEZ LES FEMMES DE PLUS DE 60 ANS");
					System.out.println("4- LES FEMMES AYANT UNE GROSSESSE NON DESIREE");
					System.out.println("5- RETOUR AU MENU PRINCIPAL");

					System.out.print("VEUILLEZ SELECTIONNER UN NUMERO: ");

					choix_menu_statistiques = sc.nextInt();
					System.out.println("###########################################");

					switch (choix_menu_statistiques) {

					case 1: {
						List<Diagnostic> mostWomenDiagnostics = daoDiagnostic.mostWomenDiagnostic();
						System.out.println("LES DIAGNOSTICS LES PLUS FREQUENTS CHEZ LES FEMMES");
						System.out.println();
						for (int i = 0; i < mostWomenDiagnostics.size(); i++) {

							System.out
									.println("" + (i + 1) + "-  " + mostWomenDiagnostics.get(i).getLibelleDiagnostic());
							System.out.println();
						}

					}
						break;

					case 2: {
						List<Diagnostic> mostmenDiagnostics = daoDiagnostic.mostmenDiagnostic();
						System.out.println("LES DIAGNOSTICS LES PLUS FREQUENTS CHEZ LES HOMMES");
						System.out.println();
						for (int i = 0; i < mostmenDiagnostics.size(); i++) {

							System.out.println("" + (i + 1) + "-  " + mostmenDiagnostics.get(i).getLibelleDiagnostic());
							System.out.println();
						}
					}
						break;

					case 3: {
						List<Diagnostic> mostwomenDePlus60Diagnostics = daoDiagnostic.mostWomenDePlus60Diagnostic();
						System.out.println("LES CINQ PREMIERS DIAGNOSTICS CHEZ LES FEMMES DE PLUS DE 60 ANS");
						System.out.println();
						for (int i = 0; i < mostwomenDePlus60Diagnostics.size(); i++) {

							System.out.println(
									"" + (i + 1) + "-  " + mostwomenDePlus60Diagnostics.get(i).getLibelleDiagnostic());
							System.out.println();
						}

					}
						break;

					case 4: {
						List<Patient> notDesiredPregnantPatients = daoPatient.getNotDesiredPregnantPatient();
						System.out.println("LES FEMMES AYANT UNE GROSSESSE NON DESIREE ("
								+ notDesiredPregnantPatients.size() + " Femmes)");
						System.out.println();
						for (int i = 0; i < notDesiredPregnantPatients.size(); i++) {

							System.out.println("" + (i + 1) + "- Nom: " + notDesiredPregnantPatients.get(i).getNom()
									+ " | Prenom: " + notDesiredPregnantPatients.get(i).getPrenom() + " | Sexe: "
									+ (notDesiredPregnantPatients.get(i).getSexe() == 1 ? "Masculin" : "Féminin")
									+ " | Date de naissance: " + notDesiredPregnantPatients.get(i).getDateNaissance());
							System.out.println();
						}

					}
						break;
					case 5: {
						k2 = 1;
					}
					default:

					}
				} while (k2 != 1);

			}
				break;
			case 5: {
				System.out.println("AUREVOIR ET A BIENTOT …! ");
				System.exit(0);
			}
			default:

			}

		} while (k != 1);

		connection.close();
		sc.close();

	}

	
}

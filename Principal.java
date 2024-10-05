import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {

    // Champs de texte pour les entrées utilisateur
    private JTextField tauxHoraireField;
    private JTextField heuresParJourField;
    private JTextField joursParMoisField;
    private JTextField cfeField;
    private JTextField fraisSupplField;

    // Labels pour afficher les résultats
    private JLabel resultatMensuelLabel;
    private JLabel resultatAnnuelLabel;

    public Principal() {
        // Configuration de la fenêtre
        super("Calcul du Revenu");
        setLayout(new FlowLayout());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des composants
        add(new JLabel("Taux horaire (en euros) :"));
        tauxHoraireField = new JTextField(10);
        add(tauxHoraireField);

        add(new JLabel("Heures par jour :"));
        heuresParJourField = new JTextField(10);
        add(heuresParJourField);

        add(new JLabel("Jours par mois :"));
        joursParMoisField = new JTextField(10);
        add(joursParMoisField);

        add(new JLabel("Montant de la CFE :"));
        cfeField = new JTextField(10);
        add(cfeField);

        add(new JLabel("Frais supplémentaires :"));
        fraisSupplField = new JTextField(10);
        add(fraisSupplField);

        // Bouton pour calculer
        JButton calculerButton = new JButton("Calculer le revenu");
        add(calculerButton);

        // Labels pour afficher les résultats
        resultatMensuelLabel = new JLabel("Revenu mensuel : ");
        add(resultatMensuelLabel);

        resultatAnnuelLabel = new JLabel("Revenu annuel : ");
        add(resultatAnnuelLabel);

        // Ajouter un action listener au bouton
        calculerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculerRevenu();
            }
        });

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // Méthode pour calculer et afficher les revenus
    private void calculerRevenu() {
        // Récupérer les valeurs saisies par l'utilisateur
        double tauxHoraire = Double.parseDouble(tauxHoraireField.getText());
        int heuresParJour = Integer.parseInt(heuresParJourField.getText());
        int joursParMois = Integer.parseInt(joursParMoisField.getText());
        double cfe = Double.parseDouble(cfeField.getText());
        double fraisSupp = Double.parseDouble(fraisSupplField.getText());

        // Calculer le revenu mensuel
        int nombreHeureMois = heuresParJour * joursParMois;
        double revenuMensuelBrut = tauxHoraire * nombreHeureMois;

        // Calculer le revenu annuel avant impôts et frais
        double revenuAnnuelBrut = revenuMensuelBrut * 12;

        // Calculer l'impôt et les déductions
        double tauxImpot = 66.0;
        double impot = revenuAnnuelBrut * (tauxImpot / 100);
        double revenuAnnuelApresImpot = revenuAnnuelBrut - impot;

        // Calculer le revenu après la CFE et frais supplémentaires
        double revenuAnnuelFinal = revenuAnnuelApresImpot - cfe - fraisSupp;
        double revenuMensuelFinal = revenuAnnuelFinal / 12;

        // Afficher les résultats dans les labels
        resultatMensuelLabel.setText("Revenu mensuel : " + String.format("%.2f", revenuMensuelFinal) + " euros");
        resultatAnnuelLabel.setText("Revenu annuel : " + String.format("%.2f", revenuAnnuelFinal) + " euros");
    }

    public static void main(String[] args) {
        // Lancer l'application
        new Principal();
    }
}

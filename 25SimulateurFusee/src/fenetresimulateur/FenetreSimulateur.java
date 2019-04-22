package fenetresimulateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import ecouteurspersonnalises.MettreAJourDonneesVolListener;
import ecouteurspersonnalises.MettreAJourListener;
import ecouteurspersonnalises.PasserAuVolOrbitalListener;
import ecouteurspersonnalises.SelectionPiecesListener;
import menuoptionapplication.ConceptsScientifiques;
import menuoptionapplication.GuideUtilisation;
import pieces.EnsemblePieces;
import zonededessin.ZoneDessinAssemblage;
import zonededessin.ZoneDessinDecollage;
import zonededessin.ZoneDessinMiniMap;
import zonededessin.ZoneDessinOrbitale;
/**
 * La fen�tre principale du simulateur de fus�e
 * @author Thomas Corbeil
 * @author James Huynh
 *
 */
public class FenetreSimulateur extends JFrame {

	private static final long serialVersionUID = -8972431796574362590L;

	private EnsemblePieces ensemblePieces = new EnsemblePieces();
	
	private final String NOM_IMAGE_LIGNES_JAUNES = "/lignesDiagonalesAvertissement.jpg";
	private ImageIcon imageLignesJaunes;
	private JPanel contentPane;
	private ZoneDessinOrbitale zoneDessinOrbite;
	private ZoneDessinDecollage zoneDessinDecollage;
	private ZoneDessinAssemblage zoneDessinAssemblage;
	private JButton btnDecollage;
	private JLabel lblPhaseDecollage;
	private JButton btnLancerLaFusee;
	private JLabel lblPhaseVol;
	private JMenuBar menuBar;
	private JLabel lblInclinaison;
	private JSlider sliderAngle;
	private JPanel panelDonneesVolE;
	private JLabel lblMasseDecollage;
	private JLabel lblAffichageMasseDecollage;
	private JPanel panelDonneesVolD;
	private JLabel lblPousseEnX;
	private JLabel lblPousseEnY;
	private JLabel lblAffichagePousseeX;
	private JLabel lblAffichagePousseeY;
	private JPanel panelDonneesVolC;
	private JLabel lblAltitude;
	private JLabel lblAffichageAltitude;
	private JPanel panelDonneesVolB;
	private JLabel lblAccelerationEnX;
	private JLabel lblAccelerationEnY;
	private JLabel lblAffichageAccX;
	private JLabel lblAffichageAccY;
	private JPanel panelDonneesVolA;
	private JProgressBar progressBarPoussee;
	private JLabel lblPousse;
	private JLabel lblCarburanRestant;
	private JProgressBar progressBarCarburantRestant;
	private JPanel panelStatistiquesFusee;
	private JLabel lblMasse;
	private JLabel lblCarburant;
	private JLabel lblPousseMaximalekn;
	private JLabel lblAffichagePoussee;
	private JLabel lblAffichageCarburant;
	private JLabel lblAffichageMasse;
	private JPanel panelVerificationElements;
	private JLabel lblCapsulePresente;
	private JLabel lblReservoirPresent;
	private JLabel lblMoteurPresent;
	private JPanel panelIndicateurAssemblage;
	private JLabel lblPhaseAssemblage;
	private JPanel panelIndicateurDecollage;
	private JPanel panelIndicateurVolOrbital;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	private JSlider sliderPoussee;
	private JLabel lblPoussee;
	private JPanel panelDonneesVolF;
	private JLabel lblVitesseX;
	private JLabel lblAffichageVitesseX;
	private JLabel lblVitesseY;
	private JLabel lblAffichageVitesseY;
	private JButton btnMenu;
	private JButton btnAssemblage2;
	private JButton btnAssemblage1;
	private JFrame fenetreMere;
	private final double ALTITUDE_SLIDER = 20;
	private final double RAYON_TERRE = 6371000;
	private ZoneDessinMiniMap zoneDessinMiniMap;
	private JLabel lblFacteurEcoulementTemps;
	private JSlider sliderEcoulementTemps;
	private final String MESSAGE_A_PROPOS = "R�alis� par Thomas Corbeil et James Huynh" + "\n" + "420-SCD" + "\n" + "Hiver 2016-2017" + "\n" + "Coll�ge de Maisonneuve";
	private final Color COULEUR_FENETRE_PRINCIPALE = new Color (48, 63, 66), 
			COULEUR_JAUNE = new Color(238, 192, 21), 
			COULEUR_GRIS_PALE = new Color (108, 122, 125);
	private JLabel rectangleCitation;

	/**
	 * Create the frame.
	 */
	public FenetreSimulateur(JFrame fenetreMere) {
		initialiserLignesdiagonalesJaunes();
		
		this.fenetreMere = fenetreMere;
		
		setTitle("La science des fus\u00E9es pour les nuls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		mnOptions.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnOptions);
		
		JMenuItem mntmGuideDUtilisation = new JMenuItem("Guide d'utilisation");
		mntmGuideDUtilisation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherGuideUtilisation();
			}
		});
		mntmGuideDUtilisation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnOptions.add(mntmGuideDUtilisation);
		
		JMenuItem mntmConceptScientifiques = new JMenuItem("Concepts Scientifiques");
		mntmConceptScientifiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherConceptsScientifiques();
			}
		});
		mntmConceptScientifiques.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnOptions.add(mntmConceptScientifiques);
		
		JMenuItem mntmAPropos = new JMenuItem("\u00C0 propos");
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherAPropos();
			}
		});
		mntmAPropos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnOptions.add(mntmAPropos);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quiterApp();
			}
		});
		mntmQuitter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnOptions.add(mntmQuitter);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(COULEUR_FENETRE_PRINCIPALE);
		
		zoneDessinAssemblage = new ZoneDessinAssemblage(ensemblePieces.getListeCompletPieces());
		zoneDessinAssemblage.addSelectionPiecesListenerListener(new SelectionPiecesListener() {
			@Override
			public void envoyerPieces(int k) {
				selectionnerPiece(k);
				if((ensemblePieces.capsulePresente()) && (ensemblePieces.reservoirPresent()) && (ensemblePieces.moteurPresent())) {
					btnLancerLaFusee.setEnabled(true);
				}
			}

			@Override
			public void enleverPieces(int k) {
				deselectionnerPiece(k);
				if(!((ensemblePieces.capsulePresente()) && (ensemblePieces.reservoirPresent()) && (ensemblePieces.moteurPresent()))) {
					btnLancerLaFusee.setEnabled(false);
				}
			}
		});
		
		panelDonneesVolC = new JPanel();
		panelDonneesVolC.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDonneesVolC.setVisible(false);
		panelDonneesVolC.setBackground(COULEUR_GRIS_PALE);
		
		panelDonneesVolD = new JPanel();
		panelDonneesVolD.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDonneesVolD.setLayout(null);
		panelDonneesVolD.setVisible(false);
		panelDonneesVolD.setBackground(COULEUR_GRIS_PALE);
		
		panelDonneesVolE = new JPanel();
		panelDonneesVolE.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDonneesVolE.setVisible(false);
		panelDonneesVolE.setBackground(COULEUR_GRIS_PALE);
		
		sliderAngle = new JSlider();
		sliderAngle.setForeground(Color.WHITE);
		sliderAngle.setValue(90);
		sliderAngle.setMajorTickSpacing(10);
		sliderAngle.setMinorTickSpacing(5);
		sliderAngle.setMaximum(180);
		sliderAngle.setPaintTicks(true);
		sliderAngle.setPaintLabels(true);
		sliderAngle.setFont(new Font("Agency FB", Font.PLAIN, 10));
		sliderAngle.setBounds(94, 535, 344, 46);
		sliderAngle.setBackground(COULEUR_GRIS_PALE);
		sliderAngle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//debut
				changerAngleFusee();
				//fin
			}
		});
		
		sliderAngle.setVisible(false);
		
		btnLeft = new JButton("<");
		btnLeft.setBackground(UIManager.getColor("Button.background"));
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialiserForceRCS(KeyEvent.VK_LEFT);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				zoneDessinOrbite.retirerForceRCS();
			}
		});
		btnLeft.setVisible(false);
		
		btnUp = new JButton("v");
		btnUp.setBackground(UIManager.getColor("Button.background"));
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialiserForceRCS(KeyEvent.VK_DOWN);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				zoneDessinOrbite.retirerForceRCS();
			}
		});
		btnUp.setVisible(false);
		
		btnDown = new JButton(">");
		btnDown.setBackground(UIManager.getColor("Button.background"));
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialiserForceRCS(KeyEvent.VK_RIGHT);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				zoneDessinOrbite.retirerForceRCS();
			}
		});
		btnDown.setVisible(false);
		
		btnRight = new JButton("^");
		btnRight.setBackground(UIManager.getColor("Button.background"));
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				initialiserForceRCS(KeyEvent.VK_UP);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				zoneDessinOrbite.retirerForceRCS();
			}
		});
		btnRight.setVisible(false);
	
		
		lblFacteurEcoulementTemps = new JLabel("Facteur d'\u00E9coulement du temps");
		lblFacteurEcoulementTemps.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacteurEcoulementTemps.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblFacteurEcoulementTemps.setBounds(459, 548, 715, 27);
		lblFacteurEcoulementTemps.setForeground(COULEUR_JAUNE);
		contentPane.add(lblFacteurEcoulementTemps);
		lblFacteurEcoulementTemps.setVisible(false);
		
		btnAssemblage1 = new JButton("RETOURNER A L'ASSEMBLAGE");
		btnAssemblage1.setBounds(10, 691, 429, 38);
		contentPane.add(btnAssemblage1);
		btnAssemblage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//debut
				revenirALAssemblage();
				//fin
			}
		});
		btnAssemblage1.setVisible(false);
		btnAssemblage1.setFont(new Font("Agency FB", Font.PLAIN, 40));
		btnRight.setBounds(170, 560, 89, 27);
		contentPane.add(btnRight);
		btnDown.setBounds(302, 608, 89, 27);
		contentPane.add(btnDown);
		btnUp.setBounds(170, 608, 89, 27);
		contentPane.add(btnUp);
		btnLeft.setBounds(40, 608, 89, 27);
		contentPane.add(btnLeft);
		
		contentPane.add(sliderAngle);
		panelDonneesVolE.setBounds(10, 398, 429, 38);
		contentPane.add(panelDonneesVolE);
		panelDonneesVolE.setLayout(null);
		
		lblMasseDecollage = new JLabel("Masse (kg):");
		lblMasseDecollage.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblMasseDecollage.setBounds(10, 4, 70, 29);
		panelDonneesVolE.add(lblMasseDecollage);
		
		lblAffichageMasseDecollage = new JLabel((int) ensemblePieces.getMasseAvecCarburant() + "");
		lblAffichageMasseDecollage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageMasseDecollage.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichageMasseDecollage.setBounds(210, 4, 209, 29);
		panelDonneesVolE.add(lblAffichageMasseDecollage);
		panelDonneesVolD.setBounds(10, 319, 429, 68);
		contentPane.add(panelDonneesVolD);
		
		lblPousseEnX = new JLabel("Pouss\u00E9e en X(N):");
		lblPousseEnX.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblPousseEnX.setBounds(10, 3, 190, 29);
		panelDonneesVolD.add(lblPousseEnX);
		
		lblPousseEnY = new JLabel("Pouss\u00E9e en Y(N):");
		lblPousseEnY.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblPousseEnY.setBounds(10, 35, 190, 29);
		panelDonneesVolD.add(lblPousseEnY);
		
		lblAffichagePousseeX = new JLabel("0");
		lblAffichagePousseeX.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichagePousseeX.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichagePousseeX.setBounds(210, 3, 209, 29);
		panelDonneesVolD.add(lblAffichagePousseeX);
		
		lblAffichagePousseeY = new JLabel("0");
		lblAffichagePousseeY.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichagePousseeY.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichagePousseeY.setBounds(210, 35, 209, 29);
		panelDonneesVolD.add(lblAffichagePousseeY);
		panelDonneesVolC.setBounds(10, 270, 429, 38);
		contentPane.add(panelDonneesVolC);
		panelDonneesVolC.setLayout(null);
		
		lblAltitude = new JLabel("Altitude (m):");
		lblAltitude.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAltitude.setBounds(10, 4, 70, 29);
		panelDonneesVolC.add(lblAltitude);
		
		lblAffichageAltitude = new JLabel("0");
		lblAffichageAltitude.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageAltitude.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichageAltitude.setBounds(210, 4, 209, 29);
		panelDonneesVolC.add(lblAffichageAltitude);
		
		panelDonneesVolB = new JPanel();
		panelDonneesVolB.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDonneesVolB.setVisible(false);
		panelDonneesVolB.setBounds(10, 191, 429, 68);
		contentPane.add(panelDonneesVolB);
		panelDonneesVolB.setLayout(null);
		panelDonneesVolB.setBackground(COULEUR_GRIS_PALE);
		
		lblAccelerationEnX = new JLabel("Acc\u00E9l\u00E9ration en X(m/s\u00B2):");
		lblAccelerationEnX.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAccelerationEnX.setBounds(10, 3, 190, 29);
		panelDonneesVolB.add(lblAccelerationEnX);
		
		lblAccelerationEnY = new JLabel("Acc\u00E9l\u00E9ration en Y(m/s\u00B2):");
		lblAccelerationEnY.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAccelerationEnY.setBounds(10, 35, 190, 29);
		panelDonneesVolB.add(lblAccelerationEnY);
		
		lblAffichageAccX = new JLabel("0");
		lblAffichageAccX.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageAccX.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichageAccX.setBounds(210, 3, 209, 29);
		panelDonneesVolB.add(lblAffichageAccX);
		
		lblAffichageAccY = new JLabel("0");
		lblAffichageAccY.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageAccY.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichageAccY.setBounds(210, 35, 209, 29);
		panelDonneesVolB.add(lblAffichageAccY);
		
		panelDonneesVolA = new JPanel();
		panelDonneesVolA.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDonneesVolA.setVisible(false);
		panelDonneesVolA.setBounds(10, 11, 429, 169);
		contentPane.add(panelDonneesVolA);
		panelDonneesVolA.setLayout(null);
		panelDonneesVolA.setBackground(COULEUR_GRIS_PALE);
		
		progressBarPoussee = new JProgressBar();
		progressBarPoussee.setFont(new Font("Agency FB", Font.PLAIN, 24));
		progressBarPoussee.setForeground(Color.GREEN);
		progressBarPoussee.setStringPainted(true);
		progressBarPoussee.setBounds(10, 45, 409, 32);
		progressBarPoussee.setUI(new BasicProgressBarUI() {
		      protected Color getSelectionBackground() { return Color.black; }
		      protected Color getSelectionForeground() { return Color.black; }
		    });
		panelDonneesVolA.add(progressBarPoussee);
		
		lblPousse = new JLabel("Pouss\u00E9e:");
		lblPousse.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblPousse.setBounds(10, 11, 68, 23);
		lblPousse.setForeground(COULEUR_JAUNE);
		panelDonneesVolA.add(lblPousse);
		
		lblCarburanRestant = new JLabel("Carburant restant:");
		lblCarburanRestant.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblCarburanRestant.setBounds(10, 88, 139, 23);
		lblCarburanRestant.setForeground(COULEUR_JAUNE);
		panelDonneesVolA.add(lblCarburanRestant);
		
		progressBarCarburantRestant = new JProgressBar();
		progressBarCarburantRestant.setValue(100);
		progressBarCarburantRestant.setStringPainted(true);
		progressBarCarburantRestant.setForeground(Color.GREEN);
		progressBarCarburantRestant.setFont(new Font("Agency FB", Font.PLAIN, 24));
		progressBarCarburantRestant.setBounds(10, 122, 409, 32);
		progressBarCarburantRestant.setUI(new BasicProgressBarUI() {
		      protected Color getSelectionBackground() { return Color.black; }
		      protected Color getSelectionForeground() { return Color.black; }
		    });
		panelDonneesVolA.add(progressBarCarburantRestant);
		zoneDessinAssemblage.setBounds(459, 11, 715, 530);
		contentPane.add(zoneDessinAssemblage);
		
		zoneDessinDecollage = new ZoneDessinDecollage(ensemblePieces);
		zoneDessinDecollage.addMettreAJourDonneesVolListener(new MettreAJourDonneesVolListener() {
			public void mettreAJourDonneesVol() {
				//debut
				mettreAJourDecollage();
				//fin
			}
		});
		zoneDessinDecollage.addPasserAuVolOrbitalListener(new PasserAuVolOrbitalListener() {
			public void passerAuVolOrbital() {
				//D�but
				passerALOrbite();
				//Fin
			}
		});
		zoneDessinDecollage.setBounds(554, 11, 620, 530);
		contentPane.add(zoneDessinDecollage);
		zoneDessinDecollage.setVisible(false);
		
		panelStatistiquesFusee = new JPanel();
		panelStatistiquesFusee.setFont(new Font("Agency FB", Font.PLAIN, 16));
		panelStatistiquesFusee.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Statistiques de la fus\u00E9e", TitledBorder.LEADING, TitledBorder.TOP, null, COULEUR_JAUNE));
		panelStatistiquesFusee.setBackground(COULEUR_GRIS_PALE);
		panelStatistiquesFusee.setBounds(10, 11, 429, 350);
		contentPane.add(panelStatistiquesFusee);
		panelStatistiquesFusee.setLayout(null);
		
		lblMasse = new JLabel("Masse (kg):");
		lblMasse.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblMasse.setBounds(10, 65, 87, 29);
		panelStatistiquesFusee.add(lblMasse);
		
		lblCarburant = new JLabel("Carburant (L):");
		lblCarburant.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblCarburant.setBounds(10, 159, 104, 29);
		panelStatistiquesFusee.add(lblCarburant);
		
		lblPousseMaximalekn = new JLabel("Pouss\u00E9e maximale (kN):");
		lblPousseMaximalekn.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblPousseMaximalekn.setBounds(10, 253, 178, 29);
		panelStatistiquesFusee.add(lblPousseMaximalekn);
		
		lblAffichagePoussee = new JLabel("0.0");
		lblAffichagePoussee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichagePoussee.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblAffichagePoussee.setBounds(260, 254, 100, 27);
		panelStatistiquesFusee.add(lblAffichagePoussee);
		
		lblAffichageCarburant = new JLabel("0.0");
		lblAffichageCarburant.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageCarburant.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblAffichageCarburant.setBounds(260, 159, 100, 27);
		panelStatistiquesFusee.add(lblAffichageCarburant);
		
		lblAffichageMasse = new JLabel("0.0");
		lblAffichageMasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageMasse.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblAffichageMasse.setBounds(260, 65, 100, 27);
		panelStatistiquesFusee.add(lblAffichageMasse);
		
		panelDonneesVolF = new JPanel();
		panelDonneesVolF.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDonneesVolF.setBounds(10, 447, 429, 68);
		panelDonneesVolF.setBackground(COULEUR_GRIS_PALE);
		contentPane.add(panelDonneesVolF);
		panelDonneesVolF.setLayout(null);
		panelDonneesVolF.setVisible(false);
		
		lblVitesseX = new JLabel("Vitesse en X(m/s):");
		lblVitesseX.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblVitesseX.setBounds(10, 3, 111, 29);
		panelDonneesVolF.add(lblVitesseX);
		
		lblAffichageVitesseX = new JLabel("0");
		lblAffichageVitesseX.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageVitesseX.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichageVitesseX.setBounds(210, 3, 209, 29);
		panelDonneesVolF.add(lblAffichageVitesseX);
		
		lblVitesseY = new JLabel("Vitesse en Y(m/s):");
		lblVitesseY.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblVitesseY.setBounds(10, 35, 111, 29);
		panelDonneesVolF.add(lblVitesseY);
		
		lblAffichageVitesseY = new JLabel("0");
		lblAffichageVitesseY.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichageVitesseY.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAffichageVitesseY.setBounds(210, 35, 209, 29);
		panelDonneesVolF.add(lblAffichageVitesseY);
		
		panelVerificationElements = new JPanel();
		panelVerificationElements.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "V\u00E9ricateur de pi\u00E8ces", TitledBorder.LEADING, TitledBorder.TOP, null, COULEUR_JAUNE));
		panelVerificationElements.setBackground(COULEUR_GRIS_PALE);
		panelVerificationElements.setBounds(10, 372, 429, 169);
		contentPane.add(panelVerificationElements);
		panelVerificationElements.setLayout(null);
		
		lblCapsulePresente = new JLabel("Capsule pr\u00E9sente");
		lblCapsulePresente.setForeground(Color.RED);
		lblCapsulePresente.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblCapsulePresente.setBounds(10, 20, 131, 29);
		panelVerificationElements.add(lblCapsulePresente);
		
		lblReservoirPresent = new JLabel("R\u00E9servoir pr\u00E9sent");
		lblReservoirPresent.setForeground(Color.RED);
		lblReservoirPresent.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblReservoirPresent.setBounds(10, 69, 137, 29);
		panelVerificationElements.add(lblReservoirPresent);
		
		lblMoteurPresent = new JLabel("Moteur pr\u00E9sent");
		lblMoteurPresent.setForeground(Color.RED);
		lblMoteurPresent.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblMoteurPresent.setBounds(10, 118, 116, 29);
		panelVerificationElements.add(lblMoteurPresent);
		
		panelIndicateurAssemblage = new JPanel();
		panelIndicateurAssemblage.setBorder(null);
		panelIndicateurAssemblage.setBackground(SystemColor.menu);
		panelIndicateurAssemblage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelIndicateurAssemblage.setBounds(459, 667, 183, 46);
		contentPane.add(panelIndicateurAssemblage);
		panelIndicateurAssemblage.setLayout(null);
		
		lblPhaseAssemblage = new JLabel("Phase 1: assemblage");
		lblPhaseAssemblage.setForeground(Color.GREEN);
		lblPhaseAssemblage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhaseAssemblage.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblPhaseAssemblage.setBounds(10, 0, 163, 46);
		panelIndicateurAssemblage.add(lblPhaseAssemblage);
		
		panelIndicateurDecollage = new JPanel();
		panelIndicateurDecollage.setBorder(null);
		panelIndicateurDecollage.setBackground(SystemColor.menu);
		panelIndicateurDecollage.setLayout(null);
		panelIndicateurDecollage.setBounds(727, 667, 183, 46);
		contentPane.add(panelIndicateurDecollage);
		
		lblPhaseDecollage = new JLabel("Phase 2: d\u00E9collage");
		lblPhaseDecollage.setForeground(Color.RED);
		lblPhaseDecollage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhaseDecollage.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblPhaseDecollage.setBounds(10, 0, 163, 46);
		panelIndicateurDecollage.add(lblPhaseDecollage);
		
		btnLancerLaFusee = new JButton("LANCER LA FUS\u00C9E");
		btnLancerLaFusee.setForeground(new Color(204, 102, 0));
		btnLancerLaFusee.setIcon(imageLignesJaunes);
		btnLancerLaFusee.setHorizontalTextPosition(JButton.CENTER);
		btnLancerLaFusee.setVerticalTextPosition(JButton.CENTER);
		btnLancerLaFusee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//debut
				passerAuDecollage();
				//fin
			} 
		});
		btnLancerLaFusee.setFont(new Font("Agency FB", Font.PLAIN, 44));
		btnLancerLaFusee.setBounds(10, 649, 429, 80);
		contentPane.add(btnLancerLaFusee);
		
		panelIndicateurVolOrbital = new JPanel();
		panelIndicateurVolOrbital.setBorder(null);
		panelIndicateurVolOrbital.setBackground(SystemColor.menu);
		panelIndicateurVolOrbital.setLayout(null);
		panelIndicateurVolOrbital.setBounds(991, 667, 172, 46);
		contentPane.add(panelIndicateurVolOrbital);
		
		lblPhaseVol = new JLabel("Phase 3: vol orbital");
		lblPhaseVol.setForeground(Color.RED);
		lblPhaseVol.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhaseVol.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblPhaseVol.setBounds(10, 0, 163, 46);
		panelIndicateurVolOrbital.add(lblPhaseVol);
		
		btnDecollage = new JButton("D\u00C9COLLAGE");
		btnDecollage.setFont(new Font("Agency FB", Font.PLAIN, 40));
		btnDecollage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//d�but
				lancerLaFusee();
				//fin
			}
		});
		btnDecollage.setBounds(10, 649, 429, 38);
		contentPane.add(btnDecollage);
		btnDecollage.setVisible(false);
		
		sliderPoussee = new JSlider();
		sliderPoussee.setForeground(Color.WHITE);
		sliderPoussee.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//debut
				changerPoussee();
				//fin
			}
		});
		sliderPoussee.setFont(new Font("Agency FB", Font.PLAIN, 10));
		sliderPoussee.setMajorTickSpacing(10);
		sliderPoussee.setValue(100);
		sliderPoussee.setPaintTicks(true);
		sliderPoussee.setPaintLabels(true);
		sliderPoussee.setMinorTickSpacing(5);
		sliderPoussee.setVisible(false);
		sliderPoussee.setBounds(94, 592, 345, 46);
		sliderPoussee.setBackground(COULEUR_GRIS_PALE);
		contentPane.add(sliderPoussee);
		
		lblPoussee = new JLabel("Pouss\u00E9e:");
		lblPoussee.setVisible(false);
		lblPoussee.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblPoussee.setBounds(10, 600, 58, 38);
		lblPoussee.setForeground(COULEUR_JAUNE);
		contentPane.add(lblPoussee);
		
		btnMenu = new JButton("RETOURNER AU MENU");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//debut
				revenirAuMenu();
				//fin
			}
		});
		btnMenu.setVisible(false);
		btnMenu.setFont(new Font("Agency FB", Font.PLAIN, 40));
		btnMenu.setBounds(10, 691, 429, 38);
		contentPane.add(btnMenu);
		
		btnAssemblage2 = new JButton("RETOURNER A L'ASSEMBLAGE");
		btnAssemblage2.setBackground(UIManager.getColor("Button.background"));
		btnAssemblage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//d�but
				revenirALAssemblage();
				//fin
			}
		});
		btnAssemblage2.setVisible(false);
		btnAssemblage2.setFont(new Font("Agency FB", Font.PLAIN, 40));
		btnAssemblage2.setBounds(10, 649, 429, 38);
		contentPane.add(btnAssemblage2);
		
		zoneDessinMiniMap = new ZoneDessinMiniMap();
		zoneDessinMiniMap.setBounds(459, 11, 95, 530);
		contentPane.add(zoneDessinMiniMap);
		
		zoneDessinOrbite = new ZoneDessinOrbitale(ensemblePieces);
		zoneDessinOrbite.addMettreAJourListenerListener(new MettreAJourListener() {
			public void ParametresOrbitale() {
				//debut
				mettreAJourOrbite();
				//fin
			}
		});
		zoneDessinOrbite.setBounds(459, 11, 715, 530);
		contentPane.add(zoneDessinOrbite);
		zoneDessinOrbite.setVisible(false);
		
		sliderEcoulementTemps = new JSlider();
		sliderEcoulementTemps.setForeground(Color.WHITE);
		sliderEcoulementTemps.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		sliderEcoulementTemps.setBackground(COULEUR_FENETRE_PRINCIPALE);
		sliderEcoulementTemps.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//debut
				changerEcoulementTemps(sliderEcoulementTemps.getValue());
				//fin
			}
		});
		sliderEcoulementTemps.setMajorTickSpacing(1);
		sliderEcoulementTemps.setSnapToTicks(true);
		sliderEcoulementTemps.setPaintLabels(true);
		sliderEcoulementTemps.setPaintTicks(true);
		sliderEcoulementTemps.setMinimum(1);
		sliderEcoulementTemps.setMaximum(9);
		sliderEcoulementTemps.setValue(5);
		sliderEcoulementTemps.setBounds(456, 585, 718, 56);
		contentPane.add(sliderEcoulementTemps);
		
		JPanel panelPhases = new JPanel();
		panelPhases.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panelPhases.setBounds(456, 649, 718, 80);
		contentPane.add(panelPhases);
		
		lblInclinaison = new JLabel("Inclinaison:");
		lblInclinaison.setBounds(10, 548, 64, 27);
		contentPane.add(lblInclinaison);
		lblInclinaison.setVisible(false);
		lblInclinaison.setForeground(COULEUR_JAUNE);
		lblInclinaison.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		rectangleCitation = new JLabel("\u00AB C'est un petit pas pour l'homme mais un bond de g\u00E9ant pour l'humanit\u00E9. \u00BB - Neil Armstrong");
		rectangleCitation.setFont(new Font("Vivaldi", Font.PLAIN, 24));
		rectangleCitation.setHorizontalAlignment(SwingConstants.CENTER);
		rectangleCitation.setOpaque(true);
		rectangleCitation.setBackground(Color.WHITE);
		rectangleCitation.setBounds(10, 560, 1164, 72);
		contentPane.add(rectangleCitation);
		sliderEcoulementTemps.setVisible(false);
		
		zoneDessinMiniMap.setVisible(false);
		
		initialiserLabelCustom();
		
		//Initialiser les composantes aux bonnes valeurs
		initialiserComposantesAssemblage();
		
	}

	
	//James
	/**
	 * M�thode permettant de quitter l'application
	 */
	private void quiterApp() {
		System.exit(0);
	}
	
	//James
	/**
	 * M�thode pour afficher le message du � propos 
	 */
	private void afficherAPropos() {
		JOptionPane.showMessageDialog(null, MESSAGE_A_PROPOS);
	}
	
	//James
	/**
	 * M�thode permettant d'afficher les concepts scientifiques reli�s � la simulation
	 */
	private void afficherConceptsScientifiques() {
		ConceptsScientifiques fenetreConceptsScientifiques = new ConceptsScientifiques();
		fenetreConceptsScientifiques.setVisible(true);
		fenetreConceptsScientifiques.setLocationRelativeTo(null);
	}
	
	//James
	/**
	 * M�thode permettant d'afficher le guide d'utilisation 
	 */
	private void afficherGuideUtilisation() {
		GuideUtilisation fenetreGuideUtilisation = new GuideUtilisation();
		fenetreGuideUtilisation.setVisible(true);
		fenetreGuideUtilisation.setLocationRelativeTo(null);
	}
	//Thomas
	/**
	 * M�thode qui permet de passer � la phase de vol orbital
	 */
	private void passerALOrbite() {
		zoneDessinOrbite.requestFocus();
		zoneDessinOrbite.setVisible(true);
		zoneDessinOrbite.initialiserVolOrbital();
		zoneDessinDecollage.setVisible(false);
		sliderAngle.setVisible(false);
		sliderPoussee.setVisible(false);
		lblInclinaison.setVisible(false);
		lblPoussee.setVisible(false);
		btnDecollage.setVisible(false);
		btnUp.setVisible(true);
		btnDown.setVisible(true);
		btnLeft.setVisible(true);
		btnRight.setVisible(true);
		btnAssemblage2.setVisible(true);
		btnAssemblage1.setVisible(false);
		btnMenu.setVisible(true);
		zoneDessinMiniMap.setVisible(false);
		lblPhaseVol.setForeground(Color.green);
	}
	//Thomas
	/**
	 * M�thode qui permet de passer � la phase de d�collage
	 */
	private void passerAuDecollage() {
		rectangleCitation.setVisible(false);
		zoneDessinAssemblage.setVisible(false);
		panelStatistiquesFusee.setVisible(false);
		panelVerificationElements.setVisible(false);
		btnLancerLaFusee.setVisible(false);
		panelDonneesVolA.setVisible(true);
		panelDonneesVolB.setVisible(true);
		panelDonneesVolC.setVisible(true);
		panelDonneesVolD.setVisible(true);
		panelDonneesVolE.setVisible(true);
		panelDonneesVolF.setVisible(true);
		sliderAngle.setVisible(true);
		sliderPoussee.setVisible(true);
		lblInclinaison.setVisible(true);
		lblPoussee.setVisible(true);
		btnDecollage.setVisible(true);
		zoneDessinDecollage.setVisible(true);
		zoneDessinDecollage.initialiserValeurs();
		zoneDessinMiniMap.setVisible(true);
		sliderEcoulementTemps.setVisible(true);
		lblFacteurEcoulementTemps.setVisible(true);
		lblPhaseDecollage.setForeground(Color.GREEN);
		btnAssemblage1.setVisible(true);
		initialiserComposantesVol();
	}
	//Thomas
	/**�
	 * M�thode qui lance la fus�e
	 */
	private void lancerLaFusee() {
		sliderAngle.requestFocus();
		zoneDessinDecollage.lancerLeDecollage();
		btnDecollage.setEnabled(false);
		progressBarPoussee.setValue(sliderPoussee.getValue());
	}
	//Thomas
	/**
	 * M�thode qui change l'angle de la fus�e
	 */
	private void changerAngleFusee() {
		zoneDessinDecollage.setInclinaison(sliderAngle.getValue());
	}
	//Thomas
	/**
	 * M�thode qui change le pourcentage de pouss�e de la fus�e
	 */
	private void changerPoussee() {
		zoneDessinDecollage.setPourcentagePousse(sliderPoussee.getValue());
		if(btnDecollage.isEnabled()) {
			progressBarPoussee.setValue(0);
		} else {
			progressBarPoussee.setValue(sliderPoussee.getValue());
		}
	}
	//Thomas
	/**
	 * M�thode qui met � jour les donn�es pendant le d�collage
	 */
	private void mettreAJourDecollage() {
		lblAffichageAccX.setText((int) zoneDessinDecollage.getAcceleration().getX() + "");
		lblAffichageAccY.setText((int) zoneDessinDecollage.getAcceleration().getY() + "");
		lblAffichageAltitude.setText((int) zoneDessinDecollage.getPosition().getY() + "");
		lblAffichagePousseeX.setText((int) zoneDessinDecollage.getPoussee().getX() + "");
		lblAffichagePousseeY.setText((int) zoneDessinDecollage.getPoussee().getY() + "");
		lblAffichageMasseDecollage.setText((int) ensemblePieces.getMasseAvecCarburant() + "");
		lblAffichageVitesseX.setText((int) zoneDessinDecollage.getVitesse().getX() + "");
		lblAffichageVitesseY.setText((int) zoneDessinDecollage.getVitesse().getY() + "");
		progressBarCarburantRestant.setValue((int) ((ensemblePieces.getCarburantRestant()/ensemblePieces.getCarburant()) * 100));
		zoneDessinMiniMap.mettreAJourValeurs(zoneDessinDecollage.getPosition().getY());
		
		if(zoneDessinDecollage.getPosition().getY() > ALTITUDE_SLIDER) {
			sliderAngle.setEnabled(true);
		}
	}
	//Thomas
	/**
	 * M�thode qui met � jour les donn�es de vol pendant le d�collage
	 */
	private void mettreAJourOrbite() {
		lblAffichageAccX.setText((int) zoneDessinOrbite.getAcceleration().getX() + "");
		lblAffichageAccY.setText((int) zoneDessinOrbite.getAcceleration().getY() + "");
		
		if((zoneDessinOrbite.getPosition().module() - RAYON_TERRE) > 0) {
			lblAffichageAltitude.setText((int) zoneDessinOrbite.getPosition().module() - RAYON_TERRE + "");
		} else {
			lblAffichageAltitude.setText("0");
		}
		
		lblAffichagePousseeX.setText((int) zoneDessinOrbite.getForceRCS().getX() + "");
		lblAffichagePousseeY.setText((int) zoneDessinOrbite.getForceRCS().getY() + "");
		lblAffichageMasseDecollage.setText((int) ensemblePieces.getCapsule().getMasse() + "");
		lblAffichageVitesseX.setText((int) zoneDessinOrbite.getVitesse().getX() + "");
		lblAffichageVitesseY.setText((int) zoneDessinOrbite.getVitesse().getY() + "");
		progressBarCarburantRestant.setValue(0);
		if((zoneDessinOrbite.getForceRCS().getX() != 0) || (zoneDessinOrbite.getForceRCS().getY() != 0)) {
			progressBarPoussee.setValue(100);
		} else {
			progressBarPoussee.setValue(0);
		}
	}
	
	//James
	/**
	 * M�thode pour signaler l'ajout d'une force RCS sur la fus�e
	 * @param touche Le code du keyEvent de la touche directionnelle
	 */
	private void initialiserForceRCS(int touche) {
		double forceRCS = ensemblePieces.getPousseeRCS();
		zoneDessinOrbite.ajouterForceRCS(touche, forceRCS);
	}
	
	//Thomas
	/**
	 * M�thode qui permet de s�lectionner une pi�ce
	 * @param indice l'indice de la pi�ce
	 */
	private void selectionnerPiece(int indice) {
		ensemblePieces.selectionnerPiece(indice);
		lblAffichagePoussee.setText(ensemblePieces.getPousseeFusee() + "");
		lblAffichageMasse.setText(ensemblePieces.getMasseAVide() + "");
		lblAffichageCarburant.setText(ensemblePieces.getCarburant() + "");
		if(ensemblePieces.capsulePresente()) {
			lblCapsulePresente.setForeground(Color.green);
		}

		if(ensemblePieces.reservoirPresent()) {
			lblReservoirPresent.setForeground(Color.green);
		}

		if(ensemblePieces.moteurPresent()) {
			lblMoteurPresent.setForeground(Color.green);
		}
	}
	//Thomas
	/**
	 * M�thode qui permet de d�s�lectionner une pi�ce
	 * @param indice l'indice du type de pi�ce � d�s�lectionner
	 */
	private void deselectionnerPiece(int indice) {
		if(indice == 1) {
			ensemblePieces.setCapsule(null);
		} else {
			if(indice == 2) {
				ensemblePieces.setReservoir(null);
			} else {
				ensemblePieces.setMoteur(null);
			}
		}
		
		lblAffichagePoussee.setText(ensemblePieces.getPousseeFusee() + "");
		lblAffichageMasse.setText(ensemblePieces.getMasseAVide() + "");
		lblAffichageCarburant.setText(ensemblePieces.getCarburant() + "");
		
		if(!ensemblePieces.capsulePresente()) {
			lblCapsulePresente.setForeground(Color.red);
		}

		if(!ensemblePieces.reservoirPresent()) {
			lblReservoirPresent.setForeground(Color.red);
		}

		if(!ensemblePieces.moteurPresent()) {
			lblMoteurPresent.setForeground(Color.red);
		}
	}
	//Thomas
	/**
	 * M�thode qui permet de revenir � la phase d'assemblage
	 */
	private void revenirALAssemblage() {
		rectangleCitation.setVisible(true);
		zoneDessinAssemblage.setVisible(true);
		panelStatistiquesFusee.setVisible(true);
		panelVerificationElements.setVisible(true);
		btnLancerLaFusee.setVisible(true);
		btnDecollage.setEnabled(true);
		lblPhaseDecollage.setForeground(Color.red);
		lblPhaseVol.setForeground(Color.red);
		zoneDessinOrbite.setVisible(false);
		zoneDessinDecollage.setVisible(false);;
		btnUp.setVisible(false);
		btnDown.setVisible(false);
		btnLeft.setVisible(false);
		btnRight.setVisible(false);
		btnAssemblage2.setVisible(false);
		btnAssemblage1.setVisible(false);
		btnMenu.setVisible(false);
		panelDonneesVolA.setVisible(false);
		panelDonneesVolB.setVisible(false);
		panelDonneesVolC.setVisible(false);
		panelDonneesVolD.setVisible(false);
		panelDonneesVolE.setVisible(false);
		panelDonneesVolF.setVisible(false);
		zoneDessinAssemblage.reinitialiserValeursAssemblage();
		zoneDessinDecollage.reinitialiserValeurs();
		zoneDessinOrbite.reinitialiserValeursOrbitales();
		zoneDessinMiniMap.setVisible(false);
		zoneDessinMiniMap.reinitialiserValeurs();
		ensemblePieces.reinitialiserValeurs();
		sliderEcoulementTemps.setVisible(false);
		lblFacteurEcoulementTemps.setVisible(false);
		sliderAngle.setVisible(false);
		sliderPoussee.setVisible(false);
		lblInclinaison.setVisible(false);
		lblPoussee.setVisible(false);
		btnDecollage.setVisible(false);
		initialiserComposantesAssemblage();
		
	}
	//Thomas
	/**
	 * M�thode qui initialise les composantes d'interfaces d'assemblage aux valeurs d�sir�es
	 */
	private void initialiserComposantesAssemblage() {
		lblAffichageMasse.setText(ensemblePieces.getMasseAVide() + "");
		lblAffichagePoussee.setText(ensemblePieces.getPousseeFusee() + "");
		lblAffichageCarburant.setText(ensemblePieces.getCarburant() + "");
		btnLancerLaFusee.setEnabled(false);
		lblCapsulePresente.setForeground(Color.red);
		lblReservoirPresent.setForeground(Color.red);
		lblMoteurPresent.setForeground(Color.red);
	}
	//Thomas
	/**
	* M�thode qui initialise les composantes d'interfaces de vol aux valeurs d�sir�es
	*/
	private void initialiserComposantesVol() {
		lblAffichageAccX.setText(0 + "");
		lblAffichageAccY.setText(0 + "");
		lblAffichageAltitude.setText(0 + "");
		lblAffichageMasseDecollage.setText((int) (ensemblePieces.getMasseAvecCarburant()) + "");
		lblAffichagePousseeX.setText(0 + "");
		lblAffichagePousseeY.setText(0 + "");
		lblAffichageVitesseX.setText(0 + "");
		lblAffichageVitesseY.setText(0 + "");
		sliderAngle.setValue(90);
		sliderAngle.setEnabled(false);
		sliderPoussee.setValue(100);
		progressBarCarburantRestant.setValue(100);
		progressBarPoussee.setValue(0);
		sliderEcoulementTemps.setValue(5);
	}
	//Thomas
	/**
	 * M�thode qui permet de revenir au menu
	 */
	private void revenirAuMenu() {
		fenetreMere.setVisible(true);
		zoneDessinOrbite.arreterAnimation();
		dispose();
	}
	//Thomas
	/**
	 * M�thode qui change l'�coulement du temps de la simulation
	 * @param facteur le facteur d'�coulement du temps
	 */
	private void changerEcoulementTemps(int facteur) {
		switch (facteur)  {
		case 1:
			zoneDessinDecollage.definirEcoulementTemps(1.0/5.0);
			zoneDessinOrbite.setEcoulementTemps(1.0/5.0);
			break;
		case 2:
			zoneDessinDecollage.definirEcoulementTemps(1.0/4.0);
			zoneDessinOrbite.setEcoulementTemps(1.0/4.0);
			break;
		case 3:
			zoneDessinDecollage.definirEcoulementTemps(1.0/3.0);
			zoneDessinOrbite.setEcoulementTemps(1.0/3.0);
			break;
		case 4:
			zoneDessinDecollage.definirEcoulementTemps(1.0/2.0);
			zoneDessinOrbite.setEcoulementTemps(1.0/2.0);
			break;
		case 5:
			zoneDessinDecollage.definirEcoulementTemps(1);
			zoneDessinOrbite.setEcoulementTemps(1);
			break;
		case 6:
			zoneDessinDecollage.definirEcoulementTemps(2);
			zoneDessinOrbite.setEcoulementTemps(2);
			break;
		case 7:
			zoneDessinDecollage.definirEcoulementTemps(3);
			zoneDessinOrbite.setEcoulementTemps(3);
			break;
		case 8:
			zoneDessinDecollage.definirEcoulementTemps(4);
			zoneDessinOrbite.setEcoulementTemps(4);
			break;
		case 9:
			zoneDessinDecollage.definirEcoulementTemps(5);
			zoneDessinOrbite.setEcoulementTemps(5);
			break;
		}
	}
	//James
	/**
	 * M�thode qui charge une image de lignes diagonales
	 */
	private void initialiserLignesdiagonalesJaunes() {
		URL urlImageLignesJaunes = FenetreSimulateur.class.getResource(NOM_IMAGE_LIGNES_JAUNES);
		if(urlImageLignesJaunes == null) {
			System.out.println("L'image " + NOM_IMAGE_LIGNES_JAUNES + " est introuvable");
		}
		imageLignesJaunes = new ImageIcon(urlImageLignesJaunes);
	}
	//Thomas
	/**
	 * M�thode qui cr�e des �tiquettes personnalis�es pour le slider du temps
	 */
	private void initialiserLabelCustom() {
		JLabel lblA = new JLabel("1/5");
		JLabel lblB = new JLabel("1/4");
		JLabel lblC = new JLabel("1/3");
		JLabel lblD = new JLabel("1/2");
		JLabel lblE = new JLabel("1");
		JLabel lblF = new JLabel("2");
		JLabel lblG = new JLabel("3");
		JLabel lblH = new JLabel("4");
		JLabel lblI = new JLabel("5");
		
		lblA.setForeground(Color.white);
		lblB.setForeground(Color.white);
		lblC.setForeground(Color.white);
		lblD.setForeground(Color.white);
		lblE.setForeground(Color.white);
		lblF.setForeground(Color.white);
		lblG.setForeground(Color.white);
		lblH.setForeground(Color.white);
		lblI.setForeground(Color.white);

		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(1, lblA);
		labels.put(2, lblB);
		labels.put(3, lblC);
		labels.put(4, lblD);
		labels.put(5, lblE);
		labels.put(6, lblF);
		labels.put(7, lblG);
		labels.put(8, lblH);
		labels.put(9, lblI);
		sliderEcoulementTemps.setLabelTable(labels);

	}
}
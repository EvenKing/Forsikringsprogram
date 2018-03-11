/*
Klasse for visning av alle info om forsikringer, hentes fram av kunde og ansatt
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ForsikringsProfil extends JFrame
{
    private Register register;
    private BilForsikring bil;
    private BåtForsikring båt;
    private HusForsikring hus;
    private HytteForsikring hytte;
    private MainListener lytter;
    private Kunde kunde;
    private BorderLayout layout;
    private Container c;
    private Font font, font2, errorfont;
    private JButton opphev, seSkaderBil, seSkaderBåt, seSkaderHus, seSkaderHytte;
    private JLabel forsikringsnr, kundenr, regnr, merke, modell, regår, kjørelengde,			//Bil og båt
    								type, modellår, lengde, motortype, motorstyrke, eier,		//Båt og bil
    								adresse, boligtype, byggemateriale, standard, byggeår, 		//Hus og fritid
    								størrelse, byggbeløp, innbobeløp, 							//Hus og fritid
    								totalbeløp, forsikringsinfo, bilde,							//Alle
    				forsikringsnrLabel, kundenrLabel, regnrLabel, merkeLabel, kjørelengdeLabel,
					    								typeLabel, lengdeLabel, motortypeLabel, eierLabel,
					    								adresseLabel, boligtypeLabel, byggematerialeLabel, standardLabel, byggeårLabel,
					    								størrelseLabel, byggbeløpLabel, innbobeløpLabel,
    								totalbeløpLabel, forsikringsinfoLabel, avsluttaLabel, deaktivLabel;

    private String forsNr, kundNr, regiNr, bilMerke, årsModell, registrertÅr, årLengde, båtLengde,
    				båtMotor, motorHK, denSomKjører, boligAdresse, typeBolig, lagetAv, stand, årBygget,
    				kvadratMeter, beløpBygg, beløpInn, beløpTotal, infoForsikring;
    private JTextField kjørelengdeFelt, adresseFelt, egenandelFelt, informasjonsFelt;			//Felter som kan forandres
    private JPanel seSkaderPanel, toppPanel, bildePanel, profilen, info, navnPanel, flyt;

    public ForsikringsProfil(BilForsikring bilfors)
    {
        super("Bilforsikring");
        bil = bilfors;
        kunde = bil.getKunde();
        lytter = new MainListener();
        font = new Font("SansSerif", Font.BOLD, 20);
        font2 = new Font("SansSerif", Font.BOLD, 15);
        errorfont = new Font("SansSerif", Font.BOLD, 30);

        c = getContentPane();
        layout = new BorderLayout();
        c.setLayout(layout);
        c.setBackground(Color.white);

        seSkaderBil = new JButton("Skademeldinger");
        seSkaderBil.addActionListener(lytter);

        toppPanel = new JPanel(new BorderLayout());
        toppPanel.add(seSkaderBil, BorderLayout.LINE_END);
        toppPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        bilde = new JLabel(new ImageIcon( getClass().getResource("bilder/bil.png")));
		bildePanel = new JPanel(new BorderLayout());
		bildePanel.add(bilde, BorderLayout.PAGE_START);
		bildePanel.setPreferredSize(new Dimension(130,130));
		bildePanel.setMaximumSize(new Dimension(130,130));

		forsNr = String.valueOf(bil.getForsikringsNr());
        kundNr = bil.getKundeNr();
        regiNr = bil.getRegistreringsnr();
        bilMerke = bil.getType();
		årsModell = bil.getModell();
		registrertÅr = String.valueOf(bil.getRegistreringsår());
		årLengde = String.valueOf(bil.getKjørelengde());
		denSomKjører = bil.getEiernavn();
		beløpTotal = String.valueOf(bil.bilTypeBeløp() + bil.regÅrBeløp());
		infoForsikring = bil.getForsikringsinfo();

		avsluttaLabel = new JLabel("Forsikringsavtalen er opphevet");
		avsluttaLabel.setFont(errorfont);
		avsluttaLabel.setForeground(Color.red);
		forsikringsnrLabel = new JLabel("Forsikringsnr");
		forsikringsnrLabel.setFont(font2);
		forsikringsnr = new JLabel(forsNr);
		forsikringsnr.setFont(font);
		kundenrLabel = new JLabel("Kundenr");
		kundenrLabel.setFont(font2);
		kundenr = new JLabel(kundNr);
		kundenr.setFont(font);
		eierLabel = new JLabel("Registrert eier");
		eierLabel.setFont(font2);
		eier = new JLabel(denSomKjører);
		eier.setFont(font);
		regnrLabel = new JLabel("Registreringsnr");
		regnrLabel.setFont(font2);
		regnr = new JLabel(regiNr);
		regnr.setFont(font);
		merkeLabel = new JLabel("Modell");
		merkeLabel.setFont(font2);
		String bilen = bilMerke + " " + årsModell + " " + registrertÅr;
		merke = new JLabel(bilen);
		merke.setFont(font);
		kjørelengdeLabel = new JLabel("Årlig kjørelengde");
		kjørelengdeLabel.setFont(font2);
		kjørelengde = new JLabel(årLengde);
		kjørelengde.setFont(font);
		forsikringsinfoLabel = new JLabel("Forsikring");
		forsikringsinfoLabel.setFont(font2);
		forsikringsinfo = new JLabel(infoForsikring);
		forsikringsinfo.setFont(font);
		kjørelengdeLabel = new JLabel("Årlig kjørelengde");
		kjørelengdeLabel.setFont(font2);
		kjørelengde = new JLabel(årLengde);
		kjørelengde.setFont(font);

        info = new JPanel(new GridLayout(15,1));
        info.add(forsikringsnrLabel);
		info.add(forsikringsnr);
		info.add(kundenrLabel);
		info.add(kundenr);
		info.add(eierLabel);
		info.add(eier);
		info.add(regnrLabel);
		info.add(regnr);
		info.add(merkeLabel);
		info.add(merke);
		info.add(kjørelengdeLabel);
		info.add(kjørelengde);
		info.add(forsikringsinfoLabel);
		info.add(forsikringsinfo);
		info.setBorder(new EmptyBorder(new Insets(5, 20, 20, 5)));
		info.setBackground(Color.white);


        profilen = new JPanel(new BorderLayout(10,10));
        profilen.setPreferredSize(new Dimension(200, 550));
        profilen.setMaximumSize(new Dimension(200, 550));
        profilen.add(bildePanel, BorderLayout.PAGE_START);
        profilen.add(info, BorderLayout.CENTER);

        flyt = new JPanel( new FlowLayout(FlowLayout.CENTER));
        if(!kunde.getAktiv())
        {
			flyt.add(deaktivLabel, BorderLayout.PAGE_START);
		}
        flyt.add(profilen);
        setLayout(new BorderLayout());
        c.add(toppPanel, BorderLayout.PAGE_START);
		c.add(flyt, BorderLayout.CENTER);

        setDimensjon();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    private void setDimensjon()
    {
        int høyde = 650, bredde = 400;
        setSize(bredde, høyde);
    }

	public void seSkademeldingerBil()
	{
		Skademelding tem;
		SkademeldingReg skalist = bil.getSkader();
		Iterator<Skademelding> iter = skalist.iterator();
		while(iter.hasNext())
		{
			tem = iter.next();
			//SkademeldingProfil skap = new SkademeldingProfil(tem);
		}
	}


    private class MainListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == seSkaderBil)
            	seSkademeldingerBil();
        }
    }

    private class WinListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            register.exit();
        }
    }
}

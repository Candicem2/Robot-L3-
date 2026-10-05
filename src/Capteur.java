import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;


public class Capteur {

	// ==========================================
    // === PARTIE CAPTEUR DE PRESSION         ===
    // ==========================================
    // Représente la prise physique sur la brique EV3
    Port portPression;

    // Objet représentant notre capteur de contact physique
    EV3TouchSensor capteurPression;

    // Le mode de lecture du capteur (le cptr prend pas de mesures) 
    SampleProvider modePression; 

    // peu importe ce que le cptr mesure, il renvoie les données en un tableau de décimaux 
    float[] echantillonPression;

    // Initialisation dans le constructeur de la classe
    public Capteur() {
        // le port s1 est par défaut , faut vérifier sur le robot lequel est branché 
        portPression = LocalEV3.get().getPort("S2"); 

        // associer le cptr au port choisi
        capteurPression = new EV3TouchSensor(portPression);

        // extraction du mode "Touch" du cptr pour pouvoir lire s'il est pressé ou non
        modePression = capteurPression.getMode("Touch");
        
        // initialisation du tableau avec la taille exacte demandée par le mode
        echantillonPression = new float[modePression.sampleSize()];
    }

    public boolean detecterPression() { 
        // le cptr prend la mesure et la range dans le tab 
        modePression.fetchSample(echantillonPression, 0); 
        
        // Renvoie true si la valeur à l'index 0 vaut 1.0 (activé)
        return echantillonPression[0] == 1.0f; 
    }

  

	// ==========================================
    // === PARTIE CAPTEUR DE distance         ===
    // ==========================================
public float distance() {
		 SampleProvider distance = cs.getDistanceMode();
		    float[] mesure = new float[1];
		    distance.fetchSample(mesure, 0);
		    return mesure[0];
	}



	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Capteur monCapteur = new Capteur();
		while (true) {
			System.out.println(monCapteur.detecterPression());
            
            // Petite pause de 200 millisecondes pour ne pas saturer l'écran
            lejos.utility.Delay.msDelay(200); 
		}
	}



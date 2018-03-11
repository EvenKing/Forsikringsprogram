/*
Kjøreprogrammet
*/
import java.awt.*;

public class ForsikringsProgram
{
	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable(){
			public void run()
			{
				Datasjef data = new Datasjef();		//sjekker om det allerede fins register. hvis ikke skapes et nytt
				Register reg = data.lesRegisterFil();	//leser registeret
				Huvudvindu vindu = new Huvudvindu(reg);		//åpner hovedvinduet

				vindu.setVisible( true );
				//vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			}
		});
	}
}

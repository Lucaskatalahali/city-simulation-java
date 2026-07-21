/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Konsol ekranını temizlemek için kullanılan yardımcı sınıftır.
* İşletim sistemine göre uygun komutu çalıştırarak ekranı temizler.
* </p>
*/

package citysimulation;
import java.io.IOException;
public class Console {
	public static void clear(){
        try{
            if(System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
            	new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch(IOException | InterruptedException ex){
            
        }
    }
}

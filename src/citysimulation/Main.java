/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Kullanıcıdan giriş alan ve simülasyonu başlatan ana sınıftır. Oyun
* sınıfını oluşturur, tur döngüsünü yönetir ve sonuçları ekrana yazdırır.
* Ayrıca kullanıcıdan koordinat alarak ilgili şehri detaylı şekilde gösterir.
* </p>
*/

package citysimulation;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Kullanıcıdan simülasyonun kaç tur süreceği bilgisi alınır
		System.out.print("Tur sayisini girin: ");
		int turSayisi = scanner.nextInt();
		scanner.nextLine(); // Buffer temizleme
		System.out.print("Sehir numaralarini boslukla ayirarak girin (Ornek: 20 66 12): ");
		String userInput = scanner.nextLine();
		
		//Girilen metin parçalara ayrılır ve tam sayı dizisine dönüştürülür
		String[] temp = userInput.split(" ");
		int[] numbers = new int [temp.length];
		for(int i = 0; i <temp.length; i++) {
			numbers[i] = Integer.parseInt(temp[i]);
		}
		// Oyun mantığını yönetecek olan nesne, alınan numaralarla başlatılır
        Oyun oyun = new Oyun(numbers);
        
        ///Belirtilen tur sayısı kadar simülasyon döngüsü çalıştırılır
        for(int i = 0; i < turSayisi; i++) {
        	System.out.println("\n\t==== " + (i+1) + ". Tur ====\n");
        	oyun.turBaslat();
        	oyun.sehirlerGostermek(); //Güncel şehir listesi ekranda gösterilir
        	Console.clear();
        }
        
        //Simülasyon bittikten sonra oluşan son tablo ekrana yazdırılır
        System.out.println("\n\t ==== Oyun Bitti - Son Durum ====\n");
        oyun.sehirlerGostermek();
        //Kullanıcının belirli bir şehrin detaylarını (İlçe, Mahalle, Kişiler) görmesi sağlanır
        System.out.print("\nBir sehir hakkindaki bilgileri gormek icin:");
        System.out.print("\nSatir girin (0'dan baslayarak): ");
        int satir = scanner.nextInt();
        System.out.print("Sutun girin (0'dan baslayarak): ");
        int sutun = scanner.nextInt();
        //Girilen koordinata göre ilgili şehrin hiyerarşik yapısı yazdırılır
        oyun.koordinatlaSehirGoster(satir, sutun);
        
       //Programın hemen kapanmaması için kullanıcıdan bir tuş girişi beklenir.
        System.out.println("\nOyunu kapatmak icin bir tusa yada Enter tusuna basin...");
        try {
            System.in.read();
        } catch (Exception e) {}
        scanner.close();
	}
}

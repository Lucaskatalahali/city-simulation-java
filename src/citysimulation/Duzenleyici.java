/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Bu sınıf, simülasyonda kullanılan verilerin doğrulanması ve düzenlenmesinden
* sorumlu yardımcı sınıftır. Nüfus değerlerinin uygun hale
* getirilmesini sağlar ve nüfus artış oranını hesaplar.
* </p>
*/

package citysimulation;

//Kullanıcı nüfus (şehir) rakamlarını girdiğinde, bu fonksiyonda rakamı doğrulayıp düzelteceğim.
public class Duzenleyici {
	public static int sehirSayisiKontrol(int sayi) {
		// Onlar basamağını alarak ilçe kısmını belirler
		int ilceKismi = sayi/10; 
		//Birler basamağını alarak mahalle kısmını belirler.
		int mahalleKismi = sayi%10;
		//Mahalle kısmı sıfır olamaz, bu durumda 1 artar
		if(mahalleKismi == 0) mahalleKismi++; 
		while(mahalleKismi%ilceKismi != 0) { // Mahalle kısmının ilçe kısmına tam bölünene kadar değerini ayarlar
			mahalleKismi++;
			if(mahalleKismi > 9)
				mahalleKismi = 1;
		}
		// Düzenlenmiş ilçe ve mahalle kısımlarını birleştirerek yeni sayıyı döner
		sayi = ilceKismi*10 + mahalleKismi;
		return sayi;
	}
	//Şehir nufusu mahalleye tam bölünebilirliğini kontrol eder.
	public static int mahalleNufusKontrol(int sayi) {
		int mahalleKismi = sayi%10;
		if(mahalleKismi == 0) mahalleKismi++;
		// Sayıyı, mahalle kısmına tam bölünene kadar yukarı yuvarlar.
		while(sayi%mahalleKismi != 0) {
			sayi++;
		}
		return sayi;
	}
	//Sayının (şehir nufusu) son iki basamağını kullanarak nüfus artış oranını hesaplar.
	public static int nufusArtisOraniBul(int sayi) {
		// Son iki basamağın birler ve onlar değerlerini ayırır.
		int birler = (sayi%100)%10;
		int onlar = (sayi%100)/10;
		// Artış oranı bu iki basamağın toplamıdır.
		int artisOrani = birler + onlar;
		return artisOrani;
	}
}

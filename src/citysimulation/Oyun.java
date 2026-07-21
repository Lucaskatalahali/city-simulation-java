/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Bu sınıf, oyunun ana kontrol sınıfıdır. Şehirlerin oluşturulması,
* simülasyonun yürütülmesi, nüfus artışı ve şehirlerin
* bölünmesi gibi tüm temel işlemleri yönetir.
* </p>
*/

package citysimulation;

import java.util.List;
import java.util.Locale;
import com.github.javafaker.Faker;
import java.util.ArrayList;

public class Oyun {
	private List<Sehir> sehirler = new ArrayList<>(); // Simülasyondaki aktif şehirlerin listesi.
	private Faker faker;
	
	
	//Oyun kurucu metod: Şehirleri, ilçeleri ve mahalleleri başlangıç nüfuslarına göre hiyerarşik olarak oluşturur.
	public Oyun(int[] sehirSayisi) {
		this.faker = new Faker(Locale.forLanguageTag("tr"));
		for(int count : sehirSayisi) {
			Sehir sehir = new Sehir(faker.address().city());
			//Nüfus rakamını kurallara göre düzeltir ve hiyerarşiyi (ilçe/mahalle) hesaplar
			count = Duzenleyici.sehirSayisiKontrol(count);
			int ilceSayisi = count/10;
			
			//Her ilçenin sahip olacağı mahalle sayısı
			int mahalleSayisi = (count%10)/(count/10);
			//Nüfusu eşit olarak bölmek için kullanılacak mahalle payını tutar
			int mahalleKismi = count%10;
			count = Duzenleyici.mahalleNufusKontrol(count);
			//Her mahalledeki nüfus miktarı
			int mahalleNufus = count/mahalleKismi;
			
			// Hiyerarşik oluşturma döngüleri.
			for(int i = 0; i < ilceSayisi; i++) {
				Ilce ilce = new Ilce(faker.address().cityName());
				
				for(int j = 0; j < mahalleSayisi; j++) {
					Mahalle mahalle = new Mahalle(faker.address().streetName());
					for(int k = 0; k < mahalleNufus; k++) {
						// KisiOlustur() metodunu çağır ve kişiyi mahalleye ekle
						mahalle.addKisiler(KisiOlustur());
					}
					// Mahalle nüfusu eklendikten sonra, nüfus bilgisini güncelle ve mahalle ilçeye ekle
					mahalle.setNufus();
					ilce.addMahalle(mahalle);
				}
				//Ilçeye mahalleleri eklendikten sonra, ilçe nüfusunu güncelle ve ilçe şehre ekle
				ilce.setNufus();
				sehir.addIlce(ilce);
			}
			//Şehre ilçeleri eklendikten sonra, şehir nüfusunu güncelle ve şehir şehir listesine ekle
			sehir.setNufus();
			sehirler.add(sehir);
		}
	}
	
	public List<Sehir> getSehirler(){
		return sehirler;
	}
	
	//Her turda nüfusu yaşlandırır ve artış oranına göre yeni kişiler ekler.
	public void turBaslat() {
		for(Sehir buSehir : sehirler) { 
			int artisOrani = Duzenleyici.nufusArtisOraniBul(buSehir.getNufus());
			for(Ilce buIlce : buSehir.getIlceListesi()) {
				for(Mahalle buMahalle : buIlce.getMahalleListesi()) {
					// Yeni kişiler eklenmeden önce, mevcut kişiler 1 yaş yaşlandırılmalıdır
					for(Kisi kisi : buMahalle.getKisiler()) {
						kisi.yaslan();
					}
					// Nüfus artış formülü uygulaması
					int mevcut = buMahalle.getNufus();
					//Eğer artış oranı sıfır ise, her mahalleye sadece bir kişi eklenir. Sıfır değilse, formül uygulanır
					int eklenecek = (artisOrani == 0) ? 1 :  mevcut * (artisOrani - 1);
					for(int k = 0; k < eklenecek; k++) {
						buMahalle.addKisiler(KisiOlustur());
					}
					//Yeni kişiler eklendikten sonra nüfus bilgisini güncelliyoruz.
					buMahalle.setNufus();
				}
				buIlce.setNufus();
			}
			buSehir.setNufus();
		}	
		//Bir sonraki tura geçmeden önce, 4 haneli nüfusa ulaşan ve ikiye bölünmesi gereken şehirleri kontrol etmek için 
		//sehirNufusuKontrol metodunu çağırıyoruz.
		sehirNufusuKontrol();
	}
	
	private Kisi KisiOlustur() {
		Kisi newKisi = new Kisi(faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(0, 51));
		return newKisi;
	}
	
	//Eşik değeri (1000) aşan şehirleri tespit eder ve bölme işlemini tetikler
	public void sehirNufusuKontrol() {
		// Öncelikle, bölünmesi gereken şehirlerin bir listesini oluşturuyoruz.
	    List<Sehir> bolunecekler = new ArrayList<>();

	    for(Sehir sehir : sehirler) {
	        if(sehir.getNufus() >= 1000) {
	            bolunecekler.add(sehir);
	        }
	    }
	    // Bölünecek şehirlerin listesini aldıktan sonra, her şehri bölmek ve yeni oluşan şehri orijinal şehir 
	    //listesine eklemek için listenin üzerinden geçiyoruz
	    for(Sehir sehir : bolunecekler) {
	        yeniSehirOlustur(sehir);
	    }
	}
	
	public void yeniSehirOlustur(Sehir sehir) {
		// 'count' değişkeni, yeni şehrin sahip olacağı ilçe sayısını temsil eder. 
		//Bölme işlemi tam sayı değilse, yeni şehir otomatik olarak daha az parçaya (ilçeye) sahip olacaktır.
		int count = sehir.getIlceListesi().size()/2;
		
		// Eğer 'count' sıfır ise, şehir sadece bir ilçeye sahiptir; 
		//bu durumda bu tür şehir bölünmesinden sorumlu olan özel metodu çağırmamız gerekir.
		if(count == 0) ozelYeniSehirOlustur(sehir);
		else {
			List<Ilce> tasinanlar = new ArrayList<>();

			for(int i = 0; i < count; i++) {
			    tasinanlar.add(sehir.getIlceListesi().remove(0));
			}
			sehir.setNufus(); // Şimdi, yeni nüfus sayısını eski şehre atamamız gerekiyor.
			
			
			Sehir newSehir = new Sehir(faker.address().city()); 
			newSehir.addIlceListesi(tasinanlar); // newSehir, eski şehirden çıkarılan listeyi devralır
			newSehir.setNufus(); // Şimdi, yeni nüfus sayısını yeni şehre atamamız gerekiyor
			sehirler.add(newSehir); //Son olarak, yeni şehri orijinal şehir listemizin sonuna ekliyoruz
		}
	}
	
	//Tek bir ilçesi olan şehrin bölünmesinden sorumlu fonksiyon
	//İlçeleri değil, mahalleleri bölüyoruz
	public void ozelYeniSehirOlustur(Sehir sehir) {
		//'count' değişkeni, (yeni şehre eklenecek olan) yeni ilçenin sahip olacağı mahalle sayısını temsil eder.
		int count = sehir.getIlceListesi().getFirst().getMahalleListesi().size()/2;
		// Eğer 'count' sıfır ise, ilçe sadece bir mahalle sahiptir; 
		//bu durumda bu tür şehir bölünmesinden sorumlu olan özel metodu çağırmamız gerekir.
		if(count == 0) sonOzelYeniSehirOlustur(sehir);
		else{
			List<Mahalle> tasinanlar = new ArrayList<Mahalle>();
			for(int i = 0; i < count; i++) {
				tasinanlar.add(sehir.getIlceListesi().getFirst().getMahalleListesi().remove(0));
			}
			sehir.getIlceListesi().getFirst().setNufus();
			sehir.setNufus();
			
			Ilce yeniIlce = new Ilce(faker.address().cityName());
			yeniIlce.addMahalleListesi(tasinanlar);
			yeniIlce.setNufus();
			
			Sehir yeniSehir = new Sehir(faker.address().city());
			yeniSehir.addIlce(yeniIlce);
			yeniSehir.setNufus();
			sehirler.add(yeniSehir);
		}	
	}
	
	// Bu fonksiyon, nüfusu doğrudan ikiye böler ve bir kısmını yeni şehre aktarır
	public void sonOzelYeniSehirOlustur(Sehir sehir){
		// 'count' değişkeni, yeni şehrin sahip olacağı kişi sayısını temsil eder
		int count = sehir.getIlceListesi().getFirst().getMahalleListesi().getFirst().getKisiler().size()/2;
		List<Kisi> tasinanlar = new ArrayList<>(); // Eski şehirden çıkarılacak ve yeni şehre eklenecek kişilerin listesi
		
		//Bu döngüde, kişileri eski listeden çıkarıyoruz. 'remove' fonksiyonu çıkarılan nesneyi döndürür. 
		//Bu nesneyi (kişiyi), yeni şehre yüklenecek olan yeni listeye doğrudan ekliyoruz
		for(int i = 0; i < count; i++) {
		    tasinanlar.add(sehir.getIlceListesi().getFirst().getMahalleListesi().getFirst().getKisiler().remove(0));
		}
		// Şimdi mahalle, ilçe ve eski şehrin nüfusunu güncellememiz gerekiyor; çünkü bazı kişiler bu listeden çıkarıldı
		sehir.getIlceListesi().getFirst().getMahalleListesi().getFirst().setNufus();
		sehir.getIlceListesi().getFirst().setNufus();
		sehir.setNufus();
		
		// Yeni mahalleyi oluşturuyoruz ve eski şehirden çıkarılan kişileri bu yeni mahalleye ekliyoruz.
		Mahalle yeniMahalle = new Mahalle(faker.address().streetName());
		yeniMahalle.addKisiListesi(tasinanlar);
		yeniMahalle.setNufus();
		// Yeni bir ilçe oluşturuyoruz, içine yeni mahalleyi ekliyoruz ve nüfusu güncelliyoruz
		Ilce yeniIlce = new Ilce(faker.address().cityName());
		yeniIlce.getMahalleListesi().add(yeniMahalle);
		yeniIlce.setNufus();
		// Yeni bir şehir oluşturuyoruz, içine yeni ilçe ekliyoruz ve nüfusu güncelliyoruz
		Sehir yeniSehir = new Sehir(faker.address().city());
		yeniSehir.getIlceListesi().add(yeniIlce);
		yeniSehir.setNufus();
		
		sehirler.add(yeniSehir); //Son olarak, yeni şehri şehir listemizin sonuna ekliyoruz
	}
	
	// Şehirleri görüntüleme metodu. Her satırda 5 şehir gösterilir.
	public void sehirlerGostermek() {
	    int blok = 0;

	    for (int i = 0; i < sehirler.size(); i++) {
	        System.out.print("[" + sehirler.get(i).getNufus() + "]");
	        blok++;

	        boolean listedekiSon = (i == sehirler.size() - 1);
	        boolean satirBiti = (blok == 5);

	        // Tireyi sadece son eleman değilse ve 5. satırın sonu değilse kullan.
	        if (!listedekiSon && !satirBiti) {
	            System.out.print("-");
	        }

	        if (satirBiti) {
	            System.out.println(); //Break Line
	            blok = 0;         
	        }
	    }
	    //Eğer liste 5'in katı ile bitmiyorsa, sonda bir satır atla
	    if (blok != 0) {
	        System.out.println();
	    }
	}
	
	// Burada indeksin sınırlar dışında olup olmadığını kontrol ediyorum; 
	//eğer değilse, Sehir sınıfındaki detayliYazdir metodunu çağırarak şehir detaylarını yazdırıyorum.
	public void koordinatlaSehirGoster(int satir, int sutun) {
	    int indexOfSehir = (satir * 5) + sutun;
	    if (indexOfSehir >= 0 && indexOfSehir < sehirler.size()) {
	        sehirler.get(indexOfSehir).detayliYazdir();
	    } else {
	        System.out.println("Hata: Gecersiz koordinat!");
	    }
	}
}

